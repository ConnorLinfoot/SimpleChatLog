package com.connorlinfoot.simplechatlog;

import com.connorlinfoot.simplechatlog.API.SimpleChatAPI;
import com.connorlinfoot.simplechatlog.Commands.SCLCommand;
import com.connorlinfoot.simplechatlog.Listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class SimpleChatLog extends JavaPlugin implements Listener {
    public static Plugin plugin;
    public static String location = "logs/chat/";
    public static boolean logChat = true;
    public static boolean logPM = true;
    public static boolean logShutdown = true;
    public static boolean logStartup = true;
    public static boolean logPlayerJoin = false;
    public static boolean logPlayerQuit = false;
    public static boolean logCommands = false;
    public static boolean filePerDay = true;

    public void onEnable() {
        plugin = this;
        getConfig().options().copyDefaults(true);
        saveConfig();
        Server server = getServer();
        ConsoleCommandSender console = server.getConsoleSender();

        checkOldConfig();
        getSetConfig();

        console.sendMessage("");
        console.sendMessage(ChatColor.BLUE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        console.sendMessage("");
        console.sendMessage(ChatColor.AQUA + getDescription().getName());
        console.sendMessage(ChatColor.AQUA + "Version " + getDescription().getVersion());
        console.sendMessage("");
        console.sendMessage(ChatColor.BLUE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        console.sendMessage("");

        Bukkit.getPluginManager().registerEvents(new Chat(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuit(),this);
        Bukkit.getPluginManager().registerEvents(new PM(),this);
        Bukkit.getPluginManager().registerEvents(new Commands(),this);
        Bukkit.getPluginCommand("scl").setExecutor(new SCLCommand());
        if( logStartup ) SimpleChatAPI.addLine("------ SERVER STARTED (or reloaded) ------");
    }

    public void onDisable() {
        if (logShutdown) SimpleChatAPI.addLine("------ SERVER SHUTDOWN ------");
        getLogger().info(getDescription().getName() + " has been disabled!");
    }

    private void getSetConfig(){
        if( getConfig().isSet("File.Location") ){
            location = getConfig().getString("File.Location");
        }

        if( getConfig().isSet("Log.Chat") ){
            logChat = getConfig().getBoolean("Log.Chat");
        }

        if( getConfig().isSet("Log.PM") ){
            logPM = getConfig().getBoolean("Log.PM");
        }

        if( getConfig().isSet("Log.Shutdown") ){
            logShutdown = getConfig().getBoolean("Log.Shutdown");
        }

        if( getConfig().isSet("Log.Startup") ){
            logStartup = getConfig().getBoolean("Log.Startup");
        }

        if( getConfig().isSet("Log.Player Join") ){
            logPlayerJoin = getConfig().getBoolean("Log.Player Join");
        }

        if( getConfig().isSet("Log.Player Quit") ){
            logPlayerQuit = getConfig().getBoolean("Log.Player Quit");
        }

        if( getConfig().isSet("File.File Per Day") ){
            filePerDay = getConfig().getBoolean("File.File Per Day");
        }

        if( getConfig().isSet("Log.Commands") ){
            logCommands = getConfig().getBoolean("Log.Commands");
        }
    }

    private void checkOldConfig(){
        if( getConfig().isSet("Log Location") ){
            getConfig().set("File.Location", getConfig().getString("Log Location"));
            getConfig().set("Log Location", null);
        }

        if( getConfig().isSet("Log Chat") ){
            getConfig().set("Log.Chat", getConfig().getString("Log Chat"));
            getConfig().set("Log Chat", null);
        }

        if( getConfig().isSet("Log PM") ){
            getConfig().set("Log.PM", getConfig().getString("Log PM"));
            getConfig().set("Log PM", null);
        }

        if( getConfig().isSet("Log Shutdown") ){
            getConfig().set("Log.Shutdown", getConfig().getString("Log Shutdown"));
            getConfig().set("Log Shutdown", null);
        }

        if( getConfig().isSet("Log Startup") ){
            getConfig().set("Log.Startup", getConfig().getString("Log Startup"));
            getConfig().set("Log Startup", null);
        }
        saveConfig();
    }
}
