package rip.bridge.bridge.bukkit.parameters.packets.filter;

import rip.bridge.bridge.BridgeGlobal;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import rip.bridge.bridge.global.filter.Filter;
import rip.bridge.qlib.xpacket.XPacket;

@AllArgsConstructor
public class FilterCreatePacket implements XPacket {

    private Filter filter;
    private String sender;

    @Override
    public void onReceive() {
        if(sender.equalsIgnoreCase(Bukkit.getServerName())) return;
        BridgeGlobal.getFilterHandler().addFilter(filter);
    }
}
