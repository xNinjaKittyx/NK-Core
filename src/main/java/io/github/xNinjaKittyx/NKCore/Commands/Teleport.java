package io.github.xNinjaKittyx.NKCore.Commands;

import io.github.xNinjaKittyx.NKCore.ErrorMsg;
import io.github.xNinjaKittyx.NKCore.PEXRankCheck;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Daniel on 4/16/2015.
 */
public class Teleport {

    public static boolean tp(CommandSender sender, String[] args) {

        if (args.length == 1) {
            //Checking if it is a player sending the command
            if (!(sender instanceof Player)) {
                ErrorMsg.consoleSenderError(sender);
                return false;
            }

            Player player = (Player) sender;
            //Checking if the player has the permissions
            if (!player.hasPermission("NKCore.tp.me")) {
                ErrorMsg.noPermError(player);
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);

            //Check if Player is Online
            if (target == null) {
                ErrorMsg.notOnlineError(player, args[0]);
                return false;
            }

            //Check if Player is not him/herself
            if (target == player) {
                player.sendMessage(ChatColor.RED + "You can't TP to yourself!");
                return false;
            }

            //Check if Player's rank is higher or not.
            if (PEXRankCheck.isLess(player, target)) {
                ErrorMsg.highRankError(player);
                return false;
            }

            player.teleport(target);
            return true;
        }
        else if (args.length == 2) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                //Checking if the player has the permissions
                if (!player.hasPermission("NKCore.tp.other")) {
                    ErrorMsg.noPermError(player);
                    return true;
                }

            }
            Player source = Bukkit.getPlayer(args[0]);
            Player destination = Bukkit.getPlayer(args[1]);

            //Check if both players are Online
            if (source == null) {
                ErrorMsg.notOnlineError(sender, args[0]);
                return false;
            }
            else if(destination == null) {
                ErrorMsg.notOnlineError(sender, args[1]);
                return false;
            }

            //Check if both player and target are not the same person.
            if (source == destination) {
                sender.sendMessage(ChatColor.RED + "You can't teleport a player to the same player!");
                return false;
            }


            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (PEXRankCheck.isLess(player, source) && PEXRankCheck.isLess(player, destination)) {
                    ErrorMsg.highRankError(player);
                    return false;
                }
            }

            source.teleport(destination);
            return true;
        }
        else if (args.length == 3) {

            if (!(sender instanceof Player)) {
                ErrorMsg.consoleSenderError(sender);
                return true;
            }

            Player player = (Player) sender;
            if (!player.hasPermission("NKCore.tp.xyz")) {
                ErrorMsg.noPermError(player);
                return true;
            }

            try {
                final double x = Double.parseDouble(args[0]);
                final double y = Double.parseDouble(args[1]);
                final double z = Double.parseDouble(args[2]);
                Location location = new Location(player.getWorld(), x, y, z);
                player.teleport(location);
                // TP to coordinates (arg1,arg2,arg3)
            } catch (NumberFormatException ex) {
                player.sendMessage(ChatColor.RED + "Invalid Location");
                return false;
            }
            return true;
        }

        else {
            ErrorMsg.invalidParamsError(sender);
            return false;
        }
    }

    public static boolean tphere(CommandSender sender, String[] args) {

        //Checking if it is a player sending the command
        if (!(sender instanceof Player)) {
            ErrorMsg.consoleSenderError(sender);
            return false;
        }

        Player player = (Player) sender;
        //Checking if the player has the permissions
        if (!player.hasPermission("NKCore.tp.here")) {
            ErrorMsg.noPermError(player);
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);

        //Check if Player is Online
        if (target == null) {
            ErrorMsg.notOnlineError(player, args[0]);
            return false;
        }

        //Check if Player is not him/herself
        if (target == player) {
            player.sendMessage(ChatColor.RED + "You can't TP to yourself!");
            return false;
        }

        //Check if Player's rank is higher or not.
        if (PEXRankCheck.isLess(player, target)) {
            ErrorMsg.highRankError(player);
            return false;
        }

        target.teleport(player);
        return true;
    }

    public static boolean tpa(CommandSender sender, String[] args) {
        //TODO: TP Request system

        return true;
    }
    public static boolean tpaccept(CommandSender sender, String[] args) {
        //TODO: TP Request system Response - Accept

        return true;
    }
    public static boolean tpdeny(CommandSender sender, String[] args) {
        //TODO: TP Request system Response - Deny

        return true;
    }
}
