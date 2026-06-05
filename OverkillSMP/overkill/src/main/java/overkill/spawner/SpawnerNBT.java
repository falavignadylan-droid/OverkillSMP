package overkill.spawner;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;
import overkill.Main;

public class SpawnerNBT {

    public static NamespacedKey KEY;
    public static PersistentDataType<String, String> TYPE = PersistentDataType.STRING;

    public static void init(Main plugin) {
        KEY = new NamespacedKey(plugin, "spawner_type");
    }
}
