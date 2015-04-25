package io.github.xNinjaKittyx.NKCore;

import io.github.xNinjaKittyx.NKCore.Commands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

/**
 * Created by Daniel on 4/16/2015.
 */
public class cmdEx extends AbstractCommand implements CommandExecutor {

    public cmdEx (String command, String usage, String description) {
        super(command, usage, description);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        // TP COMMANDS

        //Basic TP Command
        if (cmd.getName().equalsIgnoreCase("tp")) {
            return Teleport.tp(sender, args);
        }

        //TP Player to your position
        else if (cmd.getName().equalsIgnoreCase("tphere")) {
            return Teleport.tphere(sender, args);
        }

        else if (cmd.getName().equalsIgnoreCase("tpa")) {
            return Teleport.tpa(sender, args);
        }

        else if (cmd.getName().equalsIgnoreCase("tpaccept")){
            return Teleport.tpaccept(sender, args);
        }

        else if (cmd.getName().equalsIgnoreCase("tpdeny")){
            return Teleport.tpdeny(sender, args);
        }

        // Waypoints

        else if (cmd.getName().equalsIgnoreCase("sethome")) {
            return Waypoints.sethome(sender);
        }

        else if (cmd.getName().equalsIgnoreCase("home")) {
            return Waypoints.home(sender);
        }

        else if (cmd.getName().equalsIgnoreCase("delhome")) {
            return Waypoints.delhome(sender);
        }

        else if (cmd.getName().equalsIgnoreCase("setspawn")) {
            return Waypoints.setspawn(sender);
        }

        else if (cmd.getName().equalsIgnoreCase("spawn")) {
            return Waypoints.spawn(sender);
        }

        // Player Chat Commands

        else if (cmd.getName().equalsIgnoreCase("w") || cmd.getName().equalsIgnoreCase("whisper")) {
            return Chat.whisper(sender, args);
        }

        else if (cmd.getName().equalsIgnoreCase("say") || cmd.getName().equalsIgnoreCase("broadcast")) {
            return Chat.broadcast(sender, args);
        }
        //Player Info Commands

        else if (cmd.getName().equalsIgnoreCase("whois")) {
            return Info.whois(sender, args);
        }

        else if (cmd.getName().equalsIgnoreCase("players") || cmd.getName().equalsIgnoreCase("who")) {
            return Info.players(sender, args);
        }

        // Player Management

        else if (cmd.getName().equalsIgnoreCase("kick")) {
            return Management.kick();
        }

        else if (cmd.getName().equalsIgnoreCase("ban")) {
            return Management.ban();
        }

        else if (cmd.getName().equalsIgnoreCase("unban")) {
            return Management.unban();
        }

        else if (cmd.getName().equalsIgnoreCase("ipban")) {
            return Management.ipban();
        }

        else if (cmd.getName().equalsIgnoreCase("mute")) {
            return Management.mute();
        }

        else if (cmd.getName().equalsIgnoreCase("unmute")) {
            return Management.unmute();
        }

        else if (cmd.getName().equalsIgnoreCase("gag")) {
            return Management.gag();
        }

        // Cheats

        else if (cmd.getName().equalsIgnoreCase("kill")) {
            return Cheats.kill(sender, args);
        }

        else if (cmd.getName().equalsIgnoreCase("fly")) {
            return Cheats.fly(sender, args);
        }

        else if (cmd.getName().equalsIgnoreCase("heal")) {
            return Cheats.heal(sender, args);
        }

        else if (cmd.getName().equalsIgnoreCase("god")) {
            return Cheats.god(sender, args);
        }

        else if (cmd.getName().equalsIgnoreCase("repair")) {
            return Cheats.repair(sender);
        }

        else if (cmd.getName().equalsIgnoreCase("speed")) {
            return Cheats.speed(sender, args);
        }

        else {
            sender.sendMessage("Invalid Command!");
            return true;
        }

    }
}
