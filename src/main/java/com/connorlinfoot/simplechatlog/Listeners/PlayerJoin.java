package com.connorlinfoot.simplechatlog.Listeners;

import com.connorlinfoot.simplechatlog.API.SimpleChatAPI;
import com.connorlinfoot.simplechatlog.SimpleChatLog;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event){
        if( SimpleChatLog.logPlayerJoin ){
            Player player = event.getPlayer();

            String text = SimpleChatLog.plugin.getConfig().getString("Format.Player Join");
            text = text.replaceAll("%time%", SimpleChatAPI.getTime());
            text = text.replaceAll("%date%", SimpleChatAPI.getDate());
            text = text.replaceAll("%playername%",player.getName());
            text = text.replaceAll("%displayname%",player.getDisplayName());
            SimpleChatAPI.addLine(text);
        }
    }
}
