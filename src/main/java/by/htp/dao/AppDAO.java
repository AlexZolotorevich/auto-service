package by.htp.dao;


import java.util.List;

import by.htp.dao.exception.DAOException;
import by.htp.entity.User;
import by.htp.entity.Vehicle;

public interface AppDAO {
	
	Integer getNumberOfRows() throws DAOException;
	
	List<Vehicle> getAllCars(Integer number, Integer start) throws DAOException;
	
	List<Vehicle> getCarsByUser(Integer user_ID) throws DAOException;
	
	Vehicle getCarByID(Integer vehicle_ID) throws DAOException;
	
	void deleteVehicleByID(Integer vehicle_ID) throws DAOException;
	
	void addVehicle(String model, String year, String typeCarcase, String price, String transmission, 
					String typeFuel, String engineCapacity, String driveUnit, String mileage, Integer user_ID, String description, String date) throws DAOException;
	
	boolean addNews(String link, int Id, String date) throws DAOException;
	
	List<Vehicle> getNewCarsOfUsers() throws DAOException;
	
	void acceptVehicle(Integer vehicle_ID) throws DAOException;
	
	void deleteVehicleByAdmin(Integer vehicle_ID) throws DAOException;
	
	List<User> getAllUsers() throws DAOException;
	
	void toBanUser(Integer userID) throws DAOException;
}
