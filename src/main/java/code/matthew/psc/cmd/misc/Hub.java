package code.matthew.psc.cmd.misc;

import code.matthew.psc.PSC;
import code.matthew.psc.api.command.ICommand;
import code.matthew.psc.utils.data.ConfigCache;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Hub extends ICommand {

    public Hub() {
        super("hub", "psc.hub", "Teleport to hub!", true);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(ConfigCache.getConfigString("hub"));
        p.sendPluginMessage(PSC.getInstance(), "BungeeCord", out.toByteArray());
        return true;
    }
}
