package com.connorlinfoot.simplechatlog.Commands;

import com.connorlinfoot.simplechatlog.API.SimpleChatAPI;
import com.connorlinfoot.simplechatlog.SimpleChatLog;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SCLCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if( args.length >= 1 && args[0].equalsIgnoreCase("reload") ){
            if(!sender.hasPermission("simplechatlog.reload") ){
                sender.sendMessage(ChatColor.RED + "Sorry, you do not have the correct permission to run this command");
                return false;
            }

            if( SimpleChatLog.plugin.getConfig().isSet("File.Location") ){
                SimpleChatLog.location = SimpleChatLog.plugin.getConfig().getString("File.Location");
            }

            if( SimpleChatLog.plugin.getConfig().isSet("Log.Chat") ){
                SimpleChatLog.logChat = SimpleChatLog.plugin.getConfig().getBoolean("Log.Chat");
            }

            if( SimpleChatLog.plugin.getConfig().isSet("Log.PM") ){
                SimpleChatLog.logPM = SimpleChatLog.plugin.getConfig().getBoolean("Log.PM");
            }

            if( SimpleChatLog.plugin.getConfig().isSet("Log.Shutdown") ){
                SimpleChatLog.logShutdown = SimpleChatLog.plugin.getConfig().getBoolean("Log.Shutdown");
            }

            if( SimpleChatLog.plugin.getConfig().isSet("Log.Startup") ){
                SimpleChatLog.logStartup = SimpleChatLog.plugin.getConfig().getBoolean("Log.Startup");
            }

            sender.sendMessage(ChatColor.GREEN + "Config Reloaded");
            String name;
            if( sender instanceof Player){
                Player player = (Player) sender;
                name = player.getName();
            } else {
                name = "Console";
            }
            SimpleChatAPI.addLine("------ Config Was Reloaded By " + name + " ------");
            return true;
        }

        sender.sendMessage(ChatColor.AQUA + "\"" + SimpleChatLog.plugin.getDescription().getName() + "\" - Version " + SimpleChatLog.plugin.getDescription().getVersion() + " - Created By Connor Linfoot");
        return false;
    }

}
