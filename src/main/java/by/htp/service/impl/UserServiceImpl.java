package by.htp.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import by.htp.dao.DAOFactory;
import by.htp.dao.UserDAO;
import by.htp.dao.exception.DAOException;
import by.htp.entity.AdminUser;
import by.htp.entity.User;
import by.htp.service.UserService;
import by.htp.service.exception.ServiceException;
import by.htp.service.impl.validation.ValidationProvider;


public class UserServiceImpl implements UserService {

	
	private final static Logger logger = Logger.getLogger(UserServiceImpl.class);
	private List<String> list = null;
	private UserService userService;
	
	
	/** constructor for factory*/
	public UserServiceImpl() {
	}
	
	/** constructor for test*/
	public UserServiceImpl (UserService userService) {
		this.userService = userService;
	}
	
	
	@Override
	public User signIn(String login, String password) throws ServiceException{
		User user = null;
		list = ValidationProvider.checkSignInUser(login, password);
		
		try {
			if(list.isEmpty()) {
				DAOFactory daoFactory = DAOFactory.getInstance();
				UserDAO userDAO = daoFactory.getUserDAO();
				user = userDAO.signIn(login, password);
				
				if(user == null) {
					list.add("incorrectDates");
				}
				
				if(user != null && Integer.parseInt(user.getStatus()) == 0) {
					list.add("errorBan");
				}
			}
		} catch (DAOException e) {
			logger.warn("ServletException in Controller", e);
			throw new ServiceException("From DAO", e);
		}
	return user;
	}

	
	@Override
	public User signUp(String login, String password, String name, String phoneInner, String email) throws ServiceException {
		
		String phone = trimData(phoneInner);
		User user = new User(login, password, name, phone, email);
		User userNew = null;
		
		try {
			list = ValidationProvider.checkNewUser(user);
			
			if(list.isEmpty()) {
				DAOFactory daoFactory = DAOFactory.getInstance();
				UserDAO userDAO = daoFactory.getUserDAO();
				
					if(userDAO.checkLogin(login)) {
						userNew = userDAO.signUp(login, password, name, phone, email);
						
					}else {
						list.add("User Exist");
					}
			}
		} catch (DAOException e) {
			logger.warn("ServiceException in Service");
			throw new ServiceException("From DAO", e);
		} 
		return userNew;
	}
	
	
	private String trimData(String date) {
		String result = date.replaceAll("\\s","");
		return result;
	}
	
	
	
	@Override
	public User editUser(long ID, String login, String password, String name, String phone, String email) throws ServiceException {
		
		User user = new User(login, password, name, phone, email);
		User userNew = null;
		
		try {
			
			list = ValidationProvider.checkNewUser(user);
			if(list.isEmpty()) {
				DAOFactory daoFactory = DAOFactory.getInstance();
				UserDAO userDAO = daoFactory.getUserDAO();
				
					if(userDAO.checkLogin(login)) {
						userNew = userDAO.editUser(ID, login, password, name, phone, email);
					}else {
						list.add("User Exist");
					}
			}
			
		}catch(DAOException e) {
			logger.warn("ServiceException in Service");
			throw new ServiceException("From DAO", e);
		}
		
		return userNew;
	}
		
	
	@Override
	public AdminUser signInAdmin(String login, String password) throws ServiceException {
		AdminUser admin = null;
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		
		try {
			admin = userDAO.signInAdmin(login, password);
		} catch (DAOException e) {
			logger.warn("ServiceException in Service");
			throw new ServiceException("From DAO", e);
		}
		return admin;
	}
	
	@Override
	public List<String> getList (){
		return list;
	}


	
	
}
