package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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

@WebServlet("/customUser")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String LIST_SHIP = "customUser/ships.jsp";
	private static String LIST_EXCURSIONS = "customUser/excursions.jsp";
	private static String LIST_USER_EXCURSIONS = "customUser/userExcursions.jsp";
	private static String LIST_USER_SHIPS = "customUser/userShips.jsp";
	private static String AVAILABLE_LIST_EXCURSIONS = "customUser/availableExcursions.jsp";
	private DAOShip daoShip;
	private  DAOUser daoUser;
	private ApplicationService service;


	private DAOExcursion daoExc;
	
    public CustomerController() 
    {
        super();
        daoShip = new DAOShip();        
        daoUser = new DAOUser();     
        daoExc = new DAOExcursion();
        service = new ApplicationService();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		User u = (User)request.getSession().getAttribute("user");	
		String login = u.getLogin();		
		String locale = (String) request.getSession().getAttribute("sessionLocale");	
		User cash = daoUser.getByID(u.getLogin(),request.getParameter("sessionLocale"));
		request.getSession().setAttribute("cash",cash.getCash());			
		String action =  request.getParameter("action");
		
		if(action.equals("listShips")) 
		{					
			request.getRequestDispatcher(LIST_SHIP).forward(request, response);
			request.getSession().removeAttribute("error");
		}
		else if(action.equals("listBy")) 
		{			
			request.setAttribute("ships", daoShip.getAllOrder(locale,request.getParameter("orderBy")));
			request.getRequestDispatcher(LIST_SHIP).forward(request, response);
						
		}else if(action.equals("listUserBy")) 
		{						
			request.setAttribute("userShips", daoShip.getAllUser(locale,request.getParameter("orderBy"),login));
			request.getRequestDispatcher(LIST_USER_SHIPS).forward(request, response);
						
		}else if(action.equals("listExcursions")) 
		{					
			service.excListByPages(request, locale);
			request.getRequestDispatcher( LIST_EXCURSIONS).forward(request, response);			
		}
		else if(action.equals("availableListExcursions")) 
		{			 		
			List<Excursion> excursions = new ArrayList<>();
			List<String> ids = daoShip.getAllUserShipID(login);
			for(String id : ids) 
			{
				List<Excursion> tempexcs = daoExc.getAllAvailable(locale, id);
				for(Excursion excurs : tempexcs) 
				{
					if(!excursions.contains(excurs)) 
					{
						excursions.add(excurs);
					}
				}
			}			
			request.setAttribute("availableExcursions", excursions);
			request.getRequestDispatcher(AVAILABLE_LIST_EXCURSIONS).forward(request, response);			
		}
		else if(action.equals("buyShip")) 
		{
			try {
				service.buyShip(request, response, login, locale);
			} catch (SQLException e) 
			{
				request.getSession().setAttribute("error", "Something wrong with transaction");
				response.sendRedirect("?action=buyShip&sessionLocale=" + locale);			
			}
		}
		else if(action.equals("buyExcursion")) 
		{
			try {
				service.buyExcursion(request, response, login, locale);
			} catch (SQLException e) {
				request.getSession().setAttribute("error", "Something wrong with transaction");
				response.sendRedirect("?action=buyExcursion&sessionLocale=" + locale);			
			}
		}
		else if(action.equals("userListExcursions")) 
		{				
			request.setAttribute("userExcursions", daoExc.getAllUser(locale, login));
			request.getRequestDispatcher(LIST_USER_EXCURSIONS).forward(request, response);		
		}
		else if(action.equals("userListShips")) 
		{			
			request.setAttribute("userShips",daoShip.getAllUser(locale,"capacity",login));
			request.getRequestDispatcher(LIST_USER_SHIPS).forward(request, response);			
		}
		else if(action.equals("revokeShip")) 
		{										
			try {
				service.revokeShip(request, response, u, login, locale);
			} catch (SQLException e) 
			{
				request.getSession().setAttribute("error", "Something wrong with transaction");
				response.sendRedirect("?action=revokeShip&sessionLocale=" + locale);			
			}
		}
		else if(action.equals("revokeExcursion")) 
		{				
			try {
				service.revokeExcursion(request, response, u, login, locale);
			} catch (SQLException e) {
				request.getSession().setAttribute("error", "Something wrong with transaction");
				response.sendRedirect("?action=revokeExcursion&sessionLocale=" + locale);			
			}
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		String action =  request.getParameter("action");
		String locale= request.getParameter("sessionLocale");
		if(action.equals("addCash")) 
		{
			try 
			{					
			User user;
			String login = service.addCash(request);
			if(request.getParameter("page").equals("ship"))
			{				
				user = daoUser.getByID(login,locale);
				request.getSession().setAttribute("cash", user.getCash());
				response.sendRedirect("?action=listShips&sessionLocale=" + locale);
			}else if(request.getParameter("page").equals("exc"))
			{
				user = daoUser.getByID(login, locale);
				request.getSession().setAttribute("cash", user.getCash());
				response.sendRedirect("?action=listExcursions&sessionLocale=" + locale);

			}
			}catch(NumberFormatException e) 
			{				
				if(request.getParameter("page").equals("ship"))
				{
				request.getSession().setAttribute("error", "Invalid data");
				request.getRequestDispatcher(LIST_SHIP).forward(request, response);
				}
				else if(request.getParameter("page").equals("exc"))
				{
				request.getSession().setAttribute("error", "Invalid data");
				request.getRequestDispatcher(LIST_EXCURSIONS).forward(request, response);
				}
			} catch (SQLException e) 
			{
				request.getSession().setAttribute("error", "Something wrong with transaction");
				response.sendRedirect("?action=listShips&sessionLocale=" + locale);			
			}
		}

	}


	

}
