package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Fournisseur;

public class FournisseurDaoJdbc2 implements FournisseurDao {

	private String url;
	private String user;
	private String pwd;
	
	public FournisseurDaoJdbc2() {
		ResourceBundle config = ResourceBundle.getBundle("config");
		String url = config.getString("database.url");
		String user = config.getString("database.user");
		String pwd = config.getString("database.password");
		System.out.println(url);	
		
	}
	
	@Override
	public List<Fournisseur> extraire() {
		
		
		Connection maConnexion = null;
		PreparedStatement stat = null;
		ResultSet resultat = null;
		
		ArrayList<Fournisseur> listeFournisseurs = null;
		
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			maConnexion = DriverManager.getConnection(url, user, pwd);
			
			listeFournisseurs = new ArrayList<>();
			stat = maConnexion.prepareStatement("SELECT ID, NOM from FOURNISSEUR");
			resultat = stat.executeQuery();

			while (resultat.next()) {
				Fournisseur f = new Fournisseur(resultat.getInt("ID"), resultat.getString("NOM"));
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
				resultat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeResources(maConnexion, stat);
		}
		return listeFournisseurs;
		
	}

	@Override
	public void insert(Fournisseur fournisseur) {


		Connection maConnexion = null;
		PreparedStatement stat = null;

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			maConnexion = DriverManager.getConnection(url, user, pwd);

			// faire une insertion
			stat = maConnexion.prepareStatement("insert into FOURNISSEUR (ID, NOM) values (?, ?)");
			stat.setInt(1, fournisseur.getId());
			stat.setString(2, fournisseur.getNom());
			int nbLignesAjoutees = stat.executeUpdate();
			System.out.println(nbLignesAjoutees);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			closeResources(maConnexion, stat);
		}
		
	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		
		Connection maConnexion = null;
		PreparedStatement stat = null;

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			maConnexion = DriverManager.getConnection(url, user, pwd);

			// faire un update

			stat = maConnexion.prepareStatement("update FOURNISSEUR set NOM = ? where NOM = ?");
			stat.setString(1, nouveauNom);
			stat.setString(2, ancienNom);
			int nbLignesModif = stat.executeUpdate();
			System.out.println(nbLignesModif);

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			closeResources(maConnexion, stat);
		}

		return 0;
	}

	@Override
	public boolean delete(Fournisseur fournisseur) {
		
		Connection maConnexion = null;
		PreparedStatement stat = null;

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			maConnexion = DriverManager.getConnection(url, user, pwd);

			// Faire une suppression

			stat = maConnexion.prepareStatement("delete from FOURNISSEUR where ID = ?");
			stat.setInt(1, fournisseur.getId());
			//stat.setString(2, fournisseur.getNom());
			int nbLignesDelete = stat.executeUpdate();
			System.out.println(nbLignesDelete);

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			closeResources(maConnexion, stat);
		}

		return false;
	}

	private void closeResources(Connection maConnexion, PreparedStatement stat) {
		try {
			maConnexion.close();
			stat.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	

}
