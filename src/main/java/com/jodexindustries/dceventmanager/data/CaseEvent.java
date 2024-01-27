package com.jodexindustries.dceventmanager.data;

public enum CaseEvent {
    ANIMATION_END,
    ANIMATION_PRE_START,
    ANIMATION_REGISTERED,
    ANIMATION_START,
    CASE_INTERACT,
    OPEN_CASE,
    PRE_OPEN_CASE,
    SUB_COMMAND_REGISTERED,
    DONATE_CASE_DISABLE,
    DONATE_CASE_ENABLE;

    public static CaseEvent fromString(String name) {
        try {
            String formattedName = name.toUpperCase();
            return CaseEvent.valueOf(formattedName);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}