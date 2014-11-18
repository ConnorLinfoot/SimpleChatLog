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

            SimpleChatLog.getSetConfig();

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

        sender.sendMessage(ChatColor.AQUA + "\"" + SimpleChatLog.plugin.getDescription().getName() + "\" - Version: " + SimpleChatLog.plugin.getDescription().getVersion());
        sender.sendMessage(ChatColor.AQUA + "Made by Connor Linfoot http://connorlinfoot.com");
        return false;
    }

}
