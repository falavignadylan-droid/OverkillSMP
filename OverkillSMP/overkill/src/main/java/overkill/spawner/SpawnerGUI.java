package overkill.spawner;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.*;

public class SpawnerGUI {

    public Inventory getPage(SpawnerData data, int page) {

        Inventory inv = Bukkit.createInventory(null, 54, "§eSpawner Storage");

        ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = filler.getItemMeta();
        meta.setDisplayName(" ");
        filler.setItemMeta(meta);

        for (int i = 45; i < 54; i++) inv.setItem(i, filler);

        Inventory storage = data.getInventory();

        int start = (page - 1) * 45;
        int end = Math.min(start + 45, storage.getSize());

        for (int i = start; i < end; i++) {
            inv.setItem(i - start, storage.getItem(i));
        }

        if (page > 1)
            inv.setItem(45, create(Material.ARROW, "§cPrevious Page"));

        if (end < storage.getSize())
            inv.setItem(53, create(Material.ARROW, "§aNext Page"));

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
