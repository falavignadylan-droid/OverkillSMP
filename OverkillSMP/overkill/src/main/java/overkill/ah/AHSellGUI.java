package me.dylan.overkill.ah;

import me.dylan.overkill.utils.PriceFormatter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AHSellGUI {

    public Inventory get(Player p, double price) {

        Inventory inv = Bukkit.createInventory(null, 54, "§aList Item");

        ItemStack filler = create(Material.GRAY_STAINED_GLASS_PANE, " ");
        for (int i = 0; i < 54; i++) inv.setItem(i, filler);

        // Slot centrale per l’item
        inv.setItem(22, create(Material.CHEST, "§ePlace item here"));

        // Prezzo attuale
        inv.setItem(13, create(Material.PAPER, "§bPrice: §a$" + PriceFormatter.format(price)));

        // AUMENTO
        inv.setItem(28, create(Material.LIME_DYE, "§a+1"));
        inv.setItem(29, create(Material.LIME_DYE, "§a+10"));
        inv.setItem(30, create(Material.LIME_DYE, "§a+100"));
        inv.setItem(31, create(Material.LIME_DYE, "§a+1K"));
        inv.setItem(32, create(Material.LIME_DYE, "§a+10K"));
        inv.setItem(33, create(Material.LIME_DYE, "§a+100K"));
        inv.setItem(34, create(Material.LIME_DYE, "§a+1M"));
        inv.setItem(35, create(Material.LIME_DYE, "§a+10M"));
        inv.setItem(36, create(Material.LIME_DYE, "§a+100M"));
        inv.setItem(37, create(Material.LIME_DYE, "§a+1B"));

        // DIMINUZIONE
        inv.setItem(19, create(Material.RED_DYE, "§c-1"));
        inv.setItem(20, create(Material.RED_DYE, "§c-10"));
        inv.setItem(21, create(Material.RED_DYE, "§c-100"));
        inv.setItem(22, inv.getItem(22)); // item slot
        inv.setItem(23, create(Material.RED_DYE, "§c-1K"));
        inv.setItem(24, create(Material.RED_DYE, "§c-10K"));
        inv.setItem(25, create(Material.RED_DYE, "§c-100K"));
        inv.setItem(26, create(Material.RED_DYE, "§c-1M"));
        inv.setItem(27, create(Material.RED_DYE, "§c-10M"));
        inv.setItem(38, create(Material.RED_DYE, "§c-100M"));
        inv.setItem(39, create(Material.RED_DYE, "§c-1B"));

        // Confirm & Cancel
        inv.setItem(48, create(Material.GREEN_CONCRETE, "§aConfirm"));
        inv.setItem(50, create(Material.RED_CONCRETE, "§cCancel"));

        return inv;
    }

    private ItemStack create(Material m, String name) {
        ItemStack i = new ItemStack(m);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(name);
        i.setItemMeta(meta);
        return i;
    }
}
