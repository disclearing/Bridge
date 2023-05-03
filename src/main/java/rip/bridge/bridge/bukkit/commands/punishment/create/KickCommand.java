package rip.bridge.bridge.bukkit.commands.punishment.create;

import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.bukkit.BukkitAPI;
import rip.bridge.bridge.global.packet.PacketHandler;
import rip.bridge.bridge.global.packet.types.PunishmentPacket;
import rip.bridge.bridge.global.profile.Profile;
import rip.bridge.bridge.global.punishment.Punishment;
import rip.bridge.bridge.global.punishment.PunishmentType;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Flag;
import rip.bridge.qlib.command.Param;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.HashSet;

public class KickCommand
{
    @Command(names = { "kick", "k" }, permission = "basic.staff", description = "Kick a player from the server")
    public static void pMuteCmd(CommandSender s, @Flag(value = {"a", "announce"}, description = "Announce this kick to the server") boolean silent, @Param(name = "target") Profile target, @Param(name = "reason", defaultValue = "Kicked by a staff member", wildcard = true) String reason) {
        Profile pf = BukkitAPI.getProfile(s);

        if (!BukkitAPI.canOverride(pf, target)) {
            s.sendMessage(ChatColor.RED + "You cannot punish this player.");
            return;
        }

        Punishment punishment = new Punishment(target, pf, BridgeGlobal.getSystemName(), reason, PunishmentType.KICK, new HashSet<>(),false, !silent, false, Long.MAX_VALUE);
        target.getPunishments().add(punishment);
        pf.getStaffPunishments().add(punishment);
        target.saveProfile();
        pf.saveProfile();
        PacketHandler.sendToAll(new PunishmentPacket(punishment));
    }
}