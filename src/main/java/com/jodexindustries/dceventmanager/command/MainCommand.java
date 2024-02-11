package com.jodexindustries.dceventmanager.command;

import com.jodexindustries.dceventmanager.listener.EventListener;
import com.jodexindustries.donatecase.api.data.SubCommand;
import com.jodexindustries.donatecase.api.data.SubCommandType;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

import static com.jodexindustries.dceventmanager.Main.instance;

public class MainCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        instance.reloadConfig();
        instance.loadEvents();
        sender.sendMessage(EventListener.rc("&aConfig and events reloaded!"));
    }

    @Override
    public List<String> getTabCompletions(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }

    @Override
    public SubCommandType getType() {
        return SubCommandType.ADMIN;
    }

    @Override
    public String[] getArgs() {
        return SubCommand.super.getArgs();
    }

    @Override
    public String getDescription() {
        return "Reload DCEventManager";
    }
}
