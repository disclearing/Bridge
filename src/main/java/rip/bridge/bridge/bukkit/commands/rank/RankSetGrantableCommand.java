package rip.bridge.bridge.bukkit.commands.rank;

import org.bukkit.command.CommandSender;
import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.global.packet.PacketHandler;
import rip.bridge.bridge.global.packet.types.NetworkBroadcastPacket;
import rip.bridge.bridge.global.packet.types.RankUpdatePacket;
import rip.bridge.bridge.global.ranks.Rank;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

public class RankSetGrantableCommand {

    @Command(names = {"rank setgrantable"}, permission = "bridge.rank", description = "Set a rank grantable status", hidden = true, async = true)
    public static void RankSetGrantableCmd(CommandSender s, @Param(name = "rank") Rank r, @Param(name = "grantable") boolean grantable) {
        r.setGrantable(grantable);
        r.saveRank();
        s.sendMessage("§aSuccessfully changed the grantable status to " + grantable + "§a!");
        PacketHandler.sendToAll(new RankUpdatePacket(r, s.getName(), BridgeGlobal.getSystemName()));
        PacketHandler.sendToAll(new NetworkBroadcastPacket("bridge.update.view", "&8[&eServer Monitor&8] &fRefreshed rank " + r.getColor() + r.getDisplayName()));
    }
}
