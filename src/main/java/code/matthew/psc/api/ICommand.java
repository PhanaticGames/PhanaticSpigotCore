package code.matthew.psc.api;

import code.matthew.psc.utils.data.ConfigCache;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("WeakerAccess")
public class ICommand extends Command {

    @Getter @Setter
    private String cmd;

    @Getter @Setter
    private String perm;

    @Getter @Setter
    private String desc;

    @Getter @Setter
    private boolean playerReq;

    public ICommand(String cmd, String perm, String desc, boolean playerRequired) {
        super(cmd);
        this.setCmd(cmd);
        this.setDescription(desc);
        this.setDescription(desc);
        this.setPermission(perm);
        this.setPerm(perm);
        this.setPlayerReq(playerRequired);
    }

    @Override
    public boolean execute(CommandSender sender, String lbl, String[] args) {
        if(lbl.equalsIgnoreCase(getCmd())) {
            if(sender.hasPermission(getPerm())) {
                if(playerReq) {
                    Player p = (Player) sender;

                    if(p != null) {
                        finalExe(sender, args);
                    } else {
                        sender.sendMessage(ConfigCache.getMsg("mustBePlayer"));
                        return false;
                    }
                } else {
                    finalExe(sender, args);
                }

            } else {
                sender.sendMessage(ConfigCache.getMsg("noPerm"));
                return false;
            }
        }
        return false;
    }

    public boolean finalExe(CommandSender sender, String[] args) {
        return false;
    }


}
