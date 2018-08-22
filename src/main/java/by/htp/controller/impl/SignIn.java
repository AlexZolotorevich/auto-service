package by.htp.controller.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.controller.command.Command;
import by.htp.controller.page_path.PagePath;
import by.htp.entity.AdminUser;
import by.htp.entity.User;
import by.htp.service.ServiceFactory;
import by.htp.service.UserService;
import by.htp.service.exception.ServiceException;

public class SignIn implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter(ConstantParam.LOGIN);
		String password = request.getParameter(ConstantParam.PASSWORD);
		String nextPage = PagePath.MAIN_PAGE;

		try {
			UserService userService = ServiceFactory.getInstance().getUserService();

			
			/** check admin or user with prefix [Admin]
			 * if it's true - make login*/
			if (login.contains(ConstantParam.ADMIN_PREFIX)) {
				AdminUser admin = userService.signInAdmin(login, password);
				
				if(admin != null) {
				request.getSession(true).setAttribute(ConstantParam.ADMIN, admin);
				
				}else{
				request.setAttribute(ConstantParam.ERROR_MESSAGE, ConstantParam.INCORRECT_DATES);
				nextPage = PagePath.SIGN_IN_PAGE;
				}

				/** else we work with simple user and check him*/
			} else {
				User user = userService.signIn(login, password);

				if (user == null) {
					request.setAttribute(ConstantParam.ERROR_MESSAGE, ConstantParam.INCORRECT_DATES);
					nextPage = PagePath.SIGN_IN_PAGE;

				} else {
					if (user.getStatus().equals(ConstantParam.NUMBER_ONE)) {
						request.getSession(true).setAttribute(ConstantParam.USER, user);
						request.setAttribute(ConstantParam.LOGIN, user.getLogin());
						
						/** if we get user and user.status == 0
						 * it means that user was banned from admin*/
					} else {
						request.setAttribute(ConstantParam.BAN, ConstantParam.BAN);
						nextPage = PagePath.SIGN_IN_PAGE;
					}
				}
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
			response.sendRedirect(PagePath.ERROR_PAGE);
		}

	}
	
}
