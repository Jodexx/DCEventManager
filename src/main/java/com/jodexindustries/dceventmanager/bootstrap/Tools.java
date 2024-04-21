package com.jodexindustries.dceventmanager.bootstrap;

import com.jodexindustries.dceventmanager.data.CaseEvent;
import com.jodexindustries.dceventmanager.data.CasedEventData;
import com.jodexindustries.dceventmanager.data.DefaultEventData;
import com.jodexindustries.dceventmanager.data.GuiClickEventData;
import com.jodexindustries.dceventmanager.data.interfaces.EventData;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Tools {
    public static Map<CaseEvent, List<EventData>> eventMap = new HashMap<>();
    private final Logger logger;
    private final Main main;
    public Tools(Main main, Logger logger) {
        this.logger = logger;
        this.main = main;

    }
    public void loadEvents() {
        eventMap.clear();
        ConfigurationSection section = main.getAddonConfig().getConfig().getConfigurationSection("Events");
        if(section == null) return;
        for (String event : section.getKeys(false)) {
            String eventName = section.getString(event + ".Event");
            if(eventName == null || eventName.isEmpty()) {
                logger.warning("Event " + event + " does not have an Event parameter");
                continue;
            }
            CaseEvent caseEvent = CaseEvent.fromString(eventName);
            if(caseEvent == null) {
                logger.warning("Event " + event + " not founded!");
                continue;
            }
            List<String> actions = section.getStringList(event + ".Actions");
            String caseName = section.getString(event + ".Case");
            int slot = section.getInt(event + ".Slot", -1);
            EventData data;
            switch (caseEvent.type) {
                case CASED:
                    data = new CasedEventData(caseEvent,actions, caseName);
                    break;
                case GUI:
                    data = new GuiClickEventData(caseEvent, actions, caseName, slot);
                    break;
                default:
                    data = new DefaultEventData(caseEvent, actions);
                    break;
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
