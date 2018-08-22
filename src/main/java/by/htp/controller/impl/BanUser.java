package by.htp.controller.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.controller.command.Command;
import by.htp.controller.page_path.PagePath;
import by.htp.service.AppService;
import by.htp.service.ServiceFactory;
import by.htp.service.exception.ServiceException;

public class BanUser implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userID = request.getParameter(ConstantParam.USER_ID);
		AppService appService = ServiceFactory.getInstance().getAppService();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.GET_ALL_USERS);
		dispatcher.forward(request, response);
		
		try {
			appService.toBanUser(userID);
			
		} catch (ServiceException e) {
			response.sendRedirect(PagePath.ERROR_PAGE);
		}
		
	}

}
