package rip.bridge.bridge.global.packet.types;

import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.global.packet.Packet;
import rip.bridge.bridge.global.packet.PacketHandler;
import rip.bridge.bridge.global.ranks.Rank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import rip.bridge.bridge.bukkit.listener.GeneralListener;

@AllArgsConstructor @NoArgsConstructor
public class RankUpdatePacket implements Packet {

    private Rank rank;
    private String creator, server;

    @Override
    public void onReceive() {
        if(server.equals(BridgeGlobal.getSystemName())) {
            rank.getOnlineProfilesInRank().forEach(profile -> GeneralListener.updatePermissions(profile.getUuid()));
            return;
        }
        BridgeGlobal.getRankHandler().addRank(rank);
        BridgeGlobal.sendLog("Refreshed rank " + rank.getDisplayName() + " (Executed by " + creator + " on " + server + ")");
        rank.getOnlineProfilesInRank().forEach(profile -> GeneralListener.updatePermissions(profile.getUuid()));

    }

}
