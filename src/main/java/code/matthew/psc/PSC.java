package code.matthew.psc;

import code.matthew.psc.cmd.ban.BanCmd;
import code.matthew.psc.cmd.ban.UnbanCmd;
import code.matthew.psc.cmd.data.ForceSync;
import code.matthew.psc.cmd.gamemode.GM3Cmd;
import code.matthew.psc.cmd.gamemode.GMACmd;
import code.matthew.psc.cmd.gamemode.GMCCmd;
import code.matthew.psc.cmd.gamemode.GMSCmd;
import code.matthew.psc.listener.AsyncPreLogin;
import code.matthew.psc.utils.core.BanManager;
import code.matthew.psc.utils.core.CommandManager;
import code.matthew.psc.utils.data.ConfigCache;
import code.matthew.psc.utils.data.Database;
import code.matthew.psc.utils.data.Files;
import code.matthew.psc.utils.logs.LogCleaner;
import code.matthew.psc.utils.logs.Logger;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class PSC extends JavaPlugin {

    @Getter public static PSC instance;
    @Getter private Database db;
    @Getter private Files files;
    @Getter private BanManager bm;

    @Override
    public void onEnable() {
        instance = this;
        files = new Files(this);
        ConfigCache.setup(this);

        Logger.setDebug(ConfigCache.getConfigBoolean("debug"));

        db = new Database(this);
        db.connect();
        bm = new BanManager(this);

        regListeners();
        regCommands();

        if (ConfigCache.getConfigBoolean("clearLogsOnStart")) {
            LogCleaner.cleanOldLogs();
        }
    }

    @Override
    public void onDisable() {
        db.close();
    }

    private void regListeners() {
        new AsyncPreLogin(this);
    }

    private void regCommands() {
        CommandManager.setup();
        CommandManager.regCommand(new BanCmd());
        CommandManager.regCommand(new ForceSync());
        CommandManager.regCommand(new UnbanCmd());
        CommandManager.regCommand(new GMSCmd());
        CommandManager.regCommand(new GMCCmd());
        CommandManager.regCommand(new GM3Cmd());
        CommandManager.regCommand(new GMACmd());
    }
}
