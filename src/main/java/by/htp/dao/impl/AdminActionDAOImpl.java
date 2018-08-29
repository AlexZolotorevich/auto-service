package by.htp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.htp.dao.AdminActionDAO;
import by.htp.dao.connection_pool.ConnectionPool;
import by.htp.dao.exception.DAOException;
import by.htp.entity.User;
import by.htp.entity.Vehicle;

public class AdminActionDAOImpl implements AdminActionDAO{
	
	private final static Logger logger = Logger.getLogger(AppDAOImpl.class);
	

	@Override
	public boolean addNews(String title, String text, int Id, String date) throws DAOException {

		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement preparedStatement;

		try (Connection connection = connectionPool.takeConnection()) {
			preparedStatement = connection.prepareStatement(SQLquery.ADD_NEWS);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, text);
			preparedStatement.setString(3, date);
			preparedStatement.setInt(4, Id);
			preparedStatement.execute();
			
			return true;

		} catch (SQLException e) {
			logger.fatal("SQLException in DAO impl", e);
			throw new DAOException("SQLException", e);

		} catch (InterruptedException e) {
			logger.fatal("InterruptedException in DAO impl", e);
			throw new DAOException("InterruptedException", e);
		}
	}

	
	@Override
	public List<Vehicle> getNewCarsOfUsers() throws DAOException {
		
		List<Vehicle> cars = new ArrayList<Vehicle>();
		Statement statement;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Integer ID = 0;
		Vehicle vehicle = null;

		try (Connection connection = connectionPool.takeConnection()) {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQLquery.SELECT_NEW_CARS);

			while (resultSet.next()) {
				ID = resultSet.getInt(SQLquery.ID);
				String model = resultSet.getString(SQLquery.MODEL);
				String year = resultSet.getString(SQLquery.YEAR);
				String typeCarcase = resultSet.getString(SQLquery.TYPE_OF_CARCASE);
				String price = resultSet.getString(SQLquery.PRICE);
				String transmission = resultSet.getString(SQLquery.TRANSMISSION);
				String typeFuel = resultSet.getString(SQLquery.TYPE_OF_FUEL);
				String engineCapacity = resultSet.getString(SQLquery.ENGINE_CAPACITY);
				String driveUnit = resultSet.getString(SQLquery.DRIVE_UNIT);
				String mileage = resultSet.getString(SQLquery.MILEAGE);
				String date = resultSet.getString(SQLquery.DATE);

				vehicle = new Vehicle(ID, model, year, typeCarcase, price, transmission, typeFuel, engineCapacity,
						driveUnit, mileage, date);
				cars.add(vehicle);
			}

			preparedStatement = connection.prepareStatement(SQLquery.SELECT_DESCRIPTION_BY_ID);
			preparedStatement.setInt(1, ID);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String description = resultSet.getString(SQLquery.DESCRIPTION);
				vehicle.setDescription(description);
			}

		} catch (SQLException e) {
			logger.fatal("SQLException in DAO impl", e);
			throw new DAOException("SQLException", e);

		} catch (InterruptedException e) {
			logger.fatal("InterruptedException in DAO impl", e);
			throw new DAOException("InterruptedException", e);
		}
		return cars;
	}

	@Override
	public void acceptVehicle(Integer vehicleID) throws DAOException{
		
		PreparedStatement preparedStatement;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		
		try(Connection connection = connectionPool.takeConnection()){
		preparedStatement = connection.prepareStatement(SQLquery.ACCEPT_VEHICLE);	
		preparedStatement.setInt(1, vehicleID);
		preparedStatement.execute();
			
		}catch (SQLException e) {
			logger.fatal("SQLException in DAO impl", e);
			throw new DAOException("SQLException", e);

		} catch (InterruptedException e) {
			logger.fatal("InterruptedException in DAO impl", e);
			throw new DAOException("InterruptedException", e);
		}
		
	}

	@Override
	public void deleteVehicleByAdmin(Integer vehicleID) throws DAOException {
		
		PreparedStatement preparedStatement;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		
		try(Connection connection = connectionPool.takeConnection()){
			connection.setAutoCommit(false);
			
			try {
				preparedStatement = connection.prepareStatement(SQLquery.DELETE_VEHICLE_DESCRIPTION_BY_ADMIN);	
				preparedStatement.setInt(1, vehicleID);
				preparedStatement.execute();
				
				preparedStatement = connection.prepareStatement(SQLquery.DELETE_VEHICLE_BY_ADMIN);
				preparedStatement.setInt(1, vehicleID);
				preparedStatement.execute();
				connection.commit();
				
			}catch (SQLException e) {
				logger.warn("SQLException in DAO impl. Making rollback", e);
				connection.rollback();
				throw new DAOException("SQLException", e);
			}
			
			}catch (SQLException e) {
				logger.fatal("SQLException in DAO impl", e);
				throw new DAOException("SQLException", e);

			} catch (InterruptedException e) {
				logger.fatal("InterruptedException in DAO impl", e);
				throw new DAOException("InterruptedException", e);
			}
	}

	@Override
	public List<User> getAllUsers() throws DAOException {
		
		List<User> usersList = new ArrayList<User>();
		Statement statement;
		ResultSet resultSet;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		
		try(Connection connection = connectionPool.takeConnection()){
			statement = connection.createStatement();
			resultSet =statement.executeQuery(SQLquery.GET_ALL_USERS);
			
			while(resultSet.next()) {
				int ID = resultSet.getInt(SQLquery.ID);
				String login = resultSet.getString(SQLquery.LOGIN);
				String role = resultSet.getString(SQLquery.ROLE);
				String status = resultSet.getString(SQLquery.STATUS);
				String name = resultSet.getString(SQLquery.NAME);
				String phone = resultSet.getString(SQLquery.PHONE);
				String email = resultSet.getString(SQLquery.EMAIL);
				
				User user = new User(ID, login, role, status, name, phone, email);
				usersList.add(user);
			}
			
		}catch (SQLException e) {
			logger.fatal("SQLException in DAO impl", e);
			throw new DAOException("SQLException", e);

		} catch (InterruptedException e) {
			logger.fatal("InterruptedException in DAO impl", e);
			throw new DAOException("InterruptedException", e);
		}
		
		return usersList;
	}

	@Override
	public void toBanUser(Integer userID) throws DAOException {
		
		PreparedStatement preparedStatement;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		
		try(Connection connection = connectionPool.takeConnection()){
			preparedStatement = connection.prepareStatement(SQLquery.BAN_USER);
			preparedStatement.setInt(1, userID);
			preparedStatement.executeUpdate();
			
		}catch(SQLException e) {
			logger.fatal("SQLException in DAO impl", e);
			throw new DAOException("SQLException", e);
			
		}catch(InterruptedException e) {
			logger.fatal("InterruptedException in DAO impl", e);
			throw new DAOException("InterruptedException", e);
		}
		
	}


	@Override
	public void toUnBanUser(Integer userID) throws DAOException {

		PreparedStatement preparedStatement;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		
		try(Connection connection = connectionPool.takeConnection()){
			preparedStatement = connection.prepareStatement(SQLquery.UNBAN_USER);
			preparedStatement.setInt(1, userID);
			preparedStatement.executeUpdate();
			
		}catch(SQLException e) {
			logger.fatal("SQLException in DAO impl", e);
			throw new DAOException("SQLException", e);
			
		}catch(InterruptedException e) {
			logger.fatal("InterruptedException in DAO impl", e);
			throw new DAOException("InterruptedException", e);
		}
		
	}



}
