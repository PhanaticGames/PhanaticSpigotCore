package code.matthew.psc.cmd.staff;

import code.matthew.psc.api.command.ICommand;
import code.matthew.psc.api.player.PlayerUtils;
import code.matthew.psc.utils.data.ConfigCache;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kick extends ICommand {

    public Kick() {
        super("kick", "psc.kick", "Kick a player", false);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ConfigCache.getMsg("kickUsage"));
            return false;
        }

        Player target = PlayerUtils.getOnlinePlayer(args[0]);

        if (target != null) {
            sender.sendMessage(ConfigCache.getMsg("playerNotOn"));
            return false;
        }

        String why;

        if (args.length >= 2) {
            StringBuilder builder = new StringBuilder();

            for (int i = 1; i <= args.length; i++) {
                builder.append(args[i]).append(" ");
            }

            why = builder.toString();
        } else {
            why = ConfigCache.getMsg("defaultKickMsg");
        }

        target.kickPlayer(why);

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("psc.staff")) {
                p.sendMessage(ConfigCache.getMsg("staffKick")
                        .replace("%STAFF%", sender.getName())
                        .replace("%PLAYER%", target.getName())
                        .replace("%REASON%", why));
            } else {
                p.sendMessage(ConfigCache.getMsg("pubKick")
                        .replace("%STAFF%", sender.getName())
                        .replace("%PLAYER%", target.getName())
                        .replace("%REASON%", why));
            }
        }
        return true;
    }
}
