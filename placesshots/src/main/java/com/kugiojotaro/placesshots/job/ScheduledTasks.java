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

import com.kugiojotaro.placesshots.util.Consts;
import com.kugiojotaro.placesshots.util.Helper;

@Configuration
@EnableScheduling
public class ScheduledTasks implements ServletContextAware {

	private static final Logger LOGGER = Logger.getLogger(ScheduledTasks.class);
	
	private ServletContext servletContext;

//    @Scheduled(fixedRate = 180000)
//    public void keepAwake() {
//    	Calendar c = Calendar.getInstance(Locale.ENGLISH);
//    	LOGGER.info(" keepAwake: " + c.getTime());
//    }
    
    @Scheduled(cron="0 0 11 * * ?")
    public void nextWeekOrRound() {
    	LOGGER.info(" nextWeekOrRound");
 	
    	Connection conn = null;
		Statement stmt = null;
		
		try {
			Properties properties = new Properties();
			properties.load(servletContext.getResourceAsStream("/WEB-INF/properties/jdbc.properties"));
			
			Class.forName(properties.getProperty("jdbc.driverClassName"));
	        conn = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
	        
			stmt = conn.createStatement();
			
			Calendar cal = Calendar.getInstance(Locale.ENGLISH);
			
			// toNextWeek
			Boolean gotoNextWeek = Boolean.FALSE;
			if (cal.get(Calendar.MONTH) == cal.JUNE) {
				if (cal.get(Calendar.DAY_OF_MONTH) > 9 && (cal.get(Calendar.DAY_OF_MONTH) != 23 && cal.get(Calendar.DAY_OF_MONTH) != 24 && cal.get(Calendar.DAY_OF_MONTH) != 28 && cal.get(Calendar.DAY_OF_MONTH) != 29)) {
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
		        int week = (Helper.string2Integer((String) servletContext.getAttribute("week")) + 1);

		        stmt.executeUpdate("UPDATE config SET week=" + week + ", live='1'");
		      
		        servletContext.setAttribute(Consts.WEEK, (week + ""));
		        
		        servletContext.setAttribute(Consts.LIVE, Boolean.TRUE);
		        
		        LOGGER.info(" to week:" + week);
			}
			
			// toNextRound
			Boolean gotoNextRound = Boolean.FALSE;
			if (cal.get(Calendar.MONTH) == cal.JUNE) {
				if (cal.get(Calendar.DAY_OF_MONTH) == 25 || cal.get(Calendar.DAY_OF_MONTH) == 30) {
					gotoNextRound = Boolean.TRUE;
				}
			}
			if (cal.get(Calendar.MONTH) == cal.JULY) {
				if (cal.get(Calendar.DAY_OF_MONTH) == 6) {
					gotoNextRound = Boolean.TRUE;
				}
			}
			if (gotoNextRound) {
				LOGGER.info(" from round: " + ((String) servletContext.getAttribute("round")));
	
				String nextRound = (Integer.parseInt((String) servletContext.getAttribute("round")) / 2) + "";
				
		        stmt.executeUpdate("UPDATE config SET round=" + nextRound);
		      
		        servletContext.setAttribute("round", nextRound);
		        
		        LOGGER.info(" to round:" + nextRound);
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
    
    @Scheduled(cron="0 0 19 * * ?")
    public void endLive() {
    	LOGGER.info(" endLive");
    	
    	Connection conn = null;
		Statement stmt = null;
		
    	try {
    		if ((boolean) servletContext.getAttribute(Consts.LIVE)) {
    			Properties properties = new Properties();
    			properties.load(servletContext.getResourceAsStream("/WEB-INF/properties/jdbc.properties"));
    			
    			Class.forName(properties.getProperty("jdbc.driverClassName"));
    	        conn = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
    	        
    	        stmt = conn.createStatement();
    	        stmt.executeUpdate("UPDATE config SET live='0'");
    	        
    			servletContext.setAttribute(Consts.LIVE, Boolean.FALSE);
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

//    @Scheduled(cron="0 0 8 * * ?")
//    public void toNextRound() {
//    	LOGGER.info(" toNextRound");
// 	
//    	Connection conn = null;
//		Statement stmt = null;
//		
//		try {
//			Calendar cal = Calendar.getInstance(Locale.ENGLISH);
//			
//			Boolean gotoNextRound = Boolean.FALSE;
//			if (cal.get(Calendar.MONTH) == cal.JUNE) {
//				if (cal.get(Calendar.DAY_OF_MONTH) == 25 || cal.get(Calendar.DAY_OF_MONTH) == 30) {
//					gotoNextRound = Boolean.TRUE;
//				}
//			}
//			if (cal.get(Calendar.MONTH) == cal.JULY) {
//				if (cal.get(Calendar.DAY_OF_MONTH) == 6) {
//					gotoNextRound = Boolean.TRUE;
//				}
//			}
//			if (gotoNextRound) {
//				LOGGER.info(" from round: " + ((String) servletContext.getAttribute("round")));
//	
//				String nextRound = (Integer.parseInt((String) servletContext.getAttribute("round")) / 2) + "";
//	
//				Properties properties = new Properties();
//				properties.load(servletContext.getResourceAsStream("/WEB-INF/properties/jdbc.properties"));
//				
//				Class.forName(properties.getProperty("jdbc.driverClassName"));
//		        conn = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
//	
//		        stmt = conn.createStatement();
//		        stmt.executeUpdate("UPDATE config SET round=" + nextRound);
//		      
//		        servletContext.setAttribute("round", nextRound);
//		        
//		        LOGGER.info(" to round:" + nextRound);
//			}
//	    }
//	    catch (Exception e) {
//	    	LOGGER.error(e, e);
//	    }
//		finally {
//			try {
//                if (stmt != null) {
//                	stmt.close();
//                }
//                if (conn != null) {
//                	conn.close();
//                }
//
//            } catch (Exception e) {
//            	LOGGER.error(e, e);
//            }
//		}		
//    }
    
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
    
}