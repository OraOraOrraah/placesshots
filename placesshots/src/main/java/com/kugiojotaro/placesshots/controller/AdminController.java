package com.kugiojotaro.placesshots.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kugiojotaro.placesshots.constant.PlaceShotsConstant;
import com.kugiojotaro.placesshots.service.PredictService;
import com.kugiojotaro.placesshots.util.Helper;

@Controller
@RequestMapping(value="/adm")
public class AdminController {
	
	private static final Logger LOGGER = Logger.getLogger(AdminController.class);

	@Autowired
	private PredictService predictService;
	
	@RequestMapping(value="/update_week/{week}", method=RequestMethod.GET)
	public String updateWeek(ModelMap modelMap, HttpServletRequest request, @PathVariable String week) throws Exception {
		LOGGER.info(" updateWeek : " + week);
		
		/*
		Writer writer = null;
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(getClass().getClassLoader().getResource("placesshots.txt").getFile())), "UTF-8"));
		    writer.write(week);
		    
		    request.getServletContext().setAttribute("week", week);
		} catch (IOException ex) {
			LOGGER.error(ex, ex);
		} finally {
		    try {
			    writer.close();
		    } catch (Exception ex) {
		    	LOGGER.error(ex, ex);
		    }
		}
		*/
		
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
	    	LOGGER.error(e, e);
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
            	LOGGER.error(e, e);
            }
		}
		
		return "success";
	}
	
	@RequestMapping(value="/update_round/{round}", method=RequestMethod.GET)
	public String updateRound(ModelMap modelMap, HttpServletRequest request, @PathVariable String round) throws Exception {
		LOGGER.info(" updateRound : " + round);

		Connection conn = null;
		Statement stmt = null;
		
		try {
			Properties properties = new Properties();
			properties.load(request.getServletContext().getResourceAsStream("/WEB-INF/properties/jdbc.properties"));
			
			Class.forName(properties.getProperty("jdbc.driverClassName"));
	        conn = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
	        
	        stmt = conn.createStatement();
	        stmt.executeUpdate("UPDATE config SET round=" + Helper.string2Short(round));
	      
	        request.getServletContext().setAttribute(PlaceShotsConstant.ROUND, round);
	    }
	    catch (Exception e) {
	    	LOGGER.error(e, e);
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
            	LOGGER.error(e, e);
            }
		}
		
		return "success";
	}
	
	@RequestMapping(value="/update_live/{live}", method=RequestMethod.GET)
	public String updateLive(ModelMap modelMap, HttpServletRequest request, @PathVariable String live) throws Exception {
		LOGGER.info(" updateLive : " + live);
		
		Connection conn = null;
		Statement stmt = null;

		try {
			Properties properties = new Properties();
			properties.load(request.getServletContext().getResourceAsStream("/WEB-INF/properties/jdbc.properties"));
			
			Class.forName(properties.getProperty("jdbc.driverClassName"));
	        conn = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
	        
	        stmt = conn.createStatement();
	        stmt.executeUpdate("UPDATE config SET live='" + live + "'");
	        
	        request.getServletContext().setAttribute(PlaceShotsConstant.LIVE, live.equals("1") ? Boolean.TRUE : Boolean.FALSE);
	    }
	    catch (Exception e) {
	    	LOGGER.error(e, e);
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
            	LOGGER.error(e, e);
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