package com.gcb.lobbyselector.eventos;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gcb.lobbyselector.Main;
import com.gcb.lobbyselector.mysql.API;
import com.gcb.lobbyselector.utils.ServidorSocket;

public class GUI implements Listener {
	
	@EventHandler
	public void onInventoryClick(PlayerInteractEvent e) {
		if(e.getItem() != null) {
			if(e.getItem().getType() != Material.AIR) {
				if(e.getItem().hasItemMeta()) {
					if(e.getItem().getItemMeta().hasDisplayName()) {
						if(e.getItem().getItemMeta().getDisplayName().equals("§c§lLobbys")) {
							e.setCancelled(true);
							Inventory inv = Bukkit.createInventory(null, 54, "§fSelecione um lobby");
							
							ConfigurationSection cfg = Main.plugin.getConfig().getConfigurationSection("Lobbys");
							for(String s : cfg.getKeys(false)) {
								ItemStack lobby = new ItemStack(Material.IRON_BLOCK);
								ItemMeta lobbym = lobby.getItemMeta();
								lobbym.setDisplayName("§7"+cfg.getString(s+".Nome"));
								ServidorSocket ss = new ServidorSocket("localhost");
								lobbym.setLore(Arrays.asList(" ", "§e"+ss.getMaxPlayers()+"§f/"+"§e"+ss.getPlayersOnline(), "§eStatus: §f"+API.getStatus(Main.plugin.getConfig().getString(s+".Servidor"))));
								lobby.setItemMeta(lobbym);
								if(!API.getStatus(Main.plugin.getConfig().getString(s+".Servidor")).equals("Fechado")) {
								inv.addItem(lobby);
								}
							}
							e.getPlayer().openInventory(inv);
						}
					
					}
					}
				}
			}
		}
	}
