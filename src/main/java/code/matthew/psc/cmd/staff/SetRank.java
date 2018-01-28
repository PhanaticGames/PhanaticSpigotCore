package code.matthew.psc.cmd.staff;

import code.matthew.psc.PSC;
import code.matthew.psc.api.command.ICommand;
import code.matthew.psc.api.player.PlayerUtils;
import code.matthew.psc.utils.data.ConfigCache;
import code.matthew.psc.utils.strings.ColorUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetRank extends ICommand {

    public SetRank() {
        super("setrank", "psc.setrank", "Set a player", false);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        if (args.length <= 1) {
            sender.sendMessage(ColorUtil.colorStr(ConfigCache.getMsg("setrankUsage")));
            return false;
        }

        Player target = PlayerUtils.getOnlinePlayer(args[0]);

        if (target == null) {
            sender.sendMessage(ColorUtil.colorStr(ConfigCache.getMsg("playerNotOn")));
            return false;
        }

        boolean rankAdded = PSC.getPerms().playerAddGroup(target, args[1]);

        if (rankAdded) {
            sender.sendMessage(ColorUtil.colorStr(ConfigCache.getMsg("rankSet")));
            return true;
        } else {
            sender.sendMessage(ColorUtil.colorStr(ConfigCache.getMsg("errRankSet")));
            return false;
        }


    }
}
