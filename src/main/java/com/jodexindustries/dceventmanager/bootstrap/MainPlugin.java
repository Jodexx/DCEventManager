package com.jodexindustries.dceventmanager.bootstrap;

import com.jodexindustries.dceventmanager.config.Config;
import com.jodexindustries.dceventmanager.utils.Tools;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class MainPlugin extends JavaPlugin implements Main {
    public Config config;
    public Tools t;

    @Override
    public void onEnable() {
        t = new Tools(this);
        config = new Config(this);
        t.load();
    }

    @Override
    public void onDisable() {
        t.unload();
    }
    @Override
    public Config getAddonConfig() {
        return config;
    }

    @Override
    public Tools getTools() {
        return t;
    }

    @Override
    public Plugin getPlugin() {
        return this;
    }

}
