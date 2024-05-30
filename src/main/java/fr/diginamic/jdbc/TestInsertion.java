package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TestInsertion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ResourceBundle config = ResourceBundle.getBundle("config");
		String url = config.getString("database.url");
		String user = config.getString("database.user");
		String pwd = config.getString("database.password");
		System.out.println(url);

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection maConnexion = DriverManager.getConnection(url, user, pwd);

			// faire une insertion
			Statement stat = maConnexion.createStatement();
			int nbLignesAjoutees = stat.executeUpdate("insert into FOURNISSEUR (ID, NOM) values (4, 'La Maison de la Peinture')");
			System.out.println(nbLignesAjoutees);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
