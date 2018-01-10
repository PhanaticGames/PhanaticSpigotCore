package code.matthew.psc.utils.data;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DataCore {

    private static List<Player> frozenStore = new ArrayList<>();

    public static void freezePlayer(Player p) {
        if (!frozenStore.contains(p)) {
            frozenStore.add(p);
        }
    }

    public static boolean isFrozen(Player p) {
        return frozenStore.contains(p);
    }

    public static void unfrezzePlayer(Player p) {
        if (frozenStore.contains(p)) {
            frozenStore.remove(p);
        }
    }
}
