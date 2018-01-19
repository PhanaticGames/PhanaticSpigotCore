package code.matthew.psc.utils.strings;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A class to help with colors for chat and console!
 */
public class ColorUtil {

    /**
     * Color a string.
     *
     * @param str The string to color
     * @return The string, colored
     */
    public static String colorStr(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    /**
     * Color a list of strings
     * @param list A list of strings
     * @return The colored list of strings
     */
    public static List<String> colorList(List<String> list) {
        return list.stream().map(ColorUtil::colorStr).collect(Collectors.toList());
    }
}
