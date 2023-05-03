package rip.bridge.bridge.global.packets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import rip.bridge.bridge.global.ranks.Rank;
import rip.bridge.bridge.global.util.JsonChain;
import rip.bridge.bridge.global.packet.Packet;

public class RankUpdatePacket
implements Packet {
    private Rank rank;
    private String creator;
    private String server;

    public RankUpdatePacket() {
    }

    public RankUpdatePacket(Rank rank, String creator, String server) {
        this.rank = rank;
        this.creator = creator;
        this.server = server;
    }

    @Override
    public void onReceive() {

    }

    public int id() {
        return 2;
    }

    public String sentFrom() {
        return this.server;
    }

    public boolean selfRecieve() {
        return false;
    }

    public JsonObject serialize() {
        return new JsonChain().addProperty("rank", new Gson().toJson(this.rank)).addProperty("creator", this.creator).addProperty("server", this.server).get();
    }

    public void deserialize(JsonObject jsonObject) {
        this.rank = new Gson().fromJson(jsonObject.get("rank").getAsString(), Rank.class);
        this.creator = jsonObject.get("creator").getAsString();
        this.server = jsonObject.get("server").getAsString();
    }

    public Rank getRank() {
        return this.rank;
    }

    public String getCreator() {
        return this.creator;
    }

    public String getServer() {
        return this.server;
    }
}

