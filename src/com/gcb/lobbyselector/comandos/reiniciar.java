package com.gcb.lobbyselector.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gcb.lobbyselector.Main;
import com.gcb.lobbyselector.mysql.API;

public class reiniciar implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		if(sender instanceof Player) {
			if(cmd.getName().equalsIgnoreCase("reiniciar")){
				if(sender.isOp()){
					Bukkit.broadcastMessage("§c[Lobby] Este lobby será fechado por alguns minutos!");
					API.removeServidor(Main.lobby.getNome());
					//depois enviar o players pra outro lobby
					
				}
		
		

	
	}
		}
		return false;
	}
}
