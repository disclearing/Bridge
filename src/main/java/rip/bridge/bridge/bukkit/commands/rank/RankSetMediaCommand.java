package rip.bridge.bridge.bukkit.commands.rank;

import org.bukkit.command.CommandSender;
import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.global.packet.PacketHandler;
import rip.bridge.bridge.global.packet.types.NetworkBroadcastPacket;
import rip.bridge.bridge.global.packet.types.RankUpdatePacket;
import rip.bridge.bridge.global.ranks.Rank;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

public class RankSetMediaCommand {

    @Command(names = {"rank setmedia"}, permission = "bridge.rank", description = "Set a rank staff status", hidden = true, async = true)
    public static void RankSetMediaCmd(CommandSender s, @Param(name = "rank") Rank r, @Param(name = "media") boolean media) {
        r.setMedia(media);
        r.saveRank();
        s.sendMessage("§aSuccessfully changed the media status to " + media + "§a!");
        PacketHandler.sendToAll(new RankUpdatePacket(r, s.getName(), BridgeGlobal.getSystemName()));
        PacketHandler.sendToAll(new NetworkBroadcastPacket("bridge.update.view", "&8[&eServer Monitor&8] &fRefreshed rank " + r.getColor() + r.getDisplayName()));
    }
}