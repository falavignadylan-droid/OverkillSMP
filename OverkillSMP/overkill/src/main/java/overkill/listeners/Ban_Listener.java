package overkill.listeners;

import overkill.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class BanListener implements Listener {

    private final Main plugin;

    public BanListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {

        String name = e.getPlayer().getName();

        if (!plugin.getConfig().contains("bans." + name)) return;

        long expire = plugin.getConfig().getLong("bans." + name + ".expire");
        String cause = plugin.getConfig().getString("bans." + name + ".cause");

        long now = System.currentTimeMillis();

        if (now >= expire) {
            plugin.getConfig().set("bans." + name, null);
            plugin.saveConfig();
            return;
        }

        long remainingMs = expire - now;
        long remainingDays = remainingMs / (24L * 60L * 60L * 1000L);

        e.disallow(PlayerLoginEvent.Result.KICK_BANNED,
                "§cYou are banned!\n" +
                "§7Reason: §f" + cause + "\n" +
                "§7Remaining days: §f" + remainingDays + "\n\n" +
                "§7You can appeal the ban on:\n§bdiscord.gg/overkill");
    }
}
