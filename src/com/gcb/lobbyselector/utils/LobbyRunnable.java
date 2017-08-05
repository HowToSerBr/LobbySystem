package com.gcb.lobbyselector.utils;

import org.bukkit.Bukkit;

import com.gcb.lobbyselector.Main;
import com.gcb.lobbyselector.mysql.API;

public class LobbyRunnable extends Thread {

	public static boolean isRun = false;
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		if(!Main.lobby.getNome().equals("gcb-1")) {
		if(Bukkit.getOnlinePlayers().size() == 0) {
			Bukkit.getConsoleSender().sendMessage("§c§l[Lobby] §cO lobby "+ Main.lobby.getNome() + " será encerrado por falta de players!");
			API.setStatus(Main.plugin.getConfig().getString("Lobby"), "Fechado");
			try {
				Thread.sleep(5000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			this.destroy();
		}
		} else {
			this.destroy();
		}
	}
}
