package code.matthew.psc.cmd.mute;

import code.matthew.psc.PSC;
import code.matthew.psc.api.command.ICommand;
import code.matthew.psc.api.player.PlayerUtils;
import code.matthew.psc.utils.data.ConfigCache;
import code.matthew.psc.utils.strings.StringUtil;
import code.matthew.psc.utils.time.TimeUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;

public class TempMuteCmd extends ICommand {

    public TempMuteCmd() {
        super("tempmute", "psc.tempmute", "Temp mute a player", false);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {

        if (args.length < 2) {
            sender.sendMessage(ConfigCache.getMsg("tmpBanUsage"));
            return false;
        }

        Player p = PlayerUtils.getOnlinePlayer(args[0]);

        if (p == null) {
            sender.sendMessage(ConfigCache.getMsg("playerNotOn"));
            return false;
        }

        String reason;
        String staff = sender.getName();

        if (args.length == 2) {
            reason = ConfigCache.getMsg("defaultMuteWhy");
        } else {
            StringBuilder builder = new StringBuilder();

            for (int i = 2; i < args.length; i++) {
                builder.append(args[i]).append(" ");
            }
            reason = builder.toString();
        }

        String timeUnit = String.valueOf(args[1].charAt(args[1].length() - 1));
        String time = StringUtil.removeLastChar(args[1]);

        Date end = TimeUtil.rawTimeToDate(time, timeUnit);

        PSC.getInstance().getMm().mutePlayer(p, staff, reason, end.toString());

        sender.sendMessage(ConfigCache.getMsg("tmpMutedPlayer").replace("%PLAYER%", p.getName()));

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("psc.staff")) {
                player.sendMessage(ConfigCache.getMsg("staffTempMute").replace("%PLAYER%", p.getName()).replace("%STAFF%", staff).replace("%REASON%", reason));
            } else {
                player.sendMessage(ConfigCache.getMsg("pubTempMute").replace("%PLAYER%", p.getName()).replace("%STAFF%", staff).replace("%REASON%", reason));
            }
        }

        return true;

    }
}
