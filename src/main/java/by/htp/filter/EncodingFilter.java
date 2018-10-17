package by.htp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class EncodingFilter implements Filter{
	
	private static final String UTF_8 = "utf-8";

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println("1111");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponce =  (HttpServletResponse) response;
		httpServletRequest.setCharacterEncoding(UTF_8);
		httpServletResponce.setCharacterEncoding(UTF_8);
		
		filterChain.doFilter(httpServletRequest, httpServletResponce);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
