package by.htp.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.controller.command.Command;
import by.htp.service.AdminActionService;
import by.htp.service.ServiceFactory;
import by.htp.service.exception.ServiceException;

public class DeleteVehicleAdmin implements Command{

	private final static String command = "?command=to_profile_page_admin";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer vehicleID = Integer.parseInt(request.getParameter(ConstantParam.VEHICLE_ID));
		
		AdminActionService adminActionService = ServiceFactory.getInstance().getAdminActionService();
		
		try {
			adminActionService.deleteVehicleByAdmin(vehicleID);
			response.sendRedirect(request.getRequestURL() + command);
		} catch (ServiceException e) {
			response.sendRedirect(ConstantParam.ERROR_MESSAGE);
		}
		
	}

}
