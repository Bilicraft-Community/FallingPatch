package com.bilicraft.fallingpatch;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class FallingPatch extends JavaPlugin implements Listener {


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
       if(event.getPlayer().getLocation().add(0,-1,0).getBlock().getType() == Material.AIR){
           // 强制落地
           event.getPlayer().teleport(event.getPlayer().getWorld().getHighestBlockAt(event.getPlayer().getLocation()).getLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
       }
    }
}
