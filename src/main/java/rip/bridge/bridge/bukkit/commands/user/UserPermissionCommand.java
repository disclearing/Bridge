package rip.bridge.bridge.bukkit.commands.user;

import org.bukkit.command.CommandSender;
import rip.bridge.bridge.bukkit.listener.GeneralListener;
import rip.bridge.bridge.global.profile.Profile;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

public class UserPermissionCommand {

    @Command(names = {"user permission", "user perm"}, permission = "bridge.user", description = "Add/Remove a player's permission", hidden = true, async = true)
    public static void UserPermissionCmd(CommandSender s, @Param(name = "player") Profile pf, @Param(name = "permission") String perm, @Param(name = "group", defaultValue = "§") String serverGroup) {
        String group = serverGroup.equals("§") ? "Global" : serverGroup;
        boolean b = pf.togglePerm(perm, group);
        pf.saveProfile();
        GeneralListener.updatePermissions(pf.getUuid());
        s.sendMessage("§aSuccessfully " + (b ? "added" : "removed") + " the permission " + perm + " to the scope: " + group);
    }
}
