package rip.bridge.bridge.bukkit.commands.filter;

import rip.bridge.bridge.bukkit.parameters.packets.filter.FilterDeletePacket;
import rip.bridge.bridge.global.filter.Filter;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;
import rip.bridge.qlib.xpacket.FrozenXPacketHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class FilterDeleteCommand {

    @Command(names = "filter delete", permission = "bridge.filter", description = "Delete a filter", hidden = true)
    public static void delete(CommandSender sender, @Param(name = "filter", wildcard = true) Filter filter) {
        filter.delete();
        FrozenXPacketHandler.sendToAll(new FilterDeletePacket(filter, Bukkit.getServerName()));
        sender.sendMessage(ChatColor.GREEN + "Successfully deleted the filter.");
    }
}
