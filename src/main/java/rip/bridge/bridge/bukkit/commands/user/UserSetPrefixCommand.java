package rip.bridge.bridge.bukkit.commands.user;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import rip.bridge.bridge.global.profile.Profile;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

public class UserSetPrefixCommand {

    @Command(names = {"user setprefix"}, permission = "bridge.user", description = "Set a players prefix", async = true, hidden = true)
    public static void UserSetPrefixCmd(CommandSender s, @Param(name = "player") Profile pf, @Param(name = "prefix", wildcard = true) String prefix) {
        String tag = ChatColor.translateAlternateColorCodes('&', prefix);
        if (prefix.equals("clear")) tag = "";
        pf.setPrefix(tag);
        pf.saveProfile();
        s.sendMessage("§aSuccessfully " + (tag.equals("") ? "cleared" : "set") + " the prefix of " + pf.getUsername() + (!tag.equals("") ? " to " + tag : ""));
    }
}
