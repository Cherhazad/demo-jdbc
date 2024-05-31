package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.entites.Fournisseur;

public class TestDaoJdbc {

	public static FournisseurDaoJdbc fournisseur1 = new FournisseurDaoJdbc();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		fournisseur1.extraire();
		fournisseur1.insert(new Fournisseur(5, "France de matériaux"));
		fournisseur1.update("France de matériaux", "France matériaux");
		fournisseur1.extraire();
		fournisseur1.delete(new Fournisseur(5, null));
		fournisseur1.extraire();
		fournisseur1.insert(new Fournisseur(5 ,"L'espace création"));
	}

}
