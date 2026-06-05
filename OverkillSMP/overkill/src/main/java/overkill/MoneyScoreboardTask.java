package your.plugin.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ScoreboardTask extends BukkitRunnable {

    @Override
    public void run() {
        var econ = Bukkit.getServer().getServicesManager()
                .getRegistration(net.milkbowl.vault.economy.Economy.class)
                .getProvider();

        for (Player p : Bukkit.getOnlinePlayers()) {

            double money = econ.getBalance(p);
            int kills = p.getStatistic(org.bukkit.Statistic.PLAYER_KILLS);

            MoneyScoreboard.update(p, money, kills);
        }
    }
}
