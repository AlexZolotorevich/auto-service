package by.htp.controller.filter.csrf;


import java.io.IOException;
import java.util.UUID;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

public class LoadSalt implements Filter {

	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		
	}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        char[] csrfPreventionSaltCache = (char[]) httpRequest.getSession().getAttribute(Constants.CSRF_CACHE);

        if (csrfPreventionSaltCache == null){
        	csrfPreventionSaltCache  = generateString();
        	httpRequest.getSession().setAttribute(Constants.CSRF_CACHE, csrfPreventionSaltCache);
        }

        httpRequest.setAttribute(Constants.CSRF_CACHE, csrfPreventionSaltCache);
        chain.doFilter(request, response);
    }

	
	@Override
	public void destroy() {
	}
	
	private static char[] generateString() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.toCharArray();
    }

}
