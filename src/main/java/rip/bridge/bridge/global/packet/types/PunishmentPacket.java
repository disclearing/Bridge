package rip.bridge.bridge.global.packet.types;

import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.global.packet.Packet;
import rip.bridge.bridge.global.util.SystemType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import rip.bridge.bridge.bukkit.listener.GeneralListener;
import rip.bridge.bridge.global.punishment.Punishment;

@AllArgsConstructor @NoArgsConstructor
public class PunishmentPacket implements Packet {

    private Punishment punishment;

    @Override
    public void onReceive() {
        if(BridgeGlobal.getSystemType() == SystemType.BUKKIT) GeneralListener.handlePunishment(punishment, punishment.isPardoned());
    }

}
