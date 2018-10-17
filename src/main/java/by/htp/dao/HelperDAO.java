package by.htp.dao;

import by.htp.dao.exception.DAOException;

public interface HelperDAO {
	
	Integer getNumberOfRows(String querry) throws DAOException;
	


}
