package rip.bridge.bridge.bukkit.commands.server.menu.server;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.bukkit.commands.server.menu.server.buttons.ServerButton;
import rip.bridge.bridge.global.status.BridgeServer;
import rip.bridge.qlib.menu.Button;
import rip.bridge.qlib.menu.Menu;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerMenu extends Menu {

    public ServerMenu() {
        super(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Servers");
        setAutoUpdate(true);
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttonMap = new HashMap<>();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        BridgeGlobal.getServerHandler().getServers().values().stream().sorted(Comparator.comparingLong(this::getStartTime)).forEach(s -> {
            buttonMap.put(atomicInteger.getAndIncrement(), new ServerButton(s));
        });
        return buttonMap;
    }

    private long getStartTime(BridgeServer server) {
        try {
            return server.getBootTime();
        } catch (Exception ignored) {}
        return System.currentTimeMillis();
    }
}