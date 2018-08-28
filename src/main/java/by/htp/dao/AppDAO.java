package by.htp.dao;


import java.util.List;

import by.htp.dao.exception.DAOException;
import by.htp.entity.News;
import by.htp.entity.User;
import by.htp.entity.Vehicle;

public interface AppDAO {
	
	
	Integer getNumberOfRows() throws DAOException;
	
	List<Vehicle> getAllCars(Integer number, Integer start) throws DAOException;
	
	List<Vehicle> getCarsByUser(Integer userID) throws DAOException;
	
	Vehicle getCarByID(Integer vehicleID) throws DAOException;
	
	void deleteVehicleByID(Integer vehicleID) throws DAOException;
	
	void addVehicle(Vehicle vehicle, Integer userID, String date) throws DAOException;
	
	boolean addNews(String title, String text, int Id, String date) throws DAOException;
	
	List<News> getAllNews() throws DAOException;
	
	List<Vehicle> getNewCarsOfUsers() throws DAOException;
	
	void acceptVehicle(Integer vehicleID) throws DAOException;
	
	void deleteVehicleByAdmin(Integer vehicleID) throws DAOException;
	
	List<User> getAllUsers() throws DAOException;
	
	void toBanUser(Integer userID) throws DAOException;
	
	List<Vehicle> filtrateVehicle() throws DAOException;
}
