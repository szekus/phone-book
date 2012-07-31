package com.opensource.phonebook.core;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * This class provides an application-wide access to the
 * Spring ApplicationContext! The ApplicationContext is
 * injected in a static method of the class "AppContext".
 *
 * Use AppContext.getApplicationContext() to get access
 * to all Spring Beans.
 */ 

public class ApplicationContextProvider implements ApplicationContextAware { 
	private static final Logger log = Logger.getLogger(ApplicationContextProvider.class);
    public void setApplicationContext(ApplicationContext ctx) throws BeansException { 
        // Wiring the ApplicationContext into a static method 
    	log.debug("Setting Application Context for Restlet Web Service.");
        AppContext.setApplicationContext(ctx); 
    } 
}