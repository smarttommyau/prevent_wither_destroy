package dev.tommy.witherdestroyprevent;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
public class App extends JavaPlugin
{
    FileConfiguration config = getConfig();
    
    @Override
    public void onEnable() {
        getLogger().info("WitherDestroyPrevent is started");
        // setup event handler
        getServer().getPluginManager().registerEvents(new Prevent_destoy(this),this);

        TabExecutor cmd = new Mycommand(this);
        getCommand("wither").setTabCompleter(cmd);
        getCommand("wither").setExecutor(cmd);
    }
    @Override
    public void onDisable() {
        getLogger().info("WitherDestroyPrevent is disabled");
    }
}
