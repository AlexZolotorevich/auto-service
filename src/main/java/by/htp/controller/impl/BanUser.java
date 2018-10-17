package by.htp.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.controller.command.Command;
import by.htp.controller.page_path.PagePath;
import by.htp.service.AdminActionService;
import by.htp.service.ServiceFactory;
import by.htp.service.exception.ServiceException;

public class BanUser implements Command{

	private final static String command = "?command=get_all_users";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userID = request.getParameter(ConstantParam.USER_ID);
		AdminActionService adminActionService = ServiceFactory.getInstance().getAdminActionService();
		
		try {
			adminActionService.toBanUser(userID);
			response.sendRedirect(request.getRequestURL() + command);
			
		} catch (ServiceException e) {
			response.sendRedirect(PagePath.ERROR_PAGE);
		}
		
	}

}
