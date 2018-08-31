package by.htp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import by.htp.controller.impl.ConstantParam;


public class LocFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String previousQuery = httpRequest.getQueryString();
		
		if(previousQuery != null ) {
			httpRequest.getSession().setAttribute(ConstantParam.PREVIOUS_QUERY, previousQuery);
			
		}
		chain.doFilter(httpRequest, response);
			
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void destroy() {
		
	}

	

}
