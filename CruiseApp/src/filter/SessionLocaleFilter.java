package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import DAO.DAOUser;
import model.User;


@WebFilter("/*")
public class SessionLocaleFilter implements Filter {

 
	public void destroy() {	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{			
		HttpServletRequest req = (HttpServletRequest) request;		 
	        if (req.getParameter("sessionLocale") != null) {
	            req.getSession().setAttribute("sessionLocale", req.getParameter("sessionLocale"));	            
	            System.out.println("Locale:" + req.getParameter("sessionLocale"));
	        }
		chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {	}

}
