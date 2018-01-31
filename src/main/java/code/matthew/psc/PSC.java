package code.matthew.psc;

import code.matthew.psc.cmd.ban.BanCmd;
import code.matthew.psc.cmd.ban.TempBanCmd;
import code.matthew.psc.cmd.ban.UnbanCmd;
import code.matthew.psc.cmd.data.ForceSync;
import code.matthew.psc.cmd.gamemode.GM3Cmd;
import code.matthew.psc.cmd.gamemode.GMACmd;
import code.matthew.psc.cmd.gamemode.GMCCmd;
import code.matthew.psc.cmd.gamemode.GMSCmd;
import code.matthew.psc.cmd.misc.*;
import code.matthew.psc.cmd.mute.MuteCmd;
import code.matthew.psc.cmd.mute.UnmuteCmd;
import code.matthew.psc.cmd.staff.*;
import code.matthew.psc.listener.AsyncPreLogin;
import code.matthew.psc.listener.AyncChatEvent;
import code.matthew.psc.listener.MoveEvent;
import code.matthew.psc.listener.PlayerLeave;
import code.matthew.psc.nms.NMSUtil;
import code.matthew.psc.utils.core.BanManager;
import code.matthew.psc.utils.core.CommandManager;
import code.matthew.psc.utils.core.MuteManager;
import code.matthew.psc.utils.data.ConfigCache;
import code.matthew.psc.utils.data.Database;
import code.matthew.psc.utils.data.Files;
import code.matthew.psc.utils.logs.LogCleaner;
import code.matthew.psc.utils.logs.Logger;
import lombok.Getter;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class PSC extends JavaPlugin {

    @Getter public static PSC instance;
    @Getter private Database db;
    @Getter private Files files;
    @Getter private BanManager bm;
    @Getter
    private MuteManager mm;
    @Getter
    private NMSUtil nmsUtil;
    @Getter
    private static Permission perms = null;

    @Override
    public void onLoad() {
        instance = this;
        nmsUtil = new NMSUtil();
    }

    @Override
    public void onEnable() {
        files = new Files(this);
        ConfigCache.setup(this);

        Logger.setDebug(ConfigCache.getConfigBoolean("debug"));

        db = new Database(this);
        db.connect();
        bm = new BanManager(this);
        mm = new MuteManager(this);

        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();

        regListeners();
        regCommands();

        if (ConfigCache.getConfigBoolean("clearLogsOnStart")) {
            LogCleaner.cleanOldLogs();
        }

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    @Override
    public void onDisable() {
        bm.sync();
        mm.sync();
        db.close();
    }

    private void regListeners() {
        new AsyncPreLogin();
        new AyncChatEvent(this);
        new MoveEvent(this);
        new PlayerLeave(this);
    }

    private void regCommands() {
        CommandManager.setup();
        CommandManager.regCommand(new BanCmd());
        CommandManager.regCommand(new ForceSync());
        CommandManager.regCommand(new UnbanCmd());
        CommandManager.regCommand(new TempBanCmd());
        CommandManager.regCommand(new GMSCmd());
        CommandManager.regCommand(new GMCCmd());
        CommandManager.regCommand(new GM3Cmd());
        CommandManager.regCommand(new GMACmd());
        CommandManager.regCommand(new ClearChat());
        CommandManager.regCommand(new Kick());
        CommandManager.regCommand(new Feed());
        CommandManager.regCommand(new Broadcast());
        CommandManager.regCommand(new Heal());
        CommandManager.regCommand(new Extinguish());
        CommandManager.regCommand(new ClearInventory());
        CommandManager.regCommand(new Freeze());
        CommandManager.regCommand(new Fly());
        CommandManager.regCommand(new Hub());
        CommandManager.regCommand(new SetRank());
        CommandManager.regCommand(new MuteCmd());
        CommandManager.regCommand(new UnmuteCmd());
    }
}
