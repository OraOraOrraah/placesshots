package com.kugiojotaro.placesshots.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kugiojotaro.placesshots.util.Consts;
import com.kugiojotaro.placesshots.util.Helper;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value="/adm")
@Log4j
public class AdminController {
	
	@RequestMapping(value="/update_week/{week}", method=RequestMethod.GET)
	public String updateWeek(ModelMap modelMap, HttpServletRequest request, @PathVariable String week) throws Exception {
		log.info(" updateWeek : " + week);
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Properties properties = new Properties();
			properties.load(request.getServletContext().getResourceAsStream("/WEB-INF/properties/jdbc.properties"));
			
			Class.forName(properties.getProperty("jdbc.driverClassName"));
	        conn = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
	        
	        stmt = conn.createStatement();
	        stmt.executeUpdate("UPDATE config SET week=" + Helper.string2Short(week));
	      
	        request.getServletContext().setAttribute("week", week);
	    }
	    catch (Exception e) {
	    	log.error(e, e);
	    }
		finally {
			try {
                if (stmt != null) {
                	stmt.close();
                }
                if (conn != null) {
                	conn.close();
                }

            } catch (Exception e) {
            	log.error(e, e);
            }
		}
		
		return "success";
	}
	
	@RequestMapping(value="/update_round/{round}", method=RequestMethod.GET)
	public String updateRound(ModelMap modelMap, HttpServletRequest request, @PathVariable String round) throws Exception {
		log.info(" updateRound : " + round);

		Connection conn = null;
		Statement stmt = null;
		
		try {
			Properties properties = new Properties();
			properties.load(request.getServletContext().getResourceAsStream("/WEB-INF/properties/jdbc.properties"));
			
			Class.forName(properties.getProperty("jdbc.driverClassName"));
	        conn = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
	        
	        stmt = conn.createStatement();
	        stmt.executeUpdate("UPDATE config SET round=" + Helper.string2Short(round));
	      
	        request.getServletContext().setAttribute(Consts.ROUND, round);
	    }
	    catch (Exception e) {
	    	log.error(e, e);
	    }
		finally {
			try {
                if (stmt != null) {
                	stmt.close();
                }
                if (conn != null) {
                	conn.close();
                }

            } catch (Exception e) {
            	log.error(e, e);
            }
		}
		
		return "success";
	}
	
	@RequestMapping(value="/update_live/{live}", method=RequestMethod.GET)
	public String updateLive(ModelMap modelMap, HttpServletRequest request, @PathVariable String live) throws Exception {
		log.info(" updateLive : " + live);
		
		Connection conn = null;
		Statement stmt = null;

		try {
			Properties properties = new Properties();
			properties.load(request.getServletContext().getResourceAsStream("/WEB-INF/properties/jdbc.properties"));
			
			Class.forName(properties.getProperty("jdbc.driverClassName"));
	        conn = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
	        
	        stmt = conn.createStatement();
	        stmt.executeUpdate("UPDATE config SET live='" + live + "'");
	        
	        request.getServletContext().setAttribute(Consts.LIVE, live.equals("1") ? Boolean.TRUE : Boolean.FALSE);
	    }
	    catch (Exception e) {
	    	log.error(e, e);
	    }
		finally {
			try {
                if (stmt != null) {
                	stmt.close();
                }
                if (conn != null) {
                	conn.close();
                }

            } catch (Exception e) {
            	log.error(e, e);
            }
		}
		
		return "success";
	}
	
	/*
	@RequestMapping(value="/update_point/{week}", method=RequestMethod.GET)
	public String updatePoint(ModelMap modelMap, HttpServletRequest request, @PathVariable String week) throws Exception {
		//predictService.updatePoint(Helper.string2Short(week));
		
		return "success";
	}
	*/
	
}