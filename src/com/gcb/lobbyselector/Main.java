package com.gcb.lobbyselector;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.gcb.lobbyselector.comandos.reiniciar;
import com.gcb.lobbyselector.eventos.GUI;
import com.gcb.lobbyselector.eventos.JoinEvent;
import com.gcb.lobbyselector.eventos.Login;
import com.gcb.lobbyselector.mysql.API;
import com.gcb.lobbyselector.mysql.Conexao;
import com.gcb.lobbyselector.utils.Lobby;

public class Main extends JavaPlugin {
	
	
	public static Main plugin;
	public static Lobby lobby;

	public void onEnable(){
		plugin = this;
		
		saveDefaultConfig();
		
		Conexao.getConnection();
		
		API.criarTabelas();
		
		API.addServidor(getConfig().getString("Lobby"));
		API.setStatus(getConfig().getString("Lobby"), "Aberto");
		
		lobby = new Lobby(getConfig().getString("Lobby"));

		Bukkit.getPluginManager().registerEvents(new GUI(), this);
		Bukkit.getPluginManager().registerEvents(new Login(), this);
		Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
		getCommand("reiniciar").setExecutor(new reiniciar());

		
		
		Bukkit.getConsoleSender().sendMessage("§3§lGame Coders §f§l> §eLobby Selector");
		Bukkit.getConsoleSender().sendMessage("§3§lGame Coders §f§l> §aAtivado com sucesso!");

	}
	public void onDisable(){
		Bukkit.getConsoleSender().sendMessage("§3§lGame Coders §f§l> §eLobby Selector");
		Bukkit.getConsoleSender().sendMessage("§3§lGame Coders §f§l> §cDesativado com sucesso!");
		
	}
	
}
