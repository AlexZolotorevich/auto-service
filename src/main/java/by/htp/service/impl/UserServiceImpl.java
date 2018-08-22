package by.htp.service.impl;

import java.util.List;
import java.util.Map;

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

	
	private final static Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
	private List<String> list = null;
	
	
	@Override
	public User signIn(String login, String password) throws ServiceException{
		User user = null;
		
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			user = userDAO.signIn(login, password);
			
		} catch (DAOException e) {
			LOGGER.warn("ServletException in Controller", e);
			throw new ServiceException("From DAO", e);
		}
	return user;
	}

	
	@Override
	public User signUp(String login, String password, String name, String phone, String email) throws ServiceException {
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
			LOGGER.warn("ServiceException in Service");
			throw new ServiceException("From DAO", e);
		} 
		return userNew;
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
			LOGGER.warn("ServiceException in Service");
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
			LOGGER.warn("ServiceException in Service");
			throw new ServiceException("From DAO", e);
		}
		return admin;
	}
	
	
	
	public void outPutErrors(Map<String, String> errors) {
		for(Map.Entry<String, String> pair : errors.entrySet()) {
			System.out.println(pair);
		}
	}
	
	
	
	
	
	
	@Override
	public List<String> getList (){
		return list;
	}


	
	
}
