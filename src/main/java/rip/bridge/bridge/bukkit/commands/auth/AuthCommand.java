package rip.bridge.bridge.bukkit.commands.auth;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import rip.bridge.bridge.BridgeGlobal;
import rip.bridge.bridge.bukkit.Bridge;
import rip.bridge.bridge.global.profile.Profile;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

public class AuthCommand {

    @Command(names = {"auth", "authenticate", "2fa", "otp"}, permission = "basic.staff", description = "Authenticate with the API, verifying your identity", hidden = true)
    public static void auth(Player sender, @Param(name = "code", wildcard = true) String input) {
        Profile profile = BridgeGlobal.getProfileHandler().getProfileByUUID(sender.getUniqueId());
        if(!sender.hasMetadata("Locked")) {
            sender.sendMessage(ChatColor.RED + "You don't need to authenticate at the moment.");
        } else {
            input = input.replace(" ", "");
            int code = Integer.parseInt(input);
            Bukkit.getScheduler().runTask(Bridge.getInstance(), () -> {
                if (profile == null) {
                    sender.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "Your profile hasn't yet been loaded.");
                } else {

                    if(profile.getSecretKey().isEmpty()) {
                        sender.sendMessage(ChatColor.RED + "You need to setup your 2FA first using: /2fasetup.");
                        return;
                    }

                    GoogleAuthenticator gAuth = new GoogleAuthenticator();
                    boolean isValid = gAuth.authorize(profile.getSecretKey(), code);

                    if(isValid) {
                        sender.removeMetadata("Locked", Bridge.getInstance());

                        sender.sendMessage(ChatColor.DARK_GREEN + "✓ " + ChatColor.GREEN + "Authenticated");
                    } else {
                        sender.sendMessage(ChatColor.RED + "Invalid auth code.");
                    }

                }
            });
        }
    }

}
