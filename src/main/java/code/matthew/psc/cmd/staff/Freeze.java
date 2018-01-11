package code.matthew.psc.cmd.staff;

import code.matthew.psc.api.ICommand;
import code.matthew.psc.api.PlayerUtils;
import code.matthew.psc.utils.data.ConfigCache;
import code.matthew.psc.utils.data.DataCore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Freeze extends ICommand {

    public Freeze() {
        super("feeze", "psc.freeze", "Feeeze or unfreeze a player", false);

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
            sender.sendMessage(ConfigCache.getMsg("unFroze"));
            DataCore.unfrezzePlayer(p);
        } else {
            sender.sendMessage(ConfigCache.getMsg("froze"));
            DataCore.freezePlayer(p);
        }

        return true;
    }
}