package code.matthew.psc.cmd.mute;

import code.matthew.psc.PSC;
import code.matthew.psc.api.command.ICommand;
import code.matthew.psc.api.mute.Mute;
import code.matthew.psc.utils.data.ConfigCache;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnmuteCmd extends ICommand {

    public UnmuteCmd() {
        super("unmute", "psc.ummute", "Unmute a player", false);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ConfigCache.getMsg("unmuteUsage"));
            return false;
        }

        // TODO Sometime, in the far far future, we will use mojang api for this
        OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);

        Mute mute = PSC.getInstance().getMm().getMute(p.getUniqueId().toString());

        if (mute == null) {
            sender.sendMessage(ConfigCache.getMsg("pNotMuted"));
            return false;
        }

        if (!mute.isMuted()) {
            sender.sendMessage(ConfigCache.getMsg("pNotMuted"));
            return false;
        }

        PSC.getInstance().getMm().unMute(mute);

        sender.sendMessage(ConfigCache.getMsg("unmutedPlayer").replace("%PLAYER%", p.getName()));

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("psc.staff")) {
                player.sendMessage(ConfigCache.getMsg("staffUnmute").replace("%PLAYER%", p.getName()).replace("%STAFF%", sender.getName()));
            } else {
                player.sendMessage(ConfigCache.getMsg("pubUnmute").replace("%PLAYER%", p.getName()).replace("%STAFF%", sender.getName()));
            }
        }

        return true;
    }
}
