package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDao {

	
	
	@Override
	public List<Fournisseur> extraire() {
		ResourceBundle config = ResourceBundle.getBundle("config");
		String url = config.getString("database.url");
		String user = config.getString("database.user");
		String pwd = config.getString("database.password");
		System.out.println(url);
		
		Connection maConnexion = null;
		Statement stat = null;
		ResultSet resultat = null;
		
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			maConnexion = DriverManager.getConnection(url, user, pwd);
			
			ArrayList<Fournisseur> listeFournisseurs = new ArrayList<>();
			stat = maConnexion.createStatement();
			resultat = stat.executeQuery("SELECT ID, NOM from FOURNISSEUR");

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
		} finally {
			
			try {
				maConnexion.close();
				stat.close();
				resultat.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
	}

	@Override
	public void insert(Fournisseur fournisseur) {

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
			int nbLignesAjoutees = stat.executeUpdate("insert into FOURNISSEUR (ID, NOM) values ("+fournisseur.getId()+", '"+fournisseur.getNom()+"')");
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

	@Override
	public int update(String ancienNom, String nouveauNom) {
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
			int nbLignesModif = stat.executeUpdate("update FOURNISSEUR set NOM = '"+nouveauNom+"' where NOM = '"+ancienNom+"'");
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

		return 0;
	}

	@Override
	public boolean delete(Fournisseur fournisseur) {
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

			// Faire une suppression

			stat = maConnexion.createStatement();
			int nbLignesDelete = stat.executeUpdate("delete from FOURNISSEUR where ID = "+fournisseur.getId()+"");
			System.out.println(nbLignesDelete);

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

		return false;
	}

	

}
