package code.matthew.psc.listener;

import code.matthew.psc.PSC;
import code.matthew.psc.utils.data.DataCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent implements Listener {

    public MoveEvent(PSC psc) {
        psc.getServer().getPluginManager().registerEvents(this, psc);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (DataCore.isFrozen(e.getPlayer())) {
            e.setCancelled(true);
        }
    }
}
