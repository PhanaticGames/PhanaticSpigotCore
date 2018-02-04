package code.matthew.psc.cmd.staff;

import code.matthew.psc.api.command.ICommand;
import code.matthew.psc.utils.data.ConfigCache;
import code.matthew.psc.utils.data.DataCore;
import code.matthew.psc.utils.strings.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class MuteChatCmd extends ICommand {

    public MuteChatCmd() {
        super("mutechat", "psc.mutechat", "Mute the chat", false);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        if (DataCore.isIsChatMuted()) {
            DataCore.setIsChatMuted(false);
            Bukkit.broadcastMessage(ColorUtil.colorStr(ConfigCache.getMsg("chatUnmuted").replaceAll("%PLAYER%", sender.getName())));
        } else {
            for (int i = 0; i < 256; i++) {
                Bukkit.broadcastMessage("             ");
            }
            DataCore.setIsChatMuted(true);
            Bukkit.broadcastMessage(ColorUtil.colorStr(ConfigCache.getMsg("chatMuted").replaceAll("%PLAYER%", sender.getName())));
        }
        return true;
    }
}
