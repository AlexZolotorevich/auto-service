package by.htp.service;

import java.util.List;

import by.htp.entity.User;
import by.htp.entity.Vehicle;
import by.htp.service.exception.ServiceException;

public interface AdminActionService {
	
	boolean addNews(String title, String text, long Id) throws ServiceException;
	
	List<Vehicle>getNewCarsOfUsers() throws ServiceException;
	
	void acceptVehicle(Integer vehicleID) throws ServiceException;
	
	void deleteVehicleByAdmin(Integer vehicleID) throws ServiceException;
	
	List<User> getAllUsers() throws ServiceException; 
	
	void toBanUser(String userID) throws ServiceException;
	
	void toUnBanUser(String userID) throws ServiceException;
}
