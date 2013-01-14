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

import static org.a11ytesting.test.wcag.Shared.KEY_DOWN;
import static org.a11ytesting.test.wcag.Shared.KEY_PRESS;
import static org.a11ytesting.test.wcag.Shared.KEY_UP;

import org.jsoup.nodes.Element;

import org.a11ytesting.filter.MouseEventFilter;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.HtmlVersion;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;


/**
 * Rule for mouse event having keyboard equivalent.
 * 
 * @author dallison
 */
public class MouseEventHasKeyboardEvent extends AbstractOperableRule {

	@Override
	public String getRuleName() {
		return "checkMouseEventHasKeyboardEvent";
	}

	@Override
	public Filter getFilter() {
		return new MouseEventFilter();
	}

	/**
	 * Elements that have mouse events should have keyboard event handlers.
	 * @param element to check
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/95/
	 * 
	 * @return issue or null.
	 */
	@Override
	public Issue check(HtmlVersion htmlVersion, Element element) {
		String[] keyboardEvents = new String[]{KEY_DOWN, KEY_PRESS, KEY_UP};
		for (String event : keyboardEvents) {
			if (element.hasAttr(event)) {
				return null;
			}
		}
		return new Issue("checkMouseEventHaveKeyboardEvent",
				"Check that elements that have mouse actions also have " +
				"keyboard actions.", Severity.ERROR, element);
	}
}