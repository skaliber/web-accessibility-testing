/* Copyright 2011 Ebay Inc.
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

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Shared;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.testng.annotations.Test;

/** 
 * Shared methods and selectors AND tests for Shared.java
 * 
 * TODO(jharty): Extract the shared methods into a utility class.
 */
public class SharedTest {
	static final String ANCHOR = "a",
	BLINK = "blink",
	BODY = "body",
	BUTTON = "button",
	FIELDSET = "fieldset",
	HTML = "html",
	IFRAME = "iframe",
	IMG = "img",
	INPUT = "input",
	LABEL = "label",
	LEGEND = "legend",
	MARQEE = "marqee",
	SELECT = "select",
	TABLE = "table",
	TEXTAREA = "textarea",
	TITLE = "title";
	
	private static final String HREF_WITH_TABINDEX = "<html><body><a href=\"#someplace\" tabindex=\"%s\"></a></body></html>";

	/**
	 * From html construct a document and select an element of a given type
	 * based on a selector.
	 * 
	 * @param html to build
	 * @param selector to use
	 * @return the selected element (first match)
	 */
	static Element selectElement(String html, String selector) {
		Document document = Jsoup.parse(html);
		Elements selected = document.select(selector);
		return selected.first();
	}
	
	static void testError(Issue result, Element target, Issue.Severity severity,
			String message) {
		assertNotNull(result, message);
		assertEquals(result.getElement(), target);
		assertEquals(result.getSeverity(), severity);
	}
	
	@Test
	public void testNegativeTabIndexIsNotFocusable() {
		String html = String.format(HREF_WITH_TABINDEX, -1);
		Element target = selectElement(html, ANCHOR);
		assertTrue(Shared.notFocusable(target));
	}
	
	@Test
	public void testZeoTabIndexIsFocusable() {
		String html = String.format(HREF_WITH_TABINDEX, 0);
		Element target = selectElement(html, ANCHOR);
		assertFalse(Shared.notFocusable(target));
	}
}
