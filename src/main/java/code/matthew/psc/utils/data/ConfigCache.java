package code.matthew.psc.utils.data;

import code.matthew.psc.PSC;
import code.matthew.psc.utils.strings.ColorUtil;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigCache {

    @Getter
    private static List<String> banFormat;

    @Getter
    private static final Map<String, String> msgs = new HashMap<>();

    @Getter
    private static final Map<String, Boolean> configBoolans = new HashMap<>();

    @Getter
    private static final Map<String, Integer> configInts = new HashMap<>();

    @Getter
    private static final Map<String, String> configStrs = new HashMap<>();

    public static void setup(PSC psc) {
        banFormat = ColorUtil.colorList(psc.getFiles().getMessagesYML().getStringList("banDenyFormat"));

        for (String key : PSC.getInstance().getFiles().getMessagesYML().getConfigurationSection("msgs").getKeys(false)) {
            msgs.put(key, ColorUtil.colorStr(PSC.getInstance().getFiles().getMessagesYML().getString("msgs." + key)));
        }

        for (String key : PSC.getInstance().getFiles().getConfig().getConfigurationSection("general").getKeys(false)) {
            Object obj = PSC.getInstance().getFiles().getConfig().get("general." + key);
            if (obj instanceof Boolean) {
                configBoolans.put(key, (Boolean) obj);
            } else if (obj instanceof Integer) {
                configInts.put(key, (Integer) obj);
            } else if (obj instanceof String) {
                configStrs.put(key, (String) obj);
            }
        }
    }

    public static String getConfigString(String key) {
        return configStrs.get(key);
    }

    public static boolean getConfigBoolean(String key) {
        return configBoolans.get(key);
    }

    public static int getConfigInt(String key) {
        return configInts.get(key);
    }

    public static String getMsg(String key) {
        return msgs.get(key);
    }

}
