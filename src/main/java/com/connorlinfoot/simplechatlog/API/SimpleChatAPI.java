package com.connorlinfoot.simplechatlog.API;

import com.connorlinfoot.simplechatlog.SimpleChatLog;
import org.bukkit.Bukkit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SimpleChatAPI {

    private static void createFile(){
        String fileName = getDate() + ".txt";
        File directory = new File(SimpleChatLog.location);
        if( !directory.exists() ){
            directory.mkdirs();
        }

        File file = new File(SimpleChatLog.location + fileName);
        if( !file.exists() ){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Deprecated
    public static String currentDate() {
        Bukkit.getLogger().warning("A plugin called the deprecated method \"currentDate()\"");
        Bukkit.getLogger().warning("Don't worry though! It should have still worked!");
        return getDate();
    }

    public static String getDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    @Deprecated
    public static String currentTime() {
        Bukkit.getLogger().warning("A plugin called the deprecated method \"currentTime()\"");
        Bukkit.getLogger().warning("Don't worry though! It should have still worked!");
        return getTime();
    }

    public static String getTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }

    public static void addLine(String text){
        String fileName;
        if( SimpleChatLog.filePerDay ){
            fileName = getDate() + ".txt";
        } else {
            fileName = "logs.txt";
        }

        File file = new File(SimpleChatLog.location + fileName);
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

}
