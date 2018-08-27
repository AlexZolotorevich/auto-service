package by.htp.controller.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import by.htp.controller.command.Command;
import by.htp.controller.page_path.PagePath;
import by.htp.service.AppService;
import by.htp.service.ServiceFactory;
import by.htp.service.exception.ServiceException;

public class AddVehicle implements Command {

	private final static String command = "?command=sales_page";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String model = request.getParameter(ConstantParam.MODEL);
		String year = request.getParameter(ConstantParam.YEAR);
		String price = request.getParameter(ConstantParam.PRICE);
		String typeCarcase = request.getParameter(ConstantParam.CARCASE);
		String transmission = request.getParameter(ConstantParam.TRANSMISSION);
		String typeFuel = request.getParameter(ConstantParam.FUEL);
		String engineCapacity = request.getParameter(ConstantParam.ENGINE);
		String driveUnit = request.getParameter(ConstantParam.DRIVE_UNIT);
		String mileage = request.getParameter(ConstantParam.MILEAGE);
		String userID = request.getParameter(ConstantParam.USER_ID);
		String description = request.getParameter(ConstantParam.DESCRIPTION);

		try {
			AppService appService = ServiceFactory.getInstance().getAppService();
			
			if(!appService.addVehicle(model, year, typeCarcase, price, transmission, typeFuel, engineCapacity, driveUnit, mileage, userID, description)){
				List<String> listErrors = appService.getListErrors();
				request.setAttribute(ConstantParam.ERROR_MESSAGE, listErrors);
			}
			response.sendRedirect(request.getRequestURL() + command);

		} catch (ServiceException e) {
			response.sendRedirect(PagePath.ERROR_PAGE);
		}

	}

}
