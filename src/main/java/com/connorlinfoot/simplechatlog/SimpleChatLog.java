package com.connorlinfoot.simplechatlog;

import com.connorlinfoot.simplechatlog.API.SimpleChatAPI;
import com.connorlinfoot.simplechatlog.Commands.SCLCommand;
import com.connorlinfoot.simplechatlog.Listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


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
    public static boolean SNAPSHOT = false;

    public static void getSetConfig() {
        if (plugin.getConfig().isSet("File.Location")) {
            location = plugin.getConfig().getString("File.Location");
        }

        if (plugin.getConfig().isSet("Log.Chat")) {
            logChat = plugin.getConfig().getBoolean("Log.Chat");
        }

        if (plugin.getConfig().isSet("Log.PM")) {
            logPM = plugin.getConfig().getBoolean("Log.PM");
        }

        if (plugin.getConfig().isSet("Log.Shutdown")) {
            logShutdown = plugin.getConfig().getBoolean("Log.Shutdown");
        }

        if (plugin.getConfig().isSet("Log.Startup")) {
            logStartup = plugin.getConfig().getBoolean("Log.Startup");
        }

        if (plugin.getConfig().isSet("Log.Player Join")) {
            logPlayerJoin = plugin.getConfig().getBoolean("Log.Player Join");
        }

        if (plugin.getConfig().isSet("Log.Player Quit")) {
            logPlayerQuit = plugin.getConfig().getBoolean("Log.Player Quit");
        }

        if (plugin.getConfig().isSet("File.File Per Day")) {
            filePerDay = plugin.getConfig().getBoolean("File.File Per Day");
        }

        if (plugin.getConfig().isSet("Log.Commands")) {
            logCommands = plugin.getConfig().getBoolean("Log.Commands");
        }
    }

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
        if (getDescription().getVersion().contains("SNAPSHOT")) {
            SNAPSHOT = true;
            console.sendMessage(ChatColor.RED + "You are running a snapshot build of " + getDescription().getName() + " please report bugs!");
            console.sendMessage(ChatColor.RED + "NO support will be given if running old snapshot build!");
        }
        console.sendMessage("");
        console.sendMessage(ChatColor.BLUE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        console.sendMessage("");

        Bukkit.getPluginManager().registerEvents(new Chat(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuit(),this);
        Bukkit.getPluginManager().registerEvents(new Commands(), this);
        Bukkit.getPluginManager().registerEvents(new PM(),this);
        Bukkit.getPluginCommand("scl").setExecutor(new SCLCommand());
        if( logStartup ) SimpleChatAPI.addLine("------ SERVER STARTED (or reloaded) ------");
    }

    public void onDisable() {
        if (logShutdown) SimpleChatAPI.addLine("------ SERVER SHUTDOWN ------");
        getLogger().info(getDescription().getName() + " has been disabled!");
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
