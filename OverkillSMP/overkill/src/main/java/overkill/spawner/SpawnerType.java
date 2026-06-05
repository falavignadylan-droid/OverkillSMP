package overkill.spawner;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum SpawnerType {

    SKELETON(
            new ItemStack[]{
                    new ItemStack(Material.BONE, 5),
                    new ItemStack(Material.ARROW, 2)
            }
    ),

    CREEPER(
            new ItemStack[]{
                    new ItemStack(Material.GUNPOWDER, 4)
            }
    ),

    ZOMBIE(
            new ItemStack[]{
                    new ItemStack(Material.IRON_INGOT, 1),
                    new ItemStack(Material.ROTTEN_FLESH, 1)
            }
    ),

    ENDERMAN(
            new ItemStack[]{
                    new ItemStack(Material.ENDER_PEARL, 3)
            }
    ),

    COW(
            new ItemStack[]{
                    new ItemStack(Material.LEATHER, 2),
                    new ItemStack(Material.BEEF, 5)
            }
    );

    private final ItemStack[] loot;

    SpawnerType(ItemStack[] loot) {
        this.loot = loot;
    }

    public ItemStack[] getLoot() {
        return loot;
    }
}
