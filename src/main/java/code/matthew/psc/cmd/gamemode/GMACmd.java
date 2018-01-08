package code.matthew.psc.cmd.gamemode;

import code.matthew.psc.api.ICommand;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GMACmd extends ICommand {

    public GMACmd() {
        super("gma", "psc.gm.gma", "Set gamemode to adventure", true);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        Player p = (Player) sender;

        p.setGameMode(GameMode.ADVENTURE);

        return true;
    }
}
