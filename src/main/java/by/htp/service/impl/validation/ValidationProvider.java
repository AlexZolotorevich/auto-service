package by.htp.service.impl.validation;


import java.util.ArrayList;
import java.util.List;

import by.htp.entity.User;

public class ValidationProvider {
	
	public static final String LOGIN_ERROR = "incorrect login";
	public static final String PASSWORD_ERROR = "incorrect password";
	public static final String NAME_ERROR = "incorrect name";
	public static final String PHONE_ERROR = "incorrect phone";
	public static final String EMAIL_ERROR = "incorrect email";
	
	
	
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
	
	
	
}
