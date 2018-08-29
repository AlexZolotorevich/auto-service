package by.htp.controller.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.controller.command.Command;
import by.htp.controller.page_path.PagePath;
import by.htp.entity.User;
import by.htp.service.AdminActionService;
import by.htp.service.ServiceFactory;
import by.htp.service.exception.ServiceException;

public class GetAllUsers implements Command{

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AdminActionService adminActionService = ServiceFactory.getInstance().getAdminActionService();
		
		try {
			List<User> allUsers = adminActionService.getAllUsers();
			request.getSession().setAttribute(ConstantParam.ALL_USERS, allUsers);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.GET_ALL_USERS);
			dispatcher.forward(request, response);
			
		} catch (ServiceException e) {
			response.sendRedirect(ConstantParam.ERROR_MESSAGE);
		}
		
	}

}
