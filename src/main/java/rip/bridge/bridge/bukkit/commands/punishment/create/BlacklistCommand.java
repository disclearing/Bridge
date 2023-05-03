package rip.bridge.bridge.bukkit.commands.punishment.create;

import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.bukkit.BukkitAPI;
import rip.bridge.bridge.global.packet.PacketHandler;
import rip.bridge.bridge.global.packet.types.PunishmentPacket;
import rip.bridge.bridge.global.profile.Profile;
import rip.bridge.bridge.global.punishment.Punishment;
import rip.bridge.bridge.global.punishment.PunishmentType;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Flag;
import rip.bridge.qlib.command.Param;

import java.util.HashSet;

public class BlacklistCommand {

    @Command(names = {"blacklist", "bl"}, permission = "bridge.blacklist", description = "Blacklist an user from the network. This type of punishment cannot be appealed", async = true)
    public static void blacklistCmd(CommandSender s, @Flag(value = {"a", "announce"}, description = "Announce this blacklist to the server") boolean silent, @Flag(value = {"c", "clear"}, description = "Clear the player's inventory") boolean clear, @Param(name = "target") Profile target, @Param(name = "reason", wildcard = true) String reason) {
        Profile pf = BukkitAPI.getProfile(s);
        if (target.getCurrentIPAddress() == null) {
            s.sendMessage(ChatColor.RED + "We can't blacklist a player that never joined before.");
            return;
        }

        if (target.getActivePunishments(PunishmentType.BLACKLIST).size() > 1) {
            s.sendMessage(ChatColor.RED + target.getUsername() + " is already blacklisted.");
            return;
        }

        if (!BukkitAPI.canOverride(pf, target)) {
            s.sendMessage(ChatColor.RED + "You cannot punish this player.");
            return;
        }

        Punishment punishment = new Punishment(target, pf, BridgeGlobal.getSystemName(), reason, PunishmentType.BLACKLIST, new HashSet<>(), true, !silent, clear, Long.MAX_VALUE);
        target.getPunishments().add(punishment);
        pf.getStaffPunishments().add(punishment);
        target.saveProfile();
        pf.saveProfile();
        PacketHandler.sendToAll(new PunishmentPacket(punishment));
    }
}
