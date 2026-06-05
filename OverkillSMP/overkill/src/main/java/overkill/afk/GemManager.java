package overkill.afk;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class GemManager {

    private final Map<Player, Integer> gems = new HashMap<>();

    public int getGems(Player p) {
        return gems.getOrDefault(p, 0);
    }

    public void addGems(Player p, int amount) {
        gems.put(p, getGems(p) + amount);
    }
}
