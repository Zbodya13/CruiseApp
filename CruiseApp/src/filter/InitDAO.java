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

/**
 *  Filter class for setting character encoding and redirect all ships and excursions to pages.
 */

@WebFilter("/*")
public class InitDAO implements Filter {

   

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
	        chain.doFilter(request, response);
	    }

	@Override
	public void destroy() {
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException 
	{
				
	}

	

}
