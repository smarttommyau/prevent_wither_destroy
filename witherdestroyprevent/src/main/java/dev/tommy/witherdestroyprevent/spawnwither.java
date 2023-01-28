package dev.tommy.witherdestroyprevent;

import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;
import org.bukkit.entity.Entity;
public class spawnwither {
    private final Plugin plugin;
    public spawnwither(Plugin plugin) { this.plugin = plugin;}
    
    public void spawnWither(World world, double x, double y, double z,CommandSender sender){
        Entity wither = world.spawnEntity(new Location(world, x, y, z),EntityType.WITHER);
        adduuid(wither.getUniqueId());
        sender.sendMessage("Special Wither should be spawn at " + x + "," + y + "," + z);
        return;
    }
    private void adduuid(UUID uuid){
        List<String> list = plugin.getConfig().getStringList("uuid");
        list.add(uuid.toString());
        plugin.getConfig().set("uuid",list);
        return;
    }
}
