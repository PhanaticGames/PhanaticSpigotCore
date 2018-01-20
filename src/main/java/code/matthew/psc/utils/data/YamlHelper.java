package code.matthew.psc.utils.data;

import org.apache.commons.lang.Validate;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * A utility class to replace some hard coded error handing
 */
public class YamlHelper {

    /**
     * Load a configuration from a file
     *
     * @param file The file
     * @return YamlConfiguration from the file
     * @throws InvalidConfigurationException Config was invalid
     * @throws IOException                   General IO exception
     */
    public static YamlConfiguration loadConfiguration(File file) throws InvalidConfigurationException, IOException {
        Validate.notNull(file, "CANNOT READ YML ON NULL FILE");
        YamlConfiguration config = new YamlConfiguration();
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException ex) {
            throw ex;
        }
        return config;
    }

}
