package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAOExcursion;
import DAO.DAOShip;
import DAO.DAOUExc;
import DAO.DAOUShip;
import DAO.DAOUser;
import model.Excursion;
import model.Ship;
import model.User;
import model.UserExcursion;
import model.UserShip;

public class ApplicationService 
{
	private DAOUser daoUser;
	private DAOExcursion daoExc;
	private DAOShip daoShip;
	private DAOUShip daoUShip;
	private DAOUExc daoUExc;
	
	public ApplicationService() 
	{	
		daoExc = new DAOExcursion();
		daoUser = new DAOUser();
		daoShip = new DAOShip(); 
		daoUShip = new DAOUShip();
		daoUExc = new DAOUExc();
	}
	
	public void makeUser(HttpServletRequest request) {
		User user = new User();
		user.setLogin(request.getParameter("login"));
		user.setPassword(request.getParameter("password"));
		user.setName(request.getParameter("name_en"));
		user.setSurname(request.getParameter("surname_en"));
		user.setTelefon(request.getParameter("telephon"));
		user.setRole("custom");
		user.setCash(Integer.parseInt(request.getParameter("cash")));
		daoUser.add(user, "en");
		user.setLogin(request.getParameter("login"));
		user.setPassword(request.getParameter("password"));
		user.setName(request.getParameter("name_ua"));
		user.setSurname(request.getParameter("surname_ua"));
		user.setTelefon(request.getParameter("telephon"));
		user.setRole("custom");
		user.setCash(Integer.parseInt(request.getParameter("cash")));
		daoUser.add(user, "ua");
	}
	
	public void makeExcursion(HttpServletRequest request, String locale) throws UnsupportedEncodingException {
		Excursion excursion = new Excursion();
		excursion.setExcursionID(new String(request.getParameter("excursionID").getBytes("iso-8859-1"),"utf-8"));
		excursion.setDescription(new String(request.getParameter("description").getBytes("iso-8859-1"),"utf-8"));
		excursion.setCity(new String(request.getParameter("city").getBytes("iso-8859-1"),"utf-8"));
		excursion.setPrice(Integer.parseInt(request.getParameter("price")));
		daoExc.add(excursion, locale);
	}
	
	public void updateExcursion(HttpServletRequest request) throws UnsupportedEncodingException {
		Excursion excursion = new Excursion();
		String localeex = (String)request.getSession().getAttribute("sessionLocale");
		excursion.setExcursionID(new String(request.getParameter("excursionID").getBytes("iso-8859-1"),"utf-8"));
		excursion.setDescription(new String(request.getParameter("description").getBytes("iso-8859-1"),"utf-8"));
		excursion.setCity(new String(request.getParameter("city").getBytes("iso-8859-1"),"utf-8"));
		excursion.setPrice(Integer.parseInt(request.getParameter("price")));
		daoExc.update(excursion, localeex);
	}
	
