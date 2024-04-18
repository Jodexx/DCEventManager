package com.jodexindustries.dceventmanager.data;

import com.jodexindustries.dceventmanager.data.interfaces.GuiEventData;

import java.util.List;

public class GuiClickEventData implements GuiEventData {
    private final CaseEvent caseEvent;
    private final List<String> actions;
    private final String caseName;
    private final int slot;

    public GuiClickEventData(CaseEvent caseEvent, List<String> actions, String caseName, int slot) {
        this.caseEvent = caseEvent;
        this.actions = actions;
        this.caseName = caseName;
        this.slot = slot;
    }
    @Override
    public int getSlot() {
        return slot;
    }

    @Override
    public String getCase() {
        return caseName;
    }

    @Override
    public CaseEvent getCaseEvent() {
        return caseEvent;
    }

    @Override
    public List<String> getActions() {
        return actions;
    }
}
