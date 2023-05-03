package rip.bridge.bridge.bukkit.commands.disguise.disguiseprofile;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.bukkit.BukkitAPI;
import rip.bridge.bridge.global.disguise.DisguisePlayer;
import rip.bridge.bridge.global.profile.Profile;
import rip.bridge.bridge.global.ranks.Rank;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

import java.util.Objects;
import java.util.regex.Pattern;

public class DisguiseProfileDisguiseCommand {

    @Command(names = {"disguiseprofile disguise"}, permission = "bridge.disguise.admin", description = "Disguise other players", hidden = true)
    public static void odisguise(Player sender, @Param(name = "player") String playerName, @Param(name = "rank") String rank, @Param(name = "name") String name, @Param(name = "skin") String skin) {
        Player player = Bukkit.getPlayer(playerName);

        if (player == null) {
            for (DisguisePlayer disguisePlayer : BridgeGlobal.getDisguiseManager().getDisguisePlayers().values()) {
                if (playerName.equalsIgnoreCase(disguisePlayer.getName())) {
                    Player dPlayer = Bukkit.getPlayer(disguisePlayer.getDisguiseName());

                    if (dPlayer != null) {
                        sender.sendMessage(ChatColor.RED + "That player is already disguised.");
                        return;
                    }
                }
            }
        }

        if (player == null) {
            sender.sendMessage("§cNo player with the name" + " \"" + name + "\" found.");
            return;
        }

        if (!Pattern.compile("^\\w{1,16}$").matcher(name).matches()) {
            player.sendMessage(ChatColor.RED + "Invalid username: " + name);
            return;
        }

        if (Bukkit.getPlayerExact(name) != null || Objects.requireNonNull(BridgeGlobal.getUsedDisguisedNames()).stream().anyMatch(n -> n.equalsIgnoreCase(name))) {
            player.sendMessage(ChatColor.RED + "The name \"" + name + "\" is unavailable.");
            return;
        }

        if (BukkitAPI.getProfile(name) != null && BukkitAPI.getPlayerRank(BukkitAPI.getProfile(name)).isStaff()) {
            sender.sendMessage(ChatColor.RED + name + " exists as a Bridge player. You cannot impersonate others, please choose another name.");
            return;
        }

        if (BukkitAPI.getProfile(name) != null) {
            sender.sendMessage(ChatColor.RED + name + " exists as a Bridge player. You cannot impersonate others, please choose another name.");
            return;
        }

        if (!Pattern.compile("^\\w{1,16}$").matcher(skin).matches()) {
            sender.sendMessage(ChatColor.RED + "That is not a valid username.");
            return;
        }

        Profile profile = BridgeGlobal.getProfileHandler().getProfileByUUID(sender.getUniqueId());
        Rank current = profile.getCurrentGrant().getRank();
        Rank select = BridgeGlobal.getRankHandler().getRankByName(rank);

        if (select.getPriority() > current.getPriority()) {
            sender.sendMessage(ChatColor.RED + "You are not allowed to set " + playerName + "'s tag to " + select.getColor() + select.getDisplayName() + ChatColor.RED + ".");
            return;
        }

        DisguisePlayer disguisePlayer = new DisguisePlayer(player.getName());
        disguisePlayer.setDisguiseRank(select);
        disguisePlayer.setDisguiseName(name);
        disguisePlayer.setDisguiseSkin(skin);

        try {
            if (BridgeGlobal.getDisguiseManager().disguise(player, disguisePlayer, null,false, true, false)) {
                player.sendMessage(ChatColor.GREEN + "You have been manually disguised by a staff member.");
                player.sendMessage(ChatColor.GREEN + "Success! You now look like " + select.getPrefix() + select.getColor() + name + ChatColor.GREEN + " (in the skin of " + ChatColor.YELLOW + skin + ChatColor.GREEN + ")!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
