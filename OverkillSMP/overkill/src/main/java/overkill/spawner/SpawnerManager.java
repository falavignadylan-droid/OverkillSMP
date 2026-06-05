package overkill.spawner;

import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

public class SpawnerManager {

    private final Map<String, SpawnerData> spawners = new HashMap<>();

    private String key(Location loc) {
        return loc.getWorld().getName() + ":" + loc.getBlockX() + ":" + loc.getBlockY() + ":" + loc.getBlockZ();
    }

    public SpawnerData get(Location loc) {
        return spawners.get(key(loc));
    }

    public void add(SpawnerData data) {
        spawners.put(key(data.getLocation()), data);
    }

    public void remove(Location loc) {
        spawners.remove(key(loc));
    }

    public Map<String, SpawnerData> getAll() {
        return spawners;
    }
}
