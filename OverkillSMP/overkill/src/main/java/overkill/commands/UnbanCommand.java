package overkill.commands;

import y erkill.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UnbanCommand implements CommandExecutor {

    private final Main plugin;

    public UnbanCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length != 1) {
            sender.sendMessage("§cUsage: /unban <player>");
            return true;
        }

        String targetName = args[0];

        if (!plugin.getConfig().contains("bans." + targetName)) {
            sender.sendMessage("§cThis player is not banned.");
            return true;
        }

        plugin.getConfig().set("bans." + targetName, null);
        plugin.saveConfig();

        sender.sendMessage("§aPlayer " + targetName + " has been unbanned.");
        return true;
    }
}
