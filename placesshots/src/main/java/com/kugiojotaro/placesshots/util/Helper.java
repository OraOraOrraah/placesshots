package com.kugiojotaro.placesshots.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.util.StringUtils;

import com.kugiojotaro.placesshots.constant.PlaceShotsConstant;

public class Helper {

	private static final Logger LOGGER = Logger.getLogger(Helper.class);
	
	private static final ThreadLocal<DateFormat> SDF_DATETIME = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US);
		}
	};
	
	private static final ThreadLocal<DateFormat> SDF_DATE = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("dd/MM/yyyy", Locale.US);
		}
	};
	
	private static final ThreadLocal<DateFormat> SDF_TIME = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("HH:mm:ss", Locale.US);
		}
	};
	
	private static final ThreadLocal<DateFormat> SDF_FIXTURE_DATE = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("EEEE dd/MM/yyyy", Locale.US);
		}
	};
	
	private static final ThreadLocal<DecimalFormat> DF_RENDER_DECIMAL = new ThreadLocal<DecimalFormat>() {
		@Override
		protected DecimalFormat initialValue() {
			return new DecimalFormat("###,##0.00");
		}
	};
	
	public static Date string2Date(String value) {
		Date date = null;
		
		if (value != null && StringUtils.hasLength(value)) {
			try {
				//SDF_DATE.get().setLenient(false);
				date = SDF_DATE.get().parse(value);
			} catch (ParseException ex) {
				LOGGER.error(ex, ex);
			}
		}
		
		return date;
	}
	
	public static String datetime2String(Date value) {
		String result = "";

		try {
			if (value != null) {
				result = SDF_DATETIME.get().format(value);
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return result;
	}
	
	public static String date2String(Date value) {
		String result = "";

		try {
			if (value != null) {
				result = SDF_DATE.get().format(value);
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return result;
	}
	
	public static String date2TimeString(Date value) {
		String result = "";

		try {
			if (value != null) {
				result = SDF_TIME.get().format(value);
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return result;
	}
	
	public static String bigDecimal2String(BigDecimal value) {
		String result = "0";

		if (value != null && value.floatValue() > 0) {
			result = DF_RENDER_DECIMAL.get().format(value);
		}

		return result;
	}
	
	public static String appendDQ(String value) {
	    return "\"" + value + "\"";
	}
	
	public static Short string2Short(String value) {
		try {
			return Short.parseShort(value);
		}
		catch (Exception ex) {
			return null;
		}
	}
	
	public static Integer string2Integer(String value) {
		try {
			return Integer.parseInt(value);
		}
		catch (Exception ex) {
			return null;
		}
	}
	
	public static Long string2Long(String value) {
		try {
			return Long.parseLong(value);
		}
		catch (Exception ex) {
			return null;
		}
	}
	
	public static String null2Blank(String value) {
		if (value == null || (value != null && value.equals("null"))) {
			return "";
		}
		
		return value;
	}
	
	public static String nullObj2Blank(Object value) {
		if (value == null) {
			return "";
		}
		
		return value.toString();
	}
	
	public static String null2Zero(String value) {
		if (value == null || (value != null && value.equals("null"))) {
			return "0";
		}
		
		return value;
	}
	
	public static String nullObj2Zero(Object value) {
		if (value == null) {
			return "0";
		}
		
		return value.toString();
	}
	
	public static String nullOrBlank2Zero(String value) {
		if (value == null || (value != null && (value.equals("") || value.equals("null")))) {
			return "0";
		}
		
		return value;
	}
	
	public static boolean isLiveTime(Date d) {
        return new DateTime(d).getMinuteOfDay() < (PlaceShotsConstant.END_LIVE_HOUR * 60);
    }
	
	public static String formatFixtureDate(Date param) {
		String result = "";

		try {
			if (param != null) {
				result = SDF_FIXTURE_DATE.get().format(param);
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return result;
	}

}