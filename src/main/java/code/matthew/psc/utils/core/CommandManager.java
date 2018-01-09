package code.matthew.psc.utils.core;

import code.matthew.psc.api.ICommand;
import code.matthew.psc.utils.logs.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;

public class CommandManager {

    private static CommandMap cmdMap;

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

    public static void regCommand(ICommand cmd) {
        cmdMap.register(cmd.getCmd(), cmd);
    }
}
