package by.htp.service.impl;


import java.util.List;

import org.apache.log4j.Logger;

import by.htp.dao.AdminActionDAO;
import by.htp.dao.DAOFactory;
import by.htp.dao.exception.DAOException;
import by.htp.entity.User;
import by.htp.entity.Vehicle;
import by.htp.service.AdminActionService;
import by.htp.service.exception.ServiceException;

public class AdminActionServiceImpl implements AdminActionService{
	
	private final static Logger logger = Logger.getLogger(AppServiceImpl.class);
	private static AdminActionDAO adminActionDAO = DAOFactory.getInstance().getAdminActionDAO();

	/** add news admin */
	@Override
	public boolean addNews(String title, String text, long Id) throws ServiceException {

		String date = Util.takeDate();

			try {
				adminActionDAO.addNews(title, text, (int) Id, date);

			} catch (DAOException e) {
				logger.warn("ServletException in AppService in the method addNews", e);
				throw new ServiceException("ServiceException", e);
			}
		
		return true;
	}

	/** admin get new cars to active them */
	@Override
	public List<Vehicle> getNewCarsOfUsers() throws ServiceException {

		List<Vehicle> cars = null;

		try {
			cars = adminActionDAO.getNewCarsOfUsers();

		} catch (DAOException e) {
			logger.warn("ServletException in AppService in the method getNewCarsOfUsers", e);
			throw new ServiceException("ServiceException", e);
		}
		return cars;
	}

	/** active car by Admin */
	@Override
	public void acceptVehicle(Integer vehicleID) throws ServiceException {

		try {
			adminActionDAO.acceptVehicle(vehicleID);

		} catch (DAOException e) {
			logger.warn("ServletException in AppService in the method acceptVehicle", e);
			throw new ServiceException("ServiceException", e);
		}

	}

	/** delete new car */
	@Override
	public void deleteVehicleByAdmin(Integer vehicle_ID) throws ServiceException {

		try {
			adminActionDAO.deleteVehicleByAdmin(vehicle_ID);

		} catch (DAOException e) {
			logger.warn("ServletException in AppService in the method deleteVehicleByAdmin", e);
			throw new ServiceException("ServiceException", e);
		}

	}

	

	/** get all users */
	@Override
	public List<User> getAllUsers() throws ServiceException {

		List<User> users;
		try {
			users = adminActionDAO.getAllUsers();
		} catch (DAOException e) {
			logger.warn("ServletException in AppService in the method getAllUsers", e);
			throw new ServiceException("ServiceException", e);
		}
		return users;
	}
	
	/** ban user ha-ha */
	@Override
	public void toBanUser(String userId) throws ServiceException {
		Integer userID = Integer.parseInt(userId);
		
		try {
			adminActionDAO.toBanUser(userID);
			
		} catch (DAOException e) {
			logger.warn("ServletException in AppService in the method toBanUser", e);
			throw new ServiceException("ServiceException", e);
		}

	}

	@Override
	public void toUnBanUser(String userId) throws ServiceException {
		Integer userID = Integer.parseInt(userId);
		
		try {
			adminActionDAO.toUnBanUser(userID);
			
		} catch (DAOException e) {
			logger.warn("ServletException in AppService in the method toBanUser", e);
			throw new ServiceException("ServiceException", e);
		}
		
	}
	
}
