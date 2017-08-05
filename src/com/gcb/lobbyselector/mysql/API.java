package com.gcb.lobbyselector.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class API {
	
	public static void criarTabelas() {
		try {
			String sql = "CREATE TABLE IF NOT EXISTS `servidores` (id int PRIMARY KEY AUTO_INCREMENT, servidor text, status text);";
			Statement st = Conexao.getConnection().createStatement();
			st.executeUpdate(sql);
		} catch (SQLException ev) {
			ev.printStackTrace();
		}
	}
	
	public static void addServidor(String servidor) {
		if(!serverExists(servidor)) {
			try {
				String sql = "INSERT INTO servidores (servidor, status) VALUES ('"+servidor+"', 'Fechado');";
				Statement st = Conexao.getConnection().createStatement();
				st.executeUpdate(sql);
			} catch (SQLException ev) {
				ev.printStackTrace();
			}
		}
	}
	
	public static String getStatus(String servidor) {
		if(serverExists(servidor)) {
			String retorno = null;
			try {
				Statement st = Conexao.getConnection().createStatement();
				String sql = "SELECT * FROM servidores WHERE servidor= '"+servidor+"';";
				ResultSet rs = st.executeQuery(sql);
				if(!rs.next() || String.valueOf(rs.getString("status")) == null) {
					return retorno;
				}
				retorno = rs.getString("status");
				return retorno;
			} catch (SQLException ev) {ev.printStackTrace(); return null;}
		} else {
			return null;
			
		}
	}
	
	public static void setStatus(String servidor, String status) {
		if(serverExists(servidor)) {
			try {
				String sql = "UPDATE servidores SET status = '"+status+"' WHERE servidor= '"+servidor+"';";
				Statement st = Conexao.getConnection().createStatement();
				st.executeUpdate(sql);
			} catch (SQLException ev) {ev.printStackTrace();}
		}
	}
	
	public static void removeServidor(String servidor) {
		if(serverExists(servidor)) {
			try {
				String sql = "DELETE FROM servidores WHERE servidor= '"+servidor+"'";
				Statement st = Conexao.getConnection().createStatement();
				st.executeUpdate(sql);
			} catch (SQLException ev) {
				ev.printStackTrace();
			}
		}
	}
	
	public static boolean serverExists(String servidor) {
		try {
			Statement st = Conexao.getConnection().createStatement();
			String sql = "SELECT * FROM servidores WHERE servidor= '"+servidor+"';";
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
			if(rs.getString("servidor") == null) {
				return false;
			}
			return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
