package code.matthew.psc.cmd.misc;

import code.matthew.psc.api.command.ICommand;
import code.matthew.psc.api.player.PlayerUtils;
import code.matthew.psc.utils.data.ConfigCache;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCmd extends ICommand {

    public FeedCmd() {
        super("feed", "psc.feed", "FeedCmd you or another player", false);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        if (args.length == 0) {

            Player p = (Player) sender;

            /*
             * I know, the check for player seems redundent but I dont have a choice when it comes to feeding them
             */
            if (p == null) {
                sender.sendMessage(ConfigCache.getMsg("mustBePlayer"));
                return false;
            }

            p.setFoodLevel(20);
            p.sendMessage(ConfigCache.getMsg("fedSelf"));

        } else {

            Player p = PlayerUtils.getOnlinePlayer(args[0]);

            if (p == null) {
                sender.sendMessage(ConfigCache.getMsg("playerNotOn"));
                return false;
            }

            p.setFoodLevel(20);
            p.sendMessage(ConfigCache.getMsg("fedSelf"));
            sender.sendMessage(ConfigCache.getMsg("fedOther"));
        }
        return true;
    }
}
