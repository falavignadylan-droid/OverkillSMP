package overkill.afk;

import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class AFKManager {

    private final Set<Player> afkPlayers = new HashSet<>();

    public void setAFK(Player p, boolean afk) {
        if (afk) afkPlayers.add(p);
        else afkPlayers.remove(p);
    }

    public boolean isAFK(Player p) {
        return afkPlayers.contains(p);
    }

    public Set<Player> getAFKPlayers() {
        return afkPlayers;
    }
}
