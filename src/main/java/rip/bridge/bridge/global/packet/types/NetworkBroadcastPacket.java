package rip.bridge.bridge.global.packet.types;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import rip.bridge.bridge.bukkit.BukkitAPI;
import rip.bridge.bridge.global.packet.Packet;

public class NetworkBroadcastPacket implements Packet {

    public String permission;
    public String message;

    public NetworkBroadcastPacket(String permission, String message) {
        this.permission = permission;
        this.message = message;
    }

    @Override
    public void onReceive() {
        Bukkit.getOnlinePlayers().stream().filter(player -> BukkitAPI.getProfile(player).hasPermission(permission)).forEach(player -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', message)));
    }
}