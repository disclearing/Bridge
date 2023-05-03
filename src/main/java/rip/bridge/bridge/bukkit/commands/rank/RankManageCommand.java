package rip.bridge.bridge.bukkit.commands.rank;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.bukkit.commands.rank.menu.RankMenu;
import rip.bridge.bridge.global.ranks.Rank;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

public class RankManageCommand {

    @Command(names = {"rank manage"}, permission = "bridge.rank", description = "Manage a rank", hidden = true, async = true)
    public static void RankMangeCmd(CommandSender s, @Param(name = "rank") String name) {
        Rank r = BridgeGlobal.getRankHandler().getRankByName(name);
        if (r == null) {
            s.sendMessage("§cThere is no such rank with the name \"" + name + "\".");
            return;
        }

        new RankMenu(r).openMenu((Player) s);
    }
}
