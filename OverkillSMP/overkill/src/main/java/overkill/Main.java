package your.plugin; // <-- cambia con il tuo package

import org.bukkit.plugin.java.JavaPlugin;

// BAN SYSTEM
import your.plugin.ban.BanCommand;
import your.plugin.ban.UnbanCommand;
import your.plugin.ban.BanListener;

// SELL SYSTEM
import your.plugin.sell.SellCommand;
import your.plugin.sell.SellGUI;
import your.plugin.sell.SellListener;

// SCOREBOARD SYSTEM
import your.plugin.scoreboard.ScoreboardTask;
import your.plugin.scoreboard.MoneyScoreboard;

// SPEC SYSTEM
import your.plugin.spec.SpecCommand;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        saveDefaultConfig();

        // ----- RTP------//
        getCommand("rtp").setExecutor(new RTPCommand());


        // -------------------------
        // SELL SYSTEM
        // -------------------------
        SellGUI sellGUI = new SellGUI();
        getCommand("sell").setExecutor(new SellCommand(sellGUI));
        getServer().getPluginManager().registerEvents(new SellListener(getConfig()), this);

        // -------------------------
        // BAN SYSTEM
        // -------------------------
        getCommand("ban").setExecutor(new BanCommand(this));
        getCommand("unban").setExecutor(new UnbanCommand(this));
        getServer().getPluginManager().registerEvents(new BanListener(this), this);

        // -------------------------
        // SPEC SYSTEM
        // -------------------------
        getCommand("spec").setExecutor(new SpecCommand());

        // -------------------------
        // SCOREBOARD SYSTEM
        // -------------------------
        getServer().getPluginManager().registerEvents(this, this);
        new ScoreboardTask().runTaskTimer(this, 20L, 20L);

        getLogger().info("Overkill SMP Plugin attivato!");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        var econ = getServer().getServicesManager()
                .getRegistration(net.milkbowl.vault.economy.Economy.class)
                .getProvider();

        double money = econ.getBalance(e.getPlayer());
        int kills = e.getPlayer().getStatistic(org.bukkit.Statistic.PLAYER_KILLS);

        MoneyScoreboard.update(e.getPlayer(), money, kills);
    }
}
