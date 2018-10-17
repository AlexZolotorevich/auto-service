package by.htp.dao;

import by.htp.dao.impl.AdminActionDAOImpl;
import by.htp.dao.impl.AppDAOImpl;
import by.htp.dao.impl.HelperDAOImpl;
import by.htp.dao.impl.UserDAOImpl;


public class DAOFactory {
	
	private final static DAOFactory instance = new DAOFactory();
	
	private final UserDAO userDAO = new UserDAOImpl();
	private final AppDAO appDAO = new AppDAOImpl();
	private final AdminActionDAO adminActionDAO = new AdminActionDAOImpl();
	private final HelperDAO helperDAO = new HelperDAOImpl();
	
	public static DAOFactory getInstance() {
		return instance;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public AppDAO getAppDAO() {
		return appDAO;
	}
	
	public AdminActionDAO getAdminActionDAO() {
		return adminActionDAO;
	}
	
	public HelperDAO getHelperDAO() {
		return helperDAO;
	}
}
