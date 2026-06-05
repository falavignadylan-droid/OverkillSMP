package overkill.sell;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class SellItems {

    public static ItemStack sellButton() {
        ItemStack item = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§a§lVENDI TUTTO");
        meta.setLore(List.of("§7Clicca per vendere gli item"));
        item.setItemMeta(meta);
        return item;
    }
}
