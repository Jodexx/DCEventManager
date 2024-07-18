package com.jodexindustries.dceventmanager.event;

import com.jodexindustries.dceventmanager.bootstrap.Main;
import com.jodexindustries.dceventmanager.data.EventData;
import com.jodexindustries.dceventmanager.data.Placeholder;
import com.jodexindustries.dceventmanager.utils.Reflection;
import com.jodexindustries.donatecase.api.ActionManager;
import com.jodexindustries.donatecase.api.data.CaseAction;
import com.jodexindustries.donatecase.api.data.CaseData;
import com.jodexindustries.donatecase.api.events.DonateCaseReloadEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
            main.getTools().load();
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

            if (data.getSlot() != -1 && slot != null && slot != data.getSlot()) {
                continue;
            }

            executeActions(event, replaceList(data.getActions(), getPlaceholders(event)
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

    private void executeActions(Event event, List<String> actions) {
        for (String action : actions) {

            // new implementation start
            OfflinePlayer player = null;
            if(Reflection.hasVar(event, "getWhoClicked")) {
                player = Reflection.getVar(event, "getWhoClicked", OfflinePlayer.class);
            } else if(Reflection.hasVar(event, "getPlayer")) {
                player = Reflection.getVar(event, "getPlayer", OfflinePlayer.class);
            }

            if(player == null) player = Bukkit.getOfflinePlayer(UUID.randomUUID());

            String temp = ActionManager.getByStart(action);

            if(temp != null) {
                String context = action.replace(temp, "").trim();
                CaseAction caseAction = ActionManager.getRegisteredAction(temp);
                if (caseAction != null) {
                    caseAction.execute(player, context, 0);
                    continue;
                }
            }
            // new implementation end

            // old implementation start
            if (action.startsWith("[invoke]")) {
                action = action.replaceFirst("\\[invoke] ", "");
                Reflection.invokeMethodChain(event, action);
            }
            // old implementation end
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
