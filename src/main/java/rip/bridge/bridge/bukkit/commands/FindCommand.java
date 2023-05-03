package rip.bridge.bridge.bukkit.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.global.profile.Profile;
import rip.bridge.bridge.global.status.BridgeServer;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

public class FindCommand {

    @Command(names = { "find" }, permission = "bridge.find", description = "See the server a player is currently playing on", async = true)
    public static void find(CommandSender sender, @Param(name = "player", extraData = "get") Profile profile) {
        findresponse(sender, profile);
    }

    public static void findresponse(CommandSender sender, Profile profile) {
        BridgeServer server = BridgeGlobal.getServerHandler().findPlayerServer(profile.getUuid());
        BridgeServer proxy = BridgeGlobal.getServerHandler().findPlayerProxy(profile.getUuid());
        if (server == null) {
            sender.sendMessage(ChatColor.RED + (profile.getDisguise() != null ? profile.getDisguise().getDisguiseName() : profile.getUsername()) + " is currently not on the network.");
            return;
        }

        sender.sendMessage(ChatColor.YELLOW + (profile.getDisguise() != null ? profile.getDisguise().getDisguiseName() : profile.getUsername()) +  " is on " + ChatColor.GREEN + server.getName() + (proxy != null ? ChatColor.YELLOW + " (" + ChatColor.GREEN + proxy.getName() + " Proxy" + ChatColor.YELLOW + ")" : ""));

    }
}
