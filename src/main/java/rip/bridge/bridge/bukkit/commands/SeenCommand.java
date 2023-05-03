package rip.bridge.bridge.bukkit.commands;

import mkremins.fanciful.FancyMessage;
import net.minecraft.util.org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import rip.bridge.bridge.bukkit.BukkitAPI;
import rip.bridge.bridge.global.profile.Profile;
import rip.bridge.bridge.global.util.TimeUtil;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;
import rip.bridge.qlib.util.TimeUtils;

import java.util.Date;

public class SeenCommand {

    @Command(names = {"lastseen", "seen"}, permission = "bridge.seen", description = "See when a player was last on", async = true)
    public static void lastseen(CommandSender sender, @Param(name = "player", extraData = "get") Profile profile) {

        if (profile.isOnline()) {
            sender.sendMessage(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + StringUtils.repeat('-', 36));
            sender.sendMessage((ChatColor.RED + profile.getUsername() + ChatColor.YELLOW + " is currently on " + ChatColor.RED + profile.getConnectedServer() + ChatColor.YELLOW + "."));
            sender.sendMessage(ChatColor.GOLD + "Check out their info on the website: bridge.rip/u/" + profile.getUsername());
            sender.sendMessage(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + StringUtils.repeat('-', 36));
        } else {
            sender.sendMessage(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + StringUtils.repeat('-', 36));
            sender.sendMessage((ChatColor.RED + profile.getUsername() + ChatColor.YELLOW + " is currently " + ChatColor.RED + "offline" + ChatColor.YELLOW + "."));
            sender.sendMessage(ChatColor.YELLOW + "Last seen at " + ChatColor.RED + TimeUtils.formatIntoCalendarString(new Date(profile.getLastJoined())) + ChatColor.YELLOW + " on " + ChatColor.RED + profile.getConnectedServer() + ChatColor.YELLOW + ".");
            sender.sendMessage(ChatColor.GOLD + "Check out their info on the website: bridge.rip/u/" + profile.getUsername());
            sender.sendMessage(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + StringUtils.repeat('-', 36));
        }
    }
}
