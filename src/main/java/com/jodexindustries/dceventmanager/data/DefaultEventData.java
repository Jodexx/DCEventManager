package com.jodexindustries.dceventmanager.data;

import com.jodexindustries.dceventmanager.data.interfaces.EventData;

import java.util.List;

public class DefaultEventData implements EventData {
    private final CaseEvent caseEvent;
    private final List<String> actions;

    public DefaultEventData(CaseEvent caseEvent, List<String> actions) {
        this.caseEvent = caseEvent;
        this.actions = actions;
    }

    @Override
    public List<String> getActions() {
        return actions;
    }

    @Override
    public CaseEvent getCaseEvent() {
        return caseEvent;
    }
}
