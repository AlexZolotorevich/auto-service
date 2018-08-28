package by.htp.service.impl.validation;


import java.util.ArrayList;
import java.util.List;

import by.htp.entity.User;
import by.htp.entity.Vehicle;

public class ValidationProvider {
	
	public static final String LOGIN_ERROR = "incorrectLogin";
	public static final String PASSWORD_ERROR = "incorrectPassword";
	public static final String NAME_ERROR = "incorrectName";
	public static final String PHONE_ERROR = "incorrectPhone";
	public static final String EMAIL_ERROR = "incorrectEmail";
	
	public static final String MODEL_ERROR = "model";
	public static final String YEAR_ERROR = "year";
	public static final String TYPE_CARCASE_ERROR = "carcase";
	public static final String PRICE_ERROR = "price";
	public static final String TRANSMISSION_ERROR = "transmission";
	public static final String TYPE_FUEL_ERROR = "typeFuel";
	public static final String ENGINE_CAPACITY_ERROR = "engineCapacity";
	public static final String DRIVE_UNIT_ERROR = "driveUnit";
	public static final String MILEAGE_ERROR = "mileage";
	
	
	//Constructors
	private ValidationProvider() { 
		
	}
	
	
	private static Validation valid = new ValidationImpl();
	
	
	//Validate new User
	public static List<String> checkNewUser(User user) {
		List<String> list = new ArrayList<String>();
		if(!valid.checkLogin(user.getLogin())){ list.add(LOGIN_ERROR);}
		if(!valid.checkPassword(user.getPassword())) {list.add(PASSWORD_ERROR);}
		if(!valid.checkName(user.getName())) {list.add(NAME_ERROR);}
		if(!valid.checkPhone(user.getPhone())) {list.add(PHONE_ERROR);}
		if(!valid.checkEmail(user.getEmail())) {list.add(EMAIL_ERROR);}
		return list;
	}
	
	public static List<String> checkSignInUser(String login, String password) {
		List<String> list = new ArrayList<String>();
		if(!valid.checkLogin(login)){ list.add(LOGIN_ERROR);}
		if(!valid.checkPassword(password)) {list.add(PASSWORD_ERROR);}
		return list;
	}
	
	
	public static List<String> checkVehicle(Vehicle vehicle){
		List<String> list = new ArrayList<String>();
		if(!valid.checkModel(vehicle.getModel())) { list.add(MODEL_ERROR); }
		if(!valid.checkYear(vehicle.getYear())) { list.add(YEAR_ERROR); }
		if(!valid.checkTypeCarcase(vehicle.getTypeCarcase())) { list.add(TYPE_CARCASE_ERROR); }
		if(!valid.checkPrice(vehicle.getPrice())) { list.add(YEAR_ERROR); }
		if(!valid.checkTransmission(vehicle.getTransmission())) { list.add(TRANSMISSION_ERROR); }
		if(!valid.checkTypeFuel(vehicle.getTypeFuel())) { list.add(TYPE_FUEL_ERROR); }
		if(!valid.checkEngineCapacity(vehicle.getEngineCapacity())) { list.add(ENGINE_CAPACITY_ERROR); }
		if(!valid.checkDriveUnit(vehicle.getDriveUnit())) { list.add(DRIVE_UNIT_ERROR); }
		if(!valid.checkMileAge(vehicle.getMileage())) { list.add(MILEAGE_ERROR); }
		
		return list;
	}
}
