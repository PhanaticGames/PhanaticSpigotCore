package code.matthew.psc.cmd.staff;

import code.matthew.psc.api.command.ICommand;
import code.matthew.psc.utils.data.ConfigCache;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class ClearChatCmd extends ICommand {

    public ClearChatCmd() {
        super("clearchat", "psc.clearchat", "Clear the chat", false);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        for (int i = 0; i < 256; i++) {
            Bukkit.broadcastMessage("             ");
        }

        Bukkit.broadcastMessage(ConfigCache.getMsg("ccFormat").replace("%PLAYER%", sender.getName()));

        return true;
    }
}
