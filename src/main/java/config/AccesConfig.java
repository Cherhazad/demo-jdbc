package config;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.ResourceBundle;

// Ligne de commande pour exécuter celle classe : java -Xmx64M config.AccessConfig

public class AccesConfig {

	public static void main(String[] args) {

		ResourceBundle config = ResourceBundle.getBundle("config");
		String url = config.getString("database.url");
		String user = config.getString("database.user");
		String pwd = config.getString("database.password");
		System.out.println(url);

		// avec les méthodes de la classe Enumeration

//		Enumeration<String> keys = config.getKeys();
//
//		while (config.getKeys().hasMoreElements()) {
//			String key = config.getKeys().nextElement();
//			System.out.println(key);
//		}

		
		// ou bien avec l'iterator

//		Iterator<String> iterateur = config.getKeys().asIterator();
//
//		while (iterateur.hasNext()) {
//			String key = iterateur.next();
//			System.out.println(key);
//		}

		try {
			DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
			//DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver()); //on peut aussi écrire car font la même chose : Class.forName("com.mysql.cj.jdbc.Driver");
			Connection maConnection = DriverManager.getConnection(url, user, pwd);
			maConnection.close();
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
	}

}
