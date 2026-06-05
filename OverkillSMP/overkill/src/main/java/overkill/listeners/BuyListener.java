package overkill.listeners;

import overkill.utils.PriceFormatter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import AHManager;
import ConfirmBuyGUI;

public class BuyListener implements Listener {

    private final AHManager manager;
    private final ConfirmBuyGUI confirmGUI;
    private final Economy econ;

    public BuyListener(AHManager manager, ConfirmBuyGUI confirmGUI) {
        this.manager = manager;
        this.confirmGUI = confirmGUI;
        this.econ = Bukkit.getServicesManager().getRegistration(Economy.class).getProvider();
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        // CLICK SU ITEM NELL’AH
        if (e.getView().getTitle().startsWith("§bAuction House")) {
            e.setCancelled(true);

            if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR)
                return;

            AHItem item = manager.getByItem(e.getCurrentItem());
            if (item == null) return;

            p.openInventory(confirmGUI.get(p, item));
            return;
        }

        // CLICK NELLA CONFIRM BUY GUI
        if (e.getView().getTitle().equals("§aConfirm Purchase")) {
            e.setCancelled(true);

            if (e.getSlot() == 15) { // CANCEL
                p.closeInventory();
                return;
            }

            if (e.getSlot() == 11) { // CONFIRM
                AHItem item = manager.getByItem(e.getInventory().getItem(13));
                if (item == null) {
                    p.sendMessage("§cThis item is no longer available.");
                    p.closeInventory();
                    return;
                }

                double price = item.getPrice();

                if (econ.getBalance(p) < price) {
                    p.sendMessage("§cYou don't have enough money.");
                    return;
                }

                econ.withdrawPlayer(p, price);
                p.getInventory().addItem(item.getItem());
                manager.removeItem(item.getId());

                p.sendMessage("§aYou bought the item for §f$" + PriceFormatter.format(price));
                p.closeInventory();
            }
        }
    }
}
