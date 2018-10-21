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

import by.htp.filter.util.FilterUtil;

public class CheckParametres implements Filter {

	private final static Logger logger = Logger.getLogger(CheckParametres.class);
	private static FilterUtil filterUtil = FilterUtil.getInstance();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Enumeration<?> enumeration = httpRequest.getParameterNames();
		List<String> list = new ArrayList<String>();
		
		while (enumeration.hasMoreElements()) {
			String paramName = (String) enumeration.nextElement();
			list.add(httpRequest.getParameter(paramName));
		}
		System.out.println(validateOfData(list));
		if (validateOfData(list)) {
			chain.doFilter(request, response);

		} else {
			logger.warn("SQL Injection!!!");
		}

	}

	@Override
	public void destroy() {
	}

	
	private boolean validateOfData(List<String> list) {
		for(String position: list) {
			if(!filterUtil.isValidDates(position)) {
				return false;
			}
		}
		return true;
	}
}
