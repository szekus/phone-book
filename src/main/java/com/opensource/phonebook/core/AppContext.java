package com.opensource.phonebook.core;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

/**
 * This class provides application-wide access to the Spring ApplicationContext.
 * The ApplicationContext is injected by the class "ApplicationContextProvider".
 */ 
public class AppContext { 

	private static final Logger log = Logger.getLogger(AppContext.class);
    private static ApplicationContext ctx; 
    
    /**
     * Injected from the class "ApplicationContextProvider" which is automatically
     * loaded during Spring-Initialization.
     */ 
    public static void setApplicationContext(ApplicationContext applicationContext) { 
    	log.debug("Set Application Context");
        ctx = applicationContext; 
    } 

    /**
     * Get access to the Spring ApplicationContext from everywhere in your Application.
     * @return
     */ 
    public static ApplicationContext getApplicationContext() { 
    	log.debug("Get Application Context");
        return ctx; 
    } 
}