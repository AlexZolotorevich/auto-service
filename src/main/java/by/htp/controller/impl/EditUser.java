package by.htp.controller.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.controller.command.Command;
import by.htp.controller.page_path.PagePath;
import by.htp.entity.User;
import by.htp.service.ServiceFactory;
import by.htp.service.UserService;
import by.htp.service.exception.ServiceException;

public class EditUser implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter(ConstantParam.LOGIN);
		String password = request.getParameter(ConstantParam.PASSWORD);
		String name = request.getParameter(ConstantParam.NAME);
		String phone = request.getParameter(ConstantParam.PHONE);
		String email = request.getParameter(ConstantParam.EMAIL);
		
		User user = (User) request.getSession().getAttribute(ConstantParam.USER);
		long ID = user.getId();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.MAIN_PAGE);
		
		List<String> errorMap;
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		
		try {
			user = userService.editUser(ID, login, password, name, phone, email);
			errorMap = userService.getList();
			
			if(errorMap.isEmpty()) {
				dispatcher = request.getRequestDispatcher(PagePath.MAIN_PAGE);
				request.getSession().setAttribute(ConstantParam.USER, user);
				request.setAttribute(ConstantParam.USER, user.getLogin());
				
			} else {
				dispatcher = request.getRequestDispatcher(PagePath.PROFILE);
				request.setAttribute(ConstantParam.ERROR_MESSAGE, errorMap);
			}
			
			dispatcher.forward(request, response);
			
		} catch (ServiceException e) {
			response.sendRedirect(PagePath.ERROR_PAGE);
		}
	}

}
