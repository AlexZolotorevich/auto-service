package by.htp.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import by.htp.dao.AppDAO;
import by.htp.dao.DAOFactory;
import by.htp.dao.exception.DAOException;
import by.htp.entity.PageInformation;
import by.htp.entity.User;
import by.htp.entity.Vehicle;
import by.htp.service.AppService;
import by.htp.service.exception.ServiceException;

public class AppServiceImpl implements AppService {

	private final static Logger logger = Logger.getLogger(AppServiceImpl.class);
	private static AppDAO appDAO = DAOFactory.getInstance().getAppDAO();
	private PageInformation pageInfo = null;

	
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
			logger.warn("ServletException in AppService", e);
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
			logger.warn("Impossible to get number of rows", e);
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
			String typeFuel, String engineCapacity, String driveUnit, String mileage, String user_ID,
			String description) throws ServiceException {

		String date = takeDate();
		try {

			appDAO.addVehicle(model, year, typeCarcase, price, transmission, typeFuel, engineCapacity, driveUnit,
					mileage, Integer.parseInt(user_ID), description, date);
		} catch (DAOException e) {
			logger.warn("ServletException in AppService", e);
			throw new ServiceException("ServiceException", e);
		}
		return true;
	}

	/** add news admin */
	@Override
	public boolean addNews(String title, String text, long Id) throws ServiceException {

		String date = takeDate();

		if (title != null && text != null) {
			String link = createText(title, text);

			try {
				appDAO.addNews(link, (int) Id, date);

			} catch (DAOException e) {
				logger.warn("ServletException in AppService", e);
				throw new ServiceException("ServiceException", e);
			}
		}

		return true;
	}

	/** create file with text */
	public String createText(String title, String text) {

		final String PATH = "webapp\\static\\news";

		PrintWriter writer = null;
		String link = PATH + title + ".doc";
		String temp = "";
		int j = 0;
		try {
			writer = new PrintWriter(link, "UTF-8");
			for (int i = 0; i < text.length(); i++) {
				if (i % 120 == 0) {
					temp = text.substring(j, i);
					writer.println(temp);
					temp = "";
					j = i;
				}
			}
			temp = text.substring(j, text.length());
			writer.println(temp);
			temp = "";
			writer.close();

		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			logger.warn("FileNotFoundException | UnsupportedEncodingException in AppService", e);

		} finally {
			writer.close();
		}
		return link;

	}

	/** create List of text */
	public List<String> getText(String title) {

		Path file = Paths.get(title);
		BufferedReader reader = null;
		String line;
		List<String> array = new ArrayList<String>();

		if (Files.exists(file) && Files.isReadable(file)) {
			try {
				reader = Files.newBufferedReader(file, Charset.defaultCharset());
				while ((line = reader.readLine()) != null) {

					array.add(line);
				}

			} catch (IOException e) {
				logger.warn("Exception in AppService", e);
			} finally {
				try {
					reader.close();
				} catch (IOException e) {
					logger.warn("IOException in AppService", e);

				}
			}
		}

		return array;
	}

	/** get cars of user (admit userID) */
	@Override
	public List<Vehicle> getCarsByUser(Integer userID) throws ServiceException {

		List<Vehicle> cars = null;

		try {

			cars = appDAO.getCarsByUser(userID);
		} catch (DAOException e) {
			logger.warn("ServletException in AppService", e);
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

	/** gelete vehicle by ID */
	@Override
	public void deleteVehicleByID(String vehicle_ID) throws ServiceException {

		try {
			Integer ID = Integer.parseInt(vehicle_ID);
			appDAO.deleteVehicleByID(ID);

		} catch (DAOException e) {
			logger.warn("ServletException in AppService", e);
			throw new ServiceException("ServiceException", e);
		}

	}

	/** admin get new cars to active them */
	@Override
	public List<Vehicle> getNewCarsOfUsers() throws ServiceException {

		List<Vehicle> cars = null;

		try {
			cars = appDAO.getNewCarsOfUsers();

			for (Vehicle s : cars) {
				System.out.println(s);
			}

		} catch (DAOException e) {
			logger.warn("ServletException in AppService", e);
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
			logger.warn("ServletException in AppService", e);
			throw new ServiceException("ServiceException", e);
		}

	}

	/** delete new car */
	@Override
	public void deleteVehicleByAdmin(Integer vehicle_ID) throws ServiceException {

		try {
			appDAO.deleteVehicleByAdmin(vehicle_ID);

		} catch (DAOException e) {
			logger.warn("ServletException in AppService", e);
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
			logger.warn("ServletException in AppService", e);
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
			logger.warn("ServletException in AppService", e);
			throw new ServiceException("ServiceException", e);
		}

	}
	
	@Override
	public PageInformation getPageInfo() {
		return pageInfo;
	}
}
