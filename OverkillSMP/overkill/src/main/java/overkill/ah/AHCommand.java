package overkill.ah;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class AHCommand implements CommandExecutor {

    private final AHGUI gui;

    public AHCommand(AHGUI gui) {
        this.gui = gui;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) return true;

        p.openInventory(gui.getPage(p, 1));
        return true;
    }
}
