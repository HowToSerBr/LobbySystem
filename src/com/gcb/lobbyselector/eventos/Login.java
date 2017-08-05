package com.gcb.lobbyselector.eventos;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import com.gcb.lobbyselector.Main;
import com.gcb.lobbyselector.mysql.API;

public class Login implements Listener {
	
	@EventHandler
	public void onLogin(PlayerLoginEvent event) {
		if(API.getStatus(Main.plugin.getConfig().getString("Lobby")).equals("Fechado")){
			if(!event.getPlayer().hasPermission("lobby.bypass")) {
				event.disallow(Result.KICK_OTHER, "§cEste lobby não está liberado.");
			}
		}
	}

}
