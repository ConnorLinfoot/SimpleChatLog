package com.connorlinfoot.simplechatlog.Listeners;

import com.connorlinfoot.simplechatlog.API.SimpleChatAPI;
import com.connorlinfoot.simplechatlog.SimpleChatLog;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.regex.PatternSyntaxException;

public class PM implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPMCommand(PlayerCommandPreprocessEvent event){
        if( !SimpleChatLog.logPM ) return;
        Player player = event.getPlayer();
        String message = event.getMessage();
        String name = "";
        String newMessage = "";
        try {
            String[] splitMessage = message.split("\\s+");
            if( splitMessage.length < 3 ) return;
            name = splitMessage[1];
            newMessage = message.replaceAll(splitMessage[0] + " " + splitMessage[1] + " ","");
        } catch (PatternSyntaxException ignored) {}

        if( message.toLowerCase().contains("/pm") ||
                message.toLowerCase().contains("/message") ||
                message.toLowerCase().contains("/msg") ||
                message.toLowerCase().contains("/t") ||
                message.toLowerCase().contains("/tell") ){

            String prefix = "[" + SimpleChatAPI.currentTime() + "]";
            if( !SimpleChatLog.filePerDay ){
                prefix = "[" + SimpleChatAPI.currentDate() + " " + SimpleChatAPI.currentTime() + "]";
            }

            String text = prefix + " " + player.getName() + " -> " + name + ": " + newMessage;
            if( !name.equalsIgnoreCase("") ) SimpleChatAPI.addLine(text);
        }
    }

}
