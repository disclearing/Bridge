package rip.bridge.bridge.bukkit.parameters;

import rip.bridge.bridge.BridgeGlobal;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import rip.bridge.qlib.xpacket.XPacket;

import java.util.UUID;

@AllArgsConstructor
public class FoxtrotRevivePacket implements XPacket {

    private String server;
    private UUID player;
    private int lives;

    @Override
    public void onReceive() {
        if(BridgeGlobal.getServerName().equalsIgnoreCase(server)) {
            if(Bukkit.getPluginManager().getPlugin("HCTeams") != null && Bukkit.getPluginManager().getPlugin("HCF").isEnabled()) {
//                Foxtrot.getInstance().getSoulboundLivesMap().setLives(player, lives);
//                Foxtrot.getInstance().getDeathbanMap().revive(player);
//                System.out.println("[HCTeams] " + UUIDUtils.name(player) + " has used a life and revived themselves through Hub. (Lives: " + lives + ")");
            }
        }
    }
}
