package com.bilicraft.fallingpatch;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class FallingPatch extends JavaPlugin implements Listener {
    private final List<Player> playerList = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(this,this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    @EventHandler(ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent event){
       if(!event.getPlayer().hasPlayedBefore()){
           playerList.add(event.getPlayer());
       }
    }
    @EventHandler(ignoreCancelled = true)
    public void onQuit(PlayerQuitEvent event){
        playerList.remove(event.getPlayer());
    }
    @EventHandler(ignoreCancelled = true)
    public void onJoin(EntityDamageEvent event){
        if(event.getCause() == EntityDamageEvent.DamageCause.FALL){
            if(event.getEntity() instanceof Player){
               if(playerList.contains((Player)event.getEntity())){
                   event.setCancelled(true);
                   event.setDamage(0.0d);
                   playerList.remove((Player)event.getEntity());
               }
            }
        }
    }

}
