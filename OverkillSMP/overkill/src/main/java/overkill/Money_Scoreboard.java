package your.plugin.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class MoneyScoreboard {

    public static void update(Player p, double money, int kills) {

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();

        Objective obj = board.registerNewObjective("overkill", "dummy", "§a§lOVERKILL SMP");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        int online = Bukkit.getOnlinePlayers().size();

        obj.getScore("§f").setScore(5);
        obj.getScore("§bOnline: §e" + online).setScore(4);
        obj.getScore("§cKills: §e" + kills).setScore(3);
        obj.getScore("§aMoney: §e" + money + "$").setScore(2);
        obj.getScore("§f ").setScore(1);

        p.setScoreboard(board);
    }
}
