package rip.bridge.bridge.bukkit.commands.punishment;

import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.bukkit.BukkitAPI;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import rip.bridge.bridge.global.profile.Profile;
import rip.bridge.bridge.global.punishment.PunishmentType;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

import java.util.ArrayList;
import java.util.List;

public class AltsCommand {

    @Command(names = {"alts", "dupeip", "identities"}, permission = "bridge.alts", description = "View player's alt accounts", async = true)
    public static void alts(CommandSender sender, @Param(name = "player") Profile profile) {

        BridgeGlobal.getMongoHandler().getProfiles(profile.getCurrentIPAddress(), callback -> {
            if (callback == null || callback.isEmpty() || callback.size() == 1) {
                sender.sendMessage(BukkitAPI.getColor(profile) + profile.getUsername() + ChatColor.RED + " doesn't have any alts");
                return;
            }

            sender.sendMessage(ChatColor.YELLOW + "Fetching identities of " + profile.getUsername() + "...");
            sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.GREEN + "Online" + ChatColor.WHITE + ", " + ChatColor.GRAY + "Offline" + ChatColor.WHITE + ", " + ChatColor.DARK_RED + "Blacklisted" + ChatColor.WHITE + ", " + ChatColor.RED + "Banned" + ChatColor.WHITE + ", " + ChatColor.WHITE + "Muted" + ChatColor.WHITE + "]");
            sender.sendMessage(BukkitAPI.getColor(profile) + profile.getUsername() + ChatColor.YELLOW + "'s" + (callback.size() == 1 ? ChatColor.YELLOW + " alt" : ChatColor.YELLOW + " alts") + ChatColor.YELLOW + " (" + ChatColor.RED + callback.size() + ChatColor.YELLOW + ")" + ".");
            List<String> formattedName = new ArrayList<>();
            callback.forEach(alt -> {
                if (Bukkit.getOfflinePlayer(alt.getUuid()).isOnline())
                    formattedName.add(ChatColor.GREEN + alt.getUsername());
                else if (alt.getActivePunishments(PunishmentType.BLACKLIST).size() > 1)
                    formattedName.add(ChatColor.DARK_RED + alt.getUsername());
                else if (alt.getActivePunishments(PunishmentType.BAN).size() > 1)
                    formattedName.add(ChatColor.RED + alt.getUsername());
                else if (alt.getActivePunishments(PunishmentType.MUTE).size() > 1)
                    formattedName.add(ChatColor.GOLD + alt.getUsername());
                else formattedName.add(ChatColor.GRAY + alt.getUsername());
            });
            sender.sendMessage(StringUtils.join(formattedName, ChatColor.WHITE + ", "));

        }, false);
    }
}