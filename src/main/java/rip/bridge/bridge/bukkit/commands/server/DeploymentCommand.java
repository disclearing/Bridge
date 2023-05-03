package rip.bridge.bridge.bukkit.commands.server;

import org.bukkit.entity.Player;
import rip.bridge.bridge.bukkit.commands.server.menu.deployment.DeploymentMenu;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;

public class DeploymentCommand {

    @Command(names = {"deploy", "deployment"}, permission = "bridge.deployment", description = "Deploy a server", hidden = true)
    public static void deployCmd(Player s, @Param(name = "serverName") String serverName) {
        new DeploymentMenu(serverName).openMenu(s);
    }
}
