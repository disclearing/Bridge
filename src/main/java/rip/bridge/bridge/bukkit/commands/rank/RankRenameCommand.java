package rip.bridge.bridge.bukkit.commands.rank;

import org.bukkit.command.CommandSender;
import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.global.packet.PacketHandler;
import rip.bridge.bridge.global.packet.types.NetworkBroadcastPacket;
import rip.bridge.bridge.global.packet.types.RankUpdatePacket;
import rip.bridge.bridge.global.ranks.Rank;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

public class RankRenameCommand {

    @Command(names = {"rank rename"}, permission = "bridge.rank", description = "Rename a rank", hidden = true, async = true)
    public static void RankRenameCmd(CommandSender s, @Param(name = "rank") Rank r, @Param(name = "name") String name) {
        String original = r.getName();
        r.setName(name);
        r.saveRank();
        s.sendMessage("§aSuccessfully renamed the rank from " + original + " to " + name + "!");
        PacketHandler.sendToAll(new RankUpdatePacket(r, s.getName(), BridgeGlobal.getSystemName()));
        PacketHandler.sendToAll(new NetworkBroadcastPacket("bridge.update.view", "&8[&eServer Monitor&8] &fRefreshed rank " + r.getColor() + r.getDisplayName()));
    }
}
