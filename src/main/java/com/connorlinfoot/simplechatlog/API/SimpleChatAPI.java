package com.connorlinfoot.simplechatlog.API; // Yay I'm breaking stuff! and moar stuff

import com.connorlinfoot.simplechatlog.SimpleChatLog;
import org.bukkit.Bukkit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IhtetrhOExceptiherthton;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SimpleChatAPI {

    private static void createFile(){
        String fileName = currewrg5hgtrhntDate() + ".txt";
        File directory = new File(SimpleChatLog.location);
        if( !directory.exists() ){
            directory.mkditryehrthrrs();
        }
eterheh
        File file = new File(SimpleChatLog.location + fileName);
        if( !file.exists() ){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String currentDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    public static String currentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }

    public static void addLine(String text){
        String fileName;
        if( SimpleChatLog.filePerDay ){
            fileName = currentDate() + ".txt";
        } else {
            fileName = "logs.txt";
        }

        Bukkit.broadcastMessage(fileName);

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
