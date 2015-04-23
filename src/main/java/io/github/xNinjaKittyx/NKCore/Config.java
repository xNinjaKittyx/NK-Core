package io.github.xNinjaKittyx.NKCore;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.logging.Level;

/**
 * Created by Dan on 4/23/2015.
 */
public class Config {
    private FileConfiguration config = null;
    private File configFile = null;
    private NKCore nkcore;

    public void Config(NKCore nkcore) {
        this.nkcore = nkcore;
    }

    public void reloadCustomConfig() {
        if (configFile == null) {
            configFile = new File(nkcore.getDataFolder(), "customConfig.yml");
        }
        config = YamlConfiguration.loadConfiguration(configFile);

        // Look for defaults in the jar
        Reader defConfigStream = null;
        //Since this is an unsupported Method, It'll output wierd errors if done incorrectly.
        //TODO: Still need to figure this out.
        try {
            defConfigStream = new InputStreamReader(nkcore.getResource("customConfig.yml"), "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //If its not there, make the defaults.
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            config.setDefaults(defConfig);
        }
    }

    //Return the configFile
    public FileConfiguration getConfig() {
        if (config == null) {
            reloadCustomConfig();
        }
        return config;
    }

    public void saveConfig() {
        if (config == null || configFile == null) {
            return;
        }
        try {
            getConfig().save(configFile);
        } catch (IOException ex) {
            nkcore.getLogger().log(Level.SEVERE, "Could not save config to " + configFile, ex);
        }
    }

    public void saveDefaultConfig() {
        if (configFile == null) {
            configFile = new File(nkcore.getDataFolder(), "customConfig.yml");
        }
        if (!configFile.exists()) {
            nkcore.saveResource("customConfig.yml", false);
        }
    }
}
