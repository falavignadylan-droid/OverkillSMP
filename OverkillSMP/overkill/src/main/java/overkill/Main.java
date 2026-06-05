package overkill;

import org.bukkit.plugin.java.JavaPlugin;

// BAN
import overkill.commands.BanCommand;
import overkill.commands.UnbanCommand;
import overkill.listeners.BanListener;

// RTP
import overkill.commands.RTPCommand;

// SPEC
import overkill.commands.SpecCommand;
import overkill.listeners.SpecListener;

// AFK
import overkill.afk.AFKCommand;
import overkill.listeners.AFKListener;

// SCOREBOARD
import overkill.scoreboard.MoneyScoreboard;
import overkill.scoreboard.MoneyScoreboardTask;

// SELL
import overkill.sell.SellCommand;
import overkill.listeners.SellListener;

// AH (Auction House)
import overkill.ah.AHCommand;
import overkill.ah.AHManager;
import overkill.ah.AHStorage;
import overkill.ah.AHSellGUI;
import overkill.ah.AHSellListener;
import overkill.listeners.BuyListener;

// SPAWNER
import overkill.spawner.SpawnerListener;
import overkill.spawner.SpawnerManager;

// GEMSHOP
import overkill.gemshop.GemShopCommand;
import overkill.gemshop.GemShopListener;

// UTILS
import overkill.utils.PriceFormatter;

public class Main extends JavaPlugin {

    private AHManager ahManager;
    private SpawnerManager spawnerManager;

    @Override
    public void onEnable() {

        // Auction House
        AHStorage storage = new AHStorage();
        this.ahManager = new AHManager(storage);

        // Spawner System
        this.spawnerManager = new SpawnerManager();

        // Commands
        getCommand("ban").setExecutor(new BanCommand(this));
        getCommand("unban").setExecutor(new UnbanCommand(this));
        getCommand("rtp").setExecutor(new RTPCommand());
        getCommand("spec").setExecutor(new SpecCommand());
        getCommand("sell").setExecutor(new SellCommand());
        getCommand("ah").setExecutor(new AHCommand(ahManager));
        getCommand("gemshop").setExecutor(new GemShopCommand());
        getCommand("afk").setExecutor(new AFKCommand());

        // Listeners
        getServer().getPluginManager().registerEvents(new BanListener(), this);
        getServer().getPluginManager().registerEvents(new SpecListener(), this);
        getServer().getPluginManager().registerEvents(new SellListener(), this);
        getServer().getPluginManager().registerEvents(new AHSellListener(ahManager), this);
        getServer().getPluginManager().registerEvents(new BuyListener(ahManager), this);
        getServer().getPluginManager().registerEvents(new SpawnerListener(spawnerManager), this);
        getServer().getPluginManager().registerEvents(new GemShopListener(), this);
        getServer().getPluginManager().registerEvents(new AFKListener(), this);

        // Scoreboard
        new MoneyScoreboardTask().runTaskTimer(this, 20L, 20L);

        getLogger().info("Overkill SMP activated.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Overkill SMP disactivated");
    }

    public AHManager getAHManager() {
        return ahManager;
    }

    public SpawnerManager getSpawnerManager() {
        return spawnerManager;
    }
}
