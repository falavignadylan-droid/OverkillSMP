package overkill.scoreboard;

import overkill.utils.PriceFormatter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class MoneyScoreboardTask {

    public static void update(Player p, double money, int kills) {

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();

        Objective obj = board.registerNewObjective("overkill", "dummy", "§a§lOVERKILL SMP");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        obj.getScore("§f").setScore(6);
        obj.getScore("§aMoney: §e" + PriceFormatter.format(money) + "$").setScore(5);
        obj.getScore("§f ").setScore(4);
        obj.getScore("§aKills: §c" + kills).setScore(3);
        obj.getScore("§f  ").setScore(2);

        p.setScoreboard(board);
    }
}
