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
import by.htp.service.AppService;
import by.htp.service.ServiceFactory;
import by.htp.service.exception.ServiceException;

public class SalesPage implements Command{
	
	AppService appService = ServiceFactory.getInstance().getAppService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String currentPage = request.getParameter(ConstantParam.CURRENT_PAGE);

		try {
			List<Vehicle>  cars = appService.getPortianCars(currentPage);
			request.getSession().setAttribute(ConstantParam.CARS, cars);
			
			PageInformation pageInfo = appService.getPageInfo();
			
			request.setAttribute(ConstantParam.COUNT_PAGES, pageInfo.getCountOfPage());
			request.setAttribute(ConstantParam.CURRENT_PAGE, currentPage);
			RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.SALES_PAGE);
			dispatcher.forward(request, response);
			
		} catch (ServiceException e) {
			response.sendRedirect(PagePath.ERROR_PAGE);
		} 
		
	}
	
}
