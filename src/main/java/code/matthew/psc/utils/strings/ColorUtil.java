package code.matthew.psc.utils.strings;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class ColorUtil {

    public static String colorStr(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static List<String> colorList(List<String> list) {
        return list.stream().map(s -> colorStr(s)).collect(Collectors.toList());
    }
}
