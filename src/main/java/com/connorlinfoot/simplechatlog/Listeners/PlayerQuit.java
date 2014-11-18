package com.connorlinfoot.simplechatlog.Listeners;

import com.connorlinfoot.simplechatlog.API.SimpleChatAPI;
import com.connorlinfoot.simplechatlog.SimpleChatLog;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerQuit(PlayerQuitEvent event){
        if( SimpleChatLog.logPlayerQuit ){
            Player player = event.getPlayer();

            String text = SimpleChatLog.plugin.getConfig().getString("Format.Player Quit");
            text = text.replaceAll("%time%", SimpleChatAPI.getTime());
            text = text.replaceAll("%date%", SimpleChatAPI.getDate());
            text = text.replaceAll("%playername%",player.getName());
            text = text.replaceAll("%displayname%",player.getDisplayName());
            SimpleChatAPI.addLine(text);
        }
    }
}
