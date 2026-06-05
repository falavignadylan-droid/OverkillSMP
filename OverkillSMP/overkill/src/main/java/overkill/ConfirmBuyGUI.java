package overkill.ah;

import me.dylan.overkill.utils.PriceFormatter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ConfirmBuyGUI {

    public Inventory get(Player p, AHItem item) {

        Inventory inv = Bukkit.createInventory(null, 27, "§aConfirm Purchase");

        ItemStack filler = create(Material.GRAY_STAINED_GLASS_PANE, " ");
        for (int i = 0; i < 27; i++) inv.setItem(i, filler);

        inv.setItem(13, item.getItem());

        inv.setItem(11, create(Material.GREEN_CONCRETE, "§aConfirm §7($"
                + PriceFormatter.format(item.getPrice()) + ")"));

        inv.setItem(15, create(Material.RED_CONCRETE, "§cCancel"));

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
