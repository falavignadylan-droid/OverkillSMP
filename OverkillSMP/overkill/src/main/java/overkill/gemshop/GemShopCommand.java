package overkill.gemshop;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GemShopCommand implements CommandExecutor {

    private final GemShopGUI gui;

    public GemShopCommand(GemShopGUI gui) {
        this.gui = gui;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player p)) return true;

        p.openInventory(gui.get());
        return true;
    }
}
