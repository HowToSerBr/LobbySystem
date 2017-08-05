package com.gcb.lobbyselector.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.gcb.lobbyselector.Main;

public class Conexao {
    private static String usuario = Main.plugin.getConfig().getString("MySQL.Usuario");
    private static String senha = Main.plugin.getConfig().getString("MySQL.Senha");
    private static String banco = Main.plugin.getConfig().getString("MySQL.Database");
    private static String ip = Main.plugin.getConfig().getString("MySQL.IP");
    private static String driver = "com.mysql.jdbc.Driver";
    private static Connection conexao = null;

    public static Connection getConnection() {
        try {
            Class.forName(driver);
            if (conexao == null || conexao.isClosed()) {
                conexao = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + banco, usuario, senha);
            }
            return conexao;
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (SQLException e) {
            Conexao.closeConnection();
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

