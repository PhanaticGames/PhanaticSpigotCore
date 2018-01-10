package code.matthew.psc.cmd.misc;

import code.matthew.psc.api.ICommand;
import code.matthew.psc.api.PlayerUtils;
import code.matthew.psc.utils.data.ConfigCache;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly extends ICommand {

    public Fly() {
        super("fly", "psc.fly", "Fly!!!!!", false);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        if (args.length == 0) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(ConfigCache.getMsg("mustBePlayer"));
                return false;
            }

            Player p = (Player) sender;
            p.setAllowFlight(true);
            p.setFlying(true);
            p.sendMessage(ConfigCache.getMsg("setFlyOn"));

            return true;
        } else {

            Player p = PlayerUtils.getOnlinePlayer(args[0]);

            if (p == null) {
                sender.sendMessage(ConfigCache.getMsg("playerNotOn"));
                return false;
            }

            if (!sender.hasPermission("psc.fly.other")) {
                sender.sendMessage(ConfigCache.getMsg("noPerm"));
                return false;
            }

            p.setAllowFlight(true);
            p.setFlying(true);
            p.sendMessage(ConfigCache.getMsg("setFlyOn"));
            sender.sendMessage(ConfigCache.getMsg("setOtherFlyOn").replace("%PLAYER%", p.getName()));

            return true;
        }
    }
}
