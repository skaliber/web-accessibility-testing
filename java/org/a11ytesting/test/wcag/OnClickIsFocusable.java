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
import static org.a11ytesting.test.wcag.Shared.HREF;
import static org.a11ytesting.test.wcag.Shared.TAB_INDEX;

import java.util.Arrays;
import java.util.List;

import org.jsoup.nodes.Element;

import org.a11ytesting.filter.ClickFilter;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.HtmlVersion;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;


/**
 * Rule for on click elements also being focusable.
 * 
 * @author dallison
 */
public class OnClickIsFocusable extends AbstractOperableRule {

	@Override
	public String getRuleName() {
		return "checkOnClickIsFocusable";
	}

	@Override
	public Filter getFilter() {
		return new ClickFilter();
	}

	/**
	 * Elements with on click should be focusable.
	 * @param document to test
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/61/
	 * 
	 * @return issues with document
	 */
	@Override
	public Issue check(HtmlVersion htmlVersion, Element element) {
		if (notFocusable(element)) {
			return new Issue("checkOnClickIsFocusable",
					"Check that when an element has onclick is is also " +
					"focusable", Severity.ERROR, element);
		}
		return null;
	}

	// TODO(jharty) In this focusable method links with either an href or a tab index
	// of 0 or more are focusable. Does your implementation cover those cases?
	private boolean notFocusable(Element element) {
		List<String> focusable = Arrays.asList(new String[]{
				"button", "input", "select", "textarea" 
		});
		if (focusable.contains(element.tagName())){
			return false;
		}
		if (ANCHOR.equals(element.tagName()) &&
				(element.hasAttr(HREF) ||
						(element.hasAttr(TAB_INDEX) && 
						0 <= Integer.parseInt(element.attr(TAB_INDEX))))) {
			return false;
		}
		return true;
	}
}