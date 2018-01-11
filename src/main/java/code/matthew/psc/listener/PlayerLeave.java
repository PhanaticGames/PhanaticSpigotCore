package code.matthew.psc.listener;

import code.matthew.psc.PSC;
import code.matthew.psc.utils.data.DataCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {

    public PlayerLeave(PSC psc) {
        psc.getServer().getPluginManager().registerEvents(this, psc);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        if (DataCore.canFly(e.getPlayer())) {
            DataCore.takePlayerFly(e.getPlayer());
        }
        if (DataCore.isFrozen(e.getPlayer())) {
            DataCore.unfrezzePlayer(e.getPlayer());
        }
    }
}
