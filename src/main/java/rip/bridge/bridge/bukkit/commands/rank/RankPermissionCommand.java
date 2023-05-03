package rip.bridge.bridge.bukkit.commands.rank;

import org.bukkit.command.CommandSender;
import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.global.packet.PacketHandler;
import rip.bridge.bridge.global.packet.types.NetworkBroadcastPacket;
import rip.bridge.bridge.global.packet.types.RankUpdatePacket;
import rip.bridge.bridge.global.ranks.Rank;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

public class RankPermissionCommand {

    @Command(names = {"rank permission", "rank perm"}, permission = "bridge.rank", description = "Add/remove a permission", hidden = true, async = true)
    public static void RankPermissionCmd(CommandSender s, @Param(name = "rank") Rank r, @Param(name = "permission") String perm, @Param(name = "group", defaultValue = "§") String serverGroup) {
        String group = serverGroup.equals("§") ? "Global" : serverGroup;
        boolean b = r.togglePerm(perm, group);
        r.saveRank();
        s.sendMessage("§aSuccessfully " + (b ? "added" : "removed") + " the permission " + perm + " to the scope: " + group);
        PacketHandler.sendToAll(new RankUpdatePacket(r, s.getName(), BridgeGlobal.getSystemName()));
    }
}
