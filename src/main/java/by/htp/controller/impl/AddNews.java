package by.htp.controller.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.controller.command.Command;
import by.htp.controller.page_path.PagePath;
import by.htp.entity.AdminUser;
import by.htp.service.AppService;
import by.htp.service.ServiceFactory;
import by.htp.service.exception.ServiceException;

public class AddNews implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter(ConstantParam.TITLE);
		String text = request.getParameter(ConstantParam.TEXT);
		
		AdminUser admin = (AdminUser) request.getSession().getAttribute(ConstantParam.ADMIN);
	
		try {
			AppService appService = ServiceFactory.getInstance().getAppService();
			RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.MAIN_PAGE);
			appService.addNews(title, text, admin.getId());
			dispatcher.forward(request, response);
			
		} catch (ServiceException e) {
			response.sendRedirect(PagePath.ERROR_PAGE);
			
		}
		
		
		
		
	}

}
