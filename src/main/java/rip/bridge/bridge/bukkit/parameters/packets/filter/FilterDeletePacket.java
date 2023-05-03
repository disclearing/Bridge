package rip.bridge.bridge.bukkit.parameters.packets.filter;

import rip.bridge.bridge.BridgeGlobal;
import org.bukkit.Bukkit;
import rip.bridge.bridge.global.filter.Filter;
import rip.bridge.qlib.xpacket.XPacket;

public class FilterDeletePacket implements XPacket {

    private String filter;
    private String sender;

    public FilterDeletePacket(Filter filter, String sender) {
        this.filter = filter.getPattern();
        this.sender = sender;
    }

    @Override
    public void onReceive() {
        if(sender.equalsIgnoreCase(Bukkit.getServerName())) return;
        BridgeGlobal.getFilterHandler().removeFilter(BridgeGlobal.getFilterHandler().getFilter(filter));
    }
}
