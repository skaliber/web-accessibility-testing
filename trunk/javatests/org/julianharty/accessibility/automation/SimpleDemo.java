/**
 * Copyright the committers of the web-accessibility-project
 * (c) 2011
 */
package org.julianharty.accessibility.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * This program takes command line parameters for quick interactive checks.
 * The first optional parameter is the URL to check - no validation is 
 * performed on the URL currently.
 * The second optional parameter is the maximum number of tabs to issue. The
 * default value is currently 50, enough for many of the simpler sites.
 * 
 * @author jharty
 *
 */
public class SimpleDemo {

	private static String urlToVisit;
	private static int maxTabsToEnter;

	/**
	 * Simple program to allow quick assessments of web pages.
	 * @param args Command Line parameters.
	 */
	public static void main(String[] args) {
		if (args.length >= 2) {
			maxTabsToEnter = Integer.parseInt(args[1]);
		} else {
			maxTabsToEnter = 50;
		}
		
		if (args.length >= 1) {
			urlToVisit = args[0];
		} else {
			urlToVisit = "http://www.google.com";
		}

		WebDriver driver = new FirefoxDriver();
		driver.get(urlToVisit);
		try {
			KeyboardHelpers.tabThroughWebPage(driver, maxTabsToEnter);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
