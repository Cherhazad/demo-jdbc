package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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

		Connection maConnexion = null;
		Statement stat = null;

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			maConnexion = DriverManager.getConnection(url, user, pwd);

			// faire une insertion
			stat = maConnexion.createStatement();
			int nbLignesAjoutees = stat.executeUpdate("insert into FOURNISSEUR (ID, NOM) values (4, 'La Maison de la Peinture')");
			System.out.println(nbLignesAjoutees);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
