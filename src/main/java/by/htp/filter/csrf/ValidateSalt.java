package by.htp.filter.csrf;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ValidateSalt implements Filter  {
	
	 @Override
	    public void init(FilterConfig filterConfig) throws ServletException {
	    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

       
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        char[] salt = httpRequest.getParameter(Constants.CSRF_CACHE).toCharArray();
        System.out.println("cheking CSRF in validateSalt " + salt.toString());
        
        String csrfPreventionSaltCache = (String) httpRequest.getSession().getAttribute(Constants.CSRF_CACHE);
        
        if (salt != null && csrfPreventionSaltCache.equals(salt.toString())){
            chain.doFilter(request, response);
            
        } else {
            
            throw new ServletException("Potential CSRF detected!! Inform a scary sysadmin ASAP.");
        }
    }

   
    @Override
    public void destroy() {
    }


	


	
}
