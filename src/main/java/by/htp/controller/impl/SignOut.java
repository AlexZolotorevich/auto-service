package by.htp.controller.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import by.htp.controller.command.Command;
import by.htp.controller.page_path.PagePath;

public class SignOut implements Command{

	
		
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().invalidate();
		RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.MAIN_PAGE);
		dispatcher.forward(request, response);
	}

}
