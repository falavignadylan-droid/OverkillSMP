package overkill.rtp;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class RTPCommand implements CommandExecutor {

    private final int radius = 50000; // max RTP distance

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player p)) return true;

        startCountdown(p);
        return true;
    }

    private void startCountdown(Player p) {

        p.sendMessage("§bPreparing teleport...");

        new BukkitRunnable() {

            int timer = 3;
            Location startLoc = p.getLocation().clone();

            @Override
            public void run() {

                // Cancel if player moves
                if (!p.getLocation().getBlock().equals(startLoc.getBlock())) {
                    p.sendMessage("§cTeleport cancelled (you moved).");
                    cancel();
                    return;
                }

                // Blue particles
                p.getWorld().spawnParticle(
                        Particle.SOUL_FIRE_FLAME,
                        p.getLocation().add(0, 1, 0),
                        30, 0.5, 1, 0.5, 0.01
                );

                if (timer == 0) {
                    cancel();
                    executeRTP(p);
                    return;
                }

                p.sendMessage("§bTeleporting in §e" + timer + "§b...");
                timer--;
            }

        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("OverkillSMP"), 0L, 20L);
    }

    private void executeRTP(Player p) {

        p.sendMessage("§aSearching for a safe location...");

        new BukkitRunnable() {
            @Override
            public void run() {

                Location safe = findSafeLocation(p.getWorld());

                if (safe == null) {
                    p.sendMessage("§cCould not find a safe location.");
                    return;
                }

                Bukkit.getScheduler().runTask(
                        Bukkit.getPluginManager().getPlugin("OverkillSMP"),
                        () -> {
                            p.teleport(safe);
                            p.sendMessage("§aTeleported!");
                        }
                );
            }
        }.runTaskAsynchronously(Bukkit.getPluginManager().getPlugin("OverkillSMP"));
    }

    private Location findSafeLocation(World world) {
        Random r = new Random();

        for (int i = 0; i < 50; i++) {
            int x = r.nextInt(radius * 2) - radius;
            int z = r.nextInt(radius * 2) - radius;

            int y = world.getHighestBlockYAt(x, z);
            Location loc = new Location(world, x + 0.5, y + 1, z + 0.5);

            Material block = world.getBlockAt(x, y, z).getType();

            if (isSafe(block)) {
                return loc;
            }
        }
        return null;
    }

    private boolean isSafe(Material m) {
        return m.isSolid() &&
                m != Material.LAVA &&
                m != Material.WATER &&
                m != Material.MAGMA_BLOCK &&
                m != Material.CACTUS &&
                m != Material.FIRE &&
                m != Material.CAMPFIRE &&
                m != Material.SOUL_FIRE;
    }
}
