package code.matthew.psc.api.mute;

import org.bukkit.entity.Player;

public class MuteFactory {

    public static Mute genMute(Player p, String staff, String reason, String end, String start) {
        return new Mute(p.getName(), p.getUniqueId().toString(), staff, reason, end, start);
    }

    public static Mute genMuteRaw(String p, String uuid, String staff, String reason, String end, String start, String active) {
        return new Mute(p, uuid, staff, reason, end, start, active);
    }
}
