package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAOShip;
import DAO.DAOUser;
import model.User;


@WebServlet("/auth")
public class AuthenticationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String LIST_SHIP = "/admin/shipsAdmin.jsp";
	private static String LIST_SHIP_EN = "/admin/adminShip?action=list&sessionLocale=en";
	private static String LIST_SHIP_UA = "/admin/adminShip?action=list&sessionLocale=ua";
	private static String CUSTOM_UA = "/customUser?action=listShips&sessionLocale=ua";
	private static String CUSTOM_EN = "/customUser?action=listShips&sessionLocale=en";
	private static String AUTH = "/view/index.jsp";
	private DAOUser daoUser;
    
    public AuthenticationController() 
    {
        super();
        daoUser = new DAOUser();
        
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		request.getSession().setAttribute("sessionLocale", "ua");	
		request.getSession().removeAttribute("errorMessage");
		String login =  request.getParameter("login");
		String password = request.getParameter("password");				
		User user = daoUser.getByID(login,"en");		
		if(password.equals(user.getPassword()) && user.getRole().equals("admin"))
			{				
				request.getSession().setAttribute("user", user);		
				if(request.getSession().getAttribute("sessionLocale").equals("en")) 
				{				
				response.sendRedirect(request.getContextPath() + LIST_SHIP_EN);
				}else if(request.getSession().getAttribute("sessionLocale").equals("ua")) 
				{				
				response.sendRedirect(request.getContextPath() + LIST_SHIP_UA);
				}				
			}
		else if(password.equals(user.getPassword()) && user.getRole().equals("custom")) 
			{
				request.getSession().setAttribute("user", user);
				if(request.getSession().getAttribute("sessionLocale").equals("en")) 
				{				
				response.sendRedirect(request.getContextPath() + CUSTOM_EN);
				}else if(request.getSession().getAttribute("sessionLocale").equals("ua")) 
				{				
				response.sendRedirect(request.getContextPath() + CUSTOM_UA);
				}
			}
		else 
			{	
				request.getSession().setAttribute("errorMessage", "Invalid login or password");
				response.sendRedirect(request.getContextPath());
			}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		req.getRequestDispatcher(LIST_SHIP).forward(req, resp);
	}



}
