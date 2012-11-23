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

import static org.a11ytesting.test.wcag.Shared.FOCUS;

import org.jsoup.nodes.Element;

import org.a11ytesting.filter.MouseOverFilter;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.HtmlVersion;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;

public class OnMouseoverAndOnFocus extends AbstractOperableRule {

	@Override
	public String getRuleName() {
		return "checkOnMouseoverAndOnFocus";
	}

	@Override
	public Filter getFilter() {
		return new MouseOverFilter();
	}

	/**
	 * Element with mouse over also has on focus attribute.
	 * @param element to check
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/59/
	 * 
	 * @return issue or null
	 */
	@Override
	public Issue check(HtmlVersion htmlVersion, Element element) {
		if (!element.hasAttr(FOCUS)) {
			return new Issue("checkOnMouseoverAndOnFocus", 
					"Check that when an element has mouse over it also has " +
					"on focus", Severity.ERROR, element);
		}
		return null;
	}
}