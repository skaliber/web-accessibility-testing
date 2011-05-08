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

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**
 * Keyboard handler helper functions e.g. to tab through a web page.
 * 
 * The helper functions are intended to assist with automated testing of
 * keyboard navigation e.g. for usability and accessibility.
 */
public class KeyboardHelpers {
	private static final Logger LOG = Logger.getLogger(KeyboardHelpers.class.getCanonicalName());
	private static final String TAB_KEYWORD = "Tab:";
	
	private KeyboardHelpers() {
		// Helper class. Stop callers from instantiating us.
	}

	/**
	 * Uses the tab key to navigate around a web page.
	 *
	 * Helps determine which elements are reachable with the tab key and
	 * whether the first active element can be reached again (indicating we
	 * can 'loop' around the tabable elements on the page.
	 *
	 * Any iFrames are visited recursively.
	 * 
	 * A sample call follows:
	 * <code><br/>
	  	public void testTabbingThroughGoogleSearchResults() throws InterruptedException { <br/>
		FirefoxDriver driver = new FirefoxDriver(); <br/>
		driver.get("http://www.google.com/search?q=cheese"); <br/>
		int maxTabsToEnter = 300; <br/>
		int tabs = KeyboardHelpers.tabThroughWebPage(driver, maxTabsToEnter);<br/>
		assertTrue("Expected at least 50 tabs, only needed " + tabs, tabs > 50);<br/>
	 } </code>
	 *
	 * @param driver a WebDriver connection with the target page loaded.
	 * @param maxTabsToEnter the maximum tabs we should need to visit all the
	 * tabable elements. Consider providing a 2x or 3x the expected number,
	 * particularly for complex, unknown content.  
	 * @return the number of tabs issued if the method reached an element that
	 * matched the first active element, else -1 if it failed to complete
	 * within the specified number of tabs. 
	 * @throws InterruptedException if the call to sleep fails. 
	 */
	public static int tabThroughWebPage(WebDriver driver, int maxTabsToEnter)
		throws InterruptedException {
		
		WebElement firstElement = driver.switchTo().activeElement();
		WebElement currentElement = firstElement;
		
		int tabsIssued = 0;
		int iFrame = 0;
		String currentTagName = "(not set)";
		try {
		while (tabsIssued < (maxTabsToEnter)) {
			// Probably want to print various attributes e.g. the link text
			LOG.info(
					String.format("%03d: Tag name %s WebElement %s name %s text %s value %s", 
							tabsIssued, currentElement.getTagName(), currentElement.hashCode(),
							currentElement.toString(), currentElement.getText(),
							getValueFromWebdriverIfAvailable(currentElement)));

			currentTagName  = currentElement.getTagName();
			
			/*
			 * for iframes switch to the iframe and call ourself recursively. We
			 * think it always starts from _top of the iframe. That call will
			 * tab until it 'falls out' of the active elements in the iframe.
			 * Body will then be returned, then we need to switch back to the
			 * parent.
			 * 
			 * Note: use periods to separate nested frames.
			 */

			if (currentTagName.equals("iframe")) {
				driver.switchTo().frame(iFrame++);
				// A simple calculation to limit tabs in a 'broken' iFrame for now.
				tabThroughWebPage(driver, maxTabsToEnter - tabsIssued);
				// Will the following skip over the 'body' of the iFrame?
				driver.switchTo().defaultContent();
				currentElement = driver.switchTo().activeElement();
			}
			
			String currentTitle = getTitleOfCurrentElement(driver, currentElement);

			// Here is one of the termination conditions, if we find one of our titles.
			if (currentTitle.contains(TAB_KEYWORD)) {
				logValidTerminationCondition("Title of element matches the value set", currentElement, tabsIssued);
				return tabsIssued;
			}
			
			if (!currentTagName.equals("body") && !currentTagName.equals("iframe")) {
				try {
			      ((JavascriptExecutor) driver).executeScript(
			    		  "arguments[0].style.background = \"orange\";", currentElement);
			      ((JavascriptExecutor) driver).executeScript(
			    		  "arguments[0].title = \"" + TAB_KEYWORD + tabsIssued + "\";", currentElement);
				} catch (WebDriverException wde) {
				  LOG.log(Level.WARNING,
				      String.format("Tried to set the background for %s", currentTagName), wde);	
				}
			}

			currentElement.sendKeys(Keys.TAB);  // "\t" also works
			tabsIssued++;
			Thread.sleep(50L);
			currentElement = driver.switchTo().activeElement();
			
			/* Loop termination is still imprecise. Typically the first element
			 * is the body element, however in some GWT applications it's a div
			 * element for reasons I don't yet fathom. 
			 * TODO(jharty): improve the precision and reliability of the 
			 * matching as we learn more about how to interact with elements
			 * on the page. 
			 * 
			 * TODO(jharty): replace custom method with
			 *     currentElement.equals(firstElement) 
			 * once I've had chance to test online (I'm in VS019 currently twixt LHR and SFO)
			 */
			if (firstElement.equals(currentElement) && tabsIssued >= 3) {
				logValidTerminationCondition("Current element matches first element", currentElement, tabsIssued);
				return tabsIssued;
			}
		}
		}
		catch (WebDriverException wde) {
			String innerHTML = (String) ((JavascriptExecutor) driver).executeScript(
		    		  "return arguments[0].innerHTML;", currentElement);
			LOG.warning(String.format("Current Tag %s, InnerHTML for problem element is %s", currentTagName, innerHTML));
			throw wde;
		}
		return -1;
	}

