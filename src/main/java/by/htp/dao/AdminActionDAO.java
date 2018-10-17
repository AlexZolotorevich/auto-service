package by.htp.dao;

import java.util.List;

import by.htp.dao.exception.DAOException;
import by.htp.entity.User;
import by.htp.entity.Vehicle;

public interface AdminActionDAO {
	
	boolean addNews(String title, String text, int Id, String date) throws DAOException;
	
	List<Vehicle> getNewCarsOfUsers(Integer number, Integer start) throws DAOException;
	
	void acceptVehicle(Integer vehicleID) throws DAOException;
	
	void deleteVehicleByAdmin(Integer vehicleID) throws DAOException;
	
	List<User> getAllUsers() throws DAOException;
	
	void toBanUser(Integer userID) throws DAOException;
	
	void toUnBanUser(Integer userID) throws DAOException;
	
	Integer getNumberOfRows() throws DAOException;
}
