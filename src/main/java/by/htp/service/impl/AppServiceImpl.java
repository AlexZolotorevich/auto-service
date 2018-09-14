package by.htp.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import by.htp.dao.AppDAO;
import by.htp.dao.DAOFactory;
import by.htp.dao.exception.DAOException;
import by.htp.entity.News;
import by.htp.entity.PageInformation;
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
	public List<Vehicle> getPortianCars(String currentPageInner, String[] modelInner, String[] carcaseInner, String yearInner, String[] fuelInner) throws ServiceException {
		pageInfo = new PageInformation();
		String querry = "";
		
		if (currentPageInner == null) {
			currentPageInner = "1";
		}
		
		if(filtration(modelInner,carcaseInner,yearInner,fuelInner)) {
			BuilderQuerry builder = new BuilderQuerry();
			String model = builder.buildQuerry(modelInner);
			String carcase = builder.buildQuerry(carcaseInner);
			String fuel = builder.buildQuerry(fuelInner);
			
			querry = " AND " + builder.buildModel(model) + builder.buildCarcase(carcase) + builder.buildFuel(fuel) + builder.buildYear(yearInner);
			
		}
		
		Integer currentPage = Integer.parseInt(currentPageInner);
		pageInfo.setCurrentPage(currentPage);
		getCountOfPage();
		getStartOfPage();

		List<Vehicle> cars = null;

		try {
			cars = appDAO.getAllCars(pageInfo.getCOUNT_ITEMS_PER_PAGE(), pageInfo.getStart(), querry);

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
	
	
	private boolean filtration(String[] modelInner, String[] carcaseInner, String yearInner, String[] fuelInner) {
		if(modelInner != null || carcaseInner != null || yearInner != null || fuelInner != null ) {
			return true;	
		}
		return false;	
	}

	/** add vehicle user */
	@Override
	public boolean addVehicle(String model, String year, String typeCarcase, String price, String transmission,
			String typeFuel, String engineCapacity, String driveUnit, String mileage, String userID, String description)
			throws ServiceException {

		Vehicle vehicle = new Vehicle(model, year, typeCarcase, price, transmission, typeFuel, engineCapacity,
				driveUnit, mileage, description);
		listErrors = ValidationProvider.checkVehicle(vehicle);
		String date = Util.takeDate();

		try {
			if (listErrors.isEmpty()) {
				appDAO.addVehicle(vehicle, Integer.parseInt(userID), date);
				return true;
			}
		} catch (DAOException e) {
			logger.warn("ServletException in AppService in the method addVehicle", e);
			throw new ServiceException("ServiceException", e);
		}
		return false;
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

}
