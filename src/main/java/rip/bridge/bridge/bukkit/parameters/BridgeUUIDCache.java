package rip.bridge.bridge.bukkit.parameters;

import rip.bridge.bridge.bukkit.BukkitAPI;
import rip.bridge.qlib.uuid.UUIDCache;

import java.util.UUID;

public class BridgeUUIDCache implements UUIDCache {

    @Override
    public UUID uuid(String var1) {
        return BukkitAPI.getProfile(var1).getUuid();
    }

    @Override
    public String name(UUID var1) {
        return BukkitAPI.getName(BukkitAPI.getProfile(var1), false);
    }

    @Override
    public void ensure(UUID var1) {

    }

    @Override
    public void update(UUID var1, String var2) {

    }
}
