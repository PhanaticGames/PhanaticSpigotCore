package code.matthew.psc.utils.item;

import code.matthew.psc.utils.strings.ColorUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    @Getter
    @Setter
    private ItemStack is;

    @Getter
    @Setter
    private ItemMeta meta;

    @Getter
    @Setter
    private List<String> lore;

    public ItemBuilder(Material mat) {
        is = new ItemStack(mat);
        meta = is.getItemMeta();
        lore = new ArrayList<>();
    }

    public ItemBuilder(Material mat, int count) {
        is = new ItemStack(mat, count);
        meta = is.getItemMeta();
        lore = new ArrayList<>();
    }

    public ItemBuilder(Material mat, int count, byte id) {
        is = new ItemStack(mat, count, id);
        meta = is.getItemMeta();
        lore = new ArrayList<>();
    }

    public ItemBuilder setName(String name) {
        meta.setDisplayName(ColorUtil.colorStr(name));
        updateMeta();
        return this;
    }

    public ItemBuilder addLore(String lore) {
        this.lore.add(ColorUtil.colorStr(lore));
        meta.setLore(this.lore);
        updateMeta();
        return this;
    }

    public ItemBuilder removeLore(String lore) {
        this.lore.remove(ColorUtil.colorStr(lore));
        meta.setLore(this.lore);
        updateMeta();
        return this;
    }

    public ItemBuilder clearLore() {
        lore.clear();
        meta.setLore(lore);
        updateMeta();
        return this;
    }

    private void updateMeta() {
        is.setItemMeta(meta);
    }

    public ItemStack build() {
        return is;
    }
}
