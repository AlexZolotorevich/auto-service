package by.htp.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	
	/** create DATE */
	public static String takeDate() {
		Date date = new Date();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy.MM.dd");
		String nowDate = simple.format(date).replace(".", "-");
		return nowDate;
	}

}
