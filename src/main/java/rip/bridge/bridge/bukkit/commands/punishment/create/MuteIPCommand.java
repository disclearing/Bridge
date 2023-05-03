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
import java.util.concurrent.TimeUnit;

public class MuteIPCommand {

    @Command(names = {"muteip", "ipmute"}, permission = "bridge.muteip", description = "Temporarily mute an user, stopping them from talking in public chat", async = true)
    public static void muteIPCmd(CommandSender s, @Flag(value = {"a", "announce"}, description = "Announce this mute to the server") boolean silent, @Param(name = "target") Profile target, @Param(name = "time") Long length, @Param(name = "reason", wildcard = true) String reason) {
        Profile pf = BukkitAPI.getProfile(s);

        if (pf.getActivePunishments(PunishmentType.MUTE).size() > 0) {
            s.sendMessage(ChatColor.RED + target.getUsername() + " is already muted.");
            return;
        }

        if (!BukkitAPI.canOverride(pf, target)) {
            s.sendMessage(ChatColor.RED + "You cannot punish this player.");
            return;
        }

        if (!s.hasPermission("bridge.muteip.permanent") && TimeUnit.DAYS.toMillis(31L) < length) {
            s.sendMessage(ChatColor.RED + "You don't have permission to create a mute this long. Maximum time allowed: 30 days.");
            return;
        }

        Punishment punishment = new Punishment(target, pf, BridgeGlobal.getSystemName(), reason, PunishmentType.MUTE, new HashSet<>(), true, !silent, false, length);
        target.getPunishments().add(punishment);
        pf.getStaffPunishments().add(punishment);
        target.saveProfile();
        pf.saveProfile();
        PacketHandler.sendToAll(new PunishmentPacket(punishment));
    }
}
