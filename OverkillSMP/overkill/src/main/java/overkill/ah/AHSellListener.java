package me.dylan.overkill.ah;

import me.dylan.overkill.utils.PriceFormatter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AHSellListener implements Listener {

    private final AHManager manager;
    private final AHSellGUI gui;

    public AHSellListener(AHManager manager, AHSellGUI gui) {
        this.manager = manager;
        this.gui = gui;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        if (!e.getView().getTitle().equals("§aList Item")) return;

        e.setCancelled(true);
        Player p = (Player) e.getWhoClicked();
        Inventory inv = e.getInventory();

        ItemStack center = inv.getItem(22);
        if (center != null && center.getType() == Material.CHEST) center = null;

        double price = getPrice(inv);

        switch (e.getSlot()) {

            // AUMENTO
            case 28 -> price += 1;
            case 29 -> price += 10;
            case 30 -> price += 100;
            case 31 -> price += 1000;
            case 32 -> price += 10_000;
            case 33 -> price += 100_000;
            case 34 -> price += 1_000_000;
            case 35 -> price += 10_000_000;
            case 36 -> price += 100_000_000;
            case 37 -> price += 1_000_000_000;

            // DIMINUZIONE
            case 19 -> price = Math.max(0, price - 1);
            case 20 -> price = Math.max(0, price - 10);
            case 21 -> price = Math.max(0, price - 100);
            case 23 -> price = Math.max(0, price - 1000);
            case 24 -> price = Math.max(0, price - 10_000);
            case 25 -> price = Math.max(0, price - 100_000);
            case 26 -> price = Math.max(0, price - 1_000_000);
            case 27 -> price = Math.max(0, price - 10_000_000);
            case 38 -> price = Math.max(0, price - 100_000_000);
            case 39 -> price = Math.max(0, price - 1_000_000_000);

            // CONFIRM
            case 48 -> {
                if (center == null) {
                    p.sendMessage("§cPlace an item first.");
                    return;
                }

                AHItem item = new AHItem(
                        p.getUniqueId(),
                        p.getName(),
                        center,
                        price,
                        1000L * 60 * 60 * 24 // 24h
                );

                manager.addItem(item);

                p.sendMessage("§aItem listed for §f$" + PriceFormatter.format(price));
                p.closeInventory();
                return;
            }

            // CANCEL
            case 50 -> {
                p.closeInventory();
                return;
            }
        }

        // Aggiorna GUI
        p.openInventory(gui.get(p, price));
    }

    private double getPrice(Inventory inv) {
        ItemStack paper = inv.getItem(13);
        if (paper == null) return 0;

        String name = paper.getItemMeta().getDisplayName();
        String raw = name.replace("§bPrice: §a$", "");
        return PriceFormatter.parse(raw);
    }
}
