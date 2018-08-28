package by.htp.controller.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.controller.command.Command;
import by.htp.controller.page_path.PagePath;
import by.htp.entity.News;
import by.htp.service.AppService;
import by.htp.service.ServiceFactory;
import by.htp.service.exception.ServiceException;

public class MainPage implements Command{
	
	private final static String command = "?command=main_page";
	
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AppService appService = ServiceFactory.getInstance().getAppService();
		
		try {
			List<News> news = appService.getAllNews();
			request.getSession().setAttribute(ConstantParam.NEWS, news);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.MAIN_PAGE);
			dispatcher.forward(request, response);
					
		} catch (ServiceException e) {
			request.setAttribute(ConstantParam.ERROR_MESSAGE, ConstantParam.ERROR_MESSAGE);
			response.sendRedirect(request.getRequestURL() + command);
		}	
	}

}
