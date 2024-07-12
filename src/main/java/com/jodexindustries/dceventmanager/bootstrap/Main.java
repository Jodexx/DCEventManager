package com.jodexindustries.dceventmanager.bootstrap;

import com.jodexindustries.dceventmanager.config.Config;
import com.jodexindustries.dceventmanager.utils.Tools;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.logging.Logger;

public interface Main {
    Config getAddonConfig();
    Tools getTools();
    Plugin getPlugin();
    Logger getLogger();
    File getDataFolder();
    void saveResource(String resource, boolean replace);
}
