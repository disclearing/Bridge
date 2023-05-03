package rip.bridge.bridge.global.packet.types;

import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.global.packet.Packet;
import rip.bridge.bridge.global.packet.PacketHandler;
import rip.bridge.bridge.global.ranks.Rank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
public class RankCreatePacket implements Packet {

    private Rank rank;
    private String creator, server;

    @Override
    public void onReceive() {
        if(server.equals(BridgeGlobal.getSystemName())) return;
        BridgeGlobal.getRankHandler().addRank(rank);
        BridgeGlobal.sendLog("Created rank " + rank.getDisplayName() + " (Executed by " + creator + " on " + server + ")");
    }

}
