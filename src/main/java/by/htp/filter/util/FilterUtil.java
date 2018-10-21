package by.htp.filter.util;

public class FilterUtil {
	
	private static FilterUtil instance;
	private static short ID = 0;
	private static DatesValidations datesValidations = new DatesValidationsImpl();
	
	public FilterUtil() {
		ID++;
	}
	
	public static FilterUtil getInstance() {
		if(instance == null) {
			synchronized(FilterUtil.class) {
				if(instance == null) {
					instance = new FilterUtil();
				}
			}
			
		}
		return instance;
	}
	
	
	public short getID() {
		return ID;
	}
	
	
	public boolean isValidDates(String inner) {
		return datesValidations.isValid(inner);
	}
}
