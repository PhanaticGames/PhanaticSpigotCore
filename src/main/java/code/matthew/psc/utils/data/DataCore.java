package code.matthew.psc.utils.data;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DataCore {

    private static List<Player> frozenStore = new ArrayList<>();
    private static List<Player> flyingStore = new ArrayList<>();

    /**
     * Add a player to the fly list
     *
     * @param p Player to add
     */
    public static void addPlayerFly(Player p) {
        if (!flyingStore.contains(p)) {
            flyingStore.add(p);
        }
    }

    /**
     * Check if a player can fly
     *
     * @param p The player
     * @return If the player may fly
     */
    public static boolean canFly(Player p) {
        return flyingStore.contains(p);
    }

    /**
     * Take a players permission to fly
     *
     * @param p Player's whos fly should be removed
     */
    public static void takePlayerFly(Player p) {
        if (flyingStore.contains(p)) {
            flyingStore.remove(p);
        }
    }

    /**
     * Freeze a player
     *
     * @param p Player to freeze
     */
    public static void freezePlayer(Player p) {
        if (!frozenStore.contains(p)) {
            frozenStore.add(p);
        }
    }

    /**
     * Check if a player is frozen
     *
     * @param p Player to check
     * @return If the player is frozen
     */
    public static boolean isFrozen(Player p) {
        return frozenStore.contains(p);
    }

    /**
     * Un freeze a player
     *
     * @param p Player to unfreeze
     */
    public static void unfrezzePlayer(Player p) {
        if (frozenStore.contains(p)) {
            frozenStore.remove(p);
        }
    }
}
