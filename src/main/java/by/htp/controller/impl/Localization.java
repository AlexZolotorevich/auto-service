package by.htp.controller.impl;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.controller.command.Command;
import by.htp.controller.page_path.PagePath;

public class Localization implements Command {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		StringBuffer URL = request.getRequestURL();
		String local = request.getParameter(PagePath.LOCAL_PARAM);
		
		request.getSession().setAttribute(PagePath.LOCAL_PARAM, local);
		String previousQuery = (String) request.getSession().getAttribute(ConstantParam.PREVIOUS_QUERY);
		String nextPage = URL + "?" + previousQuery;
		
		response.sendRedirect(nextPage);
		
		

	}

}
