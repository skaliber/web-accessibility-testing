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

import static org.a11ytesting.test.wcag.Shared.ID;
import static org.a11ytesting.test.wcag.Shared.getRootElement;

import org.jsoup.nodes.Element;

import org.a11ytesting.filter.ElementFilter;
import org.a11ytesting.filter.InputControlFilter;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.HtmlVersion;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;

/**
 * Rule for Control id uniqueness.
 * 
 * @author dallison
 */
public class ControlIdUnique extends AbstractUnderstandableRule {

	@Override
	public String getRuleName() {
		return "checkControlIdUnique";
	}

	@Override
	public Filter getFilter() {
		return new InputControlFilter();
	}

	/**
 	 * Check that form controls have unique IDs
	 * @param control to check
 	 * 
 	 *  @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/83/
 	 *  
	 * @return issue or null
	 */
	@Override
	public Issue check(HtmlVersion htmlVersion, Element control) {
		if (!control.hasAttr(ID)) {
			return null;
		}
		Element root = getRootElement(control);
		ElementFilter filter = new InputControlFilter();
		for (Element otherControl : filter.result(root)) {
			// skip self
			if (control.equals(otherControl)) {
				continue;
			}
			if (otherControl.hasAttr(ID) &&
					control.attr(ID).equals(otherControl.attr(ID))) {
				return new Issue("checkControlIdUnique",
						"Check that input controls have unique id",
						Severity.ERROR, control);
			}
		}
		return null;
	}
}