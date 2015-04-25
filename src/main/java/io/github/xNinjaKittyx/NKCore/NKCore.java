package io.github.xNinjaKittyx.NKCore;

import io.github.xNinjaKittyx.NKCore.Events.ChatEvent;
import io.github.xNinjaKittyx.NKCore.Events.GodEvent;
import io.github.xNinjaKittyx.NKCore.Events.MOTDEvent;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

/**
 * Created by Daniel on 4/16/2015.
 */
public class NKCore extends JavaPlugin {


    //These are the Vault variables
    public static Permission perms = null;
    public static Chat chat = null;

    //Config File
    File configFile;
    FileConfiguration config;

    @Override
    // This is what happens when the plugin is DISABLED
    public void onDisable() {
        getLogger().info("NKCore Disabled");
        getLogger().info("Thank you for using NKCore");
        //saveYamls();
    }
    @Override
    // This is what happens when the Plugin is ENABLED
    public void onEnable() {
        /*
        //Load the Config File that stores things like spawn position and house positions.
        configFile = new File(getDataFolder(), "config.yml");

        try {
            firstRun();
        } catch (Exception e) {
            e.printStackTrace();
        }

        config = new YamlConfiguration();

        loadYamls();
        */

        //This lets us have access to the descriptions in plugin.yml
        PluginDescriptionFile pdfFile = this.getDescription();
        getLogger().info("NKCore Initiated");
        getLogger().info("NKCore created by xNinjaKittyx");
        getLogger().info("NKCore Version: " + pdfFile.getVersion());
        getLogger().info("As of now, this is not reload friendly!");

        //Vault Init
        if (!setupPermissions()) {
            getLogger().severe("No Vault Dependency Found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupChat();

        if(!isPexOn()) {
            getLogger().severe("No Pex Dependency Found!");
            return;
        }

        //Event Handlers
        //new xEvent(this);

        new MOTDEvent(this);
        new ChatEvent(this);
        new GodEvent(this);

        // Command Handlers
        AbstractCommand newCmd = new cmdEx("tp", "/<command> [args]", "TODO");
        newCmd.register();
        newCmd = new cmdEx("tp", "/<command> [args]", "TODO");
        newCmd.register();
        newCmd = new cmdEx("tphere", "/<command> [args]", "TODO");
        newCmd.register();
        newCmd = new cmdEx("tpa", "/<command> [args]", "TODO");
        newCmd.register();
        newCmd = new cmdEx("tpaccept", "/<command> [args]", "TODO");
        newCmd.register();
        newCmd = new cmdEx("tpdeny", "/<command> [args]", "TODO");
        newCmd.register();

        newCmd = new cmdEx("sethome", "/<command> [args]", "TODO");
        newCmd.register();
        newCmd = new cmdEx("home", "/<command> [args]", "TODO");
        newCmd.register();
        newCmd = new cmdEx("setspawn", "/<command> [args]", "TODO");
        newCmd.register();
        newCmd = new cmdEx("spawn", "/<command> [args]", "TODO");
        newCmd.register();

        newCmd = new cmdEx("w", "/<command> [args]", "TODO");
        newCmd.register();
        newCmd = new cmdEx("whisper", "/<command> [args]", "TODO");
        newCmd.register();
        newCmd = new cmdEx("say", "/<command> [args]", "TODO");
        newCmd.register();
        newCmd = new cmdEx("broadcast", "/<command> [args]", "TODO");
        newCmd.register();

        newCmd = new cmdEx("whois", "/<command> [args]", "TODO");
        newCmd.register();
        newCmd = new cmdEx("players", "/<command> [args]", "TODO");
        newCmd.register();

        newCmd = new cmdEx("kick", "/<command> [args]", "TODO");
        newCmd.register();
        newCmd = new cmdEx("ban", "/<command> [args]", "TODO");
        newCmd.register();
        newCmd = new cmdEx("unban", "/<command> [args]", "TODO");
        newCmd.register();
        newCmd = new cmdEx("ipban", "/<command> [args]", "TODO");
        newCmd.register();
        newCmd = new cmdEx("mute", "/<command> [args]", "TODO");
        newCmd.register();
        newCmd = new cmdEx("unmute", "/<command> [args]", "TODO");
        newCmd.register();
        newCmd = new cmdEx("gag", "/<command> [args]", "TODO");
        newCmd.register();

        newCmd = new cmdEx("kill", "/<command> [args]", "TODO");
        newCmd.register();
        newCmd = new cmdEx("fly", "/<command> [args]", "TODO");
        newCmd.register();
        newCmd = new cmdEx("heal", "/<command> [args]", "TODO");
        newCmd.register();
        newCmd = new cmdEx("god", "/<command> [args]", "TODO");
        newCmd.register();
        newCmd = new cmdEx("repair", "/<command> [args]", "TODO");
        newCmd.register();
        newCmd = new cmdEx("speed", "/<command> [args]", "TODO");
        newCmd.register();


    }

    // Functions to manage YAML config Files
    private void firstRun() throws Exception {
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            copy(getResource("config.yml"), configFile);
        }
    }

    private void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))> 0) {
                out.write(buf,0,len);
            }
            out.close();
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadYamls() {
        try {
            config.load(configFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveYamls() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //These functions help determine dependencies if they exist or not.
    private boolean setupPermissions() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Permission> rsp = getServer()
                .getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager()
                .getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean isPexOn() {
        if (getServer().getPluginManager().getPlugin("PermissionsEx") != null)
            return true;
        else
            return false;

    }


}
