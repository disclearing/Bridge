package rip.bridge.bridge.bukkit.commands.updater;

import rip.bridge.bridge.bukkit.util.PluginUtil;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;
import org.bukkit.command.CommandSender;

public class UpdaterLoadCommand {

    @Command(names = "updater load", permission = "bridge.updater", hidden = true, description = "Loads a plugin")
    public static void load(CommandSender sender, @Param(name = "fileName", wildcard = true) String fileName) {
        sender.sendMessage(PluginUtil.load(fileName));
    }
}
