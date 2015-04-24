package io.github.xNinjaKittyx.NKCore.Commands;

import io.github.xNinjaKittyx.NKCore.ErrorMsg;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by Daniel on 4/16/2015.
 */
public class Info {
    public static boolean whois (CommandSender sender, String args[]) {
        if (!(sender instanceof Player)) {
            if (args.length > 1 || args.length == 0) {
                ErrorMsg.invalidParamsError(sender);
                return false;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                ErrorMsg.notOnlineError(sender, args[0]);
                return false;
            }
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "Name: " + ChatColor.WHITE + target.getName());
            if (target.getName() != target.getDisplayName())
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "Display Name: " + ChatColor.WHITE + target.getDisplayName());
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "UUID: " + target.getUniqueId());
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "Level" + ChatColor.WHITE + target.getLevel());
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "EXP: " + ChatColor.WHITE + target.getExp() + " / " + (target.getExpToLevel() + target.getExp()));
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "Time Online:" + ChatColor.WHITE + target.getPlayerTime());
            return true;
        }
        else {
            Player player = (Player) sender;
            if (! (player.hasPermission("NKCore.info.player") || player.hasPermission("NKCore.admin") || player.hasPermission("NKCore.info"))) {
                ErrorMsg.noPermError(player);
                return false;
            }
            if (args.length > 1 || args.length == 0) {
                ErrorMsg.invalidParamsError(sender);
                return false;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                ErrorMsg.notOnlineError(player, args[0]);
                return false;
            }
            player.sendMessage(ChatColor.AQUA + "========================================");
            player.sendMessage(ChatColor.LIGHT_PURPLE + "Name: " + ChatColor.WHITE + target.getName());
            if (target.getName() != target.getDisplayName())
                player.sendMessage(ChatColor.LIGHT_PURPLE + "Display Name: " + ChatColor.WHITE + target.getDisplayName());
            player.sendMessage(ChatColor.LIGHT_PURPLE + "UUID: " + target.getUniqueId());
            player.sendMessage(ChatColor.LIGHT_PURPLE + "Level" + ChatColor.WHITE + target.getLevel());
            player.sendMessage(ChatColor.LIGHT_PURPLE + "EXP: " + ChatColor.WHITE + target.getExp() + " / " + (target.getExpToLevel() + target.getExp()));
            player.sendMessage(ChatColor.LIGHT_PURPLE + "Time Online:" + ChatColor.WHITE + target.getPlayerTime());
            player.sendMessage(ChatColor.AQUA + "========================================");
            //player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.WHITE +);
            return true;
        }
    }

    public static boolean players (CommandSender sender, String args[]) {
        //ArrayList that stores all online players.
        ArrayList<String> onlinePlayers = new ArrayList<String>();
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if ( (player.hasPermission("TestPlugin.who") || player.hasPermission("NKCore.admin") || player.hasPermission("NKCore.info"))) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    onlinePlayers.add(p.getName());
                }
                // This string may be used if the current setup does not work.
                // String players = StringUtils.join(onlinePlayers.toArray(), ' ', 0, onlinePlayers.size() - 1);
                sender.sendMessage(ChatColor.YELLOW + "There are " + Bukkit.getOnlinePlayers().size() + " online players:"
                        + ChatColor.WHITE + onlinePlayers.toString());
                onlinePlayers.clear();
            }
            else {
                ErrorMsg.noPermError(player);
            }
        } else {
            for (Player p : Bukkit.getOnlinePlayers()) {
                onlinePlayers.add(p.getName());
            }
            sender.sendMessage(ChatColor.YELLOW + "There are " + Bukkit.getOnlinePlayers().size() + " online players:"
                    + ChatColor.WHITE + onlinePlayers.toString());
            onlinePlayers.clear();
        }
        return true;
    }
}
