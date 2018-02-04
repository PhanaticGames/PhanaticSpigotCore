package code.matthew.psc.cmd.misc;

import code.matthew.psc.api.command.ICommand;
import code.matthew.psc.utils.data.ConfigCache;
import code.matthew.psc.utils.data.DataCore;
import code.matthew.psc.utils.strings.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCmd extends ICommand {

    public VanishCmd() {
        super("vanish", "psc.vanish", "Go in/out of vanish", true);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        if (DataCore.isVanish(p)) {
            DataCore.removeFromVanish(p);
            for (Player pp : Bukkit.getOnlinePlayers()) {
                pp.showPlayer(p);
            }
            p.sendMessage(ColorUtil.colorStr(ConfigCache.getMsg("outVanish")));
        } else {
            DataCore.addToVanish(p);
            for (Player pp : Bukkit.getOnlinePlayers()) {
                pp.hidePlayer(p);
            }
            p.sendMessage(ColorUtil.colorStr(ConfigCache.getMsg("inVanish")));
        }
        return true;
    }
}
