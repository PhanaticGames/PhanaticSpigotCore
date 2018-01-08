package code.matthew.psc.utils.data;

import code.matthew.psc.PSC;
import code.matthew.psc.utils.logs.Logger;
import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Files {

    private PSC psc;

    @Getter
    private File mySQL;

    @Getter
    private File configFile;

    private File messages;

    @Getter
    private Properties mySQLProp;

    @Getter
    private YamlConfiguration config;

    @Getter
    private YamlConfiguration messagesYML;

    public Files(PSC psc) {
        this.psc = psc;
        mySQL = new File(psc.getDataFolder() + File.separator + "mysql.properties");
        messages = new File(psc.getDataFolder() + File.separator + "messages.yml");
        configFile = new File(psc.getDataFolder() + File.separator + "config.yml");
        testExist();
        reload();
    }

    private void testExist() {
        if(!psc.getDataFolder().exists()) {
            psc.getDataFolder().mkdir();
        }

        if(!mySQL.exists()) {
            psc.saveResource("mysql.properties", false);
        }

        if(!messages.exists()) {
            psc.saveResource("messages.yml", false);
        }

        if (!configFile.exists()) {
            psc.saveResource("config.yml", false);
        }
    }

    public void reload() {
        try {
            InputStream is = new FileInputStream(mySQL);
            mySQLProp = new Properties();
            mySQLProp.load(is);
        }catch (IOException ex) {
            Logger.log(Logger.LogType.ERROR, "ERROR LOADING MYSQL INFO");
        }
        messagesYML = YamlConfiguration.loadConfiguration(messages);
        config = YamlConfiguration.loadConfiguration(configFile);
    }

}
