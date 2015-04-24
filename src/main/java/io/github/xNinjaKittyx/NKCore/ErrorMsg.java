package io.github.xNinjaKittyx.NKCore;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Dan on 4/23/2015.
 */
public class ErrorMsg {

    public static void consoleSenderError(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "Only In-Game Player can use this!");
    }

    public static void noPermError(Player player) {
        player.sendMessage(ChatColor.RED + "You don't have permission to do this!");
    }

    public static void notOnlineError(Player player, String target) {

        player.sendMessage(ChatColor.RED + target + "is not Online!");
    }

    public static void notOnlineError(CommandSender sender, String target) {

        sender.sendMessage(ChatColor.RED + target + "is not Online!");
    }

    public static void invalidParamsError(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "Invalid Parameters!");
    }

    public static void highRankError(Player player) {
        player.sendMessage(ChatColor.RED + "You can't use this command on someone higher rank than you!");
    }
}
