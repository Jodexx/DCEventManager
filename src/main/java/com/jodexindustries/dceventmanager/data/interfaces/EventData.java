package com.jodexindustries.dceventmanager.data.interfaces;


import com.jodexindustries.dceventmanager.data.CaseEvent;

import java.util.List;

public interface EventData {
    CaseEvent getCaseEvent();
    List<String> getActions();

}
