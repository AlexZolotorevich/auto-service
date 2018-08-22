package by.htp.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.controller.command.Command;
import by.htp.service.AppService;
import by.htp.service.ServiceFactory;
import by.htp.service.exception.ServiceException;

public class DeleteVehicleAdmin implements Command{

	private final static String command = "?command=to_profile_page";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer vehicle_ID = Integer.parseInt(request.getParameter(ConstantParam.VEHICLE_ID));
		
		AppService appService = ServiceFactory.getInstance().getAppService();
		
		try {
			appService.deleteVehicleByAdmin(vehicle_ID);
			response.sendRedirect(request.getRequestURL() + command);
		} catch (ServiceException e) {
			
		}
		
	}

}
