package code.matthew.psc.utils.core;

import code.matthew.psc.PSC;
import code.matthew.psc.api.ban.Ban;
import code.matthew.psc.api.ban.BanFactory;
import code.matthew.psc.runnable.AsyncBans;
import code.matthew.psc.utils.data.ConfigCache;
import code.matthew.psc.utils.logs.Logger;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BanManager {

    private final List<Ban> bansToSync = new ArrayList<>();

    private final PSC psc;

    public BanManager(PSC psc) {
        this.psc = psc;
        setupSync();
    }

    private void setupSync() {
        BukkitScheduler scheduler = psc.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(psc, () -> {
            AsyncBans bans = new AsyncBans();
            bans.run();
        }, 0L, ConfigCache.getConfigInt("dataSyncTime") * 20);
    }

    public void banPlayer(Player p, String staff, String reason, String end) {
        addBanToSync(BanFactory.genBan(p, staff, reason, end, new Date().toString()));
    }

    public Ban getBan(String uuid) {

        for (Ban ban : bansToSync) {
            if (ban.getUuid().equals(uuid)) {
                return ban;
            }
        }

        PreparedStatement stmt = psc.getDb().preparePreparedStmt("SELECT * FROM bans WHERE active='true' and uuid=?", uuid);
        ResultSet rs = psc.getDb().runQuery(stmt);

        Ban ban = null;

        try {
            if (!rs.next()) {
                Logger.log(Logger.LogType.DEBUG, "Return was empty");
            } else {
                do {
                    ban = BanFactory.genBanRaw(rs.getString("name"), rs.getString("uuid"), rs.getString("staff"), rs.getString("reason"), rs.getString("end"), rs.getString("start"), rs.getString("active"));
                }
                while (rs.next());
            }
        } catch (SQLException ex) {
            Logger.log(Logger.LogType.ERROR, "ERROR READING RESULTS");
            if (Logger.isDebug()) {
                ex.printStackTrace();
            }
        }
        return ban;
    }

    public boolean checkBan(Ban ban) {
        return ban != null && ban.isBanned();
        //if (ban != null) {
        //     return ban.isBanned();
        // }
        //  return false;
    }

    public void unban(Ban ban) {
        ban.setActive("false");
        if (bansToSync.contains(ban)) {
            bansToSync.remove(ban);
        }
        bansToSync.add(ban);
    }

    private void addBanToSync(Ban ban) {
        bansToSync.add(ban);
        System.out.println(ban.getEnd());
        System.out.println(ban.getStart());
    }

    public List<Ban> getBanToSyncList() {
        return bansToSync;
    }

    public String getDenyReason(Ban ban) {

        StringBuilder builder = new StringBuilder();

        for (String s : ConfigCache.getBanFormat()) {
            s = s.replace("%REASON%", ban.getReason());
            s = s.replace("%STAFF%", ban.getStaff());
            s = s.replace("%END%", ban.getEnd());
            builder.append(s).append("\n");
        }
        return builder.toString();
    }
}
