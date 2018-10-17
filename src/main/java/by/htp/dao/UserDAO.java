package by.htp.dao;

import by.htp.dao.exception.DAOException;
import by.htp.entity.AdminUser;
import by.htp.entity.User;

public interface UserDAO {
	
	User signIn(String login, String password) throws DAOException;
	
	AdminUser signInAdmin(String login, String password) throws DAOException;
	
	User signUp(String login, String password, String name, String phone, String email) throws DAOException;
	
	User editUser(long ID, String login, String password, String name, String phone, String email) throws DAOException;
	
	boolean checkLogin(String login) throws DAOException;
}
