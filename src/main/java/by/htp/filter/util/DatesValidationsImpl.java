package by.htp.filter.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatesValidationsImpl implements DatesValidations{
	
	
	
	protected DatesValidationsImpl() {
		
	}

	@Override
	public boolean isValid(String inner) {
		Pattern pattern = Pattern.compile(ConstantsToValid.commonRegExp);
		Matcher matcher = pattern.matcher(inner);
		return !matcher.matches();
		
	}

}
