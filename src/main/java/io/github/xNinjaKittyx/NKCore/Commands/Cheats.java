package io.github.xNinjaKittyx.NKCore.Commands;

import io.github.xNinjaKittyx.NKCore.ErrorMsg;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 4/16/2015.
 */
public class Cheats {

    //Flying Command
    public static boolean fly(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            ErrorMsg.consoleSenderError(sender);
            return true;
        }

        if (args.length > 1) {
            ErrorMsg.invalidParamsError(sender);
            return false;
        }

        Player player = (Player) sender;

        // Set Player to allow flight
        if (args.length == 0) {
            if (!player.hasPermission("NKCore.cheats.fly.me")) {
                ErrorMsg.noPermError(player);
                return true;
            }
            if (player.getAllowFlight()) {
                player.setAllowFlight(false);
                player.sendMessage(ChatColor.AQUA + "Flying Disabled!");
            } else {
                player.setAllowFlight(true);
                player.sendMessage(ChatColor.AQUA + "Flying Enabled!");
            }
            return true;
        }

        // Allow different player to fly
        else if (args.length == 1) {
            if (!player.hasPermission("NKCore.cheats.fly.others")) {
                ErrorMsg.noPermError(player);
                return true;
            }

            Player target = player.getServer().getPlayer(args[0]);
            if (target == null) {
                ErrorMsg.notOnlineError(player, args[0]);
                return false;
            }
            if (target.getAllowFlight()) {
                target.setAllowFlight(false);
                player.sendMessage(ChatColor.AQUA + "Flying Disabled for " + player.getName() + ChatColor.GREEN + "!");
            } else {
                player.setAllowFlight(true);
                player.sendMessage(ChatColor.AQUA + "Flying Enabled for " + player.getName() + ChatColor.GREEN + "!");
            }
            return true;
        }

        return false;
    }


    //This allows GodEvent to keep track of who's god and who's not.
    public static List<String> godToggleList = new ArrayList<>();

    public static boolean god(CommandSender sender, String[] args) {

        if (!(sender instanceof Player)) {
            if (args.length == 0) {
                ErrorMsg.consoleSenderError(sender);
                return true;
            }
        }

        Player player = (Player) sender;
        //Godmode myself
        if (args.length == 0) {
            if (!player.hasPermission("NKCore.cheats.god.me")) {
                ErrorMsg.noPermError(player);
                return true;
            }
            if (!(godToggleList.contains(player.getName()))) {
                godToggleList.add(player.getName());
                player.sendMessage(ChatColor.GREEN + "Godmode Enabled!");
            } else {
                godToggleList.remove(player.getName());
                player.sendMessage(ChatColor.GREEN + "Godmode Disabled!");
            }
            return true;
        }

        //Godmode someone else
        else if (args.length == 1) {
            if (!player.hasPermission("NKCore.cheats.god.others")) {
                ErrorMsg.noPermError(player);
                return true;
            }
            Player target = player.getServer().getPlayer(args[0]);
            if (target == null) {
                ErrorMsg.notOnlineError(player, args[0]);
                return false;
            } else {

                if (!(godToggleList.contains(target.getName()))) {
                    godToggleList.add(target.getName());
                    player.sendMessage(ChatColor.GREEN + "Godmode Enabled for " + target.getName() + ChatColor.GREEN + "!");
                } else {
                    godToggleList.remove(target.getName());
                    player.sendMessage(ChatColor.GREEN + "Godmode Disabled for" + target.getName() + ChatColor.GREEN + "!");
                }
                return true;
            }

        } else {
            ErrorMsg.invalidParamsError(sender);
            return false;
        }

    }

    private static void healPerson(Player player) {
        double health = player.getMaxHealth();
        player.setHealth(health);
        player.setFoodLevel(20);
        player.sendMessage(ChatColor.YELLOW + "Healed!");
    }

    public static boolean heal(CommandSender sender, String[] args) {
        Player player;
        if (args.length > 1) {
            ErrorMsg.invalidParamsError(sender);
            return false;
        }

        if (!(sender instanceof Player)) {
            if (args.length == 0) {
                ErrorMsg.consoleSenderError(sender);
                return false;
            } else if (args.length == 1) {

                Player target = Bukkit.getServer().getPlayer(args[0]);
                if (target == null) {
                    ErrorMsg.notOnlineError(sender, args[0]);
                    return false;
                }
                healPerson(target);
                return true;
            }
            return false;

        } else {
            player = (Player) sender;
            if (args.length == 0) {
                if (!player.hasPermission("TestPlugin.heal.me")) {
                    ErrorMsg.noPermError(player);
                    return true;
                }
                healPerson(player);
                return true;
            } else if (args.length == 1) {
                if (!player.hasPermission("TestPlugin.heal.others")) {
                    ErrorMsg.noPermError(player);
                    return true;
                }
                Player target = Bukkit.getServer().getPlayer(args[0]);
                if (target == null) {
                    ErrorMsg.notOnlineError(player, args[0]);
                    return false;
                }

                healPerson(target);

                return true;
            } else
                return false;
        }
    }
}
