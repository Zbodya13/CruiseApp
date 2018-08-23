package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Ship;
import service.DBConnection;

public class DAOShip implements DAOcommand<Ship>
{
	private Connection connection = null;
	
	public DAOShip() 
	{
		connection = DBConnection.getConnection();
	}
	@Override
	public void add(Ship t, String locale) 
	{
		if(locale.equalsIgnoreCase("ua"))
		{
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement("insert into shipsua (shipID,capacity,route,countPort,duration,staff,type,price,services,departure) values (?,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, t.getShipID());
			preparedStatement.setInt(2, t.getCapacity());
			preparedStatement.setString(3, t.getRoute());
			preparedStatement.setInt(4, t.getCountPort());
			preparedStatement.setString(5, t.getDuration());			
			preparedStatement.setString(6, t.getStaff());
			preparedStatement.setString(7, t.getType());
			preparedStatement.setInt(8, t.getPrice());
			preparedStatement.setString(9, t.getServices());
			preparedStatement.setDate(10,new java.sql.Date(t.getDeparture().getTime()));
			preparedStatement.executeUpdate();
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		}
		else if(locale.equalsIgnoreCase("en"))
		{
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement("insert into shipsen (shipID,capacity,route,countPort,duration,staff,type,price,services,departure) values (?,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, t.getShipID());
			preparedStatement.setInt(2, t.getCapacity());
			preparedStatement.setString(3, t.getRoute());
			preparedStatement.setInt(4, t.getCountPort());
			preparedStatement.setString(5, t.getDuration());			
			preparedStatement.setString(6, t.getStaff());
			preparedStatement.setString(7, t.getType());
			preparedStatement.setInt(8, t.getPrice());
			preparedStatement.setString(9, t.getServices());
			preparedStatement.setDate(10,new java.sql.Date(t.getDeparture().getTime()));
			preparedStatement.executeUpdate();
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		}
	}

	@Override
	public void delete(String shipID) 
	{
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement("delete from shipsua where shipID=?");
			preparedStatement.setString(1,shipID);
			preparedStatement.executeUpdate();			
		}catch(SQLException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement("delete from shipsen where shipID=?");
			preparedStatement.setString(1,shipID);
			preparedStatement.executeUpdate();			
		}catch(SQLException e) 
		{
			e.printStackTrace();
		}		
	}

	@Override
	public void update(Ship t, String locale) 
	{
		if(locale.equalsIgnoreCase("ua"))
		{
		try {			
			PreparedStatement preparedStatement = connection.prepareStatement("update shipsua set shipID=?, capacity=? ,route=? ,countPort=? ,duration=? ,staff=? ,type=? ,price=? ,services=? ,departure=? where shipID=?");
			preparedStatement.setString(1, t.getShipID());
			preparedStatement.setInt(2, t.getCapacity());
			preparedStatement.setString(3, t.getRoute());
			preparedStatement.setInt(4, t.getCountPort());
			preparedStatement.setString(5, t.getDuration());
			preparedStatement.setString(6, t.getStaff());			
			preparedStatement.setString(7, t.getType());
			preparedStatement.setInt(8, t.getPrice());
			preparedStatement.setString(9, t.getServices());	
			System.out.println(t.getDeparture().getTime());
			preparedStatement.setDate(10, new java.sql.Date(t.getDeparture().getTime()));
			preparedStatement.setString(11, t.getShipID());
			preparedStatement.executeUpdate();
		} catch (SQLException e) 
		{			
			e.printStackTrace();
		}
		}
		else if(locale.equalsIgnoreCase("en")) 
		{
		try {			
			PreparedStatement preparedStatement = connection.prepareStatement("update shipsen set shipID=?, capacity=? ,route=? ,countPort=? ,duration=? ,staff=? ,type=? ,price=? ,services=? ,departure=? where shipID=?");
			preparedStatement.setString(1, t.getShipID());
			preparedStatement.setInt(2, t.getCapacity());
			preparedStatement.setString(3, t.getRoute());
			preparedStatement.setInt(4, t.getCountPort());
			preparedStatement.setString(5, t.getDuration());
			preparedStatement.setString(6, t.getStaff());			
			preparedStatement.setString(7, t.getType());
			preparedStatement.setInt(8, t.getPrice());
			preparedStatement.setString(9, t.getServices());
			System.out.println(t.getDeparture().getTime());
			preparedStatement.setDate(10, new java.sql.Date(t.getDeparture().getTime()));
			preparedStatement.setString(11, t.getShipID());
			preparedStatement.executeUpdate();
		} catch (SQLException e) 
		{			
			e.printStackTrace();
		}
		}
	}

	@Override
	public List<Ship> getAll(String locale) 
	{
		List<Ship> shipsUA = new ArrayList<Ship>();
        try {        	
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from shipsua");
            while (rs.next()) {
                Ship shipUA = new Ship();
                shipUA.setShipID(rs.getString("shipID"));
                shipUA.setCapacity(rs.getInt("capacity"));
                shipUA.setRoute(rs.getString("route"));
                shipUA.setCountPort(rs.getInt("countPort"));
                shipUA.setDuration(rs.getString("duration"));
                shipUA.setStaff(rs.getString("staff"));
                shipUA.setType(rs.getString("type"));;
                shipUA.setPrice(rs.getInt("price"));
                shipUA.setServices(rs.getString("services"));
                shipUA.setDeparture(rs.getDate("departure"));
                shipsUA.add(shipUA);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Ship> shipsEN = new ArrayList<Ship>();
        try {        	
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from shipsen");
            while (rs.next()) {
                Ship shipEN = new Ship();
                shipEN.setShipID(rs.getString("shipID"));
                shipEN.setCapacity(rs.getInt("capacity"));
                shipEN.setRoute(rs.getString("route"));
                shipEN.setCountPort(rs.getInt("countPort"));
                shipEN.setDuration(rs.getString("duration"));
                shipEN.setStaff(rs.getString("staff"));
                shipEN.setType(rs.getString("type"));;
                shipEN.setPrice(rs.getInt("price"));
                shipEN.setServices(rs.getString("services"));
                shipEN.setDeparture(rs.getDate("departure"));
                shipsEN.add(shipEN);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(locale.equalsIgnoreCase("ua"))
        {
        	return shipsUA;
        }else 
        {
        	return shipsEN;
        }
        
	}

	@Override
	public Ship getByID(String id,String locale) 
	{
		 Ship ship;
		 Ship shipUA = new Ship();
	        try {	        	
	            PreparedStatement preparedStatement = connection.prepareStatement("select * from shipsua where shipID=?");
	            preparedStatement.setString(1, id);
	            ResultSet rs = preparedStatement.executeQuery();

	            if (rs.next()) {
	            	shipUA.setShipID(rs.getString("shipID"));
	                shipUA.setCapacity(rs.getInt("capacity"));
	                shipUA.setRoute(rs.getString("route"));
	                shipUA.setCountPort(rs.getInt("countPort"));
	                shipUA.setDuration(rs.getString("duration"));
	                shipUA.setStaff(rs.getString("staff"));
	                shipUA.setType(rs.getString("type"));;
	                shipUA.setPrice(rs.getInt("price"));
	                shipUA.setServices(rs.getString("services"));
	                shipUA.setDeparture(rs.getDate("departure"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	      Ship shipEN = new Ship();
	        try {	        	
	            PreparedStatement preparedStatement = connection.prepareStatement("select * from shipsen where shipID=?");
	            preparedStatement.setString(1, id);
	            ResultSet rs = preparedStatement.executeQuery();

	            if (rs.next()) {
	            	shipEN.setShipID(rs.getString("shipID"));
	                shipEN.setCapacity(rs.getInt("capacity"));
	                shipEN.setRoute(rs.getString("route"));
	                shipEN.setCountPort(rs.getInt("countPort"));
	                shipEN.setDuration(rs.getString("duration"));
	                shipEN.setStaff(rs.getString("staff"));
	                shipEN.setType(rs.getString("type"));;
	                shipEN.setPrice(rs.getInt("price"));
	                shipEN.setServices(rs.getString("services"));
	                shipEN.setDeparture(rs.getDate("departure"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        if(locale.equals("ua"))
	        {
	        	ship = shipUA;
	        }else if(locale.equals("en")) 
	        {
	        	ship = shipEN;
	        }else 
	        {
	        	ship = shipEN;
	        }	  
	        return ship;
	        
	        
	}
	public List<Ship> getAllOrder(String locale, String orderBy) 
	{
		String queryua = "select * from shipsua";
		List<Ship> shipsUA = new ArrayList<Ship>();
        try {        	            
            switch(orderBy) 
            {
            case "capacity":
            	queryua = "select * from shipsua order by capacity";
            	break;
            case "route":
            	queryua = "select * from shipsua order by route";
            	break;
            case "countPort":
            	queryua = "select * from shipsua order by countPort";
            	break;
            case "duration":
            	queryua = "select * from shipsua order by duration";
            	break;
            case "staff":
            	queryua = "select * from shipsua order by staff";
            	break;
            case "type":
            	queryua = "select * from shipsua order by type;";
            	break;
            case "price":
            	queryua = "select * from shipsua order by price";
            	break;
            case "services":
            	queryua = "select * from shipsua order by services";
            	break;
            case "departure":
            	queryua = "select * from shipsua order by departure";
            	break;
            }
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(queryua);
            while (rs.next()) {
                Ship shipUA = new Ship();
                shipUA.setShipID(rs.getString("shipID"));
                shipUA.setCapacity(rs.getInt("capacity"));
                shipUA.setRoute(rs.getString("route"));
                shipUA.setCountPort(rs.getInt("countPort"));
                shipUA.setDuration(rs.getString("duration"));
                shipUA.setStaff(rs.getString("staff"));
                shipUA.setType(rs.getString("type"));;
                shipUA.setPrice(rs.getInt("price"));
                shipUA.setServices(rs.getString("services"));
                shipUA.setDeparture(rs.getDate("departure"));
                shipsUA.add(shipUA);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String queryen = "select * from shipsen";
        List<Ship> shipsEN = new ArrayList<Ship>();
        try {         	
        	switch(orderBy) 
            {
            case "capacity":
            	queryen = "select * from shipsen order by capacity ASC";
            	break;
            case "route":
            	queryen = "select * from shipsen order by route ASC";
            	break;
            case "countPort":
            	queryen = "select * from shipsen order by countPort ASC";
            	break;
            case "duration":
            	queryen = "select * from shipsen order by duration ASC";
            	break;
            case "staff":
            	queryen = "select * from shipsen order by staff ASC";
            	break;
            case "type":
            	queryen = "select * from shipsen order by type ASC";
            	break;
            case "price":
            	queryen = "select * from shipsen order by price ASC";
            	break;
            case "departure":
            	queryen = "select * from shipsen order by departure ASC";
            	break;
            case "services":
            	queryen = "select * from shipsen order by services ASC";
            	break;
            }
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(queryen);
            while (rs.next()) {
                Ship shipEN = new Ship();
                shipEN.setShipID(rs.getString("shipID"));
                shipEN.setCapacity(rs.getInt("capacity"));
                shipEN.setRoute(rs.getString("route"));
                shipEN.setCountPort(rs.getInt("countPort"));
                shipEN.setDuration(rs.getString("duration"));
                shipEN.setStaff(rs.getString("staff"));
                shipEN.setType(rs.getString("type"));;
                shipEN.setPrice(rs.getInt("price"));
                shipEN.setServices(rs.getString("services"));
                shipEN.setDeparture(rs.getDate("departure"));
                shipsEN.add(shipEN);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }       
        if(locale.equalsIgnoreCase("ua"))
        {
        	System.out.println("ua");
        	return shipsUA;        	
        }else 
        {
        	System.out.println("ua");
        	return shipsEN;        	
        }
        
	}
	
	public List<Ship> getAllUser(String locale, String orderBy, String login) 
	{
		String queryua = "select   user_ships.count, shipsua.shipID, shipsua.capacity, shipsua.route, shipsua.countPort, shipsua.duration, shipsua.staff, shipsua.type, shipsua.price, shipsua.services, shipsua.departure"
				+ " from shipsua join user_ships on shipsua.shipID=user_ships.shipID where user_ships.login=\"" + login + "\" order by " + orderBy;
		List<Ship> shipsUA = new ArrayList<Ship>();
        try {        	            
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(queryua);
            while (rs.next()) {
                Ship shipUA = new Ship();
                shipUA.setShipID(rs.getString("shipID"));
                shipUA.setCapacity(rs.getInt("capacity"));
                shipUA.setRoute(rs.getString("route"));
                shipUA.setCountPort(rs.getInt("countPort"));
                shipUA.setDuration(rs.getString("duration"));
                shipUA.setStaff(rs.getString("staff"));
                shipUA.setType(rs.getString("type"));;
                shipUA.setPrice(rs.getInt("price"));
                shipUA.setServices(rs.getString("services"));
                shipUA.setDeparture(rs.getDate("departure"));
                shipUA.setCount(rs.getInt("count"));
                shipsUA.add(shipUA);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String queryen = "select   user_ships.count, shipsen.shipID, shipsen.capacity, shipsen.route, shipsen.countPort, shipsen.duration, shipsen.staff, shipsen.type, shipsen.price, shipsen.services, shipsen.departure"
				+ " from shipsen join user_ships on shipsen.shipID=user_ships.shipID where user_ships.login=\"" + login + "\" order by " + orderBy;
        List<Ship> shipsEN = new ArrayList<Ship>();
        try {         	
        	Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(queryen);
            while (rs.next()) {
                Ship shipEN = new Ship();
                shipEN.setShipID(rs.getString("shipID"));
                shipEN.setCapacity(rs.getInt("capacity"));
                shipEN.setRoute(rs.getString("route"));
                shipEN.setCountPort(rs.getInt("countPort"));
                shipEN.setDuration(rs.getString("duration"));
                shipEN.setStaff(rs.getString("staff"));
                shipEN.setType(rs.getString("type"));;
                shipEN.setPrice(rs.getInt("price"));
                shipEN.setServices(rs.getString("services"));
                shipEN.setDeparture(rs.getDate("departure"));
                shipEN.setCount(rs.getInt("count"));
                shipsEN.add(shipEN);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }       
        if(locale.equalsIgnoreCase("ua"))
        {
        	System.out.println("ua");
        	return shipsUA;        	
        }else 
        {
        	System.out.println("ua");
        	return shipsEN;        	
        }        
	}
	
	public List<String> getAllUserShipID( String login) 
	{
		String queryua = "select   shipsua.shipID"
				+ " from shipsua join user_ships on shipsua.shipID=user_ships.shipID where user_ships.login=\"" + login + "\"";
		List<String> shipsid = new ArrayList<String>();
        try {        	            
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(queryua);
            while (rs.next()) {                
                shipsid.add(rs.getString("shipID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shipsid;        
	}
	
	

}
