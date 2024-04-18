package com.jodexindustries.dceventmanager.data;

public enum CaseEvent {
    ANIMATION_END(Type.CASED),
    ANIMATION_PRE_START(Type.CASED),
    ANIMATION_REGISTERED(Type.DEFAULT),
    ANIMATION_START(Type.CASED),
    CASE_INTERACT(Type.CASED),
    OPEN_CASE(Type.CASED),
    PRE_OPEN_CASE(Type.CASED),
    SUB_COMMAND_REGISTERED(Type.DEFAULT),
    DONATE_CASE_DISABLE(Type.DEFAULT),
    DONATE_CASE_ENABLE(Type.DEFAULT),
    CASE_GUI_CLICK(Type.GUI);

    public final Type type;

    CaseEvent(Type type) {
        this.type = type;
    }

    public static CaseEvent fromString(String name) {
        try {
            String formattedName = name.toUpperCase();
            return CaseEvent.valueOf(formattedName);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    public enum Type {
        DEFAULT,
        CASED,
        GUI
    }
}