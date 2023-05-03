package rip.bridge.bridge.bukkit.commands.rank;

import org.bukkit.command.CommandSender;
import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.global.packet.PacketHandler;
import rip.bridge.bridge.global.packet.types.NetworkBroadcastPacket;
import rip.bridge.bridge.global.packet.types.RankDeletePacket;
import rip.bridge.bridge.global.ranks.Rank;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

public class RankDeleteCommand {

    @Command(names = {"rank delete", "rank remove"}, permission = "bridge.rank", description = "Delete a rank", hidden = true, async = true)
    public static void RankDeleteCmd(CommandSender s, @Param(name = "rank") Rank r) {
        s.sendMessage("§aSuccessfully deleted the rank " + r.getColor() + r.getName() + "§a!");
        BridgeGlobal.getMongoHandler().removeRank(r.getUuid(), callback -> {
        }, true);
        PacketHandler.sendToAll(new RankDeletePacket(r, s.getName(), BridgeGlobal.getServerName()));
        PacketHandler.sendToAll(new NetworkBroadcastPacket("bridge.update.view", "&8[&eServer Monitor&8] &fDeleted rank " + r.getColor() + r.getDisplayName()));
    }
}
