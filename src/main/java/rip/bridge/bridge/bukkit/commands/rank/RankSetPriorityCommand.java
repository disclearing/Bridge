package rip.bridge.bridge.bukkit.commands.rank;

import org.bukkit.command.CommandSender;
import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.global.packet.PacketHandler;
import rip.bridge.bridge.global.packet.types.NetworkBroadcastPacket;
import rip.bridge.bridge.global.packet.types.RankUpdatePacket;
import rip.bridge.bridge.global.ranks.Rank;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

public class RankSetPriorityCommand {

    @Command(names = {"rank setpriority", "rank setweight"}, permission = "bridge.rank", description = "Set a ranks priority", hidden = true, async = true)
    public static void RankSetPriorityCmd(CommandSender s, @Param(name = "rank") Rank r, @Param(name = "priority") int priority) {
        r.setPriority(priority);
        r.saveRank();
        s.sendMessage("§aSuccessfully changed the priority to " + priority + "§a!");
        PacketHandler.sendToAll(new RankUpdatePacket(r, s.getName(), BridgeGlobal.getSystemName()));
        PacketHandler.sendToAll(new NetworkBroadcastPacket("bridge.update.view", "&8[&eServer Monitor&8] &fRefreshed rank " + r.getColor() + r.getDisplayName()));
    }
}
