package io.github.xNinjaKittyx.NKCore.Commands;

import io.github.xNinjaKittyx.NKCore.ErrorMsg;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Daniel on 4/16/2015.
 */
public class Waypoints {

    public static boolean sethome(CommandSender sender) {
        //TODO: Implement SetHome for the sender
        //TODO: Implement SetHome for admins to sethomes for players
        //TODO: Set Multiple Homes


        if (!(sender instanceof Player)) {
            sender.sendMessage("This can only be used by in-game Players");
            return true;
        }
        Player player = (Player) sender;

        if (!(player.hasPermission("NKCore.sethome") || player.hasPermission("NKCore.admin"))) {
            player.sendMessage(ChatColor.RED + "You don't have permission!");
            return true;
        }
        Location location = player.getLocation();
        player.setBedSpawnLocation(location, true);
        player.sendMessage(ChatColor.GREEN + "Home Set!");
        return true;


    }

    public static boolean home(CommandSender sender) {
        if (!(sender instanceof Player)) {
            ErrorMsg.consoleSenderError(sender);
            return true;
        }

        Player player = (Player) sender;
        if (!(player.hasPermission("NKCore.home") || player.hasPermission("NKCore.admin"))) {
            ErrorMsg.noPermError(player);
            return true;
        }
        Location location = player.getBedSpawnLocation();
        if (location == null) {
            player.sendMessage(ChatColor.YELLOW + "You don't have a home set!");
            return false;
        }
        player.teleport(location);
        player.sendMessage(ChatColor.GREEN + "Welcome Back Home~~");
        return true;
    }

    public static boolean delhome(CommandSender sender) {

        return true;
    }

    public static boolean setspawn(CommandSender sender) {

        return true;
    }

    public static boolean spawn(CommandSender sender) {

        return true;
    }
}
