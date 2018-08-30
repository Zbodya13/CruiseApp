package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;

import model.User;
import service.DBConnection;

/**
 *  DAO class for working with Users database.
 */


public class DAOUser implements DAOcommand<User>
{
	private Connection connection = null;
	
	public DAOUser() 
	{
		connection = DBConnection.getConnection();
	}

	@Override
	public void add(User user,String locale) throws SQLException 
	{
		if(locale.equals("ua"))
		{
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement("insert into usersua (login,password,name,surname,telephon,role,cash) values (?,?,?,?,?,?,?)");			
			connection.setAutoCommit(false);
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setString(4, user.getSurname());
			preparedStatement.setString(5, user.getTelefon());
			preparedStatement.setString(6, user.getRole());
			preparedStatement.setLong(7, user.getCash());
			preparedStatement.executeUpdate();
			connection.commit();
		}catch (SQLException e)
		{
			e.printStackTrace();
			connection.rollback();
		}
		}else if(locale.equals("en"))
		{
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement("insert into usersen (login,password,name,surname,telephon,role,cash) values (?,?,?,?,?,?,?)");			
			connection.setAutoCommit(false);
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setString(4, user.getSurname());
			preparedStatement.setString(5, user.getTelefon());	
			preparedStatement.setString(6, user.getRole());
			preparedStatement.setLong(7, user.getCash());
			preparedStatement.executeUpdate();
			connection.commit();
		}catch (SQLException e)
		{
			e.printStackTrace();
			connection.rollback();
		}
		}
	}

	@Override
	public void delete(String login) throws SQLException 
	{
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement("delete from usersua where login=?");			
			connection.setAutoCommit(false);
			preparedStatement.setString(1,login);
			preparedStatement.executeUpdate();	
			connection.commit();
		}catch(SQLException e) 
		{ 
			e.printStackTrace();
			connection.rollback();
		}
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement("delete from usersen where login=?");			
			connection.setAutoCommit(false);
			preparedStatement.setString(1,login);
			preparedStatement.executeUpdate();		
			connection.commit();
		}catch(SQLException e) 
		{
			e.printStackTrace();
			connection.rollback();
		}
		
	}

	@Override
	public void update(User user, String locale) throws SQLException 
	{
		if(locale.equals("ua"))
		{
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("update usersua set login=?, password=?, name=?, surname=?, telephon=?, role=?, cash=? where login=?");			
			connection.setAutoCommit(false);
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setString(4, user.getSurname());
			preparedStatement.setString(5, user.getTelefon());
			preparedStatement.setString(6, user.getRole());
			preparedStatement.setLong(7, user.getCash());
			preparedStatement.setString(8, user.getLogin());			
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) 
		{			
			e.printStackTrace();
			connection.rollback();
		}
		}
		else if(locale.equals("en")) 
		{
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("update usersen set login=?, password=?, name=?, surname=?, telephon=?, role=?, cash=? where login=?");
			connection.setAutoCommit(false);
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setString(4, user.getSurname());
			preparedStatement.setString(5, user.getTelefon());
			preparedStatement.setString(6, user.getRole());
			preparedStatement.setLong(7, user.getCash());
			preparedStatement.setString(8, user.getLogin());			
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) 
		{			
			e.printStackTrace();
			connection.rollback();
		}
		}
	}

	@Override
	public CopyOnWriteArrayList<User> getAll(String locale)
	{			
		CopyOnWriteArrayList<User> usersUA = new CopyOnWriteArrayList<User>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from usersua");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User userUA = new User();
                userUA.setLogin(rs.getString("login"));
                userUA.setPassword(rs.getString("password"));
                userUA.setName(rs.getString("name"));
                userUA.setSurname(rs.getString("surname"));  
                userUA.setTelefon(rs.getString("telephon"));
                userUA.setRole(rs.getString("role"));
                userUA.setCash(rs.getInt("cash"));
                usersUA.add(userUA);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
        CopyOnWriteArrayList<User> usersEN = new CopyOnWriteArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from usersen");
            while (rs.next()) {
                User userEN = new User();
                userEN.setLogin(rs.getString("login"));
                userEN.setPassword(rs.getString("password"));
                userEN.setName(rs.getString("name"));
                userEN.setSurname(rs.getString("surname"));  
                userEN.setTelefon(rs.getString("telephon"));
                userEN.setRole(rs.getString("role"));
                userEN.setCash(rs.getInt("cash"));
                usersEN.add(userEN);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usersEN;
	}

	@Override
	public User getByID(String id, String locale) 
	{		
		 User userUA = new User();
	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement("select * from usersua where login=?");
	            preparedStatement.setString(1, id);
	            ResultSet rs = preparedStatement.executeQuery();

	            if (rs.next()) {
	            	userUA.setLogin(rs.getString("login"));
	                userUA.setPassword(rs.getString("password"));
	                userUA.setName(rs.getString("name"));
	                userUA.setSurname(rs.getString("surname"));
	                userUA.setTelefon(rs.getString("telephon"));
	                userUA.setRole(rs.getString("role"));
	                userUA.setCash(rs.getInt("cash"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	     User userEN = new User();
	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement("select * from usersen where login=?");
	            preparedStatement.setString(1, id);
	            ResultSet rs = preparedStatement.executeQuery();

	            if (rs.next()) {
	            	userEN.setLogin(rs.getString("login"));
	                userEN.setPassword(rs.getString("password"));
	                userEN.setName(rs.getString("name"));
	                userEN.setSurname(rs.getString("surname"));
	                userEN.setTelefon(rs.getString("telephon"));
	                userEN.setRole(rs.getString("role"));
	                userEN.setCash(rs.getInt("cash"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        if(locale.equals("ua")) 
	        {
	        	 return userUA;
	        }else 
	        {
	        	return userEN;
	        }

	       
	}

}