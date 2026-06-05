package me.overkill.commands;

import me.overkill.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanCommand implements CommandExecutor {

    private final Main plugin;

    public BanCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length < 3) {
            sender.sendMessage("§cUsage: /ban <player> <days> <cause>");
            return true;
        }

        String targetName = args[0];
        Player target = Bukkit.getPlayer(targetName);

        int days;
        try {
            days = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage("§cDays must be a number.");
            return true;
        }

        String cause = String.join(" ", args).replace(args[0], "").replace(args[1], "").trim();

        long expire = System.currentTimeMillis() + (days * 24L * 60L * 60L * 1000L);

        plugin.getConfig().set("bans." + targetName + ".cause", cause);
        plugin.getConfig().set("bans." + targetName + ".expire", expire);
        plugin.saveConfig();

        if (target != null) {
            target.kickPlayer("§cYou are banned!\n§7Reason: §f" + cause +
                    "\n§7Days: §f" + days +
                    "\n\n§7You can appeal the ban on:\n§bdiscord.gg/overkill");
        }

        sender.sendMessage("§aPlayer " + targetName + " has been banned.");
        return true;
    }
}
