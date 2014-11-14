package com.connorlinfoot.simplechatlog;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class SimpleChatLog extends JavaPlugin implements Listener {
    private String location = "logs/chat/";

    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        Server server = getServer();
        ConsoleCommandSender console = server.getConsoleSender();

        console.sendMessage("");
        console.sendMessage(ChatColor.BLUE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        console.sendMessage("");
        console.sendMessage(ChatColor.AQUA + getDescription().getName());
        console.sendMessage(ChatColor.AQUA + "Version " + getDescription().getVersion());
        console.sendMessage("");
        console.sendMessage(ChatColor.BLUE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        console.sendMessage("");

        Bukkit.getPluginManager().registerEvents(this,this);
    }

    public void onDisable() {
        getLogger().info(getDescription().getName() + " has been disabled!");
    }

    private void createFile(){
        String fileName = currentDate() + ".txt";
        File directory = new File(location);
        if( !directory.exists() ){
            directory.mkdirs();
        }

        File file = new File(location + fileName);
        if( !file.exists() ){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String currentDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    private void addLine(String text){
        String fileName = currentDate() + ".txt";
        File file = new File(location + fileName);
        if( !file.exists() ){
            createFile();
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(text);
            bw.newLine();
            bw.close();
        } catch (Exception ignored) {}
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String message = event.getMessage();
        String text = player.getDisplayName() + " (" + player.getName() + ") - " + message;
        addLine(text);
    }
}
