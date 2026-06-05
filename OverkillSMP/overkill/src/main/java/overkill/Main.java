package overkill;

import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;

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
import overkill.afk.AFKManager;
import overkill.listeners.AFKListener;

// SCOREBOARD
import overkill.scoreboard.MoneyScoreboardTask;

// SELL
import overkill.sell.SellCommand;
import overkill.sell.SellGUI;
import overkill.listeners.SellListener;

// AH (Auction House)
import overkill.ah.AHCommand;
import overkill.ah.AHManager;
import overkill.ah.AHStorage;
import overkill.ah.AHSellGUI;
import overkill.ah.AHSellListener;
import overkill.ah.ConfirmBuyGUI;
import overkill.listeners.BuyListener;

// SPAWNER
import overkill.listeners.SpawnerListener;
import overkill.spawner.SpawnerManager;

// GEMSHOP
import overkill.gemshop.GemShopCommand;
import overkill.gemshop.GemShopGUI;
import overkill.listeners.GemShopListener;

public class Main extends JavaPlugin {

    private AHManager ahManager;
    private SpawnerManager spawnerManager;
    private AFKManager afkManager;

    @Override
    public void onEnable() {

        // -------------------------
        // AH STORAGE
        // -------------------------
        AHStorage storage = new AHStorage(new File(getDataFolder(), "ah.yml"));
        this.ahManager = new AHManager(storage);

        // -------------------------
        // SPAWNER SYSTEM
        // -------------------------
        this.spawnerManager = new SpawnerManager();

        // -------------------------
        // AFK SYSTEM
        // -------------------------
        this.afkManager = new AFKManager();

        // -------------------------
        // GUI OBJECTS
        // -------------------------
        SellGUI sellGUI = new SellGUI();
        GemShopGUI gemGUI = new GemShopGUI();
        ConfirmBuyGUI confirmGUI = new ConfirmBuyGUI();
        AHSellGUI ahSellGUI = new AHSellGUI();

        // -------------------------
        // COMMANDS
        // -------------------------
        getCommand("ban").setExecutor(new BanCommand(this));
        getCommand("unban").setExecutor(new UnbanCommand(this));
        getCommand("rtp").setExecutor(new RTPCommand());
        getCommand("spec").setExecutor(new SpecCommand());
        getCommand("sell").setExecutor(new SellCommand(sellGUI));
        getCommand("ah").setExecutor(new AHCommand(ahManager));
        getCommand("gemshop").setExecutor(new GemShopCommand(gemGUI));
        getCommand("afk").setExecutor(new AFKCommand(afkManager));

        // -------------------------
        // LISTENERS
        // -------------------------
        getServer().getPluginManager().registerEvents(new BanListener(this), this);
        getServer().getPluginManager().registerEvents(new SpecListener(), this);
        getServer().getPluginManager().registerEvents(new SellListener(getConfig()), this);
        getServer().getPluginManager().registerEvents(new AHSellListener(ahManager), this);
        getServer().getPluginManager().registerEvents(new BuyListener(ahManager, confirmGUI), this);
        getServer().getPluginManager().registerEvents(new SpawnerListener(spawnerManager), this);
        getServer().getPluginManager().registerEvents(new GemShopListener(), this);
        getServer().getPluginManager().registerEvents(new AFKListener(afkManager), this);

        // -------------------------
        // SCOREBOARD
        // -------------------------
        new MoneyScoreboardTask().runTaskTimer(this, 20L, 20L);

        getLogger().info("Overkill SMP activated.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Overkill SMP disabled.");
    }

    public AHManager getAHManager() {
        return ahManager;
    }

    public SpawnerManager getSpawnerManager() {
        return spawnerManager;
    }

    public AFKManager getAFKManager() {
        return afkManager;
    }
}
