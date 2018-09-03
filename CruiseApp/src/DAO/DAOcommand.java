package DAO;

import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *  Parent DAO class for working with database.
 */

public interface DAOcommand <T>
{
	public void add(T t, String locale) throws SQLException;
	public void delete(String ID) throws SQLException;
	public void update(T t, String locale) throws SQLException;
	public CopyOnWriteArrayList<T> getAll(String locale);
	public T getByID(String id, String locale);
	abstract public T createEntity();
	
}
