package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAOExcursion;
import DAO.DAOShipExc;
import model.Excursion;
import model.ShipExcursion;

@WebServlet("/admin/adminExcursion")
public class AdminExcursionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String CREATE_EXC = "createExcursion.jsp";
	private static String LIST_EXC = "excursionsAdmin.jsp";
	private static String EDIT_EXC = "editExcursion.jsp";
	private static String SHIP_EXC = "shipExcCreate.jsp";
    private DAOExcursion daoExc;
    private DAOShipExc daoSE;
    private ApplicationService service;
    
    public AdminExcursionController() {
        super();
        daoExc = new DAOExcursion();
        daoSE = new DAOShipExc();
        service = new ApplicationService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		String action =  request.getParameter("action");		
		if(action.equalsIgnoreCase("create")) 
		{									
			request.getRequestDispatcher(CREATE_EXC).forward(request, response);
		}
		else if(action.equals("edit")) 
		{			
			String excursionID = request.getParameter("excursionID");
			Excursion excursion = daoExc.getByID(excursionID,(String)request.getSession().getAttribute("sessionLocale"));			
			request.setAttribute("exc", excursion);			
			request.getRequestDispatcher(EDIT_EXC).forward(request, response);
		}
		else if(action.equals("delete")) 
		{			
			String excursionID = request.getParameter("excursionID");
			daoExc.delete(excursionID);
			request.getRequestDispatcher(LIST_EXC).forward(request, response);
			
		}
		else if(action.equals("list"))
		{							
			request.getRequestDispatcher(LIST_EXC).forward(request, response);
		}else if(action.equals("createShipExc")) 
		{						
			request.getRequestDispatcher(SHIP_EXC).forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		String action = request.getParameter("action");
		String locale = (String)request.getSession().getAttribute("sessionLocale");
		if(action.equals("create")) 
		{		
			try 
			{			
			service.makeExcursion(request, locale);
			}catch (NullPointerException |NumberFormatException  e) 
			{
				request.getSession().setAttribute("error", "Invalid data");
				request.getRequestDispatcher(CREATE_EXC).forward(request, response);		
			}
			
		}	
		else if(action.equals("edit")) 
		{	
			try 
			{			
			service.updateExcursion(request);
			}catch (NullPointerException | NumberFormatException  e) 
			{
				String excID = request.getParameter("excursionID");				
				Excursion excEx = daoExc.getByID(excID,(String) request.getSession().getAttribute("sessionLocale"));	
				request.getSession().setAttribute("error", "Invalid data");
			    request.setAttribute("exc",excEx);
				request.getRequestDispatcher(EDIT_EXC).forward(request, response);			
			} 
		}
		else if(action.equals("createShipExc")) 
		{			
			ShipExcursion se = new ShipExcursion();
			se.setExcursionID(request.getParameter("excursionID"));			
			se.setShipID(request.getParameter("shipID"));			
			daoSE.add(se, locale);
		}	
		request.getSession().removeAttribute("error"); 
		response.sendRedirect(request.getContextPath() + "/admin/adminExcursion?action=list");		
	}

	

	

}
