package by.htp.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;


public class CheckParametres implements Filter {

	private final static Logger logger = Logger.getLogger(CheckParametres.class);
	private final static String SCRIPT = "<script>";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		List<String> array = getParam(httpRequest);

		if (validateOfData(array) || array == null) {
			chain.doFilter(request, response);

		} else {
			logger.warn("SQL Injection!!!");
		}

	}

	@Override
	public void destroy() {

	}

	private List<String> getParam(HttpServletRequest httpRequest) {
		
		List<String> list = new ArrayList<String>();
		Enumeration<?> enumeration = httpRequest.getParameterNames();
		
		while (enumeration.hasMoreElements()) {
			String paramName = (String) enumeration.nextElement();
			list.add(httpRequest.getParameter(paramName));
		}

		return list;

	}

	private boolean validateOfData(List<String> array) {
		
		if(array.contains(SCRIPT)) {
			return false;
		}

		return true;
	}

}
