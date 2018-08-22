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

public class SignUp implements Command {
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter(ConstantParam.LOGIN);
		String password = request.getParameter(ConstantParam.PASSWORD);
		String phone = request.getParameter(ConstantParam.PHONE);
		String email = request.getParameter(ConstantParam.EMAIL);
		String name = request.getParameter(ConstantParam.NAME);
		
		User user = null;
		List<String> errorList = null;

		RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.SIGN_UP_PAGE);
		
		try {
			UserService userService = ServiceFactory.getInstance().getUserService();
			user = userService.signUp(login, password, name, phone, email);
			errorList = userService.getList();

			/** check arraylist with validation errors*/
			if (errorList.isEmpty()) {
				dispatcher = request.getRequestDispatcher(PagePath.MAIN_PAGE);
				request.getSession(true).setAttribute(ConstantParam.USER, user);
				request.setAttribute(ConstantParam.USER, user.getLogin());

			} else {
				dispatcher = request.getRequestDispatcher(PagePath.SIGN_UP_PAGE);
				request.setAttribute(ConstantParam.ERROR_MESSAGE, errorList);
			}
			
			dispatcher.forward(request, response);
			
			}catch (ServiceException e) {
			response.sendRedirect(PagePath.ERROR_PAGE);
		}

	}
}
