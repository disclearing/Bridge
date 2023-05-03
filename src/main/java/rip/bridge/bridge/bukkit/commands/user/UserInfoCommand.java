package rip.bridge.bridge.bukkit.commands.user;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.bukkit.BukkitAPI;
import rip.bridge.bridge.global.profile.Profile;
import rip.bridge.bridge.global.util.TimeUtil;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;
import rip.bridge.qlib.util.TimeUtils;

import java.util.Date;

public class UserInfoCommand {

    @Command(names = {"user info", "user check"}, permission = "bridge.user", description = "Get information about a players profile", async = true, hidden = true)
    public static void UserInfoCmd(CommandSender s, @Param(name = "player", extraData = "get") Profile pf) {
        s.sendMessage(BukkitAPI.LINE);
        s.sendMessage(BukkitAPI.getColor(pf) + pf.getUsername() + " §7❘ §fProfile Information");
        s.sendMessage(BukkitAPI.LINE);
        s.sendMessage("§6UUID: §f" + pf.getUuid());
        s.sendMessage("§6Rank: §f" + pf.getCurrentGrant().getRank().getColor() + pf.getCurrentGrant().getRank().getDisplayName());
        s.sendMessage("§6Grants: §f" + pf.getActiveGrants().size() + " active | " + pf.getGrants().size() + " total");
        s.sendMessage("§6Punishments: §f" + pf.getActivePunishments().size() + " active | " + pf.getPunishments().size() + " total");
        s.sendMessage("§6Permissions (" + pf.getPermissions().size() + ChatColor.GOLD + "): ");
        if (pf.getPermissions().isEmpty()) s.sendMessage(ChatColor.RED + "None...");
        else pf.getPermissions().keySet().forEach(str -> {
            s.sendMessage(ChatColor.GRAY + " * " + ChatColor.WHITE + str + ChatColor.GRAY + " [" + pf.getPermissions().get(str) + "]");
        });
        s.sendMessage("");
//        s.sendMessage("§6Prefix: §f" + BukkitAPI.getPrefix(pf) + " §7(" + ChatColor.stripColor(BukkitAPI.getPrefix(pf).replace("§", "&")) + ")");
//        s.sendMessage("§6Suffix: §f" + BukkitAPI.getSuffix(pf) + " §7(" + ChatColor.stripColor(BukkitAPI.getSuffix(pf).replace("§", "&")) + ")");
        s.sendMessage("§6First Joined: §f" + TimeUtils.formatIntoCalendarString(new Date(pf.getFirstJoined())));
        if (pf.isOnline()) {
            s.sendMessage("§6Connected to: §f" + BridgeGlobal.getServerHandler().findPlayerServer(pf.getUuid()).getName() + (BridgeGlobal.getServerHandler().findPlayerProxy(pf.getUuid()) != null ? " §7[" + BridgeGlobal.getServerHandler().findPlayerProxy(pf.getUuid()).getName() + "]" : ""));
        } else {
//            s.sendMessage("§6Last Seen: §7(" + TimeUtil.millisToRoundedTime(System.currentTimeMillis() - pf.getLastQuit()) + " ago)");
            s.sendMessage("§6Last Server: §f" + pf.getConnectedServer() + " §7(" + TimeUtil.millisToRoundedTime(System.currentTimeMillis() - pf.getLastQuit()) + " ago)");
        }
        s.sendMessage(BukkitAPI.LINE);
    }
}
