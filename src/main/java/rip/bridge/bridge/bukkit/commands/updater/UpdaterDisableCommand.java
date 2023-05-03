package rip.bridge.bridge.bukkit.commands.updater;

import rip.bridge.bridge.bukkit.util.PluginUtil;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class UpdaterDisableCommand {

    @Command(names = "updater disable", permission = "bridge.updater", hidden = true, description = "Disables a specific plugins")
    public static void disable(CommandSender sender, @Param(name = "plugin") Plugin plugin) {
        PluginUtil.disable(plugin);
        sender.sendMessage(ChatColor.GREEN + "Disabled " + plugin.getName() + ".");
    }

    @Command(names = "updater disable all", permission = "bridge.updater", hidden = true, description = "Disables all plugins")
    public static void disableall(CommandSender sender) {
        PluginUtil.disableAll();
        sender.sendMessage(ChatColor.GREEN + "Disabled all plugins.");
    }
}
