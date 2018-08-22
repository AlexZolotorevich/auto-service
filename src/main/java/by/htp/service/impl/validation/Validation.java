package by.htp.service.impl.validation;

public interface Validation {
	
	boolean checkLogin(String login);
	boolean checkPassword(String password);
	boolean checkName(String name);
	boolean checkPhone(String password);
	boolean checkEmail(String email);
	boolean checkYear(String year);
	boolean checkPrice(String price);
	boolean checkEngineCapacity(String engineCapacity);
	boolean checkMileAge(String mileAge);

}
