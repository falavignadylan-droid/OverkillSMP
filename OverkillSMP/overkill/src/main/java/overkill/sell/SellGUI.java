package overkill.sell;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SellGUI {

    public static Inventory create(Player player) {
        Inventory gui = Bukkit.createInventory(null, 54, "Sell Items");

        // Riempie con vetro decorativo
        ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = filler.getItemMeta();
        meta.setDisplayName(" ");
        filler.setItemMeta(meta);

        for (int i = 0; i < gui.getSize(); i++) {
            gui.setItem(i, filler);
        }

        // Slot centrali liberi per mettere gli item
        for (int i = 10; i <= 16; i++) gui.setItem(i, null);
        for (int i = 19; i <= 25; i++) gui.setItem(i, null);
        for (int i = 28; i <= 34; i++) gui.setItem(i, null);

        return gui;
    }
}
