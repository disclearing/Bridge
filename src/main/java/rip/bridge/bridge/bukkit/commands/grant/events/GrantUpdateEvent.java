package rip.bridge.bridge.bukkit.commands.grant.events;

import rip.bridge.bridge.global.grant.Grant;
import rip.bridge.bridge.bukkit.util.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class GrantUpdateEvent extends BaseEvent {

    private UUID uuid;
    private Grant grant;

}
