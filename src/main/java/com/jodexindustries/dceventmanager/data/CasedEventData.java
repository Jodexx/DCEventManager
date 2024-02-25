package com.jodexindustries.dceventmanager.data;

import com.jodexindustries.dceventmanager.data.interfaces.CaseEventData;

import java.util.List;

public class CasedEventData implements CaseEventData {
    private final CaseEvent caseEvent;
    private final List<String> actions;
    private final String caseName;

    public CasedEventData(CaseEvent caseEvent, List<String> actions, String caseName) {
        this.caseEvent = caseEvent;
        this.actions = actions;
        this.caseName = caseName;
    }

    @Override
    public List<String> getActions() {
        return actions;
    }

    @Override
    public CaseEvent getCaseEvent() {
        return caseEvent;
    }

    @Override
    public String getCase() {
        return caseName;
    }
}
