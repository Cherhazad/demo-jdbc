package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
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

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection maConnexion = DriverManager.getConnection(url, user, pwd);

			// faire un update

			Statement stat = maConnexion.createStatement();
			int nbLignesModif = stat.executeUpdate("update FOURNISSEUR set NOM = 'La Maison de Toutes les Peintures' where id = 4");
			System.out.println(nbLignesModif);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
