package code.matthew.psc.api;

import org.bukkit.entity.Player;

public class BanFactory {

    public static Ban genBan(Player p, String staff, String reason, String end, String start) {
        return new Ban(p.getName(), p.getUniqueId().toString(), staff, reason, end, start);
    }

    public static Ban genBanRaw(String p, String uuid, String staff, String reason, String end, String start, String active) {
        return new Ban(p, uuid, staff, reason, end, start, active);
    }
}
