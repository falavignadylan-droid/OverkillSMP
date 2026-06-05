package overkill.afk;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AFKTask extends BukkitRunnable {

    private final AFKManager manager;
    private final GemManager gemManager;

    public AFKTask(AFKManager manager, GemManager gemManager) {
        this.manager = manager;
        this.gemManager = gemManager;
    }

    @Override
    public void run() {

        for (Player p : manager.getAFKPlayers()) {

            // Particelle arancioni
            p.getWorld().spawnParticle(
                    Particle.REDSTONE,
                    p.getLocation().add(0, 1, 0),
                    20,
                    0.5, 0.5, 0.5,
                    new Particle.DustOptions(org.bukkit.Color.ORANGE, 1)
            );

            // Ogni 60 tick * 20 = 1200 tick = 1 minuto
            gemManager.addGems(p, 1);
            p.sendMessage("§6+1 Gem §7(earned while AFK)");
        }
    }
}
