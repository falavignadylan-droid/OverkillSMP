package overkill.afk;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AFKCommand implements CommandExecutor {

    private final AFKManager manager;

    public AFKCommand(AFKManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player p)) return true;

        if (manager.isAFK(p)) {
            manager.setAFK(p, false);
            p.sendMessage("§cYou are no longer AFK.");
        } else {
            manager.setAFK(p, true);
            p.sendMessage("§eYou are now AFK.");
        }

        return true;
    }
}
