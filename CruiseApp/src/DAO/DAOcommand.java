package DAO;

import java.sql.SQLException;
import java.util.List;

/**
 *  Parent DAO class for working with database.
 */

public interface DAOcommand <T>
{
	public void add(T t, String locale) throws SQLException;
	public void delete(String ID) throws SQLException;
	public void update(T t, String locale) throws SQLException;
	public List<T> getAll(String locale);
	public T getByID(String id, String locale);
	
}
