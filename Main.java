package me.foxtrot;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
//import org.bukkit.entity.Player;
//import org.bukkit.Bukkit;
//import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
/**
 * Created by j on 3/20/17.
 */



public class Main extends JavaPlugin {
    //public static void main(String[] args);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("test")) {
            	sender.sendMessage("testing message.");
            }
            if (cmd.getName().equalsIgnoreCase("aptime")) { // If the player typed /aptime then do the following...
                long rawTime = player.getWorld().getFullTime(); // get full server uptime for date compiling
                long rawdaytime = player.getWorld().getTime();  // get ticks relative to sunrise
                long time = rawdaytime/1000;                    // Since there are ~24000 ticks in a day, this can be converted into military time by /1000
                long converteddaytime = time;
                String converteddaytime1 = parseTime(converteddaytime);  //Convert the time from military to normal person time (am/pm)
                long timeyear = rawTime / 8640000;              //24000 ticks per day * 360 days per year = 8640000 ticks per year, take full uptime over this to find year
                long timemonth = rawTime / (720000 * timeyear); //8640000 ticks per year / 12 months * however many years its been = current month of the year, take full uptime over this to find month
                long timeday = (timemonth * 30);                //103680000 ticks per month * 30.42 average days per month, take full uptime over this to find day

                //This takes the 2017 version of each day in the calendar to figure out the exact day of the month. Its not entirely accurate as that fluctuates a day or two, but it is about as accurate as I can get it
                //This might be the hard way but it's also the only way I know how to do it xP
                //If your reading this code, you also may have noticed there is nothing for Janurary. This is because these are the first days of the month
                if(timemonth == 2)
                {
                    timeday = timeday - 31;
                }
                if(timemonth == 3)
                {
                    timeday = timeday - 59;
                }
                if(timemonth == 4)
                {
                    timeday = timeday - 90;
                }
                if(timemonth == 5)
                {
                    timeday = timeday - 120;
                }
                if(timemonth == 6)
                {
                    timeday = timeday - 151;
                }
                if(timemonth == 7)
                {
                    timeday = timeday - 181;
                }
                if(timemonth == 8)
                {
                    timeday = timeday - 212;
                }
                if(timemonth == 9)
                {
                    timeday = timeday - 243;
                }
                if(timemonth == 10)
                {
                    timeday = timeday - 274;
                }
                if(timemonth == 11)
                {
                    timeday = timeday - 304;
                }
                if(timemonth == 12)
                {
                    timeday = timeday - 335;
                }


                String message = String.format("Current Airship Pirates Date and Time (MM/DD/YY): %1$d/%2$d/%3$d %4$d", timemonth, timeday, timeyear, converteddaytime1); //put it all together
                sender.sendMessage(message);  //send the message
                //the previous lines will find the time in ap time, and send it to the player
                return true;
            }
        } else {
            sender.sendMessage("You have to be a player to run this command."); //If its not a player running the command, tell it nu
            return true;
        }

        return false;
    }




    public static String parseTime(long time) {
        long gameTime = time;
        long hours = gameTime / 1000 + 6;
        long minutes = (gameTime % 1000) * 60 / 1000;
        String ampm = "AM";
        if (hours >= 12) {
            hours -= 12;
            ampm = "PM";
        }

        if (hours >= 12) {
            hours -= 12;
            ampm = "AM";
        }

        if (hours == 0) hours = 12;

        String mm = "0" + minutes;
        mm = mm.substring(mm.length() - 2, mm.length());

        return hours + ":" + mm + " " + ampm;
    }
}


