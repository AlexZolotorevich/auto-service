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
import by.htp.entity.Vehicle;
import by.htp.service.AppService;
import by.htp.service.ServiceFactory;
import by.htp.service.exception.ServiceException;

public class ToProfilePage implements Command{

	private static final String command = "command";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String param = request.getParameter(command);
		String next = ConstantParam.COMMAND + param;
		request.getSession().setAttribute(ConstantParam.PREVIOUS_QUERY, next);
		
		User user = (User) request.getSession().getAttribute(ConstantParam.USER);
		Integer user_ID = (int) user.getId();
		
		try {
			AppService appService = ServiceFactory.getInstance().getAppService();
			List<Vehicle> cars = appService.getCarsByUser(user_ID);
			
			request.getSession().setAttribute(ConstantParam.CARS, cars);
			RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.PROFILE);
			dispatcher.forward(request, response);
			
		}catch (ServiceException e) {
			response.sendRedirect(PagePath.ERROR_PAGE);
		} 
		
		
		
	}

}
