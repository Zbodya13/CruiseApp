package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import DAO.DAOUser;
import model.User;


@WebServlet("/register")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String REGISTER = "/view/registrationForm.jsp";	
	private ApplicationService service;
  
    public RegistrationController() {
        super();       
        service = new ApplicationService();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.getRequestDispatcher(REGISTER).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
		service.makeUser(request);
		}catch (NullPointerException | NumberFormatException e) 
		{			
			request.getSession().setAttribute("error", "Invalid data");		   
			request.getRequestDispatcher(REGISTER).forward(request, response);			
		} 
		request.getSession().removeAttribute("error");  
		response.sendRedirect(request.getContextPath() + "/auth");
	}


	

}
