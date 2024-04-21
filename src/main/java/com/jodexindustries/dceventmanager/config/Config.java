package com.jodexindustries.dceventmanager.config;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config {
    private YamlConfiguration config;
    private final File file;
    public Config(File dataFolder) {
        file = new File(dataFolder, "config.yml");
        config = YamlConfiguration.loadConfiguration(file);
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(file);
    }
}
