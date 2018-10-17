package by.htp.controller.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.controller.command.Command;
import by.htp.controller.page_path.PagePath;
import by.htp.entity.Vehicle;
import by.htp.service.AppService;
import by.htp.service.ServiceFactory;
import by.htp.service.exception.ServiceException;

public class DetailVehicle implements Command{
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String vehicleID = request.getParameter(ConstantParam.VEHICLE_ID);
		AppService appService = ServiceFactory.getInstance().getAppService();
		Vehicle vehicle = null;
		System.out.println(vehicleID);
		try {
			vehicle = appService.getCarByID(vehicleID);
			request.getSession().setAttribute(ConstantParam.VEHICLE, vehicle);
		} catch (ServiceException e) {
			response.sendRedirect(PagePath.ERROR_PAGE);
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.VEHICLE_DETAIL_PAGE);
		dispatcher.forward(request, response);
		
	}

}
