package filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

@WebFilter(urlPatterns= {"/customUser/*"},
dispatcherTypes = {
DispatcherType.REQUEST, 
DispatcherType.FORWARD, 
DispatcherType.INCLUDE, 
DispatcherType.ERROR},
servletNames="AdminShipService")
public class CustomUserFilter implements Filter {


	public CustomUserFilter() 
	{   
	}


	public void destroy() 
	{	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		User user = (User) req.getSession().getAttribute("user");
		if(user!=null && user.getRole().equals("admin")) 
		{
			chain.doFilter(request, response);
		}
		else if(user!=null && user.getRole().equals("custom"))
		{
			req.getSession().setAttribute("errorMessage", "Access denied! You are not admin!");				
			res.sendRedirect("http://localhost:8080/CruiseApp/");
		}
		else 
		{
			res.sendRedirect("http://localhost:8080/CruiseApp/");
		}
	}


	public void init(FilterConfig fConfig) throws ServletException 
	{	}

}