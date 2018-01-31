package code.matthew.psc.cmd.staff;

import code.matthew.psc.api.command.ICommand;
import code.matthew.psc.utils.data.ConfigCache;
import code.matthew.psc.utils.strings.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class BroadcastCmd extends ICommand {

    public BroadcastCmd() {
        super("broadcast", "psc.broadcast", "BroadcastCmd a message", false);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(ConfigCache.getMsg("broadcastUsage"));
            return false;
        }

        StringBuilder builder = new StringBuilder();

        for (String s : args) {
            builder.append(s).append(" ");
        }

        String msg = ColorUtil.colorStr(builder.toString());

        Bukkit.broadcastMessage(ConfigCache.getMsg("bcFormat").replace("%MSG%", msg));
        return true;
    }
}
