package rip.bridge.bridge.bukkit.commands.disguise;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.global.disguise.DisguisePlayer;
import rip.bridge.bridge.global.disguise.DisguiseProfile;
import rip.bridge.bridge.global.profile.Profile;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

public class RealNameCommand {

    @Command(names = {"realname", "realnick", "checkdisguise"}, permission = "basic.staff", description = "Check who the person is!", hidden = true)
    public static void check(Player sender, @Param(name = "name") String name) {
        Player player = Bukkit.getPlayer(name);

        if (player == null) {
            for (DisguisePlayer disguisePlayer : BridgeGlobal.getDisguiseManager().getDisguisePlayers().values()) {
                if (name.equalsIgnoreCase(disguisePlayer.getName())) {
                    Player dPlayer = Bukkit.getPlayer(disguisePlayer.getDisguiseName());

                    if (dPlayer != null) {
                        player = dPlayer;
                        break;
                    }
                }
            }
        }

        if (player == null) {
            sender.sendMessage("§cNo player with the name" + " \"" + name + "\" found.");
            return;
        }

        DisguisePlayer disguisePlayer = BridgeGlobal.getDisguiseManager().getDisguisePlayers().get(player.getUniqueId());
        Profile data = BridgeGlobal.getProfileHandler().getProfileByUUID(player.getUniqueId());
        DisguiseProfile profile = disguisePlayer != null ? BridgeGlobal.getDisguiseManager().getDisguiseProfiles().values()
                .stream()
                .filter(dp -> disguisePlayer.getDisguiseSkin() != null && dp.getSkinName().equalsIgnoreCase(disguisePlayer.getDisguiseSkin()))
                .findFirst()
                .orElse(null) : null;

        sender.sendMessage(data.getCurrentGrant().getRank().getColor() + data.getUsername() + ChatColor.GOLD + (disguisePlayer != null ? " is disguised" : ChatColor.RED + " is NOT disguised") + '.');
        if (disguisePlayer != null) {
            sender.sendMessage(ChatColor.GRAY.toString() + ' ' + "*" + ' ' + ChatColor.GOLD + "Disguise Name: " + ChatColor.RESET + disguisePlayer.getDisguiseName());
            sender.sendMessage(ChatColor.GRAY.toString() + ' ' + "*" + ' ' + ChatColor.GOLD + "Disguise Skin: " + ChatColor.WHITE + ChatColor.stripColor((profile != null ? profile.getDisplayName() : disguisePlayer.getDisguiseSkin())));
            sender.sendMessage(ChatColor.GRAY.toString() + ' ' + "*" + ' ' + ChatColor.GOLD + "Disguise Rank: " + disguisePlayer.getDisguiseRank().getColor() + disguisePlayer.getDisguiseRank().getName());
        }
    }
}
