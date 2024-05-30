package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Fournisseur;

public class TestSelect {

	public static void main(String[] args) {
		
		ResourceBundle config = ResourceBundle.getBundle("config");
		String url = config.getString("database.url");
		String user = config.getString("database.user");
		String pwd = config.getString("database.password");
		System.out.println(url);
		
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection maConnexion = DriverManager.getConnection(url, user, pwd);
			
			ArrayList<Fournisseur> listeFournisseurs = new ArrayList<>();
			Statement stat = maConnexion.createStatement();
			ResultSet resultat = stat.executeQuery("SELECT ID, NOM from FOURNISSEUR");

			while (resultat.next()) {
				Fournisseur f = new Fournisseur(resultat.getInt("id"), resultat.getString("nom"));
				listeFournisseurs.add(f);
			}
			
			for (Fournisseur f : listeFournisseurs) {
				System.out.println(f);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
