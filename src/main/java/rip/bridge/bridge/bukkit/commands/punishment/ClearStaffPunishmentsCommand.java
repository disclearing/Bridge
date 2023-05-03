package rip.bridge.bridge.bukkit.commands.punishment;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import rip.bridge.bridge.global.profile.Profile;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

public class ClearStaffPunishmentsCommand {

    @Command(names = {"clearstaffpunishments", "clearstaffhistory"}, permission = "bridge.clearstaffpunishments", description = "Clear staff member's punishments from the entire network", async = true)
    public static void clearPunishments(CommandSender sender, @Param(name = "target") Profile target) {

        if (!target.getCurrentGrant().getRank().isStaff() && target.getRemovedStaffOn() == 0) {
            sender.sendMessage(ChatColor.RED + "That player has never been apart of the staff team.");
            return;
        }

        if (target.getStaffPunishments().isEmpty()) {
            sender.sendMessage(ChatColor.RED + target.getUsername() + " does not have any staff punishments.");
            return;
        }
        sender.sendMessage(ChatColor.GREEN + "Successfully cleared " + target.getCurrentGrant().getRank().getColor() + target.getUsername() + ChatColor.GREEN + "'s Staff History.");
        target.getStaffPunishments().clear();
        target.saveProfile();
    }
}

