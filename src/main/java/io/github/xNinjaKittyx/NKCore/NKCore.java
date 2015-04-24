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
        saveYamls();
    }
    @Override
    // This is what happens when the Plugin is ENABLED
    public void onEnable() {

        //Load the Config File that stores things like spawn position and house positions.
        configFile = new File(getDataFolder(), "config.yml");

        try {
            firstRun();
        } catch (Exception e) {
            e.printStackTrace();
        }

        config = new YamlConfiguration();

        loadYamls();

        //This lets us have access to the descriptions in plugin.yml
        PluginDescriptionFile pdfFile = this.getDescription();
        getLogger().info("NKCore Initiated");
        getLogger().info("NKCore created by xNinjaKittyx");
        getLogger().info("NKCore Version: " + pdfFile.getVersion());
        getLogger().info("As of now, this is not reload friendly!");

        //Vault Init
        if (setupPermissions() == false) {
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

        //Command Handlers
        //this.getCommand("Name of Command").setExecutor(new cmdEx(this));
        this.getCommand("tp").setExecutor(new cmdEx(this));
        this.getCommand("tphere").setExecutor(new cmdEx(this));
        this.getCommand("tpa").setExecutor(new cmdEx(this));
        this.getCommand("tpaccept").setExecutor(new cmdEx(this));
        this.getCommand("tpdeny").setExecutor(new cmdEx(this));

        this.getCommand("sethome").setExecutor(new cmdEx(this));
        this.getCommand("home").setExecutor(new cmdEx(this));
        this.getCommand("setspawn").setExecutor(new cmdEx(this));
        this.getCommand("spawn").setExecutor(new cmdEx(this));

        this.getCommand("w").setExecutor(new cmdEx(this));
        this.getCommand("whisper").setExecutor(new cmdEx(this));
        this.getCommand("announce").setExecutor(new cmdEx(this));

        this.getCommand("who").setExecutor(new cmdEx(this));
        this.getCommand("players").setExecutor(new cmdEx(this));

        this.getCommand("kick").setExecutor(new cmdEx(this));
        this.getCommand("ban").setExecutor(new cmdEx(this));
        this.getCommand("unban").setExecutor(new cmdEx(this));
        this.getCommand("ipban").setExecutor(new cmdEx(this));
        this.getCommand("mute").setExecutor(new cmdEx(this));
        this.getCommand("unmute").setExecutor(new cmdEx(this));
        this.getCommand("gag").setExecutor(new cmdEx(this));

        this.getCommand("kill").setExecutor(new cmdEx(this));
        this.getCommand("fly").setExecutor(new cmdEx(this));
        this.getCommand("heal").setExecutor(new cmdEx(this));
        this.getCommand("god").setExecutor(new cmdEx(this));
        this.getCommand("repair").setExecutor(new cmdEx(this));
        this.getCommand("speed").setExecutor(new cmdEx(this));
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
