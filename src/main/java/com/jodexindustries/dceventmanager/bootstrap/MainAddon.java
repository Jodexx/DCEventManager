package com.jodexindustries.dceventmanager.bootstrap;

import com.jodexindustries.dceventmanager.config.Config;
import com.jodexindustries.dceventmanager.utils.Tools;
import com.jodexindustries.donatecase.api.addon.internal.InternalJavaAddon;
import org.bukkit.plugin.Plugin;

public final class MainAddon extends InternalJavaAddon implements Main {
    public static Tools t;
    public Config config;

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
        return getDonateCase();
    }
}
