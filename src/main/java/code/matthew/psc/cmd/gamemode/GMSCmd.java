package code.matthew.psc.cmd.gamemode;

import code.matthew.psc.api.ICommand;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GMSCmd extends ICommand {

    public GMSCmd() {
        super("gms", "psc.gm.gms", "Set gamemode to survival", true);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        Player p = (Player) sender;

        p.setGameMode(GameMode.SURVIVAL);

        return true;
    }
}
