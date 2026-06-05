package overkill.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import overkill.afk.AFKManager;

public class AFKListener implements Listener {

    private final AFKManager manager;

    public AFKListener(AFKManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {

        if (!manager.isAFK(e.getPlayer())) return;

        // Se si muove di almeno 1 blocco
        if (e.getFrom().distance(e.getTo()) > 0.1) {
            manager.setAFK(e.getPlayer(), false);
            e.getPlayer().sendMessage("§cYou moved. AFK disabled.");
        }
    }
}
