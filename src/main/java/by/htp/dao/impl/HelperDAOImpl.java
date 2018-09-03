package by.htp.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import by.htp.dao.HelperDAO;
import by.htp.dao.connection_pool.ConnectionPool;
import by.htp.dao.exception.DAOException;

public class HelperDAOImpl  implements HelperDAO{
	
	private final static Logger logger = Logger.getLogger(AppDAOImpl.class);
	
	public Integer getNumberOfRows() throws DAOException {

		int numOfRows = 0;
		Statement statement;
		ResultSet resultSet;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		
		try(Connection connection = connectionPool.takeConnection()){
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQLquery.GET_COUNT_OF_NEW_VEHICLE_ADMIN);
			
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

}
