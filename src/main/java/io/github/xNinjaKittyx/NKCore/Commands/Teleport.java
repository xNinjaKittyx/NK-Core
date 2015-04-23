package io.github.xNinjaKittyx.NKCore.Commands;

import io.github.xNinjaKittyx.NKCore.NKCore;
import io.github.xNinjaKittyx.NKCore.PEXRankCheck;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;

/**
 * Created by Daniel on 4/16/2015.
 */
public class Teleport {

    public static boolean tp(CommandSender sender, String[] args) {

        if (args.length == 1) {
            //Checking if it is a player sending the command
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Console can't use this command!");
                return false;
            }

            //Checking if the player has the permissions
            if (!sender.hasPermission("NKCore.tp.me")) {
                sender.sendMessage(ChatColor.RED + "You don't have permission!");
                return true;
            }
            Player player = (Player) sender;
            Player target = Bukkit.getPlayer(args[0]);

            //Check if Player is Online
            if (target == null) {
                player.sendMessage(ChatColor.RED + args[0] + " is not Online");
                return false;
            }

            //Check if Player is not him/herself
            if (target == player) {
                player.sendMessage(ChatColor.RED + "You can't TP to yourself!");
                return false;
            }

            //Check if Player's rank is higher or not.
            if (PEXRankCheck.isLess(player, target)) {
                sender.sendMessage(ChatColor.RED
                        + "You can't TP to a player with a higher rank than you! You must send a tp request instead!");
                return false;
            }

            player.teleport(target);
            return true;
        }
        else if (args.length == 2) {

            //Checking if the player has the permissions
            if (!sender.hasPermission("NKCore.tp.other")) {
                sender.sendMessage(ChatColor.RED + "You don't have permission!");
                return true;
            }
            Player s = (Player) sender;
            Player player = Bukkit.getPlayer(args[0]);
            Player target = Bukkit.getPlayer(args[1]);

            //Check if both players are Online
            if (target == null || player == null) {
                player.sendMessage(ChatColor.RED + args[0] + " is not Online");
                return false;
            }

            //Check if both player and target are not the same person.
            if (target == player) {
                player.sendMessage(ChatColor.RED + "Teleporting a Player to itself makes no sense...");
                return false;
            }


            if (PEXRankCheck.isLess(s, player) && PEXRankCheck.isLess(s, target)) {
                sender.sendMessage(ChatColor.RED
                        + "You can't TP a player with a higher rank than you!");
                return false;
            }

            player.teleport(target);
            return true;
        }
        else if (args.length == 3) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.GRAY + "Cannot be used by Non-Player");
                return true;
            }

            if (!sender.hasPermission("NKCore.tp.xyz")) {
                sender.sendMessage(ChatColor.RED + "You don't have permission!");
                return true;
            }
            Player player = (Player) sender;
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
            sender.sendMessage(ChatColor.RED + "Invalid Arguments!");
            return false;
        }
    }

    public static boolean tphere(CommandSender sender, String[] args) {

        //Checking if it is a player sending the command
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Console can't use this command!");
            return false;
        }

        //Checking if the player has the permissions
        if (!sender.hasPermission("NKCore.tp.here")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission!");
            return true;
        }
        Player player = (Player) sender;
        Player target = Bukkit.getPlayer(args[0]);

        //Check if Player is Online
        if (target == null) {
            player.sendMessage(ChatColor.RED + args[0] + " is not Online");
            return false;
        }

        //Check if Player is not him/herself
        if (target == player) {
            player.sendMessage(ChatColor.RED + "You can't TP to yourself!");
            return false;
        }

        //Check if Player's rank is higher or not.
        if (PEXRankCheck.isLess(player, target)) {
            sender.sendMessage(ChatColor.RED
                    + "You can't TP to a player with a higher rank than you! You must send a tp request instead!");
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
