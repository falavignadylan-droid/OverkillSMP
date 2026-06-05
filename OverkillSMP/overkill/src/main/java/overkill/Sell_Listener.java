package overkill.sell;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SellListener implements Listener {

    private final FileConfiguration config;

    public SellListener(FileConfiguration config) {
        this.config = config;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals("§aSell Items")) {
            e.setCancelled(true);

            Player p = (Player) e.getWhoClicked();
            Inventory inv = e.getInventory();

            // Bottone SELL
            if (e.getRawSlot() == 53) {
                double total = 0;

                for (int i = 0; i < 53; i++) {
                    ItemStack item = inv.getItem(i);
                    if (item == null || item.getType() == Material.AIR) continue;

                    String key = item.getType().name();
                    double value = config.getDouble("items." + key, 0);

                    total += value * item.getAmount();
                    inv.setItem(i, null);
                }

                if (total > 0) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                            "eco give " + p.getName() + " " + total);

                    p.sendMessage("§aHai venduto gli item per §e" + total + "$");
                } else {
                    p.sendMessage("§cNessun item vendibile.");
                }
            }
        }
    }
}
