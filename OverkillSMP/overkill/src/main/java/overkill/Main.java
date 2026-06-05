package overkill;

import org.bukkit.plugin.java.JavaPlugin;

// -------------------------
// BAN SYSTEM
// -------------------------
import overkill.ban.BanCommand;
import overkill.ban.UnbanCommand;
import overkill.ban.BanListener;

// -------------------------
// RTP SYSTEM
// -------------------------
import overkill.rtp.RTPCommand;

// -------------------------
// SCOREBOARD SYSTEM
// -------------------------
import overkill.scoreboard.ScoreboardTask;

// -------------------------
// AUCTION HOUSE SYSTEM
// -------------------------
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


import java.io.File;

public class Main extends JavaPlugin {

    // AH SYSTEM
    private AHStorage ahStorage;
    private AHManager ahManager;
    private AHGUI ahGUI;
    private AHSellGUI ahSellGUI;

    @Override
    public void onEnable() {

        ConfirmBuyGUI confirmBuyGUI = new ConfirmBuyGUI();
        getServer().getPluginManager().registerEvents(new BuyListener(ahManager, confirmBuyGUI), this);


        // Create plugin folder
        if (!getDataFolder().exists()) getDataFolder().mkdirs();

        // -------------------------
        // BAN SYSTEM
        // -------------------------
        getCommand("ban").setExecutor(new BanCommand(this));
        getCommand("unban").setExecutor(new UnbanCommand(this));
        getServer().getPluginManager().registerEvents(new BanListener(this), this);

        // -------------------------
        // RTP SYSTEM
        // -------------------------
        getCommand("rtp").setExecutor(new RTPCommand());

        // -------------------------
        // SCOREBOARD SYSTEM
        // -------------------------
        new ScoreboardTask().runTaskTimer(this, 20L, 20L);

        // -------------------------
        // AUCTION HOUSE SYSTEM
        // -------------------------
        ahStorage = new AHStorage(new File(getDataFolder(), "auction.json"));
        ahManager = new AHManager(ahStorage);
        ahGUI = new AHGUI(ahManager);
        ahSellGUI = new AHSellGUI();

        getCommand("ah").setExecutor(new AHCommand(ahGUI));
        getServer().getPluginManager().registerEvents(new AHSellListener(ahManager, ahSellGUI), this);

        getLogger().info("Overkill SMP Plugin loaded successfully!");
    }

    // GETTERS (optional)
    public AHManager getAHManager() { return ahManager; }
    public AHGUI getAHGUI() { return ahGUI; }
    public AHSellGUI getAHSellGUI() { return ahSellGUI; }
}
