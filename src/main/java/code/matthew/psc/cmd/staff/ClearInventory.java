package code.matthew.psc.cmd.staff;

import code.matthew.psc.api.ICommand;
import code.matthew.psc.api.PlayerUtils;
import code.matthew.psc.utils.data.ConfigCache;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearInventory extends ICommand {

    public ClearInventory() {
        super("ci", "psc.ci", "Clear a players inventory", false);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        if (args.length == 0) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(ConfigCache.getMsg("mustBePlayer"));
                return false;
            }

            Player p = (Player) sender;

            p.getInventory().clear();
            sender.sendMessage(ConfigCache.getMsg("ciSelf"));
            return true;
        } else {
            Player p = PlayerUtils.getOnlinePlayer(args[0]);

            if (p == null) {
                sender.sendMessage(ConfigCache.getMsg("playerNotOn"));
                return false;
            }

            p.getInventory().clear();
            sender.sendMessage(ConfigCache.getMsg("ciOther"));
            p.sendMessage(ConfigCache.getMsg("ciSelf"));
            return true;
        }
    }
}
