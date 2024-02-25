package com.jodexindustries.dceventmanager;

import com.jodexindustries.dceventmanager.command.MainCommand;
import com.jodexindustries.dceventmanager.data.CaseEvent;
import com.jodexindustries.dceventmanager.data.CasedEventData;
import com.jodexindustries.dceventmanager.data.DefaultEventData;
import com.jodexindustries.dceventmanager.data.interfaces.EventData;
import com.jodexindustries.dceventmanager.listener.EventListener;
import com.jodexindustries.donatecase.api.SubCommandManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Main extends JavaPlugin {
    public static Main instance;
    public static Map<CaseEvent, List<EventData>> eventMap = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        SubCommandManager.registerSubCommand("dcem", new MainCommand());
        loadEvents();
    }

    @Override
    public void onDisable() {
        SubCommandManager.unregisterSubCommand("dcem");
    }
    public void loadEvents() {
        eventMap.clear();
        ConfigurationSection section = getConfig().getConfigurationSection("Events");
        if(section == null) return;
        for (String event : section.getKeys(false)) {
            String eventName = section.getString(event + ".Event");
            if(eventName == null || eventName.isEmpty()) {
                getLogger().warning("Event " + event + " does not have an Event parameter");
                continue;
            }
            CaseEvent caseEvent = CaseEvent.fromString(eventName);
            if(caseEvent == null) {
                getLogger().warning("Event " + event + " not founded!");
                continue;
            }
            List<String> actions = section.getStringList(event + ".Actions");
            String caseName = section.getString(event + ".Case");
            EventData data;
            if(caseName == null || caseName.isEmpty()) {
                data = new DefaultEventData(caseEvent, actions);
            } else {
                data = new CasedEventData(caseEvent, actions,caseName);
            }
            List<EventData> list = new ArrayList<>();
            if(eventMap.get(caseEvent) != null) {
                list = eventMap.get(caseEvent);
            }
            list.add(data);
            eventMap.put(caseEvent, list);
        }
    }

}
