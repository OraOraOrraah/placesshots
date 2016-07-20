package com.kugiojotaro.placesshots.listener;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.kugiojotaro.placesshots.constant.PlaceShotsConstant;
import com.kugiojotaro.placesshots.util.Helper;

public class AppContextListener implements ServletContextListener {

	private static final Logger LOGGER = Logger.getLogger(AppContextListener.class);
	
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		LOGGER.info(" contextInitialized");

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String week = "";
			String round = "";
			String live = "";
//			Map<String, String> mapUserIcon = new HashMap<String, String>();
			
			Properties properties = new Properties();
			properties.load(servletContextEvent.getServletContext().getResourceAsStream("/WEB-INF/properties/jdbc.properties"));

	        Class.forName(properties.getProperty("jdbc.driverClassName"));
	        conn = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));

	        String query = "SELECT week, round, live FROM config";
	        stmt = conn.createStatement();
	        rs = stmt.executeQuery(query);
	        while (rs.next()) {
	    	    week = rs.getInt("week") + "";
	    	    round = rs.getInt("round") + "";
	    	    live = rs.getString("live") + "";
	        }
	        rs.close();
	        stmt.close();
	        
	        LOGGER.info(" week: " + week);
	        LOGGER.info(" round: " + round);
	        LOGGER.info(" live: " + live);
	        
//	        query = "SELECT username, icon FROM user";
//	        stmt = conn.createStatement();
//	        rs = stmt.executeQuery(query);
//	        while (rs.next()) {
//	        	mapUserIcon.put(rs.getString("username"), Helper.null2Blank(rs.getString("icon")));
//	        }
//	        
//	        rs.close();
//	        stmt.close();
	      
	        servletContextEvent.getServletContext().setAttribute(PlaceShotsConstant.WEEK, week);
	        servletContextEvent.getServletContext().setAttribute(PlaceShotsConstant.ROUND, round);
	        servletContextEvent.getServletContext().setAttribute(PlaceShotsConstant.LIVE, live.equals("1") ? Boolean.TRUE : Boolean.FALSE);
//	        servletContextEvent.getServletContext().setAttribute(PlaceShotsConstant.MAP_USER_ICON, mapUserIcon);
	    }
	    catch (Exception e) {
	    	LOGGER.error(e, e);
	    }
		finally {
			try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                	stmt.close();
                }
                if (conn != null) {
                	conn.close();
                }

            } catch (Exception e) {
            	LOGGER.error(e, e);
            }
		}
    }
 
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    	// This manually deregisters JDBC driver, which prevents Tomcat 7 from complaining about memory leaks wrto this class
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
            }

        }
    }
    
}