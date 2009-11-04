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
		try {
		while (tabsIssued < (maxTabsToEnter)) {
			// Probably want to print various attributes e.g. the link text
			LOG.info(
					String.format("%03d: Tag name %s WebElement %s name %s text %s value %s", 
							tabsIssued, currentElement.getTagName(), currentElement.hashCode(),
							currentElement.toString(), currentElement.getText(),
							currentElement.getValue()));

			String currentTagName = currentElement.getTagName();
			
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
			}
			if (!currentTagName.equals("body") && !currentTagName.equals("iframe")) {
				try {
			      ((JavascriptExecutor) driver).executeScript(
			    		  "arguments[0].style.background = \"orange\";", currentElement);
			      ((JavascriptExecutor) driver).executeScript(
			    		  "arguments[0].title = " + tabsIssued + ";", currentElement);
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
			 */
			if (doWebElementsMatch(driver, firstElement, currentElement)) {
				LOG.info(String.format(
						"Looped through elements OK, tabbed through %d elements", tabsIssued));
				LOG.info(String.format(
						"Tag name %s WebElement %s name %s text %s value %s",
						currentElement.getTagName(), currentElement.hashCode(),
						currentElement.toString(), currentElement.getText(),
						currentElement.getValue()));
				return tabsIssued;
			}
		}
		}
		catch (WebDriverException wde) {
			String innerHTML = (String) ((JavascriptExecutor) driver).executeScript(
		    		  "return arguments[0].innerHTML;", currentElement);
			LOG.warning(String.format("InnerHTML for problem element is %s", innerHTML));
			throw wde;
		}
		return -1;
	}


	/**
	 * Uses the JavaScript Executor capability to compare web elements.
	 * 
	 * TODO (jharty): Enforce the JavaScript Executor.
	 * 
	 * @param driver WebDriver instance (needs to support JavaScript Executor).
	 * @param a first webElement
	 * @param b second webElement
	 * @return true if JavaScript within the Web Browser / WebDriver matches.
	 */
	static boolean doWebElementsMatch(WebDriver driver, WebElement a, WebElement b) {
		Boolean result = (Boolean)((JavascriptExecutor)(driver))
			.executeScript("return arguments[0] == arguments[1]; ", a, b);
		return result;
		}
	/**
	 * Compares two nullable strings.
	 * 
	 * Class access only used for testing, otherwise this would be private.
	 * 
	 * @return true if they match or if both are null.
	 */
	 static boolean compareNullableStrings(String aString,
			String bString) {
		boolean valuesCompare;
		
		if (aString == null && bString == null) {
			valuesCompare = true;
		} else if (aString == null) {
			valuesCompare = false;
		} else {
			valuesCompare = aString.equals(bString);
		}
		return valuesCompare;
	}
	
	/*
	 * for (WebElement divs : driver.findElements("div")) {
	 *   Boolean res = (Boolean) driver.executeScript (return arguments[0].onclick != undefined", element);
	 *   }
	 */
}
