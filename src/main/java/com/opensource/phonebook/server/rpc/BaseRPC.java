package com.opensource.phonebook.server.rpc;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
@Transactional
public class BaseRPC extends RemoteServiceServlet {

	   @Override
	    public void init(ServletConfig config) throws ServletException {
	        super.init(config);
	        WebApplicationContextUtils.
	                getRequiredWebApplicationContext(getServletContext()).
	                getAutowireCapableBeanFactory().
	                autowireBean(this);
	    }

	
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
