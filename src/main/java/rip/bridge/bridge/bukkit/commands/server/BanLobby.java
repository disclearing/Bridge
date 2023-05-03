package rip.bridge.bridge.bukkit.commands.server;

import lombok.Getter;
import rip.bridge.bridge.bukkit.Bridge;

public class BanLobby {

    @Getter
    private boolean maintenance, restricted, banLobbyEnabled;

    public BanLobby() {
        this.banLobbyEnabled = Bridge.getInstance().getConfig().getBoolean("server.ban-lobby");
    }

    //TODO: Do this shit later

}

