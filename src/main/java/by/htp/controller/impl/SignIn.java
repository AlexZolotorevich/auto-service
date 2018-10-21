package by.htp.controller.impl;

import java.io.IOException;
import java.util.List;

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

	private UserService userService = ServiceFactory.getInstance().getUserService();
	private String nextPage = PagePath.MAIN_PAGE;
	private List<String> errorList = null;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter(ConstantParam.LOGIN);
		String password = request.getParameter(ConstantParam.PASSWORD);

		if (login.contains(ConstantParam.ADMIN_PREFIX)) {
			loginAdmin(login, password, request);

		} else {
			loginUser(login, password, request);
		}
		
	
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);

	}

	private void loginAdmin(String login, String password, HttpServletRequest request) {

		try {
			AdminUser admin = userService.signInAdmin(login, password);

			if (admin != null) {
				request.getSession(true).setAttribute(ConstantParam.ADMIN, admin);

			} else {
				request.setAttribute(ConstantParam.ERROR_MESSAGE, ConstantParam.INCORRECT_DATES);
				nextPage = PagePath.SIGN_IN_PAGE;
			}

		} catch (ServiceException e) {
			nextPage = PagePath.ERROR_PAGE;
		}
	}

	private void loginUser(String login, String password, HttpServletRequest request) {

		try {
			User user = userService.signIn(login, password);
			errorList = userService.getList();

			if (!errorList.isEmpty()) {
				request.setAttribute(ConstantParam.ERROR_MESSAGE, errorList);
				nextPage = PagePath.SIGN_IN_PAGE;

			} else {
				request.getSession(true).setAttribute(ConstantParam.USER, user);
				request.setAttribute(ConstantParam.LOGIN, user.getLogin());
			}

		} catch (ServiceException e) {
			nextPage = PagePath.ERROR_PAGE;
		}

	}
}