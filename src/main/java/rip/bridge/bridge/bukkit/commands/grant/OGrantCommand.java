package rip.bridge.bridge.bukkit.commands.grant;

import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.global.grant.Grant;
import rip.bridge.bridge.global.profile.Profile;
import rip.bridge.bridge.global.ranks.Rank;
import rip.bridge.bridge.global.util.TimeUtil;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class OGrantCommand {

    @Command(names = {"ogrant"}, permission = "bridge.ogrant", description = "Add a grant to an player's account", async = true)
    public static void consolegrantCmd(CommandSender s, @Param(name = "player") Profile pf, @Param(name = "rank") Rank r, @Param(name = "duration") String l, @Param(name = "scopes") String scope, @Param(name = "reason", wildcard = true) String reason) {
        if (!r.isGrantable()) {
            s.sendMessage("§cThis rank is not grantable.");
            return;
        }
        List<String> scopes = Arrays.asList(scope.split(","));
        long length = (l.equalsIgnoreCase("Permanent") ? Long.MAX_VALUE : TimeUtil.parseTime(l));
        pf.applyGrant(new Grant(r, length, scopes, reason, Profile.getConsoleProfile().getUuid().toString(), BridgeGlobal.getSystemName()), null);
        pf.saveProfile();
        s.sendMessage("§aSuccessfully granted " + pf.getUsername() + " the rank " + r.getDisplayName() + " on the scopes: " + StringUtils.join(scopes, ", "));
    }
}
