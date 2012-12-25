/**
Copyright 2011 web-accessibility-testing committers

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package org.julianharty.accessibility.automation;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;

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
public class Example {

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

		WebDriver driver = new ChromeDriver();
		
		driver.get(urlToVisit);
		
		File screenshotFileBeforeTest = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		System.out.println(screenshotFileBeforeTest.getPath());
		
		try {
			int tabs = KeyboardHelpers.tabThroughWebPage(driver, maxTabsToEnter);
			System.out.println(tabs + " were issued.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (WebDriverException wde) {
			wde.printStackTrace();
		}
		
		File screenshotFileAfterTest = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		System.out.println(screenshotFileAfterTest.getPath());
	}
}
