package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Excursion;
import model.UserExcursion;
import service.DBConnection;

public class DAOUExc implements DAOcommand<UserExcursion>
{

	private Connection connection = null;
	
	public DAOUExc() 
	{
		connection = DBConnection.getConnection();
	}

	@Override
	public void add(UserExcursion t, String locale) 
	{
			try 
			{
				PreparedStatement pre = connection.prepareStatement("insert into user_excursions (login, excursionID, count) values (?,?,?)");
				pre.setString(1, t.getLogin());
				pre.setString(2, t.getExcursionID());
				pre.setInt(3, t.getCount());
				pre.executeUpdate();
			}catch (SQLException e)
			{
				e.printStackTrace();
			}
	}

	
	public void delete(String excursionID,String login ) 
	{
		UserExcursion userExc = getByID(login,excursionID);
		if(userExc.getCount()>1) 
		{
			userExc.setCount(userExc.getCount()-1);
			this.update(userExc, "en");
		}else 
		{
		try 
		{
			PreparedStatement pre = connection.prepareStatement("delete from user_excursions where login=? and excursionID=?");
			pre.setString(1, login);
			pre.setString(2, excursionID);
			pre.executeUpdate();
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		}
	}

	@Override
	public void update(UserExcursion t, String locale) 
	{
		try 
		{
			PreparedStatement pre = connection.prepareStatement("update user_excursions set login=?, excursionID=?, count=? where login=? and excursionID=?");
			pre.setString(1, t.getLogin());
			pre.setString(2, t.getExcursionID());
			pre.setInt(3, t.getCount());
			pre.setString(4, t.getLogin());
			pre.setString(5, t.getExcursionID());
			pre.executeUpdate();
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public List<UserExcursion> getAll(String locale) 
	{
		List<UserExcursion> userExc = new ArrayList<UserExcursion>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from user_excursions");
            while (rs.next()) {
                UserExcursion uExc = new UserExcursion();
                uExc.setExcursionID(rs.getString("excursionID"));
                uExc.setLogin(rs.getString("login"));
                uExc.setCount(rs.getInt("count"));
                userExc.add(uExc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userExc;
	}

	@Override
	public UserExcursion getByID(String id, String excursionID) 
	{		
		UserExcursion uExc = new UserExcursion();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user_excursions where login=? and excursionID=?");
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, excursionID);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
            	uExc.setExcursionID(rs.getString("excursionID"));
            	uExc.setLogin(rs.getString("login"));
            	uExc.setCount(rs.getInt("count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uExc;
	}

	@Override
	public void delete(String ID) {
		// TODO Auto-generated method stub
		
	}

}
