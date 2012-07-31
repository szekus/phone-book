package com.opensource.phonebook.server.rpc;

import javax.servlet.http.Cookie;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@Transactional
public class BaseRPC extends RemoteServiceServlet {

	final static long serialVersionUID = 1;

	final static public String APP_CTX_PATH = "/WEB-INF/applicationContext.xml"; // hack
	//final static public String APP_CTX_PATH = "webapps/phonebook/WEB-INF/applicationContext.xml"; //hack

	final public static ApplicationContext ctx;

	static {
		if (BaseRPC.class.getClassLoader().getResource("applicationContext.xml") != null) {
			ctx = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
			System.out.println("BaseRPC: ctx=ClassPathXmlApplicationContext");
		} else {
			ctx = new FileSystemXmlApplicationContext(APP_CTX_PATH);
			System.out.println("BaseRPC: ctx=FileSystemXmlApplicationContext");
		}
		if(ctx  == null) {
			System.out.println("BaseRPC: ctx is NULL");
		} else {
			System.out.println("BaseRPC: ctx is NOT null");
		}
	}

	public static int STATUS_FAILURE = -1;
	public static int STATUS_LOGIN_INCORRECT = -5;
	public static int STATUS_LOGIN_REQUIRED = -7;
	public static int STATUS_LOGIN_SUCCESS = -8;
	public static int STATUS_MAX_LOGIN_ATTEMPTS_EXCEEDED = -6;
	public static int STATUS_SERVER_TIMEOUT = -100;
	public static int STATUS_SUCCESS = 0;
	public static int STATUS_TRANSPORT_ERROR = -90;
	public static int STATUS_VALIDATION_ERROR = -4;
	
	public static String getCookieValue(Cookie[] cookies, String cookieName, String defaultValue) {
		for(int i=0; i<cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookieName.equals(cookie.getName())) {
				return(cookie.getValue());
			}
		}
		return(defaultValue);
	}

}
