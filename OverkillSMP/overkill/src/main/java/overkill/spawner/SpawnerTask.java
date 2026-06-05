package overkill.spawner;

import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnerTask extends BukkitRunnable {

    private final SpawnerManager manager;

    public SpawnerTask(SpawnerManager manager) {
        this.manager = manager;
    }

    @Override
    public void run() {

        manager.getAll().values().forEach(data -> {

            int stack = data.getStack();
            ItemStack[] loot = data.getType().getLoot();

            for (ItemStack item : loot) {

                ItemStack clone = item.clone();
                clone.setAmount(item.getAmount() * stack);

                data.getInventory().addItem(clone);
            }
        });
    }
}
