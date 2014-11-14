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
            String prefix = "[" + SimpleChatAPI.currentTime() + "]";
            if( !SimpleChatLog.filePerDay ){
                prefix = "[" + SimpleChatAPI.currentDate() + " " + SimpleChatAPI.currentTime() + "]";
            }
            String text = prefix + " " + player.getDisplayName() + " (" + player.getName() + ") Joined The Server";
            SimpleChatAPI.addLine(text);
        }
    }
}
