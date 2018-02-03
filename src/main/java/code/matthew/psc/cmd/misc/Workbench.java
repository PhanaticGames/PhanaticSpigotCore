package code.matthew.psc.cmd.misc;

import code.matthew.psc.api.command.ICommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class Workbench extends ICommand {

    public Workbench() {
        super("wb", "psc.wb", "Open a workbench", true);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        Inventory inv = Bukkit.createInventory(null, InventoryType.CRAFTING);
        Player p = (Player) sender;
        p.closeInventory();
        p.openInventory(inv);
        return true;
    }
}
