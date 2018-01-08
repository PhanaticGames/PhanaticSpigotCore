package code.matthew.psc.cmd.ban;

import code.matthew.psc.PSC;
import code.matthew.psc.api.ICommand;
import code.matthew.psc.utils.data.ConfigCache;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;

public class BanCmd extends ICommand {

    public BanCmd() {
        super("ban", "psc.ban", "Ban a player", false);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ConfigCache.getMsg("banUsage"));
            return false;
        }

        // TODO, in the far far future, we need to use mojang api to bet uuid based on name
        Player p = Bukkit.getPlayer(args[0]);

        if (p == null) {
            sender.sendMessage(ConfigCache.getMsg("playerNotOn"));
            return false;
        }

        String staff = sender.getName();
        String end = new Date(999999999999999999L).toString();
        String reason = ConfigCache.getMsg("defaultBanWhy");

        if (args.length > 1) {
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < args.length; i++) {
                if (i != 0) {
                    builder.append(args[i]).append(" ");
                }
            }
            reason = builder.toString();
        }

        PSC.getInstance().getBm().banPlayer(p, staff, reason, end);

        sender.sendMessage(ConfigCache.getMsg("bannedPlayer").replace("%PLAYER%", p.getName()));

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("psc.staff")) {
                player.sendMessage(ConfigCache.getMsg("staffBan").replace("%PLAYER%", p.getName()).replace("%STAFF%", staff).replace("%REASON%", reason));
            } else {
                player.sendMessage(ConfigCache.getMsg("pubBan").replace("%PLAYER%", p.getName()).replace("%STAFF%", staff).replace("%REASON%", reason));
            }
        }

        p.kickPlayer(PSC.getInstance().getBm().getDenyReason(PSC.getInstance().getBm().getBan(p.getUniqueId().toString())));
        return true;
    }
}