	/**
	 * A simple helper method to log the termination condition.
	 * 
	 * The main reason this has been created is to reduce duplication in the
	 * main method. 
	 * TODO (jharty): This code is ugly and probably worth reassessing at some
	 * point.
	 * @param whyTerminated a text-based description of which valid termination
	 * was detected. Useful while we're trying to improve the loop detection.
	 * @param currentElement the current element on the web page
	 * @param tabsIssued the number of tab keys issued so far
	 */
	private static void logValidTerminationCondition(String whyTerminated, WebElement currentElement,
			int tabsIssued) {
		LOG.info(String.format(
				"Looped through elements OK, terminated because [%s], tabbed through %d elements", 
				whyTerminated, tabsIssued));
		LOG.info(String.format(
				"Tag name %s WebElement %s name %s text %s value %s",
				currentElement.getTagName(), currentElement.hashCode(),
				currentElement.toString(), currentElement.getText(),
				getValueFromWebdriverIfAvailable(currentElement)));
	}

	/**
	 * Returns the title of the current Web Element.
	 * 
	 * At the moment it makes no distinction between web elements. We may want
	 * to limit the query to elements that have a 'title'. 
	 */
	private static String getTitleOfCurrentElement(WebDriver driver,
			WebElement currentElement) {
		String currentTitle = (String) ((JavascriptExecutor) driver)
			.executeScript("return arguments[0].title;", currentElement);
		return currentTitle;
	}

	/**
	 * Gets the value of a webdriver element if it's available.
	 * 
	 * Current versions of Webdriver throw an exception if the value isn't 
	 * available, older versions did not. This helper method catches the 
	 * exception and returns (unavailable) as a string. 
	 * @param currentElement a webdriver element.
	 */
	private static String getValueFromWebdriverIfAvailable(
			WebElement currentElement) {
		String valueOfCurrentElement;
		try {
			valueOfCurrentElement = currentElement.getValue();
		} catch (UnsupportedOperationException uoe) {
			valueOfCurrentElement = "(not available)";
		}
		return valueOfCurrentElement;
	}
	
	/*
	 * for (WebElement divs : driver.findElements("div")) {
	 *   Boolean res = (Boolean) driver.executeScript (return arguments[0].onclick != undefined", element);
	 *   }
	 */
}
