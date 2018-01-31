package code.matthew.psc.cmd.misc;

import code.matthew.psc.api.command.ICommand;
import code.matthew.psc.api.player.PlayerUtils;
import code.matthew.psc.utils.data.ConfigCache;
import code.matthew.psc.utils.data.DataCore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCmd extends ICommand {

    public FlyCmd() {
        super("fly", "psc.fly", "FlyCmd!!!!!", false);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        if (args.length == 0) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(ConfigCache.getMsg("mustBePlayer"));
                return false;
            }

            Player p = (Player) sender;

            if (DataCore.canFly(p)) {
                p.setAllowFlight(false);
                p.setFlying(false);
                p.sendMessage(ConfigCache.getMsg("setFlyOn"));
                DataCore.takePlayerFly(p);
            } else {
                p.setAllowFlight(true);
                p.setFlying(true);
                p.sendMessage(ConfigCache.getMsg("setFlyOn"));
                DataCore.addPlayerFly(p);
            }

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

            if (DataCore.canFly(p)) {
                p.setAllowFlight(false);
                p.setFlying(false);
                DataCore.takePlayerFly(p);
            } else {
                p.setAllowFlight(true);
                p.setFlying(true);
                DataCore.addPlayerFly(p);
            }

            p.sendMessage(ConfigCache.getMsg("setFlyOn"));
            sender.sendMessage(ConfigCache.getMsg("setOtherFlyOn").replace("%PLAYER%", p.getName()));

            return true;
        }
    }
}
