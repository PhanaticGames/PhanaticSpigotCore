package code.matthew.psc.cmd.misc;

import code.matthew.psc.api.command.ICommand;
import code.matthew.psc.api.player.PlayerUtils;
import code.matthew.psc.utils.data.ConfigCache;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ExtinguishCmd extends ICommand {

    public ExtinguishCmd() {
        super("ext", "psc.ext", "ExtinguishCmd you or another player", false);
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

            p.setFireTicks(0);
            p.sendMessage(ConfigCache.getMsg("extSelf"));

        } else {

            Player p = PlayerUtils.getOnlinePlayer(args[0]);

            if (p == null) {
                sender.sendMessage(ConfigCache.getMsg("playerNotOn"));
                return false;
            }

            p.setFireTicks(0);
            p.sendMessage(ConfigCache.getMsg("extSelf"));
            sender.sendMessage(ConfigCache.getMsg("extOther"));
        }
        return true;
    }
}
