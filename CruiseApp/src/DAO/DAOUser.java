package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import model.User;
import service.DBConnection;

public class DAOUser implements DAOcommand<User>
{
	private Connection connection = null;
	
	public DAOUser() 
	{
		connection = DBConnection.getConnection();
	}

	@Override
	public void add(User user,String locale) 
	{
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement("insert into usersua (login,password,name,surname,telephon,role,cash) values (?,?,?,?,?,?,?)");
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setString(4, user.getSurname());
			preparedStatement.setString(5, user.getTelefon());
			preparedStatement.setString(6, user.getRole());
			preparedStatement.setLong(7, user.getCash());
			preparedStatement.executeUpdate();
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement("insert into usersen (login,password,name,surname,telephon,role,cash) values (?,?,?,?,?,?,?)");
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setString(4, user.getSurname());
			preparedStatement.setString(5, user.getTelefon());	
			preparedStatement.setString(6, user.getRole());
			preparedStatement.setLong(7, user.getCash());
			preparedStatement.executeUpdate();
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(String login) 
	{
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement("delete from usersua where login=?");
			preparedStatement.setString(1,login);
			preparedStatement.executeUpdate();			
		}catch(SQLException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement("delete from usersen where login=?");
			preparedStatement.setString(1,login);
			preparedStatement.executeUpdate();			
		}catch(SQLException e) 
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(User user, String locale) 
	{
		if(locale.equals("ua"))
		{
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("update usersua set login=?, password=?, name=?, surname=?, telephon=?, role=?, cash=? where login=?");
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setString(4, user.getSurname());
			preparedStatement.setString(5, user.getTelefon());
			preparedStatement.setString(6, user.getRole());
			preparedStatement.setLong(7, user.getCash());
			preparedStatement.setString(8, user.getLogin());			
			preparedStatement.executeUpdate();
		} catch (SQLException e) 
		{			
			e.printStackTrace();
		}
		}
		else if(locale.equals("en")) 
		{
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("update usersen set login=?, password=?, name=?, surname=?, telephon=?, role=?, cash=? where login=?");
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setString(4, user.getSurname());
			preparedStatement.setString(5, user.getTelefon());
			preparedStatement.setString(6, user.getRole());
			preparedStatement.setLong(7, user.getCash());
			preparedStatement.setString(8, user.getLogin());			
			preparedStatement.executeUpdate();
		} catch (SQLException e) 
		{			
			e.printStackTrace();
		}
		}
	}

	@Override
	public List<User> getAll(String locale)
	{	
		List<User> usersUA = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from usersua");
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
        
        List<User> usersEN = new ArrayList<User>();
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