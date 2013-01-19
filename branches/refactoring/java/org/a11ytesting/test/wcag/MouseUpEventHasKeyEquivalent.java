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

import static org.a11ytesting.test.wcag.Shared.KEY_UP;

import org.jsoup.nodes.Element;

import org.a11ytesting.filter.MouseUpFilter;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.HtmlVersion;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;


/**
 * Rule for mouse up having a key equivalent.
 * 
 * @author dallison
 */
public class MouseUpEventHasKeyEquivalent extends AbstractOperableRule {

	@Override
	public String getRuleName() {
		return "checkMouseUpEventHasKeyEquivalent";
	}

	@Override
	public Filter getFilter() {
		return new MouseUpFilter();
	}

	/**
	 * On mouse up event should have a key equivalent.
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/63/
	 *
	 * @param element to check
	 * @return issue or null
	 */
	@Override
	public Issue check(HtmlVersion htmlVersion, Element element) {
		// check mouse up
		if (!element.hasAttr(KEY_UP)) {
			return new Issue("checkMouseUpHaveKeyEquivalent",
					"Check that mouse up has equivalent key event",
					Severity.ERROR, element);
		}
		// @todo(dallison) on mouse move and keypress
		return null;
	}
}