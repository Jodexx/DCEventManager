package com.jodexindustries.dceventmanager.event;

import com.jodexindustries.dceventmanager.bootstrap.Main;
import com.jodexindustries.dceventmanager.data.EventData;
import com.jodexindustries.dceventmanager.data.Placeholder;
import com.jodexindustries.dceventmanager.utils.Reflection;
import com.jodexindustries.donatecase.api.data.CaseData;
import com.jodexindustries.donatecase.api.events.DonateCaseReloadEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.jodexindustries.dceventmanager.utils.Tools.eventMap;
import static com.jodexindustries.dceventmanager.utils.Tools.placeholderMap;

public class DCEventExecutor implements EventExecutor {
    public final String caseEvent;
    public final Main main;

    public DCEventExecutor(String caseEvent, Main main) {
        this.caseEvent = caseEvent;
        this.main = main;
    }

    @Override
    public void execute(@NotNull Listener listener, @NotNull Event event) {
        if (event instanceof DonateCaseReloadEvent) {
            main.getAddonConfig().reloadConfig();
            main.getAddonConfig().reloadPlaceholders();
            main.getTools().loadPlaceholders();
            main.getTools().loadEvents();
            main.getLogger().info("Config reloaded");
        }

        final List<EventData> list = eventMap.getOrDefault(caseEvent.toUpperCase(), new ArrayList<>());
        String caseType = Reflection.getVar(event, "getCaseType", String.class);
        CaseData caseData;
        Integer slot = Reflection.getVar(event, "getSlot", Integer.class);

        if (caseType == null) {
            caseData = Reflection.getVar(event, "getCaseData", CaseData.class);
            if (caseData != null) {
                caseType = caseData.getCaseType();
            }
        }

        for (EventData data : list) {
            if (data.getCase() != null && !data.getCase().equalsIgnoreCase(caseType)) {
                continue;
            }

            if (slot != null && slot != data.getSlot()) {
                continue;
            }

            executeActions(replaceList(data.getActions(), getPlaceholders(event)
            ));

        }
    }

    private String[] getPlaceholders(Event event) {
        List<Placeholder> placeholders = placeholderMap.getOrDefault(caseEvent.toUpperCase(), new ArrayList<>());
        String[] values = new String[placeholders.size() * 2];

        int index = 0;
        for (Placeholder placeholder : placeholders) {
            values[index] = placeholder.getName();
            values[index + 1] = String.valueOf(Reflection.invokeMethodChain(event, placeholder.getMethod()));

            index += 2;
        }
        return values;
    }

    private void executeActions(List<String> actions) {
        for (String action : actions) {
            if (action.startsWith("[command]")) {
                action = action.replaceFirst("\\[command] ", "");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), rc(action));
            }
            if (action.startsWith("[broadcast]")) {
                action = action.replaceFirst("\\[broadcast] ", "");
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.sendMessage(rc(action));
                }
            }
        }
    }

    public static String rc(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public List<String> replaceList(List<String> list, String... args) {
        List<String> newList = new ArrayList<>();
        for (String line : list) {
            for (int i = 0; i+1 < args.length; i+=2) {
                String repl = args[i+1];
                if(repl != null) {
                    line = line.replace(args[i], repl);
                }
            }
            newList.add(rc(line));
        }
        return newList;
    }
}
