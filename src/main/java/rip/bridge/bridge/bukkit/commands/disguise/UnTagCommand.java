package rip.bridge.bridge.bukkit.commands.disguise;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.global.disguise.DisguisePlayer;
import rip.bridge.qlib.command.Command;

public class UnTagCommand {

    @Command(names = {"untag"}, permission = "bridge.disguise", hidden = true)
    public static void untag(Player player) {
        DisguisePlayer disguisePlayer = BridgeGlobal.getDisguiseManager().getDisguisePlayers().get(player.getUniqueId());

        if (disguisePlayer == null) {
            player.sendMessage(ChatColor.RED + "You're not tagged!");
            return;
        }

        player.sendMessage(ChatColor.GREEN + "Removing the tag...");
        BridgeGlobal.getDisguiseManager().undisguise(player, true, false);
    }
}
