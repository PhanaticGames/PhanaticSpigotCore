package code.matthew.psc.cmd.gamemode;

import code.matthew.psc.api.ICommand;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GMCCmd extends ICommand {

    public GMCCmd() {
        super("gmc", "psc.gm.gmc", "Set gamemode to creative", true);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        Player p = (Player) sender;

        p.setGameMode(GameMode.CREATIVE);

        return true;
    }
}
