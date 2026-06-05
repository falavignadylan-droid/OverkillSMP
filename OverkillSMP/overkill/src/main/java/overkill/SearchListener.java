package me.dylan.overkill.ah;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashSet;
import java.util.Set;

public class SearchListener implements Listener {

    private final AHManager manager;
    private final AHGUI ahGUI;
    private final SearchGUI searchGUI;

    private final Set<Player> waitingForInput = new HashSet<>();

    public SearchListener(AHManager manager, AHGUI ahGUI, SearchGUI searchGUI) {
        this.manager = manager;
        this.ahGUI = ahGUI;
        this.searchGUI = searchGUI;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        if (!e.getView().getTitle().equals("§bSearch Items")) return;

        e.setCancelled(true);
        Player p = (Player) e.getWhoClicked();

        if (e.getSlot() == 13) {
            p.closeInventory();
            p.sendMessage("§eType your search text in chat...");
            waitingForInput.add(p);
        }

        if (e.getSlot() == 22) {
            p.openInventory(ahGUI.getPage(p, 1));
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {

        Player p = e.getPlayer();

        if (!waitingForInput.contains(p)) return;

        e.setCancelled(true);
        waitingForInput.remove(p);

        String query = e.getMessage();

        Bukkit.getScheduler().runTask(manager.getPlugin(), () -> {
            p.openInventory(ahGUI.getSearchPage(p, query, 1));
        });
    }
}
