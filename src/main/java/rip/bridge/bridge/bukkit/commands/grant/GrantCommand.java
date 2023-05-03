package rip.bridge.bridge.bukkit.commands.grant;

import rip.bridge.bridge.bukkit.commands.grant.menu.grant.RanksMenu;
import rip.bridge.bridge.global.profile.Profile;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;
import org.bukkit.entity.Player;

public class GrantCommand {

    @Command(names = "grant", permission = "bridge.grant", description = "Add a grant to an player's account", async = true)
    public static void grantCmd(Player p, @Param(name = "player") Profile target) {
        new RanksMenu(target.getUsername(), target.getUuid()).openMenu(p);
    }
}
