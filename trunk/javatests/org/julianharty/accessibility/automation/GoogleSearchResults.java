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

/**
 * Simple example of tabbing through Google Search Results.
 */
package org.julianharty.accessibility.automation;

import org.openqa.selenium.firefox.FirefoxDriver;

import junit.framework.TestCase;

public class GoogleSearchResults extends TestCase {

	public void testTabbingThroughGoogleSearchResults() throws InterruptedException {
		FirefoxDriver driver = new FirefoxDriver();
		driver.get("http://www.google.com/search?q=cheese");
		int maxTabsToEnter = 300;
		int tabs = KeyboardHelpers.tabThroughWebPage(driver, maxTabsToEnter);
		assertTrue("Expected at least 50 tabs, only needed " + tabs, tabs > 50);
	}
}
