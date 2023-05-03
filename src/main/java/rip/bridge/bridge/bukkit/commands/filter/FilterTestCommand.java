package rip.bridge.bridge.bukkit.commands.filter;

import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.global.filter.Filter;
import rip.bridge.bridge.global.filter.FilterAction;
import rip.bridge.bridge.global.util.TimeUtil;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class FilterTestCommand {

    @Command(names = "filter test", permission = "bridge.filter", description = "Test a filter", hidden = true)
    public static void test(CommandSender sender, @Param(name = "message", wildcard = true) String message) {
        Filter filter = BridgeGlobal.getFilterHandler().isViolatingFilter(message);
        if(filter == null) {
            sender.sendMessage(ChatColor.GREEN + "This message is not filtered.");
            return;
        }

        sender.sendMessage(ChatColor.RED + "Your message currently flags for the filter: " + filter.getPattern());
        if(filter.getFilterAction() == FilterAction.MUTE) sender.sendMessage(ChatColor.RED + "This message would of caused you to be muted for " + TimeUtil.millisToTimer(filter.getMuteTime()));
    }
}
