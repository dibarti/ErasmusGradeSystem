package com.erasmus.grades.config;

import com.erasmus.grades.security.SecurityConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringApplicationInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {WebAppContextConfig.class, SecurityConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {AppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}