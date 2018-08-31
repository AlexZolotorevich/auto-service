package by.htp.controller.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.controller.command.Command;
import by.htp.controller.page_path.PagePath;
import by.htp.entity.Vehicle;
import by.htp.service.AppService;
import by.htp.service.ServiceFactory;
import by.htp.service.exception.ServiceException;

public class Filtrate implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String[] model = request.getParameterValues(ConstantParam.MODEL);
		String[] carcase = request.getParameterValues(ConstantParam.CARCASE);
		String year = request.getParameter(ConstantParam.YEAR);
		String[] fuel = request.getParameterValues(ConstantParam.YEAR);
		
		AppService appService = ServiceFactory.getInstance().getAppService();
		
		try {
			
			List<Vehicle> listVehicle = appService.filtrateVehicle(model, carcase, year, fuel);
			request.setAttribute(ConstantParam.LIST_VEHICLE, listVehicle);
			
		} catch (ServiceException e) {
			response.sendRedirect(PagePath.ERROR_PAGE);
		}
		

	}

}
