package com.bilicraft.fallingpatch;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.TimeUnit;

public final class FallingPatch extends JavaPlugin implements Listener {
    private final Cache<Player, Long> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .build();

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
        cache.put(event.getPlayer(),System.currentTimeMillis());
    }
    @EventHandler(ignoreCancelled = true)
    public void onJoin(EntityDamageEvent event){
        if(event.getCause() == EntityDamageEvent.DamageCause.FALL){
            if(event.getEntity() instanceof Player){
               if(cache.getIfPresent((Player)event.getEntity()) != null){
                   event.setCancelled(true);
               }
            }
        }
    }
}
