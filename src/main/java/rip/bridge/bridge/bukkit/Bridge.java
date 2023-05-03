package rip.bridge.bridge.bukkit;

import lombok.Setter;
import net.minecraft.server.v1_7_R4.Packet;
import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.bukkit.listener.BridgeListener;
import rip.bridge.bridge.bukkit.listener.FreezeListener;
import rip.bridge.bridge.bukkit.parameters.*;
import rip.bridge.bridge.bukkit.parameters.param.filter.FilterActionParameter;
import rip.bridge.bridge.bukkit.parameters.param.filter.FilterParameter;
import rip.bridge.bridge.bukkit.parameters.param.filter.FilterTypeParameter;
import rip.bridge.bridge.bukkit.util.BukkitUtils;
import rip.bridge.bridge.global.disguise.DisguiseProfile;
import rip.bridge.bridge.global.filter.Filter;
import rip.bridge.bridge.global.filter.FilterAction;
import rip.bridge.bridge.global.filter.FilterType;
import rip.bridge.bridge.global.profile.Profile;
import rip.bridge.bridge.global.ranks.Rank;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import rip.bridge.qlib.command.FrozenCommandHandler;
import rip.bridge.qlib.nametag.FrozenNametagHandler;

import java.util.ArrayList;

public class Bridge extends JavaPlugin {

    @Getter private static Bridge instance;
    @Getter private boolean isBooted = false;
    @Getter private ArrayList<Packet> blockedPackets = new ArrayList<>();
    @Getter @Setter public boolean togglePacketLogger = false;

    @Override
    public void onLoad() {
        (instance = this).saveDefaultConfig();
        new BridgeGlobal();
        BridgeGlobal.getServerHandler().registerProvider(new BukkitStatusImplementer());
    }

    @Override
    public void onEnable() {
        FrozenCommandHandler.registerAll(this);
        FrozenCommandHandler.registerParameterType(Rank.class, new RankParamater());
        FrozenCommandHandler.registerParameterType(DisguiseProfile.class, new DisguiseParameter());
        FrozenCommandHandler.registerParameterType(Profile.class, new ProfileParamater());
        FrozenCommandHandler.registerParameterType(Plugin.class, new PluginParameter());
        FrozenCommandHandler.registerParameterType(FilterAction.class, new FilterActionParameter());
        FrozenCommandHandler.registerParameterType(FilterType.class, new FilterTypeParameter());
        FrozenCommandHandler.registerParameterType(Filter.class, new FilterParameter());
        FrozenNametagHandler.registerProvider(new BridgeNameTagProvider());

        BukkitUtils.registerListeners(BridgeListener.class);
        BukkitUtils.registerListeners(FreezeListener.class);
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> isBooted = true);
        BridgeGlobal.loadDisguise(false);
    }

    @Override
    public void onDisable() {
        BridgeGlobal.shutdown();
    }

}
