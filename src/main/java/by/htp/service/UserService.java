package by.htp.service;


import java.util.List;

import by.htp.entity.AdminUser;
import by.htp.entity.User;
import by.htp.service.exception.ServiceException;

public interface UserService {
	
	User signIn(String login, String password) throws ServiceException;
	
	User signUp(String login, String password, String name, String phone, String email) throws ServiceException;
	
	AdminUser signInAdmin(String login, String password) throws ServiceException;
	
	User editUser(long ID, String login, String password, String name, String phone, String email) throws ServiceException;
	
	List<String> getList() throws ServiceException;
	

}
