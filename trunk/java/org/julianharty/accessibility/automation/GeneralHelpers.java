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

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * general helpers for the rest of the classes.
 * 
 * TODO(jharty): consider using an enum instead of a class as this only
 * contains static methods.
 * 
 * @author jharty
 */
public class GeneralHelpers {

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
	public static boolean doWebElementsMatch(WebDriver driver, WebElement a, WebElement b) {
		Boolean result = (Boolean)((JavascriptExecutor)(driver))
			.executeScript("return arguments[0] == arguments[1]; ", a, b);
		return result;
		}
	
	/**
	 * Compares 2 locations of WebElements.
	 * @param currentLocation the location currently selected
	 * @param previousLocation the previous location
	 * @return true if they are equal, else false.
	 */
	public static boolean locationMatches(Point currentLocation,
			Point previousLocation) {
		// TODO 20110906 (jharty): consider how to use a general comparator approach.
		if ((currentLocation.x == previousLocation.x) && 
				(currentLocation.y == previousLocation.y)) {
			return true;
		}
		return false;
	}
	
	public static boolean compareElementLocationsForSaneTabOrder(
			Point preTabLocation, Point postTabLocation, Point tolerance) {
		return (postTabLocation.x < (preTabLocation.x - tolerance.x) || 
				((postTabLocation.y <= (preTabLocation.y - tolerance.y) && 
						postTabLocation.x < (preTabLocation.x - tolerance.x)))); 
	}

	public static String printElementLocations(int tabsIssued, Point currentLocation,
			Point nextLocation) {
		return String.format(
				"Tab [%d] previous co-ordinates[%d,%d]," +
				"current co-ordinates[%d,%d]", tabsIssued,
				currentLocation.x, currentLocation.y, nextLocation.x, nextLocation.y);
	}
	
	/**
	 * Compares two nullable strings.
	 * 
	 * Class access only used for testing, otherwise this would be private.
	 * 
	 * @return true if they match or if both are null.
	 */
	 public static boolean compareNullableStrings(String aString,
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

	 /**
	  * compares the size of the dimensions of 2 element sizes 
	  * @param currentSize
	  * @param previousSize
	  * @return true if they are equal, else false
	  */
  public static boolean dimensionsAreEqual(Dimension currentSize, Dimension previousSize) {
    if ((currentSize.height == previousSize.height) && (currentSize.width == previousSize.width)) {
      return true;
    }
    return false;
  }
}
