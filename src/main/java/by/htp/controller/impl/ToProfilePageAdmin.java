package by.htp.controller.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.controller.command.Command;
import by.htp.controller.page_path.PagePath;
import by.htp.entity.PageInformation;
import by.htp.entity.Vehicle;
import by.htp.service.AdminActionService;
import by.htp.service.ServiceFactory;
import by.htp.service.exception.ServiceException;


public class ToProfilePageAdmin implements Command{

	private static final String command = "command";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String param = request.getParameter(command);
		String next = ConstantParam.COMMAND + param;
		request.getSession().setAttribute(ConstantParam.PREVIOUS_QUERY, next);
		
		String currentPage = request.getParameter(ConstantParam.CURRENT_PAGE);
		
		try {
			AdminActionService adminActionService = ServiceFactory.getInstance().getAdminActionService();
			List<Vehicle> newCarsOfUsers = adminActionService.getNewCarsOfUsers(currentPage);
			request.getSession().setAttribute(ConstantParam.NEW_CARS_OF_USERS, newCarsOfUsers);
			
			PageInformation pageInfo = adminActionService.getPageInfo();
			
			request.setAttribute(ConstantParam.COUNT_PAGES, pageInfo.getCountOfPage());
			request.setAttribute(ConstantParam.CURRENT_PAGE, currentPage);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.PROFILE_ADMIN);
			dispatcher.forward(request, response);
			
		} catch (ServiceException e) {
			response.sendRedirect(PagePath.ERROR_PAGE);
		}
				
		
	}

}
