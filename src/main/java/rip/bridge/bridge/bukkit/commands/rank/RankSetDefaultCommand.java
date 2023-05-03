package rip.bridge.bridge.bukkit.commands.rank;

import org.bukkit.command.CommandSender;
import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.global.packet.PacketHandler;
import rip.bridge.bridge.global.packet.types.NetworkBroadcastPacket;
import rip.bridge.bridge.global.packet.types.RankUpdatePacket;
import rip.bridge.bridge.global.ranks.Rank;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

public class RankSetDefaultCommand {

    @Command(names = {"rank setdefault"}, permission = "bridge.rank", description = "Set a rank default status", hidden = true, async = true)
    public static void RankSetDefaultCmd(CommandSender s, @Param(name = "rank") Rank r, @Param(name = "default") boolean b) {
        r.setDefaultRank(b);
        r.saveRank();
        s.sendMessage("§aSuccessfully changed the default status to " + b + "§a!");
        PacketHandler.sendToAll(new RankUpdatePacket(r, s.getName(), BridgeGlobal.getSystemName()));
        PacketHandler.sendToAll(new NetworkBroadcastPacket("bridge.update.view", "&8[&eServer Monitor&8] &fRefreshed rank " + r.getColor() + r.getDisplayName()));
    }
}
