package overkill.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import SpawnerManager;
import SpawnerGUI;

public class SpawnerListener implements Listener {

    private final SpawnerManager manager;
    private final SpawnerGUI gui;

    public SpawnerListener(SpawnerManager manager, SpawnerGUI gui) {
        this.manager = manager;
        this.gui = gui;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {

        Block b = e.getBlock();
        Player p = e.getPlayer();

        if (b.getType() != Material.SPAWNER) return;

        ItemStack tool = p.getInventory().getItemInMainHand();
        if (!tool.containsEnchantment(Enchantment.SILK_TOUCH)) {
            p.sendMessage("§cYou need Silk Touch to mine spawners.");
            e.setCancelled(true);
            return;
        }

        if (manager.get(b.getLocation()) != null) {
            manager.remove(b.getLocation());
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {

        if (e.getClickedBlock() == null) return;
        if (e.getClickedBlock().getType() != Material.SPAWNER) return;

        Player p = e.getPlayer();
        SpawnerData data = manager.get(e.getClickedBlock().getLocation());

        if (data == null) return;

        p.openInventory(gui.getPage(data, 1));
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {

    if (e.getBlockPlaced().getType() != Material.SPAWNER) return;

    ItemStack item = e.getItemInHand();
    ItemMeta meta = item.getItemMeta();

    if (!meta.getPersistentDataContainer().has(SpawnerNBT.KEY, SpawnerNBT.TYPE)) return;

    String typeName = meta.getPersistentDataContainer().get(SpawnerNBT.KEY, SpawnerNBT.TYPE);
    SpawnerType type = SpawnerType.valueOf(typeName);

    SpawnerData data = new SpawnerData(e.getBlockPlaced().getLocation(), type, 1);
    manager.add(data);

    e.getPlayer().sendMessage("§aPlaced a §e" + type.name() + " Spawner§a!");
}

}
