package by.htp.service.impl;

import org.apache.log4j.Logger;

public class BuilderQuerry {
	
	private final String MODEL_IN = "model IN";
	private final String TYPE_CARCASE_IN = "typeCarcase IN";
	private final String TYPE_FUEL_IN = "typeFuel IN";
	private final String YEAR_IN = "year IN";
	
	protected BuilderQuerry() {}
	
	private final static Logger logger = Logger.getLogger(BuilderQuerry.class);
	
	public String buildQuerry(String[] inner) {
		String result = "";
		if (inner == null) {
			logger.info("incomming dates are null in the method: buildQuerry");
			return result;
		}

		for(int i = 0; i < inner.length; i++) {
			result += "'" + inner[i] + "'";
			if(i < inner.length - 1) {
				result += ", ";
			}	
		}
		return result;

	}

	public String buildQuerry(String result, String inner) {
		if (inner != "") {
			return result + " AND " + inner;
			
		} else {
			return result;
		}
	}
	
	public String buildModel(String inner) {
		if(inner == "") {
			return inner;
			
		}else {
			return MODEL_IN + " (" + inner + ") ";
		}
		
	}
	
	public String buildCarcase(String inner) {
		if(inner == "") {
			return inner;
			
		}else {
			return TYPE_CARCASE_IN + " (" + inner + ") ";
		}
		
	}
	
	public String buildFuel(String inner) {
		if(inner == "") {
			return inner;
			
		}else {
			return TYPE_FUEL_IN + " (" + inner + ") ";
		}
		
	}
	
	public String buildYear(String inner) {
		if(inner == "") {
			return inner;
			
		}else {
			return YEAR_IN + " (" + inner + ") ";
		}
		
	}

}
