package overkill.spawner;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.Inventory;

public class SpawnerData {

    private final Location location;
    private final SpawnerType type;
    private int stack;
    private final Inventory inventory;

    public SpawnerData(Location location, SpawnerType type, int stack) {
        this.location = location;
        this.type = type;
        this.stack = stack;
        this.inventory = Bukkit.createInventory(null, 54, "§eSpawner Storage");
    }

    public Location getLocation() { return location; }
    public SpawnerType getType() { return type; }
    public int getStack() { return stack; }
    public void addStack(int amount) { stack += amount; }
    public Inventory getInventory() { return inventory; }
}
