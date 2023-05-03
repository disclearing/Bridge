package rip.bridge.bridge.bukkit.commands.rank;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.global.packet.PacketHandler;
import rip.bridge.bridge.global.packet.types.NetworkBroadcastPacket;
import rip.bridge.bridge.global.packet.types.RankUpdatePacket;
import rip.bridge.bridge.global.ranks.Rank;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

public class RankSetColorCommand {
    @Command(names = {"rank setcolor", "rank setcolour"}, permission = "bridge.rank", description = "Set a ranks color", hidden = true, async = true)
    public static void RankSetColorCmd(CommandSender s, @Param(name = "rank") Rank r, @Param(name = "color") String col) {
        String color = ChatColor.translateAlternateColorCodes('&', col);
        r.setColor(color);
        r.saveRank();
        s.sendMessage("§aSuccessfully changed the color to " + color + r.getName() + "§a!");
        PacketHandler.sendToAll(new RankUpdatePacket(r, s.getName(), BridgeGlobal.getSystemName()));
        PacketHandler.sendToAll(new NetworkBroadcastPacket("bridge.update.view", "&8[&eServer Monitor&8] &fRefreshed rank " + r.getColor() + r.getDisplayName()));
    }
}
