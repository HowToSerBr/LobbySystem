package com.gcb.lobbyselector.eventos;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gcb.lobbyselector.Main;
import com.gcb.lobbyselector.utils.LobbyRunnable;

public class JoinEvent implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		LobbyRunnable lr = new LobbyRunnable();
		if(Bukkit.getOnlinePlayers().size() == 1) {
			if(LobbyRunnable.isRun) {
			lr.destroy();
			}
		}		
	
		ItemStack lobby = new ItemStack(Material.EMERALD_BLOCK);
		ItemMeta lobbym = lobby.getItemMeta();
		lobbym.setDisplayName("§c§lLobbys");
		lobby.setItemMeta(lobbym);
		
		event.getPlayer().getInventory().addItem(lobby);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable(){
            @Override
            public void run() {
            	if(Bukkit.getOnlinePlayers().size() == 0) {
            	LobbyRunnable.isRun = true;
            	LobbyRunnable lr = new LobbyRunnable();
            	lr.start();
            }
            }
        }, 20L);
	}

}
