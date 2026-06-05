package overkill.spec;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class SpecCommand implements CommandExecutor {

    private final HashMap<UUID, Location> lastLocation = new HashMap<>();
    private final HashMap<UUID, GameMode> lastGamemode = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player p)) return true;

        // /spec off
        if (args.length == 1 && args[0].equalsIgnoreCase("off")) {

            if (!lastGamemode.containsKey(p.getUniqueId())) {
                p.sendMessage("§cYou are not in mode spectator.");
                return true;
            }

            p.setGameMode(lastGamemode.get(p.getUniqueId()));
            p.teleport(lastLocation.get(p.getUniqueId()));

            lastGamemode.remove(p.getUniqueId());
            lastLocation.remove(p.getUniqueId());

            p.sendMessage("§a You came back to the previous mode.");
            return true;
        }

        // /spec <player>
        if (args.length != 1) {
            p.sendMessage("§cUse: /spec <player> or /spec off");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            p.sendMessage("§cThat player is not online.");
            return true;
        }

        // Salva stato precedente
        lastLocation.put(p.getUniqueId(), p.getLocation());
        lastGamemode.put(p.getUniqueId(), p.getGameMode());

        // Spectator
        p.setGameMode(GameMode.SPECTATOR);
        p.teleport(target);

        p.sendMessage("§aYou are spectating §e" + target.getName());
        return true;
    }
}
