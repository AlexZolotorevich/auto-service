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
			logger.fatal("SQLException in DAO impl in the method getNumberOfRows", e);
			throw new DAOException("SQLException", e);
			
		} catch (InterruptedException e) {
			logger.fatal("InterruptedException in DAO impl in the method getNumberOfRows", e);
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
			logger.fatal("SQLException in DAO impl in the getAllCars method", e);
			throw new DAOException("SQLException", e);
		} catch (InterruptedException e) {
			logger.fatal("InterruptedException in DAO impl in the getAllCars method", e);
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
					vehicleID = resultSet.getInt(SQLquery.LAST_INSERT);

				}
				if (vehicleID != 0) {
					preparedStatement = connection.prepareStatement(SQLquery.ADD_VEHICLE_DESCRIPTION);
					preparedStatement.setInt(1, vehicleID);
					preparedStatement.setString(2, vehicle.getDescription());
					preparedStatement.execute();

					connection.commit();
				}

			} catch (SQLException e) {
				logger.debug("Something wrong. Doing rollback in the addVehicle method", e);
				connection.rollback();
				throw new DAOException("SQLException", e);
			}

		} catch (SQLException e) {
			logger.fatal("SQLException in DAO impl in the addVehicle method", e);
			throw new DAOException("SQLException", e);

		} catch (InterruptedException e) {
			logger.fatal("InterruptedException in DAO impl in the addVehicle method", e);
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
			logger.fatal("SQLException in DAO impl in the getCarsByUser method", e);
			throw new DAOException("SQLException", e);

		} catch (InterruptedException e) {
			logger.fatal("InterruptedException in DAO impl in the getCarsByUser method", e);
			throw new DAOException("InterruptedException", e);
		}

		return cars;
	}

	@Override
	public Vehicle getCarByID(Integer vehicleID) throws DAOException {
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Vehicle vehicle = null;
		String description = null;

		try (Connection connection = connectionPool.takeConnection()) {

			preparedStatement = connection.prepareStatement(SQLquery.SELECT_VEHICLES_BY_ID);
			preparedStatement.setInt(1, vehicleID);
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
			preparedStatement.setInt(1, vehicleID);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				description = resultSet.getString(SQLquery.DESCRIPTION);
				vehicle.setDescription(description);
			}

		} catch (SQLException e) {
			logger.fatal("SQLException in DAO impl in the getCarsByID method", e);
			throw new DAOException("SQLException", e);

		} catch (InterruptedException e) {
			logger.fatal("InterruptedException in DAO impl in the getCarsByID method", e);
			throw new DAOException("InterruptedException", e);
		}
		return vehicle;
	}

	
	/** change status in 0
	 * 	from profile.jsp
	 *  
	 *  */
	@Override
	public void deleteVehicleByID(Integer vehicleID) throws DAOException {
		PreparedStatement preparedStatement;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try (Connection connection = connectionPool.takeConnection()) {
			preparedStatement = connection.prepareStatement(SQLquery.DELETE_VEHICLE);
			preparedStatement.setInt(1, vehicleID);
			preparedStatement.execute();

		} catch (SQLException e) {
			logger.fatal("SQLException in DAO impl in the deleteVehicleByID method", e);
			throw new DAOException("SQLException", e);

		} catch (InterruptedException e) {
			logger.fatal("InterruptedException in DAO impl in the deleteVehicleByID method", e);
			throw new DAOException("InterruptedException", e);
		}

	}

	

	@Override
	public List<News> getAllNews() throws DAOException {
		Statement statement;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		ResultSet resultSet;
		List<News> newsList = new ArrayList<News>();
		
		try(Connection connection = connectionPool.takeConnection()){
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQLquery.GET_NEWS);
			
			while(resultSet.next()) {
				long ID = resultSet.getLong(SQLquery.ID);
				String title = resultSet.getString(SQLquery.TITLE);
				String text = resultSet.getString(SQLquery.TEXT);
				String date = resultSet.getString(SQLquery.DATE);
				int userID = resultSet.getInt(SQLquery.USER_ID);
				News news = new News(ID, title, text, date, userID);
				newsList.add(news);
			}
			
		}catch(SQLException e) {
			logger.fatal("SQLException in DAO impl in the getAllNews method", e);
			throw new DAOException("SQLException", e);
			
		}catch(InterruptedException e) {
			logger.fatal("InterruptedException in DAO impl in the getAllNews method", e);
			throw new DAOException("InterruptedException", e);
		}
		return newsList;
	}


	@Override
	public List<Vehicle> filtrateVehicle() throws DAOException {
		
		
		return null;
	}

	
}
