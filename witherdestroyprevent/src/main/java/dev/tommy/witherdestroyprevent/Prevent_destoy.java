package dev.tommy.witherdestroyprevent;

import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.plugin.Plugin;


public class Prevent_destoy implements Listener{
    private final Plugin plugin;
    public Prevent_destoy(Plugin plugin) { this.plugin = plugin;}

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        UUID uuid = event.getEntity().getUniqueId();
        if(event.getEntityType() == EntityType.WITHER && checkuuid(uuid)){
            removeuuid(uuid);
        }
   
   }

    @EventHandler
    public void onEntityDestroyEntity(EntityDamageByEntityEvent event) {
        if(checkuuid(event.getEntity().getUniqueId())){
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onHangingBreak(HangingBreakByEntityEvent event) {
        if(checkuuid(event.getEntity().getUniqueId())){
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onWitherSkullExplode(EntityExplodeEvent event) {
        Projectile projectile = (Projectile)event.getEntity();
        Entity entity = (Entity)projectile.getShooter();
        if(checkuuid(entity.getUniqueId())){
            event.blockList().clear();
        }
        
    }
    @EventHandler
    public void onWitherDestroy(EntityChangeBlockEvent event) {
        if(checkuuid(event.getEntity().getUniqueId())){
            event.setCancelled(true);
        }
    }
    
    private void removeuuid(UUID uuid){
        List<String> list = plugin.getConfig().getStringList("uuid");
        for(int i=0;i<list.size();i++){
            if(list.get(i).equals(uuid.toString())){
                list.remove(i);
                break;
            }
        }
        plugin.getConfig().set("uuid",list);
    }
    private boolean checkuuid(UUID uuid){
        List<String> list = plugin.getConfig().getStringList("uuid");
        for(int i=0;i<list.size();i++){
            if(list.get(i).equals(uuid.toString())){
                return true;
            }
        }
        return false;
    }

}
