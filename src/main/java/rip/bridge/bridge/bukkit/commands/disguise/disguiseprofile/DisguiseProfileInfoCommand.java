package rip.bridge.bridge.bukkit.commands.disguise.disguiseprofile;

import org.bukkit.command.CommandSender;
import rip.bridge.bridge.bukkit.BukkitAPI;
import rip.bridge.bridge.global.disguise.DisguiseProfile;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

public class DisguiseProfileInfoCommand {

    @Command(names = {"disguiseprofile info"}, permission = "bridge.disguise.admin", description = "Get information about a disguise profile", hidden = true)
    public static void disguiseprofileinfo(CommandSender s, @Param(name = "name") DisguiseProfile disguiseProfile) {
        s.sendMessage(BukkitAPI.LINE);
        s.sendMessage(disguiseProfile.getDisplayName() + " Disguise Profile §7❘ §fInformation");
        s.sendMessage(BukkitAPI.LINE);
        s.sendMessage("§6Name: §f" + disguiseProfile.getName() + " §7(" + net.md_5.bungee.api.ChatColor.stripColor(disguiseProfile.getDisplayName().replaceAll("§", "&")) + ")");
        s.sendMessage("§6Display Name: §f" + disguiseProfile.getDisplayName() + " §7(" + net.md_5.bungee.api.ChatColor.stripColor(disguiseProfile.getDisplayName().replaceAll("§", "&")) + ")");
        s.sendMessage("§6Skin: §f" + disguiseProfile.getSkinName());
        s.sendMessage("");
    }
}
