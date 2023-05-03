package rip.bridge.bridge.bukkit.commands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import rip.bridge.bridge.bukkit.BukkitAPI;
import rip.bridge.bridge.global.grant.Grant;
import rip.bridge.bridge.global.profile.Profile;
import rip.bridge.qlib.command.Command;
import rip.bridge.qlib.command.Param;
import rip.bridge.qlib.util.TimeUtil;
import rip.bridge.qlib.util.TimeUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class TimelineCommand {

    public static String blockColors = "▋";

    @Command(names = {"timeline"}, permission = "basic.staff", description = "Check for how long you've been staff!", async = true)
    public static void timeline(Player sender, @Param(name = "target", defaultValue = "self") Profile target) {

        if (!target.getCurrentGrant().getRank().isStaff() && target.getRemovedStaffOn() == 0) {
            sender.sendMessage(ChatColor.RED + "That player has never been apart of the staff team.");
        } else {
            sender.sendMessage(BukkitAPI.RED_LINE);

            List<Grant> grants = new ArrayList<>(target.getGrants());
            List<String> blockLines = new ArrayList<>();

            grants.sort(new SortByDate());

            boolean isFirst = false;
            for(Grant grant : grants) {
                if(!grant.getRank().isStaff()) continue;

//                Random randie = new Random();
//
//                int random = randie.nextInt(15);

                blockLines.add(grant.getRank().getColor().replace("§o", "") + ChatColor.STRIKETHROUGH + blockColors + "" + ChatColor.RESET);
                for (int i = 0; i < Math.abs(TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis() - grant.getInitialTime()) / 7); ++i) {
                    blockLines.add(ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH.toString() + blockColors + ChatColor.RESET);
                }

                if(!isFirst) {
                    sender.sendMessage(ChatColor.GOLD + target.getUsername() + ChatColor.YELLOW + " joined the team on " + ChatColor.LIGHT_PURPLE + grant.getDate()
                            + ChatColor.YELLOW + " and was given " + grant.getRank().getColor() + grant.getRank().getDisplayName()
                            + ChatColor.YELLOW + " by " + ChatColor.LIGHT_PURPLE + grant.getGrantedByColorless());
                    isFirst = true;
                    continue;
                }

                if(grant.getRank().getPriority() > 70) {
                    sender.sendMessage(ChatColor.GOLD + target.getUsername() + ChatColor.YELLOW + " was" + ChatColor.GREEN + " promoted " + ChatColor.YELLOW + "to " + grant.getRank().getColor() + grant.getRank().getDisplayName() + ChatColor.YELLOW +
                            " on " + ChatColor.LIGHT_PURPLE + grant.getDate()
                            + ChatColor.YELLOW + " by " + ChatColor.LIGHT_PURPLE + grant.getGrantedByColorless());
                } else {
                    sender.sendMessage(ChatColor.GOLD + target.getUsername() + ChatColor.YELLOW + " was granted " + grant.getRank().getColor() + grant.getRank().getDisplayName() + ChatColor.YELLOW +
                            " on " + ChatColor.LIGHT_PURPLE + grant.getDate()
                            + ChatColor.YELLOW + " by " + ChatColor.LIGHT_PURPLE + grant.getGrantedByColorless());

                }
            }

            sender.sendMessage(" ");
            sender.sendMessage(ChatColor.AQUA + "Timeline:");

            String result = StringUtils.join(blockLines, "");

            sender.sendMessage(result + ChatColor.GOLD + ChatColor.STRIKETHROUGH + "▋");

            sender.sendMessage(" ");
            if(target.getRemovedStaffOn() != 0) {
                Date timeAdded = new Date(target.getBecameStaffOn());
                Date timeRemoved = new Date(target.getRemovedStaffOn());
                long diffInM = Math.abs(timeRemoved.getTime() - timeAdded.getTime());
                long diff = TimeUnit.DAYS.convert(diffInM, TimeUnit.MILLISECONDS);

                sender.sendMessage(ChatColor.GOLD + target.getUsername() + ChatColor.YELLOW + " parted ways with the staff team on " + ChatColor.LIGHT_PURPLE + TimeUtils.formatIntoCalendarStringNoTime(new Date(target.getRemovedStaffOn())) + ChatColor.YELLOW + ".");
                sender.sendMessage(ChatColor.GOLD + target.getUsername() + ChatColor.GREEN + " helped the staff team for " + ChatColor.LIGHT_PURPLE + TimeUtil.millisToRoundedTime(timeRemoved.getTime() - timeAdded.getTime()) + ChatColor.YELLOW + ".");
            } else {
                sender.sendMessage(ChatColor.GOLD + target.getUsername() + ChatColor.YELLOW + " has been helping the staff team for " + ChatColor.LIGHT_PURPLE + TimeUtil.millisToRoundedTime(System.currentTimeMillis() - target.getBecameStaffOn()) + ChatColor.YELLOW + ".");
            }

            sender.sendMessage(BukkitAPI.RED_LINE);

        }
    }

    static class SortByDate implements Comparator<Grant> {
        @Override
        public int compare(Grant a, Grant b) {
            return a.getDate().compareTo(b.getDate());
        }
    }
}
