package code.matthew.psc.cmd.gamemode;

import code.matthew.psc.api.command.ICommand;
import code.matthew.psc.utils.data.ConfigCache;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GM3Cmd extends ICommand {

    public GM3Cmd() {
        super("gm3", "psc.gm.gm3", "Set gamemode to spectator", true);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                sender.sendMessage(ConfigCache.getMsg("playerNotOn"));
                return false;
            }

            if (sender.hasPermission("psc.gm.gm3.other")) {
                sender.sendMessage(ConfigCache.getMsg("setPlayersGmSpectator"));
                target.setGameMode(GameMode.SPECTATOR);
                return true;
            } else {
                sender.sendMessage("noPerm");
                return false;
            }
        } else {
            Player p = (Player) sender;
            p.setGameMode(GameMode.SPECTATOR);
            return true;
        }
    }
}
