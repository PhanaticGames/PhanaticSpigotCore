package code.matthew.psc.cmd.data;

import code.matthew.psc.PSC;
import code.matthew.psc.api.command.ICommand;
import code.matthew.psc.runnable.AsyncBans;
import code.matthew.psc.utils.data.ConfigCache;
import org.bukkit.command.CommandSender;

public class ForceSync extends ICommand {

    public ForceSync() {
        super("forcesync", "psc.forcesync", "Force a data sync", false);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        // TODO ADD ALL TYPES OF DATA
        PSC.getInstance().getServer().getScheduler().runTask(PSC.getInstance(), new AsyncBans());
        sender.sendMessage(ConfigCache.getMsg("forcedASync"));
        return true;
    }
}
