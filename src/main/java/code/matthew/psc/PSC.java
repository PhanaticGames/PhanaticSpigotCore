package code.matthew.psc;

import code.matthew.psc.cmd.BanCmd;
import code.matthew.psc.cmd.ForceSync;
import code.matthew.psc.cmd.UnbanCmd;
import code.matthew.psc.listener.AsyncPreLogin;
import code.matthew.psc.utils.core.BanManager;
import code.matthew.psc.utils.core.CommandManager;
import code.matthew.psc.utils.data.ConfigCache;
import code.matthew.psc.utils.data.Database;
import code.matthew.psc.utils.data.Files;
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
        // TODO MAKE DEBUG A CONFIG VAR
        instance = this;
        files = new Files(this);
        ConfigCache.setup(this);
        db = new Database(this);
        db.connect();
        bm = new BanManager(this);
        Logger.setDebug(true);
        regListeners();
        regCommands();
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
    }
}
