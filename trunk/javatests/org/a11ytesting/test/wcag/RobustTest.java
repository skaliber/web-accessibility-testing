/* Copyright 2011 eBay Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 *    
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.a11ytesting.test.wcag;

import static org.a11ytesting.test.wcag.SharedTest.*;

import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;
import org.jsoup.nodes.Element;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Tests for the WCAG robust aspect
 * 
 * @author dallison
 */
public class RobustTest {

	private static final String SELECTOR = "font, b, i, u";

	@DataProvider(name = "stylingElement")
	Object[][] stylingElement() {
		return new Object[][]{
				{"<html><body><font>Font</font></body></html>"},
				{"<html><body><b>Bold</b></body></html>"},
				{"<html><body><i>Italic</i></body></html>"},
				{"<html><body><u>Underline</u></body></html>"}};
	}
	
	// @note: ignoring positive case because always returns error and relies
	// on the filter to pass the correct element types.
	@Test(dataProvider = "stylingElement")
	public void testStylingElementNotUsedError(String html) {
		Element target = selectElement(html, SELECTOR);
		StylingElementNotUsed robust = new StylingElementNotUsed();
		Issue result = robust.check(target);
		testError(result, target, Severity.ERROR,
				"Expected error for styling element");
	}
}