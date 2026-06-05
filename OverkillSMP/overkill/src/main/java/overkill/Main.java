package overkill;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

// ===== BAN SYSTEM =====
import overkill.ban.BanCommand;
import overkill.ban.UnbanCommand;
import overkill.ban.BanListener;

// ===== RTP =====
import overkill.rtp.RTPCommand;

// ===== SPEC =====
import overkill.spec.SpecCommand;
import overkill.spec.SpecListener;

// ===== SCOREBOARD =====
import overkill.scoreboard.ScoreboardTask;

// ===== AUCTION HOUSE =====
import overkill.ah.AHStorage;
import overkill.ah.AHManager;
import overkill.ah.AHGUI;
import overkill.ah.AHCommand;
import overkill.ah.AHSellGUI;
import overkill.ah.AHSellListener;
import overkill.ah.ConfirmBuyGUI;
import overkill.ah.BuyListener;
import overkill.ah.SearchGUI;
import overkill.ah.SearchListener;

// ===== AFK + GEMS =====
import overkill.afk.AFKManager;
import overkill.afk.AFKCommand;
import overkill.afk.AFKListener;
import overkill.afk.AFKTask;
import overkill.afk.GemManager;

// ===== SPAWNER SYSTEM =====
import overkill.spawner.SpawnerManager;
import overkill.spawner.SpawnerGUI;
import overkill.spawner.SpawnerListener;
import overkill.spawner.SpawnerTask;
import overkill.spawner.SpawnerNBT;

// ===== GEMSHOP =====
import overkill.gemshop.GemShopGUI;
import overkill.gemshop.GemShopCommand;
import overkill.gemshop.GemShopListener;

public class Main extends JavaPlugin {

    // AH
    private AHStorage ahStorage;
    private AHManager ahManager;
    private AHGUI ahGUI;
    private AHSellGUI ahSellGUI;
    private ConfirmBuyGUI confirmBuyGUI;
    private SearchGUI searchGUI;

    // AFK + GEMS
    private AFKManager afkManager;
    private GemManager gemManager;

    // SPAWNER
    private SpawnerManager spawnerManager;
    private SpawnerGUI spawnerGUI;

    @Override
    public void onEnable() {

        if (!getDataFolder().exists()) getDataFolder().mkdirs();

        // ===== BAN =====
        getCommand("ban").setExecutor(new BanCommand(this));
        getCommand("unban").setExecutor(new UnbanCommand(this));
        getServer().getPluginManager().registerEvents(new BanListener(this), this);

        // ===== RTP =====
        getCommand("rtp").setExecutor(new RTPCommand());

        // ===== SPEC =====
        getCommand("spec").setExecutor(new SpecCommand());
        getServer().getPluginManager().registerEvents(new SpecListener(), this);

        // ===== AFK + GEMS =====
        afkManager = new AFKManager();
        gemManager = new GemManager();

        getCommand("afk").setExecutor(new AFKCommand(afkManager));
        getServer().getPluginManager().registerEvents(new AFKListener(afkManager), this);
        new AFKTask(afkManager, gemManager).runTaskTimer(this, 20L, 1200L); // 1 minuto

        // ===== SCOREBOARD =====
        new ScoreboardTask(this).runTaskTimer(this, 20L, 20L); // ogni secondo

        // ===== AUCTION HOUSE =====
        ahStorage = new AHStorage(new File(getDataFolder(), "auction.json"));
        ahManager = new AHManager(ahStorage, this); // se il tuo AHManager non usa Main, togli "this"
        ahGUI = new AHGUI(ahManager);
        ahSellGUI = new AHSellGUI();
        confirmBuyGUI = new ConfirmBuyGUI();
        searchGUI = new SearchGUI();

        getCommand("ah").setExecutor(new AHCommand(ahGUI, ahSellGUI, searchGUI));

        getServer().getPluginManager().registerEvents(new AHSellListener(ahManager, ahSellGUI), this);
        getServer().getPluginManager().registerEvents(new BuyListener(ahManager, confirmBuyGUI), this);
        getServer().getPluginManager().registerEvents(new SearchListener(ahManager, ahGUI, searchGUI), this);

        // ===== SPAWNER SYSTEM =====
        SpawnerNBT.init(this);
        spawnerManager = new SpawnerManager();
        spawnerGUI = new SpawnerGUI();

        getServer().getPluginManager().registerEvents(new SpawnerListener(spawnerManager, spawnerGUI), this);
        new SpawnerTask(spawnerManager).runTaskTimer(this, 20L, 1200L); // 1 minuto

        // ===== GEMSHOP =====
        GemShopGUI gemShopGUI = new GemShopGUI();
        getCommand("gemshop").setExecutor(new GemShopCommand(gemShopGUI));
        getServer().getPluginManager().registerEvents(new GemShopListener(gemManager), this);

        getLogger().info("Overkill SMP loaded with Ban, RTP, Spec, AH, AFK, Gems, Spawners, GemShop, Scoreboard.");
    }

    // ===== GETTERS =====

    public AHManager getAHManager() { return ahManager; }
    public AHGUI getAHGUI() { return ahGUI; }
    public AHSellGUI getAHSellGUI() { return ahSellGUI; }
    public ConfirmBuyGUI getConfirmBuyGUI() { return confirmBuyGUI; }
    public SearchGUI getSearchGUI() { return searchGUI; }

    public AFKManager getAFKManager() { return afkManager; }
    public GemManager getGemManager() { return gemManager; }

    public SpawnerManager getSpawnerManager() { return spawnerManager; }
    public SpawnerGUI getSpawnerGUI() { return spawnerGUI; }
}
