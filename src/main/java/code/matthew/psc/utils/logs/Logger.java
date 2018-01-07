package code.matthew.psc.utils.logs;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Logger {

    @Setter
    @Getter
    private static boolean debug;

    @Getter
    public enum LogType {

        INFO("Info", ChatColor.GOLD),
        ERROR("Error", ChatColor.DARK_RED),
        DEBUG("Debug", ChatColor.WHITE);

        private String string;
        private ChatColor chatColor;

        LogType(String string, ChatColor chatColor) {
            this.string = string;
            this.chatColor = chatColor;
        }

    }

    public static void log(LogType logType, String message) {
        switch (logType) {
            case INFO:
                Bukkit.getConsoleSender().sendMessage(logType.getChatColor() + "INFO:" + message);
                break;
            case ERROR:
                Bukkit.getConsoleSender().sendMessage(logType.getChatColor() + "ERROR:" + message);
                break;
            case DEBUG:
                if (Logger.isDebug()) {
                    Bukkit.getConsoleSender().sendMessage(logType.getChatColor() + "DEBUG:" + message);
                    break;
                }
        }
    }

}
