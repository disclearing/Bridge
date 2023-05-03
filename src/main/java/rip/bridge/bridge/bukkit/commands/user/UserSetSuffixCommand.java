package rip.bridge.bridge.bukkit.commands.user;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import rip.bridge.bridge.global.profile.Profile;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

public class UserSetSuffixCommand {

    @Command(names = {"user setsuffix"}, permission = "bridge.user", description = "Set a players suffix", async = true, hidden = true)
    public static void UserSetSuffixCmd(CommandSender s, @Param(name = "player") Profile pf, @Param(name = "suffix", wildcard = true) String suffix) {
        String tag = ChatColor.translateAlternateColorCodes('&', suffix);
        if (suffix.equals("clear")) tag = "";
        pf.setSuffix(tag);
        pf.saveProfile();
        s.sendMessage("§aSuccessfully " + (tag.equals("") ? "cleared" : "set") + " the suffix of " + pf.getUsername() + (!tag.equals("") ? " to " + tag : ""));
    }
}
