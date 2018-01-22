package code.matthew.psc.listener;

import code.matthew.psc.PSC;
import code.matthew.psc.api.ban.Ban;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

/**
 * Note to self and others,
 * This infact does run in async,
 * although it does sound clear by
 * its naming, took awhile to notice
 */
public class AsyncPreLogin implements Listener {


    public AsyncPreLogin() {
        PSC.getInstance().getServer().getPluginManager().registerEvents(this, PSC.getInstance());
    }

    @EventHandler
    public void onPreJoin(AsyncPlayerPreLoginEvent e) {
        Ban ban = PSC.getInstance().getBm().getBan(e.getUniqueId().toString());
        if (ban != null) {
            if (PSC.getInstance().getBm().checkBan(ban)) {
                String deny = PSC.getInstance().getBm().getDenyReason(ban);
                e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, deny);
            }
        }
    }
}
