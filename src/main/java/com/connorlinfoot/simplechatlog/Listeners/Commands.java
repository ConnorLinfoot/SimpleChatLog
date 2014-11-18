package com.connorlinfoot.simplechatlog.Listeners;

import com.connorlinfoot.simplechatlog.API.SimpleChatAPI;
import com.connorlinfoot.simplechatlog.SimpleChatLog;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.regex.PatternSyntaxException;

public class Commands implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onCommand(PlayerCommandPreprocessEvent event){
        if( !SimpleChatLog.logCommands ) return;
        Player player = event.getPlayer();
        String message = event.getMessage();

        String prefix = "[" + SimpleChatAPI.currentTime() + "]";
        if( !SimpleChatLog.filePerDay ){
            prefix = "[" + SimpleChatAPI.currentDate() + " " + SimpleChatAPI.currentTime() + "]";
        }

        String text = SimpleChatLog.plugin.getConfig().getString("Format.Command");
        text = text.replaceAll("%time%",SimpleChatAPI.currentTime());
        text = text.replaceAll("%date%",SimpleChatAPI.currentDate());
        text = text.replaceAll("%message%",message);
        text = text.replaceAll("%playername%",player.getName());
        text = text.replaceAll("%displayname%",player.getDisplayName());
        SimpleChatAPI.addLine(text);
    }

}
