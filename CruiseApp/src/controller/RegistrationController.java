package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import DAO.DAOUser;
import model.User;
import service.Matcher;


/**
 * This class register new users for cruise app.
 */

@WebServlet("/register")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String REGISTER = "/view/registrationForm.jsp";	
	private ApplicationService service;
	private DAOUser daoUser;
	boolean checkUser;
	Matcher match;
	private static final Logger log = Logger.getLogger(RegistrationController.class);	
	
	
    public RegistrationController() 
    {
        super();       
        service = new ApplicationService();
        daoUser = new DAOUser();  
        match = new Matcher();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		request.getSession().setAttribute("message", "null");		
		request.getRequestDispatcher(REGISTER).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
	checkUser=true;	
	try
		{
		try {
			List<User> users = daoUser.getAll((String) request.getSession().getAttribute("sessionLocale")); 
			for(User u : users) 
			{
				if(u.getLogin().equals(request.getParameter("login"))) 
					checkUser = false;
			}
			if(checkUser)
			{
				match.validateField(Matcher.phonePattern, request.getParameter("telephon"));
				match.validateField(Matcher.enPattern, request.getParameter("name_en"));
				match.validateField(Matcher.enPattern, request.getParameter("surname_en"));
				match.validateField(Matcher.uaPattern, new String(request.getParameter("name_ua").getBytes("iso-8859-1"), "utf-8"));
				match.validateField(Matcher.uaPattern, new String(request.getParameter("surname_ua").getBytes("iso-8859-1"), "utf-8"));
				match.validateField(Matcher.cashPattern, request.getParameter("cash"));
				service.makeUser(request);				 
				request.getSession().setAttribute("message", "user_registr");
				response.sendRedirect(request.getContextPath() + "/auth");			
				log.info("New user was added");
			}else 
			{
				request.getSession().setAttribute("error", "user_login");		   
				request.getRequestDispatcher(REGISTER).forward(request, response);			
				request.getSession().setAttribute("error", "null");	
				log.info("Warning!Trying to add user with same name");
			}
			}catch (SQLException e) 
			{
				request.getSession().setAttribute("error", "wrong_trans");		   
				request.getRequestDispatcher(REGISTER).forward(request, response);	
				request.getSession().setAttribute("error", "null");	
				log.info("Warning! Problem with sql transactions during adding a new user");
			}
		}catch (NullPointerException | NumberFormatException | IOException e) 
		{			
			request.getSession().setAttribute("error", "invalid_data");		   
			request.getRequestDispatcher(REGISTER).forward(request, response);	
			request.getSession().setAttribute("error", "null");	
			log.info("Warning! Invalid input data during adding a new user ");
		} 
		
	}


	

}
