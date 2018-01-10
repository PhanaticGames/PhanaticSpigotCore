package code.matthew.psc.api;

import code.matthew.psc.utils.data.ConfigCache;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * The main class for all commands
 * This is used to hide some ugly bukkit code
 * It also auto checks permissions and sender state
 */
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

    /**
     * The main constructor for an ICommand
     *
     * @param cmd            The command has shown in chat
     * @param perm           The base permission for the command
     * @param desc           A short description of the command
     * @param playerRequired True= sender must be a player
     */
    public ICommand(String cmd, String perm, String desc, boolean playerRequired) {
        super(cmd);
        this.setCmd(cmd);
        this.setDescription(desc);
        this.setDescription(desc);
        this.setPermission(perm);
        this.setPerm(perm);
        this.setPlayerReq(playerRequired);
    }

    /**
     * Used internally.
     */
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

    /**
     * Override to execute command
     * Note: if player required is true, it is safe tocast CommandSender to player
     * @param sender The sender
     * @param args The args
     * @return Weather or not the command worked
     */
    public boolean finalExe(CommandSender sender, String[] args) {
        return false;
    }


}
