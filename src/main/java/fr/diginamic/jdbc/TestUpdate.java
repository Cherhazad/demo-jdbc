package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TestUpdate {

	public static void main(String[] args) {

		ResourceBundle config = ResourceBundle.getBundle("config");
		String url = config.getString("database.url");
		String user = config.getString("database.user");
		String pwd = config.getString("database.password");
		System.out.println(url);

		Connection maConnexion = null;
		Statement stat = null;

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			maConnexion = DriverManager.getConnection(url, user, pwd);

			// faire un update

			stat = maConnexion.createStatement();
			int nbLignesModif = stat.executeUpdate("update FOURNISSEUR set NOM = 'La Maison de Toutes les Peintures' where id = 4");
			System.out.println(nbLignesModif);

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {
				maConnexion.close();
				stat.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
