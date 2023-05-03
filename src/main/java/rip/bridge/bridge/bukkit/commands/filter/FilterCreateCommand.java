package rip.bridge.bridge.bukkit.commands.filter;

import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.bukkit.parameters.packets.filter.FilterCreatePacket;
import rip.bridge.bridge.global.filter.Filter;
import rip.bridge.bridge.global.filter.FilterType;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;
import rip.bridge.qlib.xpacket.FrozenXPacketHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class FilterCreateCommand {
    @Command(names = "filter create", permission = "bridge.filter", description = "Create a filter", hidden = true)
    public static void create(CommandSender sender, @Param(name = "filterType") FilterType filterType, @Param(name = "pattern", wildcard = true) String pattern) {
        Filter filter = BridgeGlobal.getFilterHandler().getFilter(pattern);
        if (filter != null) {
            sender.sendMessage(ChatColor.RED + "This filter already exists");
            return;
        }
        (filter = new Filter(filterType, pattern)).save();
        BridgeGlobal.getFilterHandler().addFilter(filter);
        FrozenXPacketHandler.sendToAll(new FilterCreatePacket(filter, Bukkit.getServerName()));
        sender.sendMessage(ChatColor.GREEN + "Successfully created the filter.");
    }
}
