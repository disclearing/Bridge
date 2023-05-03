package rip.bridge.bridge.bukkit.commands.punishment;

import org.bukkit.command.CommandSender;
import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.bukkit.BukkitAPI;
import rip.bridge.bridge.bukkit.commands.punishment.menu.MainPunishmentMenu;
import rip.bridge.bridge.global.packet.PacketHandler;
import rip.bridge.bridge.global.packet.types.PunishmentPacket;
import rip.bridge.bridge.global.profile.Profile;
import rip.bridge.bridge.global.punishment.Punishment;
import rip.bridge.bridge.global.punishment.PunishmentType;
import rip.bridge.qlib.command.Flag;
import mkremins.fanciful.FancyMessage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

import java.util.HashSet;

public class ClearPunishmentsCommand {

    @Command(names = {"clearpunishments", "clearhistory"}, permission = "bridge.clearpunishments", description = "Clear player's punishments from the entire network", async = true)
    public static void clearPunishments(CommandSender s, @Param(name = "target") Profile target) {

        if (target.getPunishments().isEmpty()) {
            s.sendMessage(ChatColor.RED + target.getUsername() + " does not have any punishments.");
            return;
        }
        s.sendMessage(ChatColor.GREEN + "Successfully cleared " + target.getCurrentGrant().getRank().getColor() + target.getUsername() + ChatColor.GREEN + "'s punishments.");
        target.getPunishments().clear();
        target.saveProfile();
    }
}

