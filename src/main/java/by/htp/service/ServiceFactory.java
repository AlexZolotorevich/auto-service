package by.htp.service;

import by.htp.service.impl.AppServiceImpl;
import by.htp.service.impl.UserServiceImpl;

public class ServiceFactory {
	
	private final static ServiceFactory instance = new ServiceFactory();
	
	private final UserService userService = new UserServiceImpl();
	private final AppService appService = new AppServiceImpl();
	
	public static ServiceFactory getInstance() {
		return instance;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public AppService getAppService() {
		return appService;
	}
}
