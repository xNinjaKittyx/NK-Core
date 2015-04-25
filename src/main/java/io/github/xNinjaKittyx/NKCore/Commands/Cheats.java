package io.github.xNinjaKittyx.NKCore.Commands;

import io.github.xNinjaKittyx.NKCore.ErrorMsg;
import io.github.xNinjaKittyx.NKCore.PEXRankCheck;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
            if (! (player.hasPermission("NKCore.cheat.fly.me") || player.hasPermission("NKCore.admin")
                    || player.hasPermission("NKCore.cheat") || player.hasPermission("NKCore.cheat.fly"))) {
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
            if (! (player.hasPermission("NKCore.cheat.fly.others") || player.hasPermission("NKCore.admin")
                    || player.hasPermission("NKCore.cheat") || player.hasPermission("NKCore.cheat.fly"))) {
                ErrorMsg.noPermError(player);
                return true;
            }

            Player target = player.getServer().getPlayer(args[0]);
            if (target == null) {
                ErrorMsg.notOnlineError(player, args[0]);
                return true;
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
            if (! (player.hasPermission("NKCore.cheat.god.me") || player.hasPermission("NKCore.admin")
                    || player.hasPermission("NKCore.cheat") || player.hasPermission("NKCore.cheat.god"))) {
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
            if (! (player.hasPermission("NKCore.cheat.god.others") || player.hasPermission("NKCore.admin")
                    || player.hasPermission("NKCore.cheat") || player.hasPermission("NKCore.cheat.god"))) {
                ErrorMsg.noPermError(player);
                return true;
            }
            Player target = player.getServer().getPlayer(args[0]);
            if (target == null) {
                ErrorMsg.notOnlineError(player, args[0]);
                return true;
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
                return true;
            } else if (args.length == 1) {

                Player target = Bukkit.getServer().getPlayer(args[0]);
                if (target == null) {
                    ErrorMsg.notOnlineError(sender, args[0]);
                    return true;
                }
                healPerson(target);
                return true;
            } else {
                ErrorMsg.invalidParamsError(sender);
                return false;
            }

        } else {
            player = (Player) sender;
            if (args.length == 0) {
                if (! (player.hasPermission("NKCore.cheat.heal.me") || player.hasPermission("NKCore.admin")
                        || player.hasPermission("NKCore.cheat") || player.hasPermission("NKCore.cheat.heal"))) {
                    ErrorMsg.noPermError(player);
                    return true;
                }
                healPerson(player);
                return true;
            } else if (args.length == 1) {
                if (! (player.hasPermission("NKCore.cheat.heal.me") || player.hasPermission("NKCore.admin")
                        || player.hasPermission("NKCore.cheat") || player.hasPermission("NKCore.cheat.heal"))) {
                    ErrorMsg.noPermError(player);
                    return true;
                }
                Player target = Bukkit.getServer().getPlayer(args[0]);
                if (target == null) {
                    ErrorMsg.notOnlineError(player, args[0]);
                    return true;
                }

                healPerson(target);

                return true;
            } else {
                ErrorMsg.invalidParamsError(sender);
                return false;
            }
        }
    }
    public static boolean kill(CommandSender sender, String args[]) {


        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!(player.hasPermission("NKCore.kill") || player.hasPermission("NKCore.admin")
                    || player.hasPermission("NKCore.cheat") || player.hasPermission("NKCore.cheat.kill"))) {
                ErrorMsg.noPermError(player);
                return true;
            }
            Player target = player.getServer().getPlayer(args[0]);
            if (args.length < 1) {
                ErrorMsg.invalidParamsError(sender);
                return false;
            }

            if (target == null) {
                ErrorMsg.notOnlineError(player, args[0]);
                return true;
            } else {

                if (PEXRankCheck.isLess(player, target)) {
                    ErrorMsg.highRankError(player);
                    return true;
                }
                target.setHealth(0);
                return true;
            }
        } else {
            Player target = Bukkit.getServer().getPlayer(args[0]);
            if (args.length < 1) {
                ErrorMsg.invalidParamsError(sender);
                return false;
            }
            if (target == null) {
                ErrorMsg.notOnlineError(sender, args[0]);
                return true;
            } else {

                target.setHealth(0);
            }
            return true;
        }
    }

    public static boolean repair(CommandSender sender) {

        if (!(sender instanceof Player)) {
            ErrorMsg.consoleSenderError(sender);
            return true;
        }

        Player player = (Player) sender;
        if (!(player.hasPermission("NKCore.cheat.repair") || player.hasPermission("NKCore.admin") || player.hasPermission("NKCore.cheat"))) {
            ErrorMsg.noPermError(player);
            return true;
        }
        ItemStack i = player.getItemInHand();
        i.setDurability((short)0);
        player.sendMessage(ChatColor.GREEN + "Repaired!");
        return true;
    }

    public static boolean speed(CommandSender sender, String args[]) {
        if (!(sender instanceof Player)) {
        ErrorMsg.consoleSenderError(sender);
        return true;
        }

        Player player = (Player) sender;
        if (!(player.hasPermission("NKCore.cheat.speed") || player.hasPermission("NKCore.admin") || player.hasPermission("NKCore.cheat"))) {
            ErrorMsg.noPermError(player);
            return true;
        }
        //TODO: SPEED
        return true;
    }
}
