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

import java.awt.Point;

import junit.framework.TestCase;

import org.openqa.selenium.Keys;
import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LayoutAndOrdering extends TestCase {

	private static final int MAX_TABS = 100;

	public void testDetectOutOfOrderTabNavigation() throws InterruptedException {
		FirefoxDriver driver = new FirefoxDriver();
		driver.get("http://localhost/accessibility/notabindex.html");
		tabLayoutTest(driver);
	}
	
	public void testPassesForExplicitTabOrder() throws InterruptedException {
		FirefoxDriver driver = new FirefoxDriver();
		driver.get("http://localhost/accessibility/explicittabindex.html");
		tabLayoutTest(driver);
	}

	private void tabLayoutTest(FirefoxDriver driver)
			throws InterruptedException {
		Point tolerance = new Point(2,2);
		
		RenderedWebElement firstElement = (RenderedWebElement) driver.switchTo().activeElement();
		RenderedWebElement currentElement = (RenderedWebElement) firstElement;
		Point preTabLocation = currentElement.getLocation();
		int tabsIssued = 0;
		while (tabsIssued < MAX_TABS) {
			currentElement.sendKeys(Keys.TAB);  // "\t" also works
			tabsIssued++;
			Thread.sleep(50L);
			currentElement = (RenderedWebElement) driver.switchTo().activeElement();
			Point postTabLocation = currentElement.getLocation();
			System.out.println(GeneralHelpers.printElementLocations(tabsIssued, preTabLocation, postTabLocation));
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
