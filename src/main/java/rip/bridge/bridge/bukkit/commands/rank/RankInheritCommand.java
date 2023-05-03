package rip.bridge.bridge.bukkit.commands.rank;

import org.bukkit.command.CommandSender;
import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.global.packet.PacketHandler;
import rip.bridge.bridge.global.packet.types.NetworkBroadcastPacket;
import rip.bridge.bridge.global.packet.types.RankUpdatePacket;
import rip.bridge.bridge.global.ranks.Rank;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

public class RankInheritCommand {

    @Command(names = {"rank inherit"}, permission = "bridge.rank", description = "Add/remove a rank inherit", hidden = true, async = true)
    public static void RankPermissionCmd(CommandSender s, @Param(name = "rank") Rank r, @Param(name = "inherit") Rank inhr) {
        boolean b = r.toggleInherit(inhr);
        r.saveRank();
        s.sendMessage("§aSuccessfully " + (b ? "added" : "removed") + " the inherit of " + inhr.getColor() + inhr.getDisplayName());
        PacketHandler.sendToAll(new RankUpdatePacket(r, s.getName(), BridgeGlobal.getSystemName()));
        PacketHandler.sendToAll(new NetworkBroadcastPacket("bridge.update.view", "&8[&eServer Monitor&8] &fRefreshed rank " + r.getColor() + r.getDisplayName()));
    }
}
