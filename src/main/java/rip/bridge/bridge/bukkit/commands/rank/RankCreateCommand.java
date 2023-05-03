package rip.bridge.bridge.bukkit.commands.rank;

import org.bukkit.command.CommandSender;
import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.bukkit.BukkitAPI;
import rip.bridge.bridge.global.packet.PacketHandler;
import rip.bridge.bridge.global.packet.types.NetworkBroadcastPacket;
import rip.bridge.bridge.global.packet.types.RankCreatePacket;
import rip.bridge.bridge.global.ranks.Rank;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

public class RankCreateCommand {

    @Command(names = {"rank create"}, permission = "bridge.rank", description = "Create a rank", hidden = true, async = true)
    public static void RankCreateCmd(CommandSender s, @Param(name = "rank") String name) {
        Rank r = BukkitAPI.getRank(name);
        if (r != null) {
            s.sendMessage("§cThere is already a rank with the name \"" + r.getName() + "\".");
            return;
        }
        r = BukkitAPI.createRank(name);
        PacketHandler.sendToAll(new RankCreatePacket(r, s.getName(), BridgeGlobal.getSystemName()));
        PacketHandler.sendToAll(new NetworkBroadcastPacket("bridge.update.view", "&8[&eServer Monitor&8] &fCreated rank " + r.getColor() + r.getDisplayName()));
        s.sendMessage("§aSuccessfully created the rank " + r.getColor() + r.getName() + "§a!");
    }
}
