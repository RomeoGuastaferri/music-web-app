package com.musicalbum;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


public class MusicalbumIT extends SeleniumITSuite {
	
	private WebDriver web = null;
	
	@Before
	public void setUp() throws MalformedURLException, FileNotFoundException, IOException {
		web = createWebDriver();
	}
	
	@After
	public void tearDown() {
		web.close();
	}
	
	//
	// tests
	//
	
	@Test 
	public void getHomePage_Succeed() throws FileNotFoundException, IOException {
		// To be implemented...
		System.out.println("*** Integration tests being run... ***");
		web.navigate().to(getAppURL());
		System.out.println("*** Just successfully navigated to page " + web.getTitle());
	}
}
