package by.htp.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.controller.command.Command;
import by.htp.controller.page_path.PagePath;
import by.htp.service.AppService;
import by.htp.service.ServiceFactory;
import by.htp.service.exception.ServiceException;

public class AcceptVehicle implements Command{
	
	private final static String command = "?command=to_profile_page_admin";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AppService appService = ServiceFactory.getInstance().getAppService();
		Integer vehicle_ID = Integer.parseInt(request.getParameter(ConstantParam.VEHICLE_ID));
		
		try {
			appService.acceptVehicle(vehicle_ID);
			response.sendRedirect(request.getRequestURL() + command);
			
		} catch (ServiceException e) {
			response.sendRedirect(PagePath.ERROR_PAGE);
		}
		
		
	}

}
