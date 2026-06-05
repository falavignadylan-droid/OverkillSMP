package overkill.ah;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SearchGUI {

    public Inventory get(Player p) {

        Inventory inv = Bukkit.createInventory(null, 27, "§bSearch Items");

        ItemStack filler = create(Material.GRAY_STAINED_GLASS_PANE, " ");
        for (int i = 0; i < 27; i++) inv.setItem(i, filler);

        inv.setItem(13, create(Material.PAPER, "§eEnter search text"));

        inv.setItem(22, create(Material.BARRIER, "§cBack"));

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
