package dev.tommy.witherdestroyprevent;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.command.CommandSender;

public class Mycommand implements TabExecutor{
    private final Plugin plugin;
    public Mycommand(Plugin plugin) { this.plugin = plugin;}
    @Override
    public boolean onCommand( CommandSender sender,  Command cmd,  String label,
             String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Only player can use this command");
            return true;
        }
        Player player = (Player) sender;
        if(args[0].equals("spawn")){
            if(args.length == 4){
                try {
                    new spawnwither(plugin).spawnWither(player.getWorld(), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]),sender);
                } catch (Exception e) {
                    sendERRMessage(sender);
                }
            }else if(args.length == 2 && args[1].equals("current")){
                try {
                    Location loc = player.getLocation();
                    new spawnwither(plugin).spawnWither(player.getWorld(), loc.getX(), loc.getY(), loc.getZ(),sender);
                } catch (Exception e) {
                    sendERRMessage(sender);
                }
            }else{
                sendERRMessage(sender);
            }
        }else if(args.length == 1 && (args[0].equals("help") || args[0].equals("?"))){
            sender.sendMessage("Wither Destroy Prevent help menu \n /wither help: show this menu \n /wither ?: show this menu \n /wither spawn [current|<x> <y> <z>]");
        }else{
            sendERRMessage(sender);
        }        
        return true;
    }

    @Override
    public List<String> onTabComplete( CommandSender sender,  Command cmd,
             String label,  String[] args) {
        List<String> str = new ArrayList<>();
        if(!(sender instanceof Player))
            return null;
            
        if(args.length <= 1){
            str.add("help");
            str.add("?");
            str.add("spawn");
        }else if(args[0].equals("spawn")){
            switch (args.length) {
                case 2:
                    str.add("<x> <y> <z>");
                    str.add("current");
                    break;
                case 3:
                    str.add("<y> <z>");
                    break;
                case 4:
                    str.add("<z>");
                    break;
                default:
                    break;
            }
        }
        
        return str;
    }

    private void sendERRMessage(CommandSender sender){
        sender.sendMessage("use [/wither help] to see the usage of this command");
        return;
    }
    
}
