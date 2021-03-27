package me.arrow.custom_chatword_replace;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener, TabCompleter {
    private FileConfiguration config = this.getConfig();

    @Override
    public void onEnable() {
        this.saveConfig();
        PluginManager temp = getServer().getPluginManager();
        temp.registerEvents(this,this);
    }

    @Override
    public void onDisable() {
        saveConfig();
    }
    
    private int amount = this.getConfig().getInt("chatreplace.amount");
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        if(e.isCancelled())
            return;
        Player player = (Player) e.getPlayer();
        String msg = e.getMessage();
        for(int i=1;i<=amount;i++){
            String word = this.getConfig().getString("chatreplace.word"+i);
            if (msg != null && msg.equalsIgnoreCase(word)){
                String formatted = (ChatColor.AQUA+player.getDisplayName()+ " "+this.getConfig().getString("chatreplace.replace"+i));
                Bukkit.broadcastMessage(formatted);

            }
        }
        e.getRecipients().clear();

    }
}
