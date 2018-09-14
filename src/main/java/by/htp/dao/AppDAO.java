package by.htp.dao;


import java.util.List;

import by.htp.dao.exception.DAOException;
import by.htp.entity.News;
import by.htp.entity.Vehicle;

public interface AppDAO {
	
	Integer getNumberOfRows() throws DAOException;
	
	List<Vehicle> getAllCars(Integer number, Integer start, String querry) throws DAOException;
	
	List<Vehicle> getCarsByUser(Integer userID) throws DAOException;
	
	Vehicle getCarByID(Integer vehicleID) throws DAOException;
	
	void deleteVehicleByID(Integer vehicleID) throws DAOException;
	
	void addVehicle(Vehicle vehicle, Integer userID, String date) throws DAOException;
	
	List<News> getAllNews() throws DAOException;
	
}
