package fr.diginamic.jdbc.dao;

import java.util.List;

public interface GenericDao<T> {
	List<T> extraire();
	void insert(T nvObjet);
	void update(int id, T nvObjet);
	void delete(int id);
	
}
