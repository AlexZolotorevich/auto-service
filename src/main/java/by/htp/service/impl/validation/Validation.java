package by.htp.service.impl.validation;

public interface Validation {
	
	boolean checkLogin(String login);
	boolean checkPassword(String password);
	boolean checkName(String name);
	boolean checkPhone(String password);
	boolean checkEmail(String email);
	
	boolean checkModel(String model);
	boolean checkYear(String year);
	boolean checkTypeCarcase(String typeCarcase);
	boolean checkPrice(String price);
	boolean checkTransmission(String transmission);
	boolean checkTypeFuel(String typeFuel);
	boolean checkEngineCapacity(String engineCapacity);
	boolean checkDriveUnit(String driveUnit);
	boolean checkMileAge(String mileAge);

}
