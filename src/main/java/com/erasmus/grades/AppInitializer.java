package com.erasmus.grades;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
	    // Create the 'root' Spring application context
	    AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
	    rootContext.scan("com.erasmus.grades");
	    rootContext.setConfigLocations(new String[]{"WebAppContextConfig", "AppConfig"});
	    // Manages the lifecycle of the root application context
	    servletContext.addListener(new ContextLoaderListener(rootContext));

	    // Declare dispatcher servlet. Handles requests into the application
	    ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",
	            new DispatcherServlet(rootContext));
	    dispatcher.setLoadOnStartup(1);
	    dispatcher.addMapping("/");

	}

}
