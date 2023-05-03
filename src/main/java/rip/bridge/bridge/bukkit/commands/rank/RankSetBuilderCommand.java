package rip.bridge.bridge.bukkit.commands.rank;

import org.bukkit.command.CommandSender;
import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.global.packet.PacketHandler;
import rip.bridge.bridge.global.packet.types.NetworkBroadcastPacket;
import rip.bridge.bridge.global.packet.types.RankUpdatePacket;
import rip.bridge.bridge.global.ranks.Rank;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

public class RankSetBuilderCommand {

    @Command(names = {"rank setbuilder"}, permission = "bridge.rank", description = "Set a rank staff status", hidden = true, async = true)
    public static void RankSetBuilderCmd(CommandSender s, @Param(name = "rank") Rank r, @Param(name = "builder") boolean builder) {
        r.setBuilder(builder);
        r.saveRank();
        s.sendMessage("§aSuccessfully changed the builder status to " + builder + "§a!");
        PacketHandler.sendToAll(new RankUpdatePacket(r, s.getName(), BridgeGlobal.getSystemName()));
        PacketHandler.sendToAll(new NetworkBroadcastPacket("bridge.update.view", "&8[&eServer Monitor&8] &fRefreshed rank " + r.getColor() + r.getDisplayName()));
    }
}