	public void makeShip(HttpServletRequest request, String locale)
			throws ParseException, UnsupportedEncodingException {
		Ship ship = new Ship();
		ship.setCapacity(Integer.parseInt(request.getParameter("capacity")));
		ship.setCountPort(Integer.parseInt(request.getParameter("countPort")));			        
		Date departur = new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("departure"));
		ship.setDeparture(departur);			      
		ship.setDuration(new String(request.getParameter("duration").getBytes("iso-8859-1"), "utf-8"));	     
		ship.setPrice(Integer.parseInt(request.getParameter("price")));
		ship.setRoute(new String(request.getParameter("route").getBytes("iso-8859-1"), "utf-8"));
		ship.setServices(new String(request.getParameter("services").getBytes("iso-8859-1"), "utf-8"));
		ship.setShipID(new String(request.getParameter("shipID").getBytes("iso-8859-1"), "utf-8"));
		ship.setStaff(new String(request.getParameter("staff").getBytes("iso-8859-1"), "utf-8"));
		ship.setType(new String(request.getParameter("type").getBytes("iso-8859-1"), "utf-8"));
		daoShip.add(ship,locale);
	}
	
	public void updateShip(HttpServletRequest request) throws ParseException, UnsupportedEncodingException {
		Ship ship = new Ship();
		String localeed = (String)request.getSession().getAttribute("sessionLocale");
		ship.setCapacity(Integer.parseInt(request.getParameter("capacity")));
		ship.setCountPort(Integer.parseInt(request.getParameter("countPort")));					
		Date departur = new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("departure"));
		ship.setDeparture(departur);			 
		ship.setDuration(new String(request.getParameter("duration").getBytes("iso-8859-1"), "utf-8"));	     
		ship.setPrice(Integer.parseInt(request.getParameter("price")));
		ship.setRoute(new String(request.getParameter("route").getBytes("iso-8859-1"), "utf-8"));
		ship.setServices(new String(request.getParameter("services").getBytes("iso-8859-1"), "utf-8"));
		ship.setShipID(new String(request.getParameter("shipID").getBytes("iso-8859-1"), "utf-8"));
		ship.setStaff(new String(request.getParameter("staff").getBytes("iso-8859-1"), "utf-8"));
		ship.setType(new String(request.getParameter("type").getBytes("iso-8859-1"), "utf-8"));
		daoShip.update(ship,localeed);
	}
	
	public void excListByPages(HttpServletRequest request, String locale) {
		List<Excursion> exc = daoExc.getAll(locale);			
		List<Integer> pages = new ArrayList<>();			
		for(int i= 1;i<=Math.ceil(exc.size()/5)+1;i++) 
		{
			pages.add(i);
		}			
		request.setAttribute("pages", pages);			
		if(request.getParameter("page")!=null)
		{
			int page = Integer.parseInt(request.getParameter("page"));
			if(locale.equals("ua"))
			{
				request.setAttribute("excursions", daoExc.getAllByPage("ua",page));
			}else if (locale.equals("en")) 
			{
				request.setAttribute("excursions", daoExc.getAllByPage("en",page));
			}
		}
		else
		{			
			if(locale.equals("ua"))
			{
				request.setAttribute("excursions", daoExc.getAllByPage("ua",1));
			}else if (locale.equals("en")) 
			{
				request.setAttribute("excursions", daoExc.getAllByPage("en",1));
			}
		}
	}
	
	public void buyShip(HttpServletRequest request, HttpServletResponse response, String login, String locale)
			throws IOException {
		User u;
		boolean act = true;			
		u = daoUser.getByID(login, locale);
		Ship ship = (Ship)daoShip.getByID(request.getParameter("shipID"),locale);
		if(u.getCash()>ship.getPrice())
		{				
			List<UserShip> userShip = daoUShip.getAll(locale);
			for(UserShip us : userShip) 
			{
				if(us.getLogin().equals(u.getLogin()) && us.getShipID().equals(ship.getShipID())) 
				{						
					ship.setCapacity(ship.getCapacity()-1);
					daoShip.update(ship, locale);
					us.setCount(us.getCount()+1);						
					daoUShip.update(us,locale);		
					if(locale.equals("en")) 
					{
						u.setCash(u.getCash()-ship.getPrice());
						daoUser.update(u,locale);
						request.getSession().setAttribute("cash", u.getCash());
						u = daoUser.getByID(login, "ua");
						ship = (Ship)daoShip.getByID(ship.getShipID(), "ua");
						u.setCash(u.getCash()-ship.getPrice());
						daoUser.update(u, "ua");
					}
					else if(locale.equals("ua"))
					{
						u.setCash(u.getCash()-ship.getPrice());
						daoUser.update(u, locale);
						request.getSession().setAttribute("cash", u.getCash());
						u = daoUser.getByID(login, "en");
						ship = (Ship)daoShip.getByID(ship.getShipID(), "en");
						u.setCash(u.getCash()-ship.getPrice());
						daoUser.update(u, "en");
					}						
					act = false;
				}
			}
			if(act)
			{
				UserShip uShip = new UserShip(u.getLogin(),request.getParameter("shipID"),1);
				ship.setCapacity(ship.getCapacity()-1);
				daoShip.update(ship, locale);					
				if(locale.equals("en")) 
				{
					u.setCash(u.getCash()-ship.getPrice());
					daoUser.update(u,locale);
					request.getSession().setAttribute("cash", u.getCash());
					u = daoUser.getByID(login, "ua");
					ship = (Ship)daoShip.getByID(ship.getShipID(), "ua");
					u.setCash(u.getCash()-ship.getPrice());
					daoUser.update(u, "ua");
				}
				else if(locale.equals("ua"))
				{
					u.setCash(u.getCash()-ship.getPrice());
					daoUser.update(u, locale);
					request.getSession().setAttribute("cash", u.getCash());
					u = daoUser.getByID(login, "en");
					ship = (Ship)daoShip.getByID(ship.getShipID(), "en");
					u.setCash(u.getCash()-ship.getPrice());
					daoUser.update(u, "en");
				}	
				daoUShip.add(uShip,locale);		
			}
			response.sendRedirect("?action=listShips&sessionLocale=" + locale);

		}else 
		{
			request.getSession().setAttribute("error", "You havent money");
			response.sendRedirect("?action=listShips&sessionLocale=" + locale);
		}
	}
	
	public void buyExcursion(HttpServletRequest request, HttpServletResponse response, String login, String locale)
			throws IOException {
		User u;
		boolean act = true;			
		u = daoUser.getByID(login, locale);
		Excursion exc = daoExc.getByID(request.getParameter("excursionID"), locale);
		List<UserExcursion> userExcursions = daoUExc.getAll(locale);
		if(u.getCash()>exc.getPrice())
		{
			for(UserExcursion ue : userExcursions) 
			{
				if(ue.getLogin().equals(u.getLogin()) && ue.getExcursionID().equals(exc.getExcursionID())) 
				{						
					ue.setCount(ue.getCount()+1);
					daoUExc.update(ue,locale);
					if(locale.equals("en")) 
					{
						u.setCash(u.getCash()-exc.getPrice());
						daoUser.update(u,locale);
						request.getSession().setAttribute("cash", u.getCash());
						u = daoUser.getByID(login, "ua");
						exc = daoExc.getByID(request.getParameter("excursionID"), "ua");
						u.setCash(u.getCash()-exc.getPrice());
						daoUser.update(u, "ua");
					}
					else if(locale.equals("ua"))
					{
						u.setCash(u.getCash()-exc.getPrice());
						daoUser.update(u, locale);
						request.getSession().setAttribute("cash", u.getCash());
						u = daoUser.getByID(login, "en");
						exc = daoExc.getByID(request.getParameter("excursionID"), "en");
						u.setCash(u.getCash()-exc.getPrice());
						daoUser.update(u, "en");
					}	
					act = false;
				}
			}
			if(act) 
			{
				UserExcursion uExc = new UserExcursion(u.getLogin(),request.getParameter("excursionID"),1);
				if(locale.equals("en")) 
				{
					u.setCash(u.getCash()-exc.getPrice());
					daoUser.update(u,locale);
					request.getSession().setAttribute("cash", u.getCash());
					u = daoUser.getByID(login, "ua");
					exc = daoExc.getByID(request.getParameter("excursionID"), "ua");
					u.setCash(u.getCash()-exc.getPrice());
					daoUser.update(u, "ua");
				}
				else if(locale.equals("ua"))
				{
					u.setCash(u.getCash()-exc.getPrice());
					daoUser.update(u,locale);
					request.getSession().setAttribute("cash", u.getCash());
					u = daoUser.getByID(login, "en");
					exc = daoExc.getByID(request.getParameter("excursionID"), "en");
					u.setCash(u.getCash()-exc.getPrice());
					daoUser.update(u, "en");
				}	
				daoUExc.add(uExc,locale);							
			}
			response.sendRedirect("?action=userListExcursions&sessionLocale=" + locale);
		}
		else 
		{
			request.getSession().setAttribute("error", "You havent money");
			response.sendRedirect("?action=userListExcursions&sessionLocale=" + locale);
		}
	}
	
	public void revokeShip(HttpServletRequest request, HttpServletResponse response, User u, String login,	String locale) throws IOException 
	{
		Ship ship = (Ship)daoShip.getByID(request.getParameter("shipID"), "en");			
		ship.setCapacity(ship.getCapacity()+1);
		daoShip.update(ship, "en");
		ship = (Ship)daoShip.getByID(request.getParameter("shipID"), "ua");
		ship.setCapacity(ship.getCapacity()+1);
		daoShip.update(ship, "ua");
		u = daoUser.getByID(u.getLogin(), locale);
		if(locale.equals("en")) 
		{
			ship = daoShip.getByID(request.getParameter("shipID"), locale);
			u.setCash(u.getCash()+ship.getPrice());
			daoUser.update(u,locale);
			request.getSession().setAttribute("cash", u.getCash());
			u = daoUser.getByID(login, "ua");
			ship = daoShip.getByID(request.getParameter("shipID"), "ua");
			u.setCash(u.getCash()+ship.getPrice());
			daoUser.update(u, "ua");
		}
		else if(locale.equals("ua"))
		{
			ship = daoShip.getByID(request.getParameter("shipID"), locale);
			u.setCash(u.getCash()+ship.getPrice());
			daoUser.update(u,locale);
			request.getSession().setAttribute("cash", u.getCash());
			u = daoUser.getByID(login, "en");
			ship = daoShip.getByID(request.getParameter("shipID"), "en");
			u.setCash(u.getCash()+ship.getPrice());
			daoUser.update(u, "en");
		}	
		daoUShip.delete(request.getParameter("shipID"),login);		
		response.sendRedirect("?action=userListShips&sessionLocale=" + locale);
	}
	
	public void revokeExcursion(HttpServletRequest request, HttpServletResponse response, User u, String login,
			String locale) throws IOException {
		daoUExc.delete(request.getParameter("excursionID"),login);	
		u = daoUser.getByID(u.getLogin(), locale);
		if(locale.equals("en")) 
		{
			Excursion exc = daoExc.getByID(request.getParameter("excursionID"), locale);
			u.setCash(u.getCash()+exc.getPrice());
			daoUser.update(u,locale);
			request.getSession().setAttribute("cash", u.getCash());
			u = daoUser.getByID(login, "ua");
			exc = daoExc.getByID(request.getParameter("excursionID"), "ua");
			u.setCash(u.getCash()+exc.getPrice());
			daoUser.update(u, "ua");
		}
		else if(locale.equals("ua"))
		{
			Excursion exc = daoExc.getByID(request.getParameter("excursionID"), locale);
			u.setCash(u.getCash()+exc.getPrice());
			daoUser.update(u,locale);
			request.getSession().setAttribute("cash", u.getCash());
			u = daoUser.getByID(login, "en");
			exc = daoExc.getByID(request.getParameter("excursionID"), "en");
			u.setCash(u.getCash()+exc.getPrice());
			daoUser.update(u, "en");
		}	
		response.sendRedirect("?action=userListExcursions&sessionLocale=" + locale);
	}
	
	public String addCash(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		String login = user.getLogin();
		User userEN = daoUser.getByID(login, "en"); 
		int currentCash = userEN.getCash();
		currentCash=currentCash + Integer.parseInt(request.getParameter("cash"));
		userEN.setCash(currentCash);
		daoUser.update(userEN, "en");
		User userUA = daoUser.getByID(login, "ua"); 
		currentCash = userUA.getCash();
		currentCash=currentCash + (Integer.parseInt(request.getParameter("cash"))*30);
		userUA.setCash(currentCash);
		daoUser.update(userUA, "ua");
		return login;
	}

}
