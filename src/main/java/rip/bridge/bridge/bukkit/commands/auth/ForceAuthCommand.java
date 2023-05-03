package rip.bridge.bridge.bukkit.commands.auth;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import rip.bridge.bridge.bukkit.Bridge;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

public class ForceAuthCommand {

    @Command(names = {"forceauth"}, permission = "op", description = "Forcefully authenticate a player", hidden = true)
    public static void forceAuth(CommandSender sender, @Param(name = "player") Player player) {
        player.removeMetadata("Locked", Bridge.getInstance());
        player.setMetadata("ForceAuth", new FixedMetadataValue(Bridge.getInstance(), true));
        sender.sendMessage(ChatColor.YELLOW + player.getName() + " has been forcefully authenticated.");
    }

}
