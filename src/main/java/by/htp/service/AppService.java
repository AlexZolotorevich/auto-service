package by.htp.service;

import by.htp.entity.News;
import by.htp.entity.PageInformation;
import by.htp.entity.User;
import by.htp.entity.Vehicle;
import by.htp.service.exception.ServiceException;

import java.util.List;

public interface AppService {
	
	List<Vehicle> getPortianCars(String currentPage) throws ServiceException;
	
	PageInformation getPageInfo();
	
	List<Vehicle> getCarsByUser(Integer user_ID) throws ServiceException;
	
	Vehicle getCarByID(String vehicle_ID) throws ServiceException;
	
	void deleteVehicleByID(String vehicle_ID) throws ServiceException;
	
	boolean addVehicle(String model, String year, String typeCarcase, String price, String transmission, String typeFuel,
			String engineCapacity, String driveUnit, String mileage, String user_ID, String description) throws ServiceException;
	
	boolean addNews(String title, String text, long Id) throws ServiceException;
	
	News getAllNews() throws ServiceException;
	
	List<Vehicle>getNewCarsOfUsers() throws ServiceException;
	
	void acceptVehicle(Integer vehicle_ID) throws ServiceException;
	
	void deleteVehicleByAdmin(Integer vehicle_ID) throws ServiceException;
	
	List<User> getAllUsers() throws ServiceException; 
	
	void toBanUser(String userID) throws ServiceException;
}
