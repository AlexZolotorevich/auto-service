package by.htp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.htp.dao.AppDAO;
import by.htp.dao.connection_pool.ConnectionPool;
import by.htp.dao.exception.DAOException;
import by.htp.entity.News;
import by.htp.entity.User;
import by.htp.entity.Vehicle;

public class AppDAOImpl implements AppDAO {

	private final static Logger logger = Logger.getLogger(AppDAOImpl.class);

	
	@Override
	public Integer getNumberOfRows() throws DAOException {
		
		int numOfRows = 0;
		Statement statement;
		ResultSet resultSet;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		
		try(Connection connection = connectionPool.takeConnection()){
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQLquery.GET_COUNT_OF_VEHICLE);
			
			while(resultSet.next()){
				numOfRows = resultSet.getInt(SQLquery.COUNT_ID);
			}
			
		}catch (SQLException e) {
			logger.fatal("SQLException in DAO impl", e);
			throw new DAOException("SQLException", e);
			
		} catch (InterruptedException e) {
			logger.fatal("InterruptedException in DAO impl", e);
			throw new DAOException("InterruptedException", e);
		}
		
		return numOfRows;
	}

	
	
	@Override
	public List<Vehicle> getAllCars(Integer number, Integer start) throws DAOException {

		Statement statement;
		ResultSet resultSet;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		List<Vehicle> cars = new ArrayList<Vehicle>();
		final String LIMIT_AND_OFFSET = " LIMIT " + number + " OFFSET " + start;
		
		try (Connection connection = connectionPool.takeConnection()) {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQLquery.SELECT_ALL_VEHICLE + LIMIT_AND_OFFSET);

			while (resultSet.next()) {
				int ID = resultSet.getInt(SQLquery.ID);
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

				Vehicle vehicle = new Vehicle(ID, model, year, typeCarcase, price, transmission, typeFuel,
						engineCapacity, driveUnit, mileage, date);
				cars.add(vehicle);
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
	public void addVehicle(Vehicle vehicle, Integer userID, String date) throws DAOException {

		PreparedStatement preparedStatement;
		Statement statement;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		ResultSet resultSet = null;
		int vehicleID = 0;

		try (Connection connection = connectionPool.takeConnection()){
			connection.setAutoCommit(false);
			
			try {
				preparedStatement = connection.prepareStatement(SQLquery.ADD_VEHICLE);
				preparedStatement.setString(1, vehicle.getModel());
				preparedStatement.setString(2, SQLquery.STATUS_NUMBER);
				preparedStatement.setString(3, vehicle.getYear());
				preparedStatement.setString(4, vehicle.getTypeCarcase());
				preparedStatement.setString(5, vehicle.getPrice());
				preparedStatement.setString(6, vehicle.getTransmission());
				preparedStatement.setString(7, vehicle.getTypeFuel());
				preparedStatement.setString(8, vehicle.getEngineCapacity());
				preparedStatement.setString(9, vehicle.getDriveUnit());
				preparedStatement.setString(10, vehicle.getMileage());
				preparedStatement.setString(11, date);
				preparedStatement.setInt(12, userID);
				preparedStatement.execute();

				statement = connection.createStatement();
				resultSet = statement.executeQuery(SQLquery.GET_LAST_ID);

				while (resultSet.next()) {
					vehicleID = resultSet.getInt("last_insert_id()");

				}
				if (vehicleID != 0) {
					preparedStatement = connection.prepareStatement(SQLquery.ADD_VEHICLE_DESCRIPTION);
					preparedStatement.setInt(1, vehicleID);
					preparedStatement.setString(2, vehicle.getDescription());
					preparedStatement.execute();

					connection.commit();
				}

			} catch (SQLException e) {
				logger.debug("Something wrong. Doing rollback", e);
				connection.rollback();
				throw new DAOException("SQLException", e);
			}

		} catch (SQLException e) {
			logger.fatal("SQLException in DAO impl", e);
			throw new DAOException("SQLException", e);

		} catch (InterruptedException e) {
			logger.fatal("InterruptedException in DAO impl", e);
			throw new DAOException("InterruptedException", e);
		}

	}

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
	public List<Vehicle> getCarsByUser(Integer userID) throws DAOException {

		PreparedStatement preparedStatement;
		ResultSet resultSet;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		List<Vehicle> cars = new ArrayList<Vehicle>();

		try (Connection connection = connectionPool.takeConnection()) {
			preparedStatement = connection.prepareStatement(SQLquery.SELECT_VEHICLES_BY_USER);
			preparedStatement.setInt(1, userID);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int ID = resultSet.getInt(SQLquery.ID);
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

				Vehicle vehicle = new Vehicle(ID, model, year, typeCarcase, price, transmission, typeFuel,
						engineCapacity, driveUnit, mileage, date);
				cars.add(vehicle);
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
	public Vehicle getCarByID(Integer vehicle_ID) throws DAOException {
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Vehicle vehicle = null;
		String description = null;

		try (Connection connection = connectionPool.takeConnection()) {

			preparedStatement = connection.prepareStatement(SQLquery.SELECT_VEHICLES_BY_ID);
			preparedStatement.setInt(1, vehicle_ID);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int ID = resultSet.getInt(SQLquery.ID);
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
			}

			preparedStatement = connection.prepareStatement(SQLquery.SELECT_DESCRIPTION_BY_ID);
			preparedStatement.setInt(1, vehicle_ID);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				description = resultSet.getString(SQLquery.DESCRIPTION);
				vehicle.setDescription(description);
			}

		} catch (SQLException e) {
			logger.fatal("SQLException in DAO impl", e);
			throw new DAOException("SQLException", e);

		} catch (InterruptedException e) {
			logger.fatal("InterruptedException in DAO impl", e);
			throw new DAOException("InterruptedException", e);
		}
		return vehicle;
	}

	
	/** change status in 0
	 * 	from profile.jsp
	 *  
	 *  */
	@Override
	public void deleteVehicleByID(Integer vehicle_ID) throws DAOException {
		PreparedStatement preparedStatement;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try (Connection connection = connectionPool.takeConnection()) {
			preparedStatement = connection.prepareStatement(SQLquery.DELETE_VEHICLE);
			preparedStatement.setInt(1, vehicle_ID);
			preparedStatement.execute();

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
			preparedStatement.executeQuery();
			
		}catch(SQLException e) {
			logger.fatal("SQLException in DAO impl", e);
			throw new DAOException("SQLException", e);
			
		}catch(InterruptedException e) {
			logger.fatal("InterruptedException in DAO impl", e);
			throw new DAOException("InterruptedException", e);
		}
		
	}



	@Override
	public News getAllNews() throws DAOException {
		
		return null;
	}

	
}
