/*
Copyright 2010 web-accessibility-testing committers

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
import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.WebElement;

/**
 * Utility class, containing commonly used helper functions.
 */

public class Util {

  private Util() {
    // Utility class, should not be instantiated.
  }

  /**
   * Determines if the given {@code WebElement} has a computed CSS
   * {@code cursor} attribute equal to {@code pointer}.
   *
   * @param elem candidate {@code WebElement} to be tested as a decorated link
   * @return {@code true} if the {@code WebElement} is determined to have a
   *         {@code cursor} attribute equal to {@code pointer}, {@code false}
   *         otherwise
   */

  public static boolean hasPointerCursor(WebElement elem) {
    String cursor = ((RenderedWebElement) elem).getValueOfCssProperty("cursor");
    return cursor.equals("pointer");
  }

  /**
   * Determines if the given {@code WebElement} has a CSS background image.
   *
   * @param elem candidate {@code WebElement} to be tested as a decorated link
   * @return {@code true} if the {@code WebElement} is determined to have a CSS
   *         background image, {@code false} otherwise
   */
  public static boolean hasCSSBackground(WebElement elem) {
    String cssBackground =
        ((RenderedWebElement) elem).getValueOfCssProperty("background-image");
    return !cssBackground.equals("none");
  }

 
  /**

   * Determines if the given {@code WebElement} is displayed on the web page
   * (and also on screen).
   *
   * @param elem candidate {@code WebElement} to be tested as a decorated link
   * @return {@code true} if the {@code WebElement} is displayed and has
   *         non-negative x and y location coordinates, {@code false} otherwise
   */

  public static boolean isDisplayedOnWebPage(WebElement elem) {
    // Get element's location, to determine if it's located outside the
    // viewport.
    Point p = ((RenderedWebElement) elem).getLocation(); 

    if (((RenderedWebElement) elem).isDisplayed() && (p.x >= 0 || p.y >= 0))
      return true;
 
    return false;
  }
}