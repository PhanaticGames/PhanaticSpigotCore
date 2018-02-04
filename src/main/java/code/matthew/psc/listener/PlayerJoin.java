package code.matthew.psc.listener;

import code.matthew.psc.PSC;
import code.matthew.psc.utils.data.DataCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    public PlayerJoin() {
        PSC.getInstance().getServer().getPluginManager().registerEvents(this, PSC.getInstance());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        for (Player p : DataCore.getIsVanish()) {
            e.getPlayer().hidePlayer(p);
        }
    }
}
