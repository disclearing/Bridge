package rip.bridge.bridge.bukkit.parameters;

import rip.bridge.bridge.bukkit.BukkitAPI;
import rip.bridge.qlib.nametag.NametagInfo;
import rip.bridge.qlib.nametag.NametagProvider;
import org.bukkit.entity.Player;

public class BridgeNameTagProvider extends NametagProvider {

    public BridgeNameTagProvider() {
        super("Bridge Provider", 1);
    }

    @Override
    public NametagInfo fetchNametag(Player player, Player viewer) {
        return createNametag(BukkitAPI.getColor(player), "");
    }
}
