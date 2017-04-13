package br.com.drogaria.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	private static final String USUARIO = "root";
	private static final String SENHA = "nls2017";
	private static final String URL = "jdbc:mysql://localhost:3306/drogaria?useSSL=false";
	
	public static Connection conectar() throws SQLException{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conexao;
	}
	
	public static void main(String[] args) {
		try {
			@SuppressWarnings("unused")
			Connection conn = conectar();
			System.out.println("Ligacao criada com sucesso.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Erro a criar ligacao.");
		}
	}
}
