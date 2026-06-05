package overkill.listeners;

import overkill.afk.GemManager;
import overkill.spawner.SpawnerType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GemShopListener implements Listener {

    private final GemManager gemManager;

    public GemShopListener(GemManager gemManager) {
        this.gemManager = gemManager;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        if (!e.getView().getTitle().equals("§bGem Shop")) return;

        e.setCancelled(true);

        Player p = (Player) e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();

        if (clicked == null || clicked.getType() == Material.AIR) return;

        String name = clicked.getItemMeta().getDisplayName();

        SpawnerType type = null;

        if (name.contains("Skeleton")) type = SpawnerType.SKELETON;
        if (name.contains("Creeper")) type = SpawnerType.CREEPER;
        if (name.contains("Zombie")) type = SpawnerType.ZOMBIE;
        if (name.contains("Enderman")) type = SpawnerType.ENDERMAN;
        if (name.contains("Cow")) type = SpawnerType.COW;

        if (type == null) return;

        int gems = gemManager.getGems(p);

        if (gems < 300) {
            p.sendMessage("§cYou don't have enough gems.");
            return;
        }

        gemManager.addGems(p, -300);

        p.getInventory().addItem(createSpawnerItem(type));

        p.sendMessage("§aYou bought a §e" + type.name() + " Spawner §afor §b300 Gems§a!");
    }

    private ItemStack createSpawnerItem(SpawnerType type) {

        ItemStack spawner = new ItemStack(Material.SPAWNER);
        ItemMeta meta = spawner.getItemMeta();

        meta.setDisplayName("§a" + type.name() + " Spawner");

        meta.getPersistentDataContainer().set(
                SpawnerNBT.KEY,
                SpawnerNBT.TYPE,
                type.name()
        );

        spawner.setItemMeta(meta);
        return spawner;
    }
}
