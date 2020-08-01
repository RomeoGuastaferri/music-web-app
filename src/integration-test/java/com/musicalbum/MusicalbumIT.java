package com.musicalbum;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

public class MusicalbumIT {
	
	private Properties props = null;
	private String home = null;
	
	//
	// tests
	//
	
	@Test 
	public void getHomePage_Succeed() throws FileNotFoundException, IOException {
		// To be implemented...
		System.out.println("*** Integration tests being run... ***");
		System.out.println("*** received parameters: browser=" + System.getenv("browser") + ", os=" + System.getenv("os"));
		System.out.println("*** about to ping " + getHome());

		System.out.println("*** about to ping " + getHome());
	}
	
	//
	// getters
	//
	
	private String getHome() throws FileNotFoundException, IOException {
		if (home == null) {
			home = getProperties().getProperty("app.url");
		}
		return home;
	}
	
	private Properties getProperties() throws FileNotFoundException, IOException {
		if (props == null) {
			// read in properties from properties file
			try(InputStream stream = getClass().getResourceAsStream("test.properties")) {
				props = new Properties();
				props.load(stream);
			}
		}
		return props;
	}
}
