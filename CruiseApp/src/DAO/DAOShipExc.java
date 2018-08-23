package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.ShipExcursion;
import model.UserExcursion;
import service.DBConnection;

public class DAOShipExc implements DAOcommand<ShipExcursion>
{
	private Connection connection = null;

	public DAOShipExc() 
	{
		connection = DBConnection.getConnection();
	}

	@Override
	public void add(ShipExcursion t, String locale)
	{
		try 
		{
			PreparedStatement pre = connection.prepareStatement("insert into ship_excursion (shipID, excursionID) values (?,?)");
			pre.setString(1, t.getShipID());
			pre.setString(2, t.getExcursionID());			
			pre.executeUpdate();
		}catch (SQLException e)
		{
			e.printStackTrace();
		}		
	}

	@Override
	public void delete(String ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ShipExcursion t, String locale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ShipExcursion> getAll(String locale) 
	{
		List<ShipExcursion> shipExc = new ArrayList<ShipExcursion>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from ship_excursion");
            while (rs.next()) {
                ShipExcursion shExc = new ShipExcursion();
                shExc.setExcursionID(rs.getString("excursionID"));
                shExc.setShipID(rs.getString("shipID"));               
                shipExc.add(shExc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shipExc;
	}

	@Override
	public ShipExcursion getByID(String id, String locale) 
	{
		ShipExcursion shExc = new ShipExcursion();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ship_excursion where shipID=?");
            preparedStatement.setString(1, id);           
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
            	shExc.setExcursionID(rs.getString("excursionID"));
            	shExc.setShipID(rs.getString("shipID"));            	
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shExc;
	}

	
}