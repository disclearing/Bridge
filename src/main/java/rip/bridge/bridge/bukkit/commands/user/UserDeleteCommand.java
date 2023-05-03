package rip.bridge.bridge.bukkit.commands.user;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.global.profile.Profile;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

public class UserDeleteCommand {

    @Command(names = {"user delete"}, permission = "bridge.user", description = "Delete a players profile", async = true, hidden = true)
    public static void UserDeleteCmd(CommandSender s, @Param(name = "player", extraData = "get") Profile pf) {
        BridgeGlobal.getMongoHandler().removeProfile(pf.getUuid(), callback -> {
            if (callback) {
                if (Bukkit.getOfflinePlayer(pf.getUuid()).isOnline())
                    Bukkit.getPlayer(pf.getUuid()).kickPlayer("§cYour profile has been deleted - please reconnect.");
                BridgeGlobal.getProfileHandler().getProfiles().remove(pf);
                s.sendMessage("§aSuccessfully deleted " + pf.getUsername() + "'s Profile.");
            } else {
                s.sendMessage("§cFailed to delete " + pf.getUsername() + "'s Profile.");
            }
        }, false);
    }
}
