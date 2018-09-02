package by.htp.dao.impl; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.htp.dao.UserDAO;
import by.htp.dao.connection_pool.ConnectionPool;
import by.htp.dao.exception.DAOException;
import by.htp.entity.AdminUser;
import by.htp.entity.User;


public class UserDAOImpl implements UserDAO{

	private final static Logger logger = Logger.getLogger(UserDAOImpl.class);
	
	@Override
	public User signIn(String login, String password) throws DAOException{
		
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		User user = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		
		try (Connection connection = connectionPool.takeConnection()){
			preparedStatement = connection.prepareStatement(SQLquery.SIGN_IN);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				password = HidingPassword.lockPassword(password);
				int ID = resultSet.getInt(SQLquery.ID);
				String status = resultSet.getString(SQLquery.STATUS);
				String role = resultSet.getString(SQLquery.ROLE);
				String name = resultSet.getString(SQLquery.NAME);
				String phone = resultSet.getString(SQLquery.PHONE);
				String email = resultSet.getString(SQLquery.EMAIL);
				user = new User(ID, login, role, status, name, phone, email);
			}
		
		}catch(SQLException e) {
			logger.fatal("SQLException in DAO impl in the signIn method", e);
			throw new DAOException("SQLException",e);
			
		} catch (InterruptedException e) {
			logger.fatal("InterruptedException in DAO impl in the signIn method", e);
			throw new DAOException("SQLException",e);
		}
		return user;
	}

	@Override
	public User signUp(String login, String password, String name, String phone, String email) throws DAOException {
		
		PreparedStatement preparedStatement;
		ResultSet resultSet = null;
		User user = null;
		String status = SQLquery.STATUS_NUMBER_1;
		String role = SQLquery.USER;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		
		try (Connection connection = connectionPool.takeConnection()){
			preparedStatement = connection.prepareStatement(SQLquery.SIGN_UP);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, role);
			preparedStatement.setString(4, status);
			preparedStatement.setString(5, name);
			preparedStatement.setString(6, phone);
			preparedStatement.setString(7, email);
			preparedStatement.execute();
			
			preparedStatement = connection.prepareStatement(SQLquery.SELECT_USER_ID);
			preparedStatement.setString(1, login);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int ID = resultSet.getInt(SQLquery.ID);
				user = new User(ID, login, role, status, name, phone, email);
			}
			
		} catch (SQLException e) {	
			logger.fatal("SQLException in DAO impl", e);
			throw new DAOException("SQLException",e);
			
		} catch (InterruptedException e) {
			logger.fatal("InterruptedException in DAO impl", e);
			throw new DAOException("InterruptException", e);
		}
		return user;
	}
	
	@Override
	public User editUser(long ID, String login, String password, String name, String phone, String email) throws DAOException {
		
		PreparedStatement preparedStatement;
		ResultSet resultSet = null;
		User user = null;
		String status = "1";
		String role = "user";
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try(Connection connection = connectionPool.takeConnection()){
			preparedStatement = connection.prepareStatement(SQLquery.EDIT_USER);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, phone);
			preparedStatement.setString(5, email);
			preparedStatement.setLong(6, ID);
			preparedStatement.executeUpdate();
			
			
			preparedStatement = connection.prepareStatement(SQLquery.SELECT_USER_BY_ID);
			preparedStatement.setLong(1, ID);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				user = new User(ID, login, role, status, name, phone, email);
			}
			
		}catch (SQLException e) {	
			logger.fatal("SQLException in DAO impl in editUser method", e);
			throw new DAOException("SQLException",e);
			
		} catch (InterruptedException e) {
			logger.fatal("InterruptedException in DAO impl in editUser method", e);
			throw new DAOException("InterruptException", e);
		}
		return user;
	}
	
	@Override
	public boolean checkLogin(String login) throws DAOException {
		
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		
		try (Connection connection = connectionPool.takeConnection()){
			preparedStatement = connection.prepareStatement(SQLquery.CHECK_LOGIN);
			preparedStatement.setString(1, login);
			resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			return false;
		}
		}catch (SQLException e) {	
			logger.fatal("SQLException in DAO impl in checkLogin method", e);
			throw new DAOException("SQLException",e);
			
		} catch (InterruptedException e) {
			logger.fatal("InterruptedException in DAO impl in checkLogin method", e);
			throw new DAOException("InterruptException", e);
		}
		
		return true;
	}

	
	@Override
	public AdminUser signInAdmin(String login, String password) throws DAOException {
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		AdminUser admin = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		
		try(Connection connection = connectionPool.takeConnection()){
			preparedStatement = connection.prepareStatement(SQLquery.SIGN_IN);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int ID = resultSet.getInt(SQLquery.ID);
				String role = resultSet.getString(SQLquery.ROLE);
				String status = resultSet.getString(SQLquery.STATUS);
				String phone = resultSet.getString(SQLquery.PHONE);
				String email = resultSet.getString(SQLquery.EMAIL);
				admin = new AdminUser(ID, login, role, status, phone, email);
			}
		}catch(SQLException e) {
			logger.fatal("SQLException in DAO impl in signInAdmin", e);
			throw new DAOException("SQLException", e);
		}catch (InterruptedException e) {
			logger.fatal("InterruptedException in DAO impl in signInAdmin", e);
			throw new DAOException("InterruptException", e);
		}
		return admin;
	}

	

	
}
