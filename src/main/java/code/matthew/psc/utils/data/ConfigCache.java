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
    private static Map<String, String> msgs = new HashMap<>();


    public static void setup(PSC psc) {
        banFormat = ColorUtil.colorList(psc.getFiles().getMessagesYML().getStringList("banDenyFormat"));

        for(String key : PSC.getInstance().getFiles().getMessagesYML().getConfigurationSection("msgs").getKeys(false)){
            msgs.put(key, ColorUtil.colorStr(PSC.getInstance().getFiles().getMessagesYML().getString("msgs." + key)));
        }
    }

    public static String getMsg(String key) {
        return msgs.get(key);
    }
}
