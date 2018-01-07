package code.matthew.psc.listener;

import code.matthew.psc.PSC;
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

    private PSC psc;

    public AsyncPreLogin(PSC psc) {
        this.psc = psc;
        psc.getServer().getPluginManager().registerEvents(this, psc);
    }

    @EventHandler
    public void onPreJoin(AsyncPlayerPreLoginEvent e) {
        if (psc.getBm().checkBan(psc.getBm().getBan(e.getUniqueId().toString()))) {
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, psc.getBm().getDenyReason(psc.getBm().getBan(e.getUniqueId().toString())));
        }
    }
}
