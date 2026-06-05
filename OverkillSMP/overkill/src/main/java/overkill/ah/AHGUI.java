package overkill.ah;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class AHGUI {

    private final AHManager manager;


    public Inventory getSearchPage(Player p, String query, int page) {

    Inventory inv = Bukkit.createInventory(null, 54, "§bSearch: " + query);

    ItemStack filler = createButton(Material.BLACK_STAINED_GLASS_PANE, " ");
    for (int i = 45; i < 54; i++) inv.setItem(i, filler);

    List<AHItem> items = manager.searchItems(query);
    int start = (page - 1) * 45;
    int end = Math.min(start + 45, items.size());

    int slot = 0;
    for (int i = start; i < end; i++) {
        inv.setItem(slot++, buildItem(items.get(i)));
    }

    if (page > 1)
        inv.setItem(45, createButton(Material.ARROW, "§cPrevious Page"));

    if (end < items.size())
        inv.setItem(53, createButton(Material.ARROW, "§aNext Page"));

    inv.setItem(49, createButton(Material.BARRIER, "§cBack"));

    return inv;
}

    public AHGUI(AHManager manager) {
        this.manager = manager;
    }

    public Inventory getPage(Player p, int page) {

        Inventory inv = Bukkit.createInventory(null, 54, "§bAuction House §7| Page " + page);

        // FILL BLACK GLASS BORDER (Donut style)
        ItemStack filler = createItem(Material.BLACK_STAINED_GLASS_PANE, " ");
        for (int i = 45; i < 54; i++) inv.setItem(i, filler);

        // ITEMS (45 slots)
        List<AHItem> items = manager.getPage(page, 45);
        int slot = 0;

        for (AHItem ah : items) {
            inv.setItem(slot++, buildItem(ah));
        }

        // BUTTONS (Donut layout)
        inv.setItem(45, createItem(Material.ARROW, "§cPrevious Page"));
        inv.setItem(53, createItem(Material.ARROW, "§aNext Page"));

        inv.setItem(49, createItem(Material.COMPASS, "§bSearch"));
        inv.setItem(48, createItem(Material.BOOK, "§eCategories"));
        inv.setItem(50, createItem(Material.CHEST, "§6Your Listings"));
        inv.setItem(47, createItem(Material.BARRIER, "§cExpired Items"));

        return inv;
    }

    private ItemStack buildItem(AHItem ah) {
        ItemStack item = ah.getItem().clone();
        ItemMeta meta = item.getItemMeta();

        meta.setLore(List.of(
                "§7Seller: §f" + ah.getSellerName(),
                "§7Price: §a$" + ah.getPrice(),
                "§7Expires in: §e" + formatTime(ah.getExpiresAt() - System.currentTimeMillis()),
                "",
                "§aClick to buy"
        ));

        item.setItemMeta(meta);
        return item;
    }

    private ItemStack createItem(Material m, String name) {
        ItemStack i = new ItemStack(m);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(name);
        i.setItemMeta(meta);
        return i;
    }

    private String formatTime(long ms) {
        long sec = ms / 1000;
        long min = sec / 60;
        long hr = min / 60;

        if (hr > 0) return hr + "h " + (min % 60) + "m";
        if (min > 0) return min + "m " + (sec % 60) + "s";
        return sec + "s";
    }
}
