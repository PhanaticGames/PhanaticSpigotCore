package code.matthew.psc.utils.core;

import code.matthew.psc.api.command.ICommand;
import code.matthew.psc.utils.logs.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;

/**
 * A class to work around having to add a command to plugin.yml
 */
public class CommandManager {

    private static CommandMap cmdMap;

    /**
     * Called by PSC, not to be used extenally
     */
    public static void setup() {
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            cmdMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            Logger.log(Logger.LogType.ERROR, "Error setting up command map");
            if (Logger.isDebug()) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Register a command
     * Note: This means you DO NOT add your command to plugin.yml
     *
     * @param cmd The ICommand object
     */
    public static void regCommand(ICommand cmd) {
        cmdMap.register(cmd.getCmd(), cmd);
    }
}
