package com.jodexindustries.dceventmanager.bootstrap;

import com.jodexindustries.dceventmanager.command.MainCommand;
import com.jodexindustries.dceventmanager.config.Config;
import com.jodexindustries.dceventmanager.listener.EventListener;
import com.jodexindustries.donatecase.api.SubCommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MainPlugin extends JavaPlugin implements Main {
    public static MainPlugin instance;
    public Config config;
    public Tools t;

    @Override
    public void onEnable() {
        instance = this;
        t = new Tools(this, getLogger());
        config = new Config(getDataFolder());
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        SubCommandManager.registerSubCommand("dcem", new MainCommand(this));
        t.loadEvents();
    }

    @Override
    public void onDisable() {
        SubCommandManager.unregisterSubCommand("dcem");
    }
    @Override
    public Config getAddonConfig() {
        return config;
    }

    @Override
    public Tools getTools() {
        return t;
    }

}
