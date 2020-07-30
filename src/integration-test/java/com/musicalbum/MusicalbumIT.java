package com.musicalbum;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

public class MusicalbumIT {
	
	private Properties props = null;
	private String home = null;
	
	@Test 
	public void getHomePage_Succeed() throws FileNotFoundException, IOException {
		// To be implemented...
		System.out.println("*** Integration tests being run... ***");
		System.out.println("*** about to ping " + getHome());
	}
	
	//
	// implementation
	//
	
	private String getHome() throws FileNotFoundException, IOException {
		if (home == null) {
			home = getProperties().getProperty("app.url");
		}
		return home;
	}
	
	private Properties getProperties() throws FileNotFoundException, IOException {
		if (props == null) {
			props = new Properties();
			props.load(new FileInputStream("target/test-classes/com/musicalbum/application.properties"));
		}
		return props;
	}
}
