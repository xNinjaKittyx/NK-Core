package io.github.xNinjaKittyx.NKCore.Commands;

import io.github.xNinjaKittyx.NKCore.ErrorMsg;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Daniel on 4/16/2015.
 */
public class Chat {

    public static boolean broadcast(CommandSender sender,String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (! (player.hasPermission("NKCore.chat.broadcast") || player.hasPermission("NKCore.admin") || player.hasPermission("NKCore.chat"))) {
                ErrorMsg.noPermError(player);
                return true;
            }
        }
        if (args.length == 0)
            return false;
        String message = StringUtils.join(args, " ", 0, args.length);
        String newMessage = ChatColor.translateAlternateColorCodes('&', message);
        Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "[CONSOLE]: " + ChatColor.RESET + newMessage);
        return true;

    }

    public static boolean whisper(CommandSender sender, String args[]) {
        if (!(sender instanceof Player)) {
            Player t = Bukkit.getPlayer(args[0]);
            if (t == null) {
                ErrorMsg.notOnlineError(sender,  args[0]);
                return true;
            } else {

                String message = StringUtils.join(args, ' ', 1, args.length);

                t.sendMessage(ChatColor.GRAY + "[Whisper: "
                        + ChatColor.LIGHT_PURPLE + "[Console]" + ChatColor.GRAY + "]: " + message);
                return true;
            }
        }
        Player player = (Player) sender;
        if (! (player.hasPermission("NKCore.chat.whisper") || player.hasPermission("NKCore.admin") || player.hasPermission("NKCore.chat"))) {
            ErrorMsg.noPermError(player);
            return true;
        }
        if (args.length < 2) {
            return false;
        }

        Player target = player.getServer().getPlayer(args[0]);
        if (target == null) {
            ErrorMsg.notOnlineError(player, args[0]);
            return true;
        } else {

            String message = StringUtils.join(args, ' ', 1, args.length);

            target.sendMessage(ChatColor.GRAY + "[Whisper: "
                    + player.getName() + "]: " + message);
            return true;
        }
    }
}
