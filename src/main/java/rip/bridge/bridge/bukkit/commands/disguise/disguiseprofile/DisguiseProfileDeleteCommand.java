package rip.bridge.bridge.bukkit.commands.disguise.disguiseprofile;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

public class DisguiseProfileDeleteCommand {

    @Command(names = {"disguiseprofile delete", "disguiseprofile remove"}, permission = "bridge.disguise.admin", description = "Remove disguise profiles", hidden = true)
    public static void disguise(Player player, @Param(name = "name") String name) {
        boolean removed = BridgeGlobal.getDisguiseManager().removeProfile(name);

        if (!removed) {
            player.sendMessage(ChatColor.RED + "Failed to remove disguise profile with name " + name + '.');
            return;
        }

        player.sendMessage(ChatColor.GREEN + "You have deleted a disguise profile with name " + ChatColor.RESET + name + ChatColor.GREEN + '.');
    }
}
