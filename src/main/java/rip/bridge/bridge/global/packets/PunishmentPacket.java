package rip.bridge.bridge.global.packets;

import com.google.gson.JsonObject;
import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.global.util.JsonChain;
import rip.bridge.bridge.global.packet.Packet;

public class PunishmentPacket
implements Packet {
    private String json;

    public PunishmentPacket() {
    }

    public int id() {
        return 5;
    }

    public String sentFrom() {
        return BridgeGlobal.getSystemName();
    }

    public boolean selfRecieve() {
        return true;
    }

    public JsonObject serialize() {
        return new JsonChain().addProperty("json", this.json).get();
    }

    public void deserialize(JsonObject jsonObject) {
        this.json = jsonObject.get("json").getAsString();
    }

    public String getJson() {
        return this.json;
    }

    public PunishmentPacket(String json) {
        this.json = json;
    }

    @Override
    public void onReceive() {

    }
}

