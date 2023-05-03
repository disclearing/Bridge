package rip.bridge.bridge.bukkit.commands.updater.group;

import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.bukkit.Bridge;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class UpdaterGroupAddCommand {

    @Command(names = "updater group add", permission = "bridge.updater", hidden = true, description = "Add a group to the update group")
    public static void groupadd(CommandSender sender, @Param(name = "group") String group) {
        List<String> configGroups = Bridge.getInstance().getConfig().getStringList("updaterGroups");
        if (BridgeGlobal.getUpdaterGroups().stream().anyMatch(s -> s.equalsIgnoreCase(group))) {
            sender.sendMessage(ChatColor.RED + "This group is already in the update list.");
            return;
        }
        BridgeGlobal.addUpdaterGroup(group);
        configGroups.add(group);
        Bridge.getInstance().getConfig().set("updaterGroups", configGroups);
        Bridge.getInstance().saveConfig();
        sender.sendMessage(ChatColor.GREEN + "Successfully added the group \"" + group + "\" to the update groups.");
    }
}
