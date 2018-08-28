package by.htp.dao.impl;

public class SQLquery {
	
	private SQLquery() {
		
	}
	
	/** sql querry get count of vehicle*/
	
	public static final String GET_COUNT_OF_VEHICLE = "SELECT COUNT(ID) FROM `service-mercedes`.vehicle WHERE status = '1'";
	
	/** sql querry for user*/
	public static final String SIGN_IN = "SELECT ID, login, password, role, status, name, phone, email FROM `service-mercedes`.user WHERE login=? AND password=?";
	public static final String SELECT_USER_BY_ID = "SELECT login, password, role, status, name, phone, email FROM `service-mercedes`.user WHERE ID=?";
	public static final String SIGN_UP = "INSERT INTO `service-mercedes`.user (login, password, role, status, name, phone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
	public static final String EDIT_USER = "UPDATE `service-mercedes`.user SET  login = ?, password = ?, name = ?, phone = ?, email = ? WHERE ID= ?";
	public static final String CHECK_LOGIN = "SELECT login FROM `service-mercedes`.user WHERE login=?";
	public static final String DELETE_USER = "UPDATE `service-mercedes`.user SET status='0' WHERE id= ?";
	public static final String SELECT_USER_ID = "SELECT ID FROM `service-mercedes`.user WHERE login=? AND status = '1'";

	
	
	/** sql querry for vehicle*/
	public static final String SELECT_ALL_VEHICLE = "SELECT * FROM `service-mercedes`.vehicle WHERE status='1'";
	public static final String SELECT_VEHICLES_BY_USER = "SELECT * FROM `service-mercedes`.vehicle WHERE user_ID = ? AND status='1'";
	public static final String SELECT_VEHICLES_BY_ID = "SELECT * FROM `service-mercedes`.vehicle WHERE ID = ? AND status='1'";
	public static final String SELECT_DESCRIPTION_BY_ID = "SELECT linkDescription FROM `service-mercedes`.description WHERE vehicle_ID = ?";
	public static final String ADD_VEHICLE = "INSERT INTO `service-mercedes`.vehicle (model, status, year, typeCarcase, price, transmission, typeFuel, engineCapacity, driveUnit, mileage, date, user_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String GET_LAST_ID = "SELECT last_insert_id()";
	
	public static final String ADD_VEHICLE_DESCRIPTION = "INSERT INTO `service-mercedes`.description (vehicle_ID, linkDescription) VALUES (?, ?)";
	public static final String DELETE_VEHICLE = "UPDATE `service-mercedes`.`vehicle` SET `status`='0' WHERE `ID`=?";
	public static final String DELETE_VEHICLE_BY_ADMIN = "DELETE FROM `service-mercedes`.vehicle WHERE ID = ?";
	public static final String DELETE_VEHICLE_DESCRIPTION_BY_ADMIN = "DELETE FROM `service-mercedes`.description WHERE vehicle_ID = ?";
	
	
	
	
	/** sql querry for vehicle and users admin*/
	public static final String SELECT_NEW_CARS = "SELECT * FROM `service-mercedes`.vehicle WHERE status='0'";
	public static final String ADD_NEWS = "INSERT INTO `service-mercedes`.`news`(title, text, date, user_ID) VALUES (?,?,?,?)";
	public static final String GET_NEWS = "SELECT ID, title, text, date, user_ID FROM `service-mercedes`.`news`LIMIT 5";
	public static final String ACCEPT_VEHICLE = "UPDATE `service-mercedes`.vehicle SET status='1' WHERE ID= ?";
	public static final String GET_ALL_USERS = "SELECT * FROM `service-mercedes`.user WHERE role='user'";
	public static final String BAN_USER = "UPDATE `service-mercedes`.vehicle SET status='-1' WHERE ID= ?";
	
	
	/** helpers*/
	public static final String ID = "ID";
	public static final String LOGIN = "login";
	public static final String STATUS = "status";
	public static final String ROLE = "role";
	public static final String NAME = "name";
	public static final String PHONE = "phone";
	public static final String EMAIL = "email";
	public static final String STATUS_NUMBER = "0";
	
	public static final String MODEL = "model";
	public static final String YEAR = "year";
	public static final String TYPE_OF_CARCASE = "typeCarcase";
	public static final String PRICE = "price";
	public static final String TRANSMISSION = "transmission";
	public static final String TYPE_OF_FUEL = "typeFuel";
	public static final String ENGINE_CAPACITY = "engineCapacity";
	public static final String DRIVE_UNIT = "driveUnit";
	public static final String MILEAGE = "mileage";
	public static final String DATE = "date";
	public static final String DESCRIPTION = "linkDescription";
	public static final String COUNT_ID = "COUNT(ID)";
	
	public static final String TITLE = "title";
	public static final String TEXT = "text";
	public static final String USER_ID = "user_ID";
}
