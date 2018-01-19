package code.matthew.psc.api.file;

import code.matthew.psc.utils.data.YamlHelper;
import code.matthew.psc.utils.logs.Logger;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * This is the main class to make a config file for a plugin
 * This is ment for data that the plugin will never change.
 */
public class ConfigFile {

    private Plugin pl;
    private String fileName;
    private File file;
    private FileConfiguration configuration;

    /**
     * Make a new config file
     *
     * @param pl   The plugin
     * @param file The file name. MUST include file type
     */
    public ConfigFile(Plugin pl, String file) {
        this.pl = pl;
        this.fileName = file;
        this.file = new File(pl.getDataFolder() + File.separator + fileName);
    }

    /**
     * Set up the file
     */
    public void setup() {
        if (!pl.getDataFolder().exists()) {
            if (pl.getDataFolder().mkdirs()) {
                Logger.log(Logger.LogType.INFO, "Created " + pl.getDescription().getName() + "'s plugin folder");
            } else {
                Logger.log(Logger.LogType.ERROR, "Failed to create " + pl.getDescription().getName() + "'s plugin folder");
            }
        }

        if (!this.file.exists()) {
            pl.saveResource(fileName, false);
        }
    }

    /**
     * Reload the files configuration
     */
    public void reload() {
        if (configuration != null) {

            if (file == null) {
                setup();
            }

            try {
                configuration = YamlHelper.loadConfiguration(file);
            } catch (IOException | InvalidConfigurationException ex) {
                if (ex instanceof IOException) {
                    Logger.log(Logger.LogType.ERROR, "Error when loading a configuration file. General IO exception");
                    if (Logger.isDebug()) {
                        ex.printStackTrace();
                    }
                } else {
                    Logger.log(Logger.LogType.ERROR, "Error when loading a configuration file. Invalid configuration mapping");
                    if (Logger.isDebug()) {
                        ex.printStackTrace();
                    }
                    // TODO, Rename file to broken 1 and make a fresh copy from plugin
                }
            }
        }
    }

    /**
     * Short cut. Same as #getConfiguration#getString()
     *
     * @param path The path to search
     * @return The value at the provided path
     */
    public String getString(String path) {
        if (configuration == null) {
            reload();
        }
        return configuration.getString(path);
    }

    /**
     * Short cut. Same as #getConfiguration#getBoolean()
     *
     * @param path The path to search
     * @return The value at the provided path
     */
    public boolean getBoolean(String path) {
        if (configuration == null) {
            reload();
        }
        return configuration.getBoolean(path);
    }

    /**
     * Short cut. Same as #getConfiguration#getStringList()
     *
     * @param path The path to search
     * @return The value at the provided path
     */
    public List<String> getStringList(String path) {
        if (configuration == null) {
            reload();
        }
        return configuration.getStringList(path);
    }

    /**
     * Short cut. Same as #getConfiguration#getInt()
     *
     * @param path The path to search
     * @return The value at the provided path
     */
    public int getInt(String path) {
        if (configuration == null) {
            reload();
        }
        return configuration.getInt(path);
    }

    /**
     * Short cut. Same as #getConfiguration#get()
     *
     * @param path The path to search
     * @return The value at the provided path
     */
    public Object get(String path) {
        if (configuration == null) {
            reload();
        }
        return configuration.get(path);
    }

    /**
     * Get the configuration object
     *
     * @return The configuration object
     */
    public FileConfiguration getConfiguration() {
        if (configuration == null) {
            reload();
        }
        return configuration;
    }
}
