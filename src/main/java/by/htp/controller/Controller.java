package by.htp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.htp.controller.command.Command;


public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String COMMAND_PARAM_NAME = "command";
	private final static Logger logger = Logger.getLogger(Controller.class);

	private final CommandProvider provider = new CommandProvider();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		execute(request, response);
	}

	private void execute(HttpServletRequest request, HttpServletResponse response) {

		String commandName = request.getParameter(COMMAND_PARAM_NAME);
		Command command = provider.getCommand(commandName);

			try {
				command.execute(request, response);

			} catch (ServletException e) {
				logger.warn("ServletException in Controller", e);

			} catch (IOException e) {
				logger.warn("IOE in Controller", e);
			}

	
			
	}

}
