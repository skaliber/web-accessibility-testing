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

import static org.a11ytesting.test.wcag.Shared.ANCHOR;
import static org.a11ytesting.test.wcag.Shared.BUTTON;
import static org.a11ytesting.test.wcag.Shared.INPUT;
import static org.a11ytesting.test.wcag.Shared.ROLE;
import static org.a11ytesting.test.wcag.Shared.SELECT;

import java.util.Arrays;
import java.util.List;

import org.a11ytesting.aria.Role;
import org.a11ytesting.filter.EventFilter;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.HtmlVersion;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;
import org.jsoup.nodes.Element;

/**
 * Rule for non interactive elements with an even also has an Aria role.
 * 
 * @author dallison
 */
public class NonInteractiveElementWithEventHasRole
		extends AbstractOperableRule {

	@Override
	public String getRuleName() {
		return "checkNonInteractiveElementWithEventHasRole";
	}

	@Override
	public Filter getFilter() {
		return new EventFilter();
	}

	/**
	 * Elements other than link and form with event handlers also have valid roles.
	 * @param document
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/94/
	 * 
	 * @return issues identified with the document
	 */
	@Override
	public Issue check(HtmlVersion htmlVersion, Element element) {
		List<String> interactive = Arrays.asList(new String[] {
				ANCHOR, BUTTON, INPUT, SELECT});
		if (!interactive.contains(element.tagName()) && !hasAriaRole(element)) {
			return new Issue ("checkNonInteractiveElementsWithEventHasRole",
						"Check that non interactive elements have an aria " +
						"role if they use events", Severity.ERROR, element);
		}
		return null;
	}
	
	private boolean hasAriaRole(Element element) {
		if (!element.hasAttr(ROLE)) {
			return false;
		}
		// search for role in
		for (Role role : Role.values()) {
			if (element.attr(ROLE).contains(role.toString())) {
				return true;
			}
		}
		return false;
	}
}