package by.htp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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
import by.htp.service.impl.validation.ValidationProvider;

public class UserServiceTest {

	private static final long id = 1;
	private static final String login = "Login2321";
	private static final String password = "password12QWE";
	private static final String name = "alex";
	private static final String phone = "+375333784871";
	private static final String email = "alex@tut.by";
	private static final String role = "user";
	private static final String status = "1";

	private User user;
	private List<String> list;
	private ValidationProvider validPr;

	@Mock
	private UserDAO userDAO;

	@InjectMocks
	private UserServiceImpl userService;

	@Before
	public void init() {
		userDAO = mock(UserDAO.class);
		list = new ArrayList();
		validPr = mock(ValidationProvider.class);
	}

	@AfterEach
	public void reset() {
		Mockito.reset(userDAO);
	}

	@Test
	public void signIn() throws DAOException, ServiceException {
		user = new User(id, login, role, status, name, phone, email);
		when(userDAO.signIn(login, password)).thenReturn(user);
/**		when(ValidationProvider.checkSignInUser(login, password)).thenReturn(list);
		userService.signIn(login, password);		
		verify(userService).signIn(login, password);
	*/	

	}
}
