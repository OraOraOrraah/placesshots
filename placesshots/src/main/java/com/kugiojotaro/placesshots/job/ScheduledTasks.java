package com.kugiojotaro.placesshots.job;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.context.ServletContextAware;

import com.kugiojotaro.placesshots.constant.PlaceShotsConstant;
import com.kugiojotaro.placesshots.util.Helper;

@Configuration
@EnableScheduling
public class ScheduledTasks implements ServletContextAware {

	private static final Logger LOGGER = Logger.getLogger(ScheduledTasks.class);
	
	private ServletContext servletContext;

    @Scheduled(fixedRate = 180000)
    public void keepAwake() {
    	Calendar c = Calendar.getInstance(Locale.ENGLISH);
    	LOGGER.info(" keepAwake: " + c.getTime());
    }
    
    
    @Scheduled(cron="0 0 14 * * ?")
    public void toNextWeek() {
    	LOGGER.info(" toNextWeek");
 	
    	Connection conn = null;
		Statement stmt = null;
		
		try {
			Calendar cal = Calendar.getInstance(Locale.ENGLISH);
			LOGGER.info(" date: " + cal.get(Calendar.DAY_OF_MONTH));
			
			Boolean gotoNextWeek = Boolean.FALSE;
			if (cal.get(Calendar.MONTH) == cal.JUNE) {
				if (cal.get(Calendar.DAY_OF_MONTH) > 9 && cal.get(Calendar.DAY_OF_MONTH) != 23 && cal.get(Calendar.DAY_OF_MONTH) != 24 && cal.get(Calendar.DAY_OF_MONTH) != 28 && cal.get(Calendar.DAY_OF_MONTH) != 29) {
					gotoNextWeek = Boolean.TRUE;
				}
			}
			if (cal.get(Calendar.MONTH) == cal.JULY) {
				if (cal.get(Calendar.DAY_OF_MONTH) < 11 && cal.get(Calendar.DAY_OF_MONTH) != 4 && cal.get(Calendar.DAY_OF_MONTH) != 5 && cal.get(Calendar.DAY_OF_MONTH) != 8 && cal.get(Calendar.DAY_OF_MONTH) != 9) {
					gotoNextWeek = Boolean.TRUE;
				}
			}
			if (gotoNextWeek) {
				LOGGER.info(" from week: " + ((String) servletContext.getAttribute("week")));
				
				Properties properties = new Properties();
				properties.load(servletContext.getResourceAsStream("/WEB-INF/properties/jdbc.properties"));
				
				Class.forName(properties.getProperty("jdbc.driverClassName"));
		        conn = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
		        
		        int week = (Helper.string2Integer((String) servletContext.getAttribute("week")) + 1);
		        if (cal.get(Calendar.DAY_OF_MONTH) == 13) {
		        	week = 0;
		        }
		        
		        stmt = conn.createStatement();
		        stmt.executeUpdate("UPDATE config SET week=" + week + ", live='1'");
		      
		        servletContext.setAttribute(PlaceShotsConstant.WEEK, (week + ""));
		        
		        servletContext.setAttribute(PlaceShotsConstant.LIVE, Boolean.TRUE);
		        
		        LOGGER.info(" to week:" + week);
			}
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
    }
    
    @Scheduled(cron="0 0 5 * * ?")
    public void endLive() {
    	LOGGER.info(" endLive");
    	
    	Connection conn = null;
		Statement stmt = null;
		
    	try {
    		LOGGER.info(" from live: " + ((boolean)servletContext.getAttribute(PlaceShotsConstant.LIVE)));
    		
    		if ((boolean) servletContext.getAttribute(PlaceShotsConstant.LIVE)) {
    			
    			Properties properties = new Properties();
    			properties.load(servletContext.getResourceAsStream("/WEB-INF/properties/jdbc.properties"));
    			
    			Class.forName(properties.getProperty("jdbc.driverClassName"));
    	        conn = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
    	        
    	        stmt = conn.createStatement();
    	        stmt.executeUpdate("UPDATE config SET live='0'");
    	        
    			servletContext.setAttribute(PlaceShotsConstant.LIVE, Boolean.FALSE);
    		}
    		
    		LOGGER.info(" to live: " + ((boolean)servletContext.getAttribute(PlaceShotsConstant.LIVE)));
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
    }

    /*@Scheduled(cron="0 0 14 4 * ?")
    public void toRound4() {
    	LOGGER.info(" toNextRound");
 	
    	Connection conn = null;
		Statement stmt = null;
		
		try {
			LOGGER.info(" from round: " + ((String) servletContext.getAttribute("round")));
			
			Properties properties = new Properties();
			properties.load(servletContext.getResourceAsStream("/WEB-INF/properties/jdbc.properties"));
			
			Class.forName(properties.getProperty("jdbc.driverClassName"));
	        conn = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));

	        stmt = conn.createStatement();
	        stmt.executeUpdate("UPDATE config SET round=" + 4);
	      
	        servletContext.setAttribute("round", ("4"));
	        
	        LOGGER.info(" to round:" + 4);
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
    }
    
    @Scheduled(cron="0 0 14 8 * ?")
    public void toRound2() {
    	LOGGER.info(" toNextRound");
 	
    	Connection conn = null;
		Statement stmt = null;
		
		try {
			LOGGER.info(" from round: " + ((String) servletContext.getAttribute("round")));
			
			Properties properties = new Properties();
			properties.load(servletContext.getResourceAsStream("/WEB-INF/properties/jdbc.properties"));
			
			Class.forName(properties.getProperty("jdbc.driverClassName"));
	        conn = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
	        
	        stmt = conn.createStatement();
	        stmt.executeUpdate("UPDATE config SET round=" + 2);
	      
	        servletContext.setAttribute("round", ("2"));
	        
	        LOGGER.info(" to round:" + 2);
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
    }
    
    @Scheduled(cron="0 0 14 13 * ?")
    public void toRound0() {
    	LOGGER.info(" toNextRound");
 	
    	Connection conn = null;
		Statement stmt = null;
		
		try {
			LOGGER.info(" from round: " + ((String) servletContext.getAttribute("round")));
			
			Properties properties = new Properties();
			properties.load(servletContext.getResourceAsStream("/WEB-INF/properties/jdbc.properties"));
			
			Class.forName(properties.getProperty("jdbc.driverClassName"));
	        conn = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
	        
	        stmt = conn.createStatement();
	        stmt.executeUpdate("UPDATE config SET round=" + 0);
	      
	        servletContext.setAttribute("round", ("0"));
	        
	        LOGGER.info(" to round:" + 0);
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
    }
	*/
    
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
    
}