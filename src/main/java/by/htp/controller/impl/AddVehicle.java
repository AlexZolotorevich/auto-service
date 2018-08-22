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
		String user_ID = request.getParameter(ConstantParam.USER_ID);
		String description = request.getParameter(ConstantParam.DESCRIPTION);
//		List<Part> files = getFilesPart(request);

		try {
			AppService appService = ServiceFactory.getInstance().getAppService();
			appService.addVehicle(model, year, typeCarcase, price, transmission, typeFuel, engineCapacity, driveUnit,
					mileage, user_ID, description);
			response.sendRedirect(request.getRequestURL() + command);

		} catch (ServiceException e) {
			response.sendRedirect(PagePath.ERROR_PAGE);
		}

	}

//	private List<Part> getFilesPart(HttpServletRequest request) throws IOException, ServletException {
//		List<Part> files = new ArrayList<Part>();
////		for (Part part : request.getParts()) {
////			if ("image/jpg".equals(part.getContentType())) {
////				files.add(part);
////			}
////		}
//		return files;
//	}

}
