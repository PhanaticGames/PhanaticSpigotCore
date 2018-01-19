package code.matthew.psc.api.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Some general player help methods
 */
public class PlayerUtils {

    /**
     * Get a player object. Used to get around Bukkit's old method
     *
     * @param name The name of the player
     * @return The player object from the specified name
     */
    public static Player getOnlinePlayer(String name) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }
}
