package code.matthew.psc.cmd;

import code.matthew.psc.PSC;
import code.matthew.psc.api.Ban;
import code.matthew.psc.api.ICommand;
import code.matthew.psc.utils.data.ConfigCache;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnbanCmd extends ICommand {

    public UnbanCmd() {
        super("unban", "psc.unban", "Unban a player", false);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ConfigCache.getMsg("unbanUsage"));
            return false;
        }

        // TODO Sometime, in the far far future, we will use mojang api for this
        OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);

        Ban ban = PSC.getInstance().getBm().getBan(p.getUniqueId().toString());

        if (ban == null) {
            sender.sendMessage(ConfigCache.getMsg("pNotBanned"));
            return false;
        }

        if (!ban.isBanned()) {
            sender.sendMessage(ConfigCache.getMsg("pNotBanned"));
            return false;
        }

        PSC.getInstance().getBm().unban(ban);

        sender.sendMessage(ConfigCache.getMsg("unbannedPlayer").replace("%PLAYER%", p.getName()));

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("psc.staff")) {
                player.sendMessage(ConfigCache.getMsg("staffUnban").replace("%PLAYER%", p.getName()).replace("%STAFF%", sender.getName()));
            } else {
                player.sendMessage(ConfigCache.getMsg("pubUnBan").replace("%PLAYER%", p.getName()).replace("%STAFF%", sender.getName()));
            }
        }

        return true;
    }
}
