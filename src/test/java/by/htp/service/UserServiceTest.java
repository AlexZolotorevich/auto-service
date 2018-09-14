package by.htp.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import by.htp.dao.UserDAO;
import by.htp.dao.exception.DAOException;
import by.htp.dao.impl.UserDAOImpl;
import by.htp.entity.User;
import by.htp.service.exception.ServiceException;
import by.htp.service.impl.UserServiceImpl;




public class UserServiceTest {
	
	private User user;
	private UserDAO userDAO;
	private UserService userService;
	private ServiceException serviceException;
	
	private static String login = "login";
	private static String password = "password";
	
	@BeforeAll
	public void init() {
		user = new User(login, password);
		userDAO = mock(UserDAO.class);
		userService = ServiceFactory.getInstance().getUserService();
		serviceException = mock(ServiceException.class);
	}
	
	@AfterEach
	  public void reset() {
	    Mockito.reset(userDAO);
	}
	
	
	
	@Test
	public void signIn() {
		User userResult = new User(login, password);
		try {
			when(userService.signIn(login, password)).thenReturn(userResult);
			assertEquals(userService.signIn(login, password), userResult);
			doReturn(userResult).when(userService).signIn(login, password);
			verify(userService).signIn(login, password);
			
		} catch (ServiceException e) {
			
		}
		
	}
}


