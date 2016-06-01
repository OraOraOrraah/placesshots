package com.kugiojotaro.placesshots.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
//@WebServlet(name="PlacesshotsInitServlet", urlPatterns="/PlacesshotsInitServlet", loadOnStartup=2) 
public class PlacesshotsInitServlet extends HttpServlet {

	private static final long serialVersionUID = -2500928494683987836L;
	
	private static final Logger LOGGER = Logger.getLogger(PlacesshotsInitServlet.class);

	//@Autowired
	///@Resource(name="placesshotsSource")
	//private InputStreamSource placesshotsSource;
	
	//@Value("classpath:placesshots.txt")
    //private InputStreamSource placesshotsSource;
	
	public PlacesshotsInitServlet() {
        super();
    }

	@Override
	public void init(ServletConfig config) throws ServletException {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}