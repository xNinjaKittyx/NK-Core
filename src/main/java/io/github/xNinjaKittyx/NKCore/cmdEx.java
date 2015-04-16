package io.github.xNinjaKittyx.NKCore;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Daniel on 4/16/2015.
 */
public class cmdEx implements CommandExecutor{

    private final NKCore plugin;

    public cmdEx(NKCore plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        // TP COMMANDS

        if (cmd.getName().equalsIgnoreCase("tp"));

        else if (cmd.getName().equalsIgnoreCase("tphere"));

        else if (cmd.getName().equalsIgnoreCase("tpa"));

        else if (cmd.getName().equalsIgnoreCase("tpaccept"));

        else if (cmd.getName().equalsIgnoreCase("tpdeny"));

        // Waypoints

        else if (cmd.getName().equalsIgnoreCase("sethome"));

        else if (cmd.getName().equalsIgnoreCase("home"));

        else if (cmd.getName().equalsIgnoreCase("setspawn"));

        else if (cmd.getName().equalsIgnoreCase("spawn"));

        // Player Chat Commands

        else if (cmd.getName().equalsIgnoreCase("w") || cmd.getName().equalsIgnoreCase("whisper"));

        else if (cmd.getName().equalsIgnoreCase("announce"));

        //Player Info Commands

        else if (cmd.getName().equalsIgnoreCase("who"));

        else if (cmd.getName().equalsIgnoreCase("players"));

        // Player Management

        else if (cmd.getName().equalsIgnoreCase("kick"));

        else if (cmd.getName().equalsIgnoreCase("ban"));

        else if (cmd.getName().equalsIgnoreCase("unban"));

        else if (cmd.getName().equalsIgnoreCase("ipban"));

        else if (cmd.getName().equalsIgnoreCase("mute"));

        else if (cmd.getName().equalsIgnoreCase("unmute"));

        else if (cmd.getName().equalsIgnoreCase("gag"));

        // Cheats

        else if (cmd.getName().equalsIgnoreCase("kill"));

        else if (cmd.getName().equalsIgnoreCase("fly"));

        else if (cmd.getName().equalsIgnoreCase("heal"));

        else if (cmd.getName().equalsIgnoreCase("god"));

        else if (cmd.getName().equalsIgnoreCase("repair"));

        else if (cmd.getName().equalsIgnoreCase("speed"));

        if (cmd.getName().equalsIgnoreCase("")) {
            return false;
        }

        sender.sendMessage("Something went wrong with the command. This message means that there's a boolean leak in the command.");
        return false;

    }
}