package overkill.ah;

import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class AHItem {

    private final UUID id;
    private final UUID seller;
    private final String sellerName;
    private final ItemStack item;
    private final double price;
    private final long listedAt;
    private final long expiresAt;

    public AHItem(UUID seller, String sellerName, ItemStack item, double price, long duration) {
        this.id = UUID.randomUUID();
        this.seller = seller;
        this.sellerName = sellerName;
        this.item = item.clone();
        this.price = price;
        this.listedAt = System.currentTimeMillis();
        this.expiresAt = listedAt + duration;
    }

    public UUID getId() { return id; }
    public UUID getSeller() { return seller; }
    public String getSellerName() { return sellerName; }
    public ItemStack getItem() { return item; }
    public double getPrice() { return price; }
    public long getExpiresAt() { return expiresAt; }
}
