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

import org.mortbay.log.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import junit.framework.TestCase;

/**
 * Uses some test pages stored on my local machine until I find a better way of
 * embedding pages with the tests.
 * 
 * Typically the pages will come from various sources e.g.
 * - Known problems which need to be solved
 * - Externally provided pages e.g. from webkit which help us to develop and
 * test our code.
 * 
 * @author jharty
 *
 */
public class LocalAccessibilityTestPages extends TestCase {

	private static final int MAX_TABS_TO_EXPECT = 30;
	private WebDriver driver;
	private LocalWebServer server;
	private int port;
	private static final int EXPECTED_MIN_TAB_COUNT = 3;

	@Override
	protected void setUp() {
		driver = new InternetExplorerDriver();
		
		server = new LocalWebServer();
		server.start(8080);
		port = server.getPort();
	}
	
	@Override
	protected void tearDown() {
		// driver.close();
		server.stop();
	}
	public void testGwtHistoryIframeIncludedWhenTabbingAndNoTabindexSet() throws InterruptedException {
		driver.get("http://localhost:" + port + "/gwthistoryframe.html");
		int tabs = KeyboardHelpers.tabThroughWebPage(driver, MAX_TABS_TO_EXPECT);
		assertTrue("Expected at least " + EXPECTED_MIN_TAB_COUNT + ", found " + tabs, 
				tabs >= EXPECTED_MIN_TAB_COUNT);
	}
	
	public void testGwtHistoryIframeWithTabindexIsSkippedWhenTabbing() throws InterruptedException {
		driver.get("http://localhost:" + port + "/iframetabindex.html");
		int tabs = KeyboardHelpers.tabThroughWebPage(driver, MAX_TABS_TO_EXPECT);
		assertTrue("Expected at least " + EXPECTED_MIN_TAB_COUNT + ", found " + tabs, 
				tabs >= EXPECTED_MIN_TAB_COUNT);
	}

	public void testCanStartLocalWebserver() {
		driver.get("http://localhost:" + port + "/gwthistoryframe.html");
		Log.info("Connected to: " + driver.getCurrentUrl());
	}
}
