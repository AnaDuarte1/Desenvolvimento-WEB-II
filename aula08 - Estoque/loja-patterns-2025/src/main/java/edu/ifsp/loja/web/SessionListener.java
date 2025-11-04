package edu.ifsp.loja.web;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {
	
	private String getZuluTime() {
		String zuluTime = DateTimeFormatter.ISO_INSTANT.format(Instant.now());
		return zuluTime;
	}

    public void sessionCreated(HttpSessionEvent se)  {
    	System.out.println("[session created] " +  getZuluTime());    	
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    	System.out.println("[session destroyed] " + getZuluTime() + " user: " + se.getSession().getAttribute("user"));
    }
	
    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
    	String name = se.getName();
    	if ("user".equals(name)) {
    		System.out.println("[user logged in] " + getZuluTime() + " " + name + ": " + se.getValue());
    	}
    }
}
