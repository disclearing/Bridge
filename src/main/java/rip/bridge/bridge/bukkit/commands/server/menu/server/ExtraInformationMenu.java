package rip.bridge.bridge.bukkit.commands.server.menu.server;

import lombok.AllArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import rip.bridge.bridge.bukkit.commands.server.menu.server.buttons.DataButton;
import rip.bridge.bridge.global.status.BridgeServer;
import rip.bridge.bridge.global.status.start.ServerStartThread;
import rip.bridge.bridge.global.util.JsonChain;
import rip.bridge.qlib.menu.Button;
import rip.bridge.qlib.menu.buttons.BackButton;
import rip.bridge.qlib.menu.menus.ConfirmMenu;
import rip.bridge.qlib.menu.pagination.PaginatedMenu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
public class ExtraInformationMenu extends PaginatedMenu {

    private final BridgeServer server;

    @Override
    public String getPrePaginatedTitle(Player player) {
        return ChatColor.YELLOW + "Extra information: " + server.getName();
    }

    @Override
    public Map<Integer, Button> getGlobalButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        buttons.put(4, new BackButton(new ServerMenu()));
        return buttons;
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        AtomicInteger atomicInteger = new AtomicInteger(0);

        try {
            server.getMetadata().entrySet().forEach(str -> {
                buttons.put(atomicInteger.getAndIncrement(), new DataButton(str.getKey(), server.getMetadata().get(str.getKey()).toString()));
            });
        } catch (Exception ex) {
            buttons.put(4, Button.placeholder(Material.REDSTONE_BLOCK, ChatColor.RED + "This server has no extra information."));
    }
        return buttons;

    }
}