package by.htp.service.impl.validation;



import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class ValidationImpl implements Validation{

	public final static String LOGIN_PATTERN = "([a-zA-Z0-9]{1,20}[^\\s])";
	public final static String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,15})";
	public final static String PHONE_PATTERN = "^(\\+?375)(29|33)[0-9]{7}$";
	public final static String EMAIL_PATTERN = "^([a-zA-Z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
	public final static String NAME_PATTERN = "";
	public final static String ADMIN_PREFIX = "[Admin]";
	
	
	private static final Logger logger = Logger.getLogger(ValidationImpl.class);
	private final Calendar calendar = Calendar.getInstance();
	
	
	@Override
	public boolean checkLogin(String login) {
		if(login.contains(ADMIN_PREFIX)) {
			return false;
		}
		Pattern pattern = Pattern.compile(LOGIN_PATTERN);
		Matcher matcher = pattern.matcher(login);
		return matcher.matches();
	}

	@Override
	public boolean checkPassword(String password) {
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
	
	@Override
	public boolean checkName(String name) {

			return true;
	}
	
	@Override
	public boolean checkPhone(String phone) {
		Pattern pattern = Pattern.compile(PHONE_PATTERN);
		Matcher matcher = pattern.matcher(phone.replaceAll("\\s",""));
		return matcher.matches();
	}

	@Override
	public boolean checkEmail(String email) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();	
	}

	@Override
	public boolean checkYear(String year) {
		try {
			int enterYear = Integer.parseInt(year);
			if(enterYear > calendar.get(Calendar.YEAR)) {  
				return false;
			} 
			return true;
			
		}catch(NumberFormatException e) {
		logger.info("User entered incorrect year");
			return false;
		}
		
	}

	@Override
	public boolean checkPrice(String price) {
		try {
			Integer.parseInt(price);
			return true;
			
		}catch(NumberFormatException e) {
		logger.info("User entered incorrect price");
			return false;
		}
	}

	@Override
	public boolean checkEngineCapacity(String engineCapacity) {
		try {
			Double.parseDouble(engineCapacity);
			return true;
			
		}catch(NumberFormatException e) {
		logger.info("User entered incorrect engine capacity");
			return false;
		}
	}	

	@Override
	public boolean checkMileAge(String mileAge) {
		try {
			Integer.parseInt(mileAge);
			return true;
			
		}catch(NumberFormatException e) {
		logger.info("User entered incorrect mileage");
			return false;
		}
		
	}

	

}
