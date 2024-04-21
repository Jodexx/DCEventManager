package com.jodexindustries.dceventmanager.bootstrap;

import com.jodexindustries.dceventmanager.command.MainCommand;
import com.jodexindustries.dceventmanager.config.Config;
import com.jodexindustries.dceventmanager.listener.EventListener;
import com.jodexindustries.donatecase.api.SubCommandManager;
import com.jodexindustries.donatecase.api.addon.JavaAddon;
import org.bukkit.Bukkit;

import java.io.File;

public final class MainAddon extends JavaAddon implements Main {
    public static MainAddon instance;
    public static Tools t;
    public Config config;

    @Override
    public void onEnable() {
        instance = this;
        t = new Tools(this, getLogger());
        config = new Config(getDataFolder());
        if(!new File(getDataFolder(), "config.yml").exists()) {
            saveResource("config.yml", false);
        }
        Bukkit.getServer().getPluginManager().registerEvents(new EventListener(), getDonateCase());
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
