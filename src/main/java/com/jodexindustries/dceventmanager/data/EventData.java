package com.jodexindustries.dceventmanager.data;


import java.util.List;

public class EventData {
    private final CaseEvent caseEvent;
    private final List<String> actions;

    public EventData(CaseEvent caseEvent, List<String> actions) {
        this.caseEvent = caseEvent;
        this.actions = actions;
    }

    public List<String> getActions() {
        return actions;
    }

    public CaseEvent getCaseEvent() {
        return caseEvent;
    }
}
