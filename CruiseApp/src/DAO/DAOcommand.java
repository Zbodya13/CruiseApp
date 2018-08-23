package DAO;

import java.util.List;

public interface DAOcommand <T>
{
	public void add(T t, String locale);
	public void delete(String ID);
	public void update(T t, String locale);
	public List<T> getAll(String locale);
	public T getByID(String id, String locale);
	
}
