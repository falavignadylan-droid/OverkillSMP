package overkill.gemshop;

import overkill.spawner.SpawnerType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.*;

public class GemShopGUI {

    public Inventory get() {

        Inventory inv = Bukkit.createInventory(null, 27, "§bGem Shop");

        ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta fm = filler.getItemMeta();
        fm.setDisplayName(" ");
        filler.setItemMeta(fm);

        for (int i = 0; i < 27; i++) inv.setItem(i, filler);

        inv.setItem(10, createSpawnerItem("§aSkeleton Spawner", Material.SKELETON_SKULL));
        inv.setItem(11, createSpawnerItem("§aCreeper Spawner", Material.CREEPER_HEAD));
        inv.setItem(12, createSpawnerItem("§aZombie Spawner", Material.ZOMBIE_HEAD));
        inv.setItem(13, createSpawnerItem("§aEnderman Spawner", Material.ENDER_PEARL));
        inv.setItem(14, createSpawnerItem("§aCow Spawner", Material.LEATHER));

        return inv;
    }

    private ItemStack createSpawnerItem(String name, Material icon) {
        ItemStack i = new ItemStack(icon);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(name);
        m.setLore(java.util.List.of("§7Cost: §b300 Gems"));
        i.setItemMeta(m);
        return i;
    }
}
