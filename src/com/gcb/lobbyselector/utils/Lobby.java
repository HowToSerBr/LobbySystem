package com.gcb.lobbyselector.utils;

import com.gcb.lobbyselector.Main;

public class Lobby {
	
	private String nome;
	
	public Lobby(String nome) {
		nome = this.nome;
	}
	
	public String getNome() {
		return Main.plugin.getConfig().getString("Lobby");
	}

}
