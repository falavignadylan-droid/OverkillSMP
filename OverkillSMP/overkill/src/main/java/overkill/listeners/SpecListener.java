package overkill.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.GameMode;

public class SpecListener implements Listener {

    @EventHandler
    public void onGamemodeChange(PlayerGameModeChangeEvent e) {
        // Blocca i player normali dal mettersi in spectator
        if (e.getNewGameMode() == GameMode.SPECTATOR && !e.getPlayer().hasPermission("overkill.spec")) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("§cNon puoi entrare in spectator.");
        }
    }
}
