package your.plugin.spec;

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
                p.sendMessage("§cNon sei in modalità spectator.");
                return true;
            }

            p.setGameMode(lastGamemode.get(p.getUniqueId()));
            p.teleport(lastLocation.get(p.getUniqueId()));

            lastGamemode.remove(p.getUniqueId());
            lastLocation.remove(p.getUniqueId());

            p.sendMessage("§aSei tornato alla modalità precedente.");
            return true;
        }

        // /spec <player>
        if (args.length != 1) {
            p.sendMessage("§cUsa: /spec <player> oppure /spec off");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            p.sendMessage("§cQuel giocatore non è online.");
            return true;
        }

        // Salva stato precedente
        lastLocation.put(p.getUniqueId(), p.getLocation());
        lastGamemode.put(p.getUniqueId(), p.getGameMode());

        // Spectator
        p.setGameMode(GameMode.SPECTATOR);
        p.teleport(target);

        p.sendMessage("§aOra stai spettando §e" + target.getName());
        return true;
    }
}
