package by.htp.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import by.htp.dao.AppDAO;
import by.htp.dao.DAOFactory;
import by.htp.dao.exception.DAOException;
import by.htp.entity.News;
import by.htp.entity.PageInformation;
import by.htp.entity.User;
import by.htp.entity.Vehicle;
import by.htp.service.AppService;
import by.htp.service.exception.ServiceException;
import by.htp.service.impl.validation.ValidationProvider;

public class AppServiceImpl implements AppService {

	private final static Logger logger = Logger.getLogger(AppServiceImpl.class);
	private static AppDAO appDAO = DAOFactory.getInstance().getAppDAO();
	private PageInformation pageInfo = null;
	private List<String> listErrors = null;

	
	/** get portion of vehicles */
	@Override
	public List<Vehicle> getPortianCars(String currentPageInner) throws ServiceException {
		pageInfo = new PageInformation();
		
		if(currentPageInner == null) {
			currentPageInner = "1";
		}
		
		Integer currentPage = Integer.parseInt(currentPageInner);
		pageInfo.setCurrentPage(currentPage);
		getCountOfPage();
		getStartOfPage();
		
		List<Vehicle> cars = null;

		try {
			cars = appDAO.getAllCars(pageInfo.getCOUNT_ITEMS_PER_PAGE(), pageInfo.getStart());
			
		} catch (DAOException e) {
			logger.warn("ServletException in AppService in the method getPortionCars", e);
			throw new ServiceException("ServiceException", e);
		}
		return cars;
	}

	private void getCountOfPage() throws ServiceException {
		int numberOfRows = getNumberOfRows();
		Integer countOfPages = numberOfRows / pageInfo.getCOUNT_ITEMS_PER_PAGE();
		
		if (numberOfRows % pageInfo.getCOUNT_ITEMS_PER_PAGE() != 0) {
			countOfPages++;
		}
		pageInfo.setCountOfPage(countOfPages);
	}

	private Integer getNumberOfRows() throws ServiceException {
		int numberOfRows = 0;

		try {
			numberOfRows = appDAO.getNumberOfRows();
		} catch (DAOException e) {
			logger.warn("Impossible to get number of rows in the method getNumberOfRows", e);
			throw new ServiceException("ServiceException", e);
		}

		return numberOfRows;
	}
	
	private void getStartOfPage() throws ServiceException {
		int start = pageInfo.getCurrentPage() * pageInfo.getCOUNT_ITEMS_PER_PAGE() - pageInfo.getCOUNT_ITEMS_PER_PAGE();
		pageInfo.setStart(start);
	}
	
	

	/** add vehicle user */
	@Override
	public boolean addVehicle(String model, String year, String typeCarcase, String price, String transmission,
			String typeFuel, String engineCapacity, String driveUnit, String mileage, String userID, String description) throws ServiceException {
		
		Vehicle vehicle = new Vehicle(model, year, typeCarcase, price, transmission, typeFuel, engineCapacity, driveUnit, mileage, description);
		listErrors = ValidationProvider.checkVehicle(vehicle);
		String date = takeDate();
		
		try {
			if(listErrors.isEmpty()) {
				appDAO.addVehicle(vehicle, Integer.parseInt(userID), date);
				return true;
			}
		} catch (DAOException e) {
			logger.warn("ServletException in AppService in the method addVehicle", e);
			throw new ServiceException("ServiceException", e);
		}
		return false;
	}
	
	
	
	

	/** add news admin */
	@Override
	public boolean addNews(String title, String text, long Id) throws ServiceException {

		String date = takeDate();

			try {
				appDAO.addNews(title, text, (int) Id, date);

			} catch (DAOException e) {
				logger.warn("ServletException in AppService in the method addNews", e);
				throw new ServiceException("ServiceException", e);
			}
		
		return true;
	}

	/** get cars of user (admit userID) */
	@Override
	public List<Vehicle> getCarsByUser(Integer userID) throws ServiceException {

		List<Vehicle> cars = null;

		try {

			cars = appDAO.getCarsByUser(userID);
		} catch (DAOException e) {
			logger.warn("ServletException in AppService in the method getCarsByUser", e);
			throw new ServiceException("ServiceException", e);
		}
		return cars;
	}

	
	/** get car using ID */
	@Override
	public Vehicle getCarByID(String vehicleID) throws ServiceException {

		Integer ID = Integer.parseInt(vehicleID);
		Vehicle vehicle = null;
		try {
			vehicle = appDAO.getCarByID(ID);
		} catch (DAOException e) {

			e.printStackTrace();
		}

		return vehicle;
	}

	/** delete vehicle by ID */
	@Override
	public void deleteVehicleByID(String vehicle_ID) throws ServiceException {

		try {
			Integer ID = Integer.parseInt(vehicle_ID);
			appDAO.deleteVehicleByID(ID);

		} catch (DAOException e) {
			logger.warn("ServletException in AppService in the method deleteVehicleByID", e);
			throw new ServiceException("ServiceException", e);
		}

	}

	/** admin get new cars to active them */
	@Override
	public List<Vehicle> getNewCarsOfUsers() throws ServiceException {

		List<Vehicle> cars = null;

		try {
			cars = appDAO.getNewCarsOfUsers();

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
			appDAO.acceptVehicle(vehicleID);

		} catch (DAOException e) {
			logger.warn("ServletException in AppService in the method acceptVehicle", e);
			throw new ServiceException("ServiceException", e);
		}

	}

	/** delete new car */
	@Override
	public void deleteVehicleByAdmin(Integer vehicle_ID) throws ServiceException {

		try {
			appDAO.deleteVehicleByAdmin(vehicle_ID);

		} catch (DAOException e) {
			logger.warn("ServletException in AppService in the method deleteVehicleByAdmin", e);
			throw new ServiceException("ServiceException", e);
		}

	}

	/** create DATE */
	private String takeDate() {
		Date date = new Date();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy.MM.dd");
		String nowDate = simple.format(date).replace(".", "-");
		return nowDate;
	}

	/** get all users */
	@Override
	public List<User> getAllUsers() throws ServiceException {

		List<User> users;
		try {
			users = appDAO.getAllUsers();
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
			appDAO.toBanUser(userID);
			
		} catch (DAOException e) {
			logger.warn("ServletException in AppService in the method toBanUser", e);
			throw new ServiceException("ServiceException", e);
		}

	}
	
	@Override
	public PageInformation getPageInfo() {
		return pageInfo;
	}

	@Override
	public List<News> getAllNews() throws ServiceException {
		
		List<News> news = null;
		
		try {
			news = appDAO.getAllNews();
			
		} catch (DAOException e) {
			logger.warn("ServletException in AppService in the method getAllNews", e);
			throw new ServiceException("ServiceException", e);
		}
		
		return news;
	}
	@Override
	public List<String> getListErrors() {
		return listErrors;
	}

	@Override
	public List<Vehicle> filtrateVehicle() throws ServiceException {
		
		List<Vehicle> list = null;
		
		try {
			list = appDAO.filtrateVehicle();
			
		} catch (DAOException e) {
			logger.warn("ServletException in AppService in the method filtrateVehicle", e);
			throw new ServiceException("ServiceException", e);
		}
		return list;
	}
}
