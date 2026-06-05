package me.overkill;

import me.overkill.commands.BanCommand;
import me.overkill.commands.UnbanCommand;
import me.overkill.listeners.BanListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        // Register commands
        getCommand("ban").setExecutor(new BanCommand(this));
        getCommand("unban").setExecutor(new UnbanCommand(this));

        // Register listeners
        getServer().getPluginManager().registerEvents(new BanListener(this), this);

        getLogger().info("Overkill SMP ban system enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Overkill SMP ban system disabled.");
    }
}
