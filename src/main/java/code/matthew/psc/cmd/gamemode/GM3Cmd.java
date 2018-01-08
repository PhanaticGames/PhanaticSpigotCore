package code.matthew.psc.cmd.gamemode;

import code.matthew.psc.api.ICommand;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GM3Cmd extends ICommand {

    public GM3Cmd() {
        super("gm3", "psc.gm.gm3", "Set gamemode to spectator", true);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        Player p = (Player) sender;

        p.setGameMode(GameMode.SPECTATOR);

        return true;
    }
}
