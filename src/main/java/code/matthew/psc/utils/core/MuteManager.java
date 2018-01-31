package code.matthew.psc.utils.core;

import code.matthew.psc.PSC;
import code.matthew.psc.api.mute.Mute;
import code.matthew.psc.api.mute.MuteFactory;
import code.matthew.psc.runnable.AsyncMutes;
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

public class MuteManager {

    private final List<Mute> mutesToSync = new ArrayList<>();

    private final PSC psc;

    public MuteManager(PSC psc) {
        this.psc = psc;
        setupSync();
    }

    private void setupSync() {
        BukkitScheduler scheduler = psc.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(psc, () -> {
            AsyncMutes mutes = new AsyncMutes();
            mutes.run();
        }, 0L, ConfigCache.getConfigInt("dataSyncTime") * 20);
    }

    public void sync() {
        AsyncMutes mutes = new AsyncMutes();
        mutes.run();
    }

    public void mutePlayer(Player p, String staff, String reason, String end) {
        Mute mute = MuteFactory.genMute(p, staff, reason, end, new Date().toString());
        if (mutesToSync.contains(mute)) {
            mutesToSync.remove(mute);
        }
        addMuteToSync(mute);
    }

    public Mute getMute(String uuid) {
        for (Mute mute : mutesToSync) {
            if (mute.getUuid().equals(uuid)) {
                return mute;
            }
        }

        PreparedStatement stmt = psc.getDb().preparePreparedStmt("SELECT * FROM mutes WHERE active='true' and uuid=?", uuid);
        ResultSet rs = psc.getDb().runQuery(stmt);

        Mute mute = null;

        try {
            if (!rs.next()) {

            } else {
                do {
                    mute = MuteFactory.genMuteRaw(rs.getString("name"), rs.getString("uuid"), rs.getString("staff"), rs.getString("reason"), rs.getString("end"), rs.getString("start"), rs.getString("active"));
                }
                while (rs.next());
            }
        } catch (SQLException ex) {
            Logger.log(Logger.LogType.ERROR, "ERROR READING RESULTS");
            if (Logger.isDebug()) {
                ex.printStackTrace();
            }
        }
        return mute;
    }

    public boolean checkMute(Mute mute) {
        if (mute != null) {
            return mute.isMuted();
        }
        return false;
    }

    public void unMute(Mute ban) {
        ban.setActive("false");
        if (mutesToSync.contains(ban)) {
            mutesToSync.remove(ban);
        }
        mutesToSync.add(ban);
    }

    private void addMuteToSync(Mute ban) {
        mutesToSync.add(ban);
    }

    public List<Mute> getMutesToSyncList() {
        return mutesToSync;
    }

}
