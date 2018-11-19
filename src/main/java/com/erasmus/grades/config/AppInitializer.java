package com.erasmus.grades.config;

import com.erasmus.grades.security.SecurityConfiguration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

//public class AppInitializer implements WebApplicationInitializer {
//
//    @Override
//    public void onStartup(ServletContext servletContext) {
//        // Create the 'root' Spring application context
//        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//        rootContext.scan("com.erasmus.grades");
//
////        rootContext.setConfigLocations("WebAppContextConfig", "AppConfig");
//        rootContext.register(WebAppContextConfig.class);
//        rootContext.register(AppConfig.class);
//        rootContext.register(SecurityConfiguration.class);
//        // Manages the lifecycle of the root application context
//        servletContext.addListener(new ContextLoaderListener(rootContext));
//
//        // Declare dispatcher servlet. Handles requests into the application
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",
//                new DispatcherServlet(rootContext));
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/");
//
//
//    }
//
//}
