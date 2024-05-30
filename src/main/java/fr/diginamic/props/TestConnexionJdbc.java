package fr.diginamic.props;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fr.diginamic.props.entites.Fournisseur;

public class TestConnexionJdbc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ResourceBundle config = ResourceBundle.getBundle("config");
		String url = config.getString("database.url");
		String user = config.getString("database.user");
		String pwd = config.getString("database.password");
		System.out.println(url);

		Connection maConnexion = null;
		Statement stat = null;
		ResultSet resultat = null;

		try {
			// DriverManager.registerDriver(new org.mariadb.jdbc.Driver()); //si Mariadb
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver()); // on peut aussi
			// écrire car font la même chose : Class.forName("com.mysql.cj.jdbc.Driver");
			maConnexion = DriverManager.getConnection(url, user, pwd);
			System.out.println(maConnexion);

			// Exécuter un insert
			stat = maConnexion.createStatement();
			int nbLignesAjoutees = stat.executeUpdate("insert into fournisseur (id, nom) values (4, 'Leroy Merlin')");
			int nbLignesModif = stat.executeUpdate("update fournisseur set nom = 'Leroy&Merlin' where id = 4");
			int nbLignesDelete = stat.executeUpdate("delete from fournisseur where id=7");

			System.out.println(nbLignesAjoutees);
			System.out.println(nbLignesModif);
			System.out.println(nbLignesDelete);

			ArrayList<Fournisseur> listeFournisseurs = new ArrayList<>();

			resultat = stat.executeQuery("SELECT id, nom from fournisseur");

			while (resultat.next()) {

				Fournisseur f = new Fournisseur(resultat.getInt("id"), resultat.getString("nom"));

			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		} finally {
			try {

				resultat.close();
				stat.close();
				maConnexion.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				System.exit(-1);
			}
		}

	}

}
