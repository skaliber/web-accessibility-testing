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

import static org.a11ytesting.test.Shared.ANCHOR;
import static org.a11ytesting.test.Shared.BUTTON;
import static org.a11ytesting.test.Shared.INPUT;
import static org.a11ytesting.test.Shared.ROLE;
import static org.a11ytesting.test.Shared.SELECT;

import java.util.Arrays;
import java.util.List;

import org.jsoup.nodes.Element;

import org.a11ytesting.aria.Role;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;


public class NonInteractiveElementWithEventHasRole
		extends AbstractOperableRule {

	@Override
	public String getRuleName() {
		return "checkNonInteractiveElementWithEventHasRole";
	}

	@Override
	public Filter getFilter() {
		return Filter.EVENT;
	}

	/**
	 * Elements other than link and form with event handlers also have valid roles.
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/94/
	 * 
	 * @param document
	 * @return issues identified with the document
	 */
	@Override
	public Issue check(Element element) {
		// TODO Auto-generated method stub
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
