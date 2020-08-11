package com.musicalbum;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract class SeleniumITSuite {

	/** properties of this test suite to be read from file */
	private Properties props = null;
	
	/** URL of app under test */
	private URL appURL = null;
	
	/** URL of Selenium hub */
	private URL hubURL = null;
	
	/** capabilities required of selenium node to run test */
	private DesiredCapabilities capabilities = null;
	
	protected WebDriver createWebDriver() throws MalformedURLException, FileNotFoundException, IOException {
		return new RemoteWebDriver(getHubURL(), getDesiredCapabilities());
	}
	
	//
	// getters
	//
	
	protected URL getAppURL() throws FileNotFoundException, IOException {
		if (appURL == null) {
			appURL = new URL(getProperties().getProperty("app.url"));
			System.out.println("*** app.url=" + appURL.toString());
		}
		return appURL;
	}

	protected URL getHubURL() throws FileNotFoundException, IOException {
		if (hubURL == null) {
			hubURL = new URL(getProperties().getProperty("hub.url"));
			System.out.println("*** hub.url=" + hubURL.toString());
		}
		return hubURL;
	}

	protected Properties getProperties() throws FileNotFoundException, IOException {
		if (props == null) {
			props = new Properties();

			// read in properties from properties file
			try(InputStream stream = SeleniumITSuite.class.getResourceAsStream("test.properties")) {
				props.load(stream);
			}
		}
		return props;
	}
	
	//
	// desired capabilities
	//
	
	protected DesiredCapabilities getDesiredCapabilities() {
		if (capabilities == null) {
			capabilities = createDesiredCapabilities();
		}
		return capabilities;
	}
	
	protected DesiredCapabilities createDesiredCapabilities() {
		// object to create & return
		DesiredCapabilities result = null;
		
		// set browser
		switch(getBrowser()) {
			case "firefox" :
				result = DesiredCapabilities.firefox();
				break;
			case "ie" :
				result = DesiredCapabilities.internetExplorer();
				break;
			case "chrome" :
				result = DesiredCapabilities.chrome();
				break;
			case "safari" :
				result = DesiredCapabilities.safari();
				break;
			default :
				result = new DesiredCapabilities();
		}
		result.setBrowserName(getBrowser());
		
		// set Operating System
		result.setPlatform(getPlatform());
		
		System.out.println("*** os=" + result.getPlatform().toString());
		System.out.println("*** browser=" + result.getBrowserName());
		return result;
	}
	
	protected static Platform getPlatform() {
		switch(getOS()) {
			case "linux":
				return Platform.LINUX;
			case "win":
				return Platform.WINDOWS;
			case "mac":
				return Platform.MAC;
			default :
				return Platform.ANY;
		}
	}
	
	protected static String getBrowser() {
		return System.getenv("browser").toLowerCase();
	}
	
	protected static String getOS() {
		return System.getenv("os").toLowerCase();
	}
}
