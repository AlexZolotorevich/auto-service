package by.htp.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.controller.command.Command;
import by.htp.controller.page_path.PagePath;
import by.htp.entity.AdminUser;
import by.htp.service.AdminActionService;
import by.htp.service.ServiceFactory;
import by.htp.service.exception.ServiceException;

public class AddNews implements Command {
	
	private final static String command = "?command=main_page";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String title = request.getParameter(ConstantParam.TITLE);
		String text = request.getParameter(ConstantParam.TEXT);

		AdminUser admin = (AdminUser) request.getSession().getAttribute(ConstantParam.ADMIN);

		try {
			if (admin != null) {
				AdminActionService adminActionService = ServiceFactory.getInstance().getAdminActionService();
				adminActionService.addNews(title, text, admin.getId());
				
			}
			response.sendRedirect(request.getRequestURL() + command);

		} catch (ServiceException e) {
			response.sendRedirect(PagePath.ERROR_PAGE);

		}

	}

}
