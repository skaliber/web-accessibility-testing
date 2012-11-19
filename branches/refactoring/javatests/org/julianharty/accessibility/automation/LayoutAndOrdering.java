/*
Copyright 2009 web-accessibility-testing committers

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

import junit.framework.TestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LayoutAndOrdering extends TestCase {

	private static final int MAX_TABS = 100;
	private static final Point tolerance = new Point(2,2);
	private LocalWebServer server;
	private int port;
	private ChromeDriver driver;

	@Override
	protected void setUp() {
		driver = new ChromeDriver();
		if (server == null) {
			server = new LocalWebServer();
			server.start(8088);
			port = server.getPort();
		}
	}
	
	@Override
	protected void tearDown() {
		server.stop();
	}
	
	public void testDetectOutOfOrderTabNavigation() throws InterruptedException {
		driver.get("http://localhost:" + port + "/notabindex.html");
		tabLayoutTest(driver);
	}
	
	public void testPassesForExplicitTabOrder() throws InterruptedException {
		driver.get("http://localhost:" + port + "/explicittabindex.html");
		tabLayoutTest(driver);
	}

	/**
	 * Tests how the reported location of an element varies as the font size grows.
	 * 
	 * In theory, I would have expected the offset to either remain constant,
	 * or to increase as the rendered font size increases. However, the 
	 * debug text from this test indicates the co-ordinates vary slightly, and
	 * may increase or decrease after the font size is changed.
	 */
	public void testOffsetIncreasesAfterControlPlusKeyStroke() {
		driver.get("http://localhost:" + port + "/explicittabindex.html");
		WebElement firstElement = driver.switchTo().activeElement();
		WebElement currentElement = firstElement;
		
		// Go to first tabbable element on page
		currentElement.sendKeys(Keys.TAB); 
		currentElement = driver.switchTo().activeElement();
		Point initialElementLocation = currentElement.getLocation();
		System.out.println("testOffsetIncreasesAfterControlPlusKeyStroke");
		int maxChanges = 5;
		
		// First increase the rendered font size
		Keys modifierKey = Keys.ADD;
		applyModifierKeyTo(currentElement, maxChanges, modifierKey);

		// Now decrease the rendered font size
		modifierKey = Keys.SUBTRACT;
		applyModifierKeyTo(currentElement, maxChanges, modifierKey);

	}

	/**
	 * A hack that applies a modifier key several times to a web browser.
	 * 
	 * The aim is to increase or decrease the rendered font size and determine
	 * how the reported location of a given web element varies.
	 * 
	 * TODO(jharty): enable an ordered set of keys to be passed in by the
	 * caller e.g. Keys.CONTROL, Keys.ADD
	 * 
	 * TODO(jharty): find a way to compare the locations for a set of points. 
	 * This may become more generalised to allow a caller to pass in either a
	 * generated set of points (e.g. a result of applying CTRL+ several times)
	 * or a pre-determined set of points e.g. for known locations of a set of
	 * elements on a web page.
	 * 
	 * TODO(jharty): hmmm, needs refactoring anyway as the comparison of points
	 * needs to reflect the direction of the change. Consider creating a 
	 * Direction enum with 2 values: INCREASE, DECREASE, which would obviate
	 * the need to pass in a set of keystrokes and make the method tightly
	 * focused.
	 * 
	 * @param currentElement the element that currently has focus.
	 * @param maxChanges the number of times the keystroke will be applied.
	 * @param modifierKey the key to apply, currently expected to be Keys.ADD
	 * or Keys.SUBTRACT
	 */
	private void applyModifierKeyTo(WebElement currentElement,
			int maxChanges, Keys modifierKey) {
		Point currentElementLocation = currentElement.getLocation();
		for (int i = 0; i < maxChanges; i++) {
			currentElement.sendKeys(Keys.CONTROL, modifierKey);
			Point newLocation = currentElement.getLocation();
			System.out.println(GeneralHelpers.printElementLocations(i, currentElementLocation, newLocation));
			
			if (GeneralHelpers.compareElementLocationsForSaneTabOrder(
					currentElementLocation, newLocation, tolerance)) {
				fail(GeneralHelpers.printElementLocations(i, currentElementLocation, newLocation));
			}
			currentElementLocation = newLocation;
		}
	}
	
	private void tabLayoutTest(WebDriver driver)
			throws InterruptedException {
		WebElement bodyElement = driver.findElement(By.tagName("body"));
		WebElement firstElement = driver.switchTo().activeElement();
		WebElement currentElement = firstElement;
		Point preTabLocation = currentElement.getLocation();
		int tabsIssued = 0;
		while (tabsIssued < MAX_TABS) {
			// currentElement.sendKeys(Keys.CONTROL, Keys.ADD);
			currentElement.sendKeys(Keys.TAB);  // "\t" also works
			tabsIssued++;
			Thread.sleep(50L);
			currentElement = driver.switchTo().activeElement();
			currentElement.sendKeys(Keys.TAB);
			currentElement = driver.switchTo().activeElement();

			Point postTabLocation = currentElement.getLocation();
			System.out.println(GeneralHelpers.printElementLocations(tabsIssued, preTabLocation, postTabLocation));
			
			if (GeneralHelpers.locationMatches(postTabLocation, preTabLocation)) {
				// log termination condition;
				// Tell the user to check native events are working
				throw new InterruptedException("We don't seem to have moved, abandoning this test.");
			}
			if (currentElement.equals(firstElement) 
					|| currentElement.getLocation().equals(firstElement.getLocation())) {
				// Declare victory :)
				System.out.println("!!!!!! Yay!!!");
				return;
			}
			if (GeneralHelpers.compareElementLocationsForSaneTabOrder(preTabLocation, postTabLocation, tolerance)) 
			{
				fail(GeneralHelpers.printElementLocations(tabsIssued, preTabLocation, postTabLocation));
			}
			preTabLocation = postTabLocation;
			
		}
	}
}
