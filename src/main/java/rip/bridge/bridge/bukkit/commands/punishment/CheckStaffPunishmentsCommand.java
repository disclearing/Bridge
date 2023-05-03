package rip.bridge.bridge.bukkit.commands.punishment;

import rip.bridge.bridge.bukkit.commands.punishment.menu.staffhistory.MainStaffPunishmentListMenu;
import rip.bridge.bridge.global.profile.Profile;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;
import org.bukkit.entity.Player;

public class CheckStaffPunishmentsCommand {

    @Command(names = {"staffpunishments", "checkstaffpunishments", "staffhistory", "staffhist"}, permission = "bridge.staffhistory", description = "Check a player's active punishments", async = true)
    public static void staffPunishments(Player sender, @Param(name = "target") Profile target){
        new MainStaffPunishmentListMenu(target.getUuid().toString(), target.getUsername()).openMenu(sender);
    }
}