package by.htp.service;

import by.htp.entity.News;
import by.htp.entity.PageInformation;
import by.htp.entity.Vehicle;
import by.htp.service.exception.ServiceException;

import java.util.List;

public interface AppService {
	
	List<Vehicle> getPortianCars(String currentPage, String[] modelInner, String[] carcaseInner, String yearInner, String[] fuelInner) throws ServiceException;
	
	PageInformation getPageInfo();
	
	List<Vehicle> getCarsByUser(Integer user_ID) throws ServiceException;
	
	Vehicle getCarByID(String vehicleID) throws ServiceException;
	
	void deleteVehicleByID(String vehicleID) throws ServiceException;
	
	boolean addVehicle(String model, String year, String typeCarcase, String price, String transmission, String typeFuel,
			String engineCapacity, String driveUnit, String mileage, String userID, String description) throws ServiceException;
	
	List<News> getAllNews() throws ServiceException;
	
	public List<String> getListErrors();
	
}
