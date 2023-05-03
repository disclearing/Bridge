package rip.bridge.bridge.bungee.listener;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import rip.bridge.qlib.xpacket.FrozenXPacketHandler;

import java.io.File;
import java.net.InetSocketAddress;

public class GeneralBungeeListener {

    public static void logMessages(String msg) {
        BungeeCord.getInstance().getLogger().info(ChatColor.translateAlternateColorCodes('&', msg));
    }


}
