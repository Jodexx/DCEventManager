package com.jodexindustries.dceventmanager.listener;

import com.jodexindustries.dceventmanager.data.CaseEvent;
import com.jodexindustries.dceventmanager.data.CasedEventData;
import com.jodexindustries.dceventmanager.data.interfaces.CaseEventData;
import com.jodexindustries.dceventmanager.data.interfaces.EventData;
import com.jodexindustries.donatecase.api.Case;
import com.jodexindustries.donatecase.api.data.CaseData;
import com.jodexindustries.donatecase.api.events.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

import static com.jodexindustries.dceventmanager.Main.eventMap;

public class EventListener implements Listener {
    @EventHandler
    public void onAnimationEnd(AnimationEndEvent e) {
        CaseEvent event = CaseEvent.ANIMATION_END;
        List<EventData> list =  eventMap.getOrDefault(event, new ArrayList<>());
        for (EventData data : list) {
            if(data instanceof CasedEventData) {
                CaseEventData caseEventData = (CaseEventData) data;
                if(!caseEventData.getCase().equalsIgnoreCase(e.getCaseData().getCaseName())) {
                    continue;
                }
            }
            executeActions(replaceList(data.getActions(),
                    "%player%", e.getPlayer().getName(),
                    "%case%", e.getCaseData().getCaseName(),
                    "%casetitle%", e.getCaseData().getCaseTitle(),
                    "%animation%", e.getAnimation(),
                    "%group%", e.getWinItem().getGroup()
            ));
        }
    }
    @EventHandler
    public void onAnimationPreStart(AnimationPreStartEvent e) {
        CaseEvent event = CaseEvent.ANIMATION_PRE_START;
        List<EventData> list =  eventMap.getOrDefault(event, new ArrayList<>());
        for (EventData data : list) {
            if(data instanceof CasedEventData) {
                CaseEventData caseEventData = (CaseEventData) data;
                if(!caseEventData.getCase().equalsIgnoreCase(e.getCaseData().getCaseName())) {
                    continue;
                }
            }
            executeActions(replaceList(data.getActions(),
                    "%player%", e.getPlayer().getName(),
                    "%case%", e.getCaseData().getCaseName(),
                    "%casetitle%", e.getCaseData().getCaseTitle(),
                    "%animation%", e.getAnimation(),
                    "%group%", e.getWinItem().getGroup()
            ));
        }
    }
    @EventHandler
    public void onAnimationRegistered(AnimationRegisteredEvent e) {
        CaseEvent event = CaseEvent.ANIMATION_REGISTERED;
        List<EventData> list =  eventMap.getOrDefault(event, new ArrayList<>());
        for (EventData data : list) {
            executeActions(replaceList(data.getActions(),
                    "%animation%", e.getAnimationName(),
                    "%plugin%", e.getAnimationPluginName()
                    ));
        }
    }
    @EventHandler
    public void onAnimationStart(AnimationStartEvent e) {
        CaseEvent event = CaseEvent.ANIMATION_START;
        List<EventData> list =  eventMap.getOrDefault(event, new ArrayList<>());
        for (EventData data : list) {
            if(data instanceof CasedEventData) {
                CaseEventData caseEventData = (CaseEventData) data;
                if(!caseEventData.getCase().equalsIgnoreCase(e.getCaseData().getCaseName())) {
                    continue;
                }
            }
            executeActions(replaceList(data.getActions(),
                    "%player%", e.getPlayer().getName(),
                    "%case%", e.getCaseData().getCaseName(),
                    "%casetitle%", e.getCaseData().getCaseTitle(),
                    "%animation%", e.getAnimation(),
                    "%group%", e.getWinItem().getGroup()
            ));
        }
    }
    @EventHandler
    public void onCaseInteract(CaseInteractEvent e) {
        CaseEvent event = CaseEvent.CASE_INTERACT;
        List<EventData> list =  eventMap.getOrDefault(event, new ArrayList<>());
        CaseData caseData = Case.getCase(e.getCaseType());
        String title = caseData != null ? caseData.getCaseTitle() : "null";
        for (EventData data : list) {
            if(data instanceof CasedEventData) {
                CaseEventData caseEventData = (CaseEventData) data;
                if(!caseEventData.getCase().equalsIgnoreCase(e.getCaseType())) {
                    continue;
                }
            }
            executeActions(replaceList(data.getActions(),
                    "%player%", e.getPlayer().getName(),
                    "%case%", e.getCaseType(),
                    "%casetitle%", title
                    ));
        }
    }
    @EventHandler
    public void onOpenCase(OpenCaseEvent e) {
        CaseEvent event = CaseEvent.OPEN_CASE;
        List<EventData> list =  eventMap.getOrDefault(event, new ArrayList<>());
        CaseData caseData = Case.getCase(e.getCaseType());
        String title = caseData != null ? caseData.getCaseTitle() : "null";
        for (EventData data : list) {
            if(data instanceof CasedEventData) {
                CaseEventData caseEventData = (CaseEventData) data;
                if(!caseEventData.getCase().equalsIgnoreCase(e.getCaseType())) {
                    continue;
                }
            }
            executeActions(replaceList(data.getActions(),
                    "%player%", e.getPlayer().getName(),
                    "%case%", e.getCaseType(),
                    "%casetitle%", title
            ));
        }
    }
    @EventHandler
    public void onPreOpenCase(PreOpenCaseEvent e) {
        CaseEvent event = CaseEvent.PRE_OPEN_CASE;
        List<EventData> list =  eventMap.getOrDefault(event, new ArrayList<>());
        CaseData caseData = Case.getCase(e.getCaseType());
        String title = caseData != null ? caseData.getCaseTitle() : "null";
        for (EventData data : list) {
            if(data instanceof CasedEventData) {
                CaseEventData caseEventData = (CaseEventData) data;
                if(!caseEventData.getCase().equalsIgnoreCase(e.getCaseType())) {
                    continue;
                }
            }
            executeActions(replaceList(data.getActions(),
                    "%player%", e.getPlayer().getName(),
                    "%case%", e.getCaseType(),
                    "%casetitle%", title
            ));
        }
    }
    @EventHandler
    public void onSubCommandRegistered(SubCommandRegisteredEvent e) {
        CaseEvent event = CaseEvent.SUB_COMMAND_REGISTERED;
        List<EventData> list =  eventMap.getOrDefault(event, new ArrayList<>());
        for (EventData data : list) {
            executeActions(replaceList(data.getActions(),
                    "%command%", e.getSubCommandName()
            ));
        }
    }
    @EventHandler
    public void onDonateCaseEnable(DonateCaseEnableEvent e) {
        CaseEvent event = CaseEvent.DONATE_CASE_ENABLE;
        List<EventData> list =  eventMap.getOrDefault(event, new ArrayList<>());
        for (EventData data : list) {
            executeActions(data.getActions());
        }
    }
    @EventHandler
    public void onDonateCaseDisable(DonateCaseDisableEvent e) {
        CaseEvent event = CaseEvent.DONATE_CASE_DISABLE;
        List<EventData> list =  eventMap.getOrDefault(event, new ArrayList<>());
        for (EventData data : list) {
            executeActions(data.getActions());
        }
    }
    private void executeActions(List<String> actions) {
        for (String action : actions) {
            if (action.startsWith("[command] ")) {
                action = action.replaceFirst("\\[command] ", "");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), rc(action));
            }
            if (action.startsWith("[broadcast] ")) {
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
                line = line.replace(args[i], args[i+1]);
            }
            newList.add(rc(line));
        }
        return newList;
    }
}
