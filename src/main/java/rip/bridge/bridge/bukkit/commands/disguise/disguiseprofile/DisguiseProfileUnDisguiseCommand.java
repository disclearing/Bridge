package rip.bridge.bridge.bukkit.commands.disguise.disguiseprofile;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.global.disguise.DisguisePlayer;
import rip.bridge.bridge.global.profile.Profile;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

public class DisguiseProfileUnDisguiseCommand {

    @Command(names = {"disguiseprofile undisguise"}, permission = "bridge.undisguise.admin", description = "UnDisguise other players", hidden = true)
    public static void undisguiseprofile(Player player, @Param(name = "player") Player target) {
        DisguisePlayer disguisePlayer = BridgeGlobal.getDisguiseManager().getDisguisePlayers().get(target.getUniqueId());
        Profile target2 = BridgeGlobal.getProfileHandler().getProfileByUUID(target.getUniqueId());

        if (disguisePlayer == null) {
            player.sendMessage(ChatColor.RED + target2.getUsername() + " is not disguised!");
            return;
        }

        player.sendMessage(ChatColor.GREEN + "You have removed the disguise of " + target2.getColor() + target2.getUsername() + ChatColor.GREEN + ".");
        target.sendMessage(ChatColor.RED + "You have been manually undisguised by a staff member.");
        target.sendMessage(ChatColor.GREEN + "Removing the disguise...");
        BridgeGlobal.getDisguiseManager().undisguise(target, true, false);
    }
}
