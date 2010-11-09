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

import org.openqa.selenium.WebElement;

/**

 * Heuristic tests to indicate the existence of actionable page elements with a
 * CSS background image. CSS background images will be suppressed in Windows
 * High Contrast mode, rendering the click target invisible.
 */

public class HighContrast {

  /**
   * Tests whether or not a given {@code WebElement} is an actionable
   * background image by determining if it has a pointer cursor and also
   * is rendered using a CSS background image.
   *
   * @param elem candidate {@code WebElement} to be tested as a decorated link
   * @return {@code true} if the {@code WebElement} is determined to be a
   *         actionable background image, {@code false} otherwise
   */

  public static boolean isActionableBackgroundImage(WebElement elem) {

    if (Util.hasPointerCursor(elem) && Util.hasCSSBackground(elem))
      return true;
 
    return false;
  }
}