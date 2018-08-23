package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import DAO.DAOExcursion;
import DAO.DAOShip;


@WebFilter("/*")
public class CharacterEncodingAndInitDAO implements Filter {

   

	 @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException 
	 {		
		    DAOShip daoShip = new DAOShip();	
		    DAOExcursion daoExc = new DAOExcursion();
		   
		    if(request.getParameter("sessionLocale")!=null)
		    {		    	
		    	request.setAttribute("ships", daoShip.getAll(request.getParameter("sessionLocale")));			    	
		    	request.setAttribute("excursions", daoExc.getAll(request.getParameter("sessionLocale")));
		    }else 
		    {
		    	request.setAttribute("excursions",daoExc.getAll("en"));
		    	request.setAttribute("ships",daoShip.getAll("en"));
		    }
	        request.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html; charset=UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        chain.doFilter(request, response);
	    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException 
	{
				
	}

	

}