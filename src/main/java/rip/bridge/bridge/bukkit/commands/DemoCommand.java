package rip.bridge.bridge.bukkit.commands;

import net.minecraft.server.v1_7_R4.PacketPlayOutGameStateChange;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

public class DemoCommand {

    @Command(names = "demo", permission = "op", hidden = true)
    public static void demo(Player player, @Param(name = "target") Player target) {
        PacketPlayOutGameStateChange packet = new PacketPlayOutGameStateChange(5, 0);
        ((CraftPlayer)target).getHandle().playerConnection.sendPacket(packet);
        player.sendMessage(ChatColor.YELLOW + "Demo screened " + target.getDisplayName() + ChatColor.YELLOW + "!");
    }

}
