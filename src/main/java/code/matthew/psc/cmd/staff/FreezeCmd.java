package code.matthew.psc.cmd.staff;

import code.matthew.psc.api.command.ICommand;
import code.matthew.psc.api.player.PlayerUtils;
import code.matthew.psc.utils.data.ConfigCache;
import code.matthew.psc.utils.data.DataCore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FreezeCmd extends ICommand {

    public FreezeCmd() {
        super("freeze", "psc.freeze", "Feeeze or unfreeze a player", false);

    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ConfigCache.getMsg("freezeUsage"));
            return false;
        }

        Player p = PlayerUtils.getOnlinePlayer(args[0]);

        if (p == null) {
            sender.sendMessage(ConfigCache.getMsg("playerNotOn"));
            return false;
        }


        if (DataCore.isFrozen(p)) {
            sender.sendMessage(ConfigCache.getMsg("unFroze").replace("%PLAYER%", p.getName()));
            DataCore.unfrezzePlayer(p);
        } else {
            sender.sendMessage(ConfigCache.getMsg("froze").replace("%PLAYER%", p.getName()));
            DataCore.freezePlayer(p);
        }

        return true;
    }
}
