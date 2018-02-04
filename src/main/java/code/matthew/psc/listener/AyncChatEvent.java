package code.matthew.psc.listener;

import code.matthew.psc.PSC;
import code.matthew.psc.api.mute.Mute;
import code.matthew.psc.utils.data.ConfigCache;
import code.matthew.psc.utils.data.DataCore;
import code.matthew.psc.utils.strings.ColorUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AyncChatEvent implements Listener {

    public AyncChatEvent(PSC psc) {
        psc.getServer().getPluginManager().registerEvents(this, psc);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (DataCore.isIsChatMuted()) {
            p.sendMessage(ColorUtil.colorStr(ConfigCache.getMsg("noSpeak")));
            e.setCancelled(true);
        }
        Mute mute = PSC.getInstance().getMm().getMute(p.getUniqueId().toString());
        if (mute != null) {
            if (PSC.getInstance().getMm().checkMute(mute)) {
                e.setCancelled(true);
                p.sendMessage(ColorUtil.colorStr(ConfigCache.getMsg("youMayNotSpeak")));
            }
        }
        if (e.getMessage().contains("&")) {
            if (p.hasPermission("psc.chatcolor")) {
                String newMsg = ColorUtil.colorStr(e.getMessage());
                e.setMessage(newMsg);
            }
        }
    }
}
