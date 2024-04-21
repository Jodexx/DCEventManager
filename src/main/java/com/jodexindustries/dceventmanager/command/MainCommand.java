package com.jodexindustries.dceventmanager.command;

import com.jodexindustries.dceventmanager.bootstrap.Main;
import com.jodexindustries.dceventmanager.listener.EventListener;
import com.jodexindustries.donatecase.api.data.SubCommand;
import com.jodexindustries.donatecase.api.data.SubCommandType;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;


public class MainCommand implements SubCommand {
    private final Main main;
    public MainCommand(Main main) {
        this.main = main;
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        main.getAddonConfig().reloadConfig();
        main.getTools().loadEvents();
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
