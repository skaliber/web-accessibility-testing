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

import static org.a11ytesting.test.wcag.Shared.TITLE;
import static org.a11ytesting.test.wcag.Shared.VALUE;

import org.jsoup.nodes.Element;

import org.a11ytesting.filter.FormControlFilter;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;

/**
 * Rule for form control has description also.
 * 
 * @author dallison
 */
public class FormControlHasDescription extends AbstractUnderstandableRule {

	@Override
	public String getRuleName() {
		return "checkFormControlHasDescription";
	}

	@Override
	public Filter getFilter() {
		return new FormControlFilter();
	}

	/**
	 * Check that form controls have either a value or title.
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/77/
	 * 
	 * @param input to check
	 * @return issue or null
	 */
	@Override
	public Issue check(Element input) {
		if (input.hasAttr(TITLE) || input.hasAttr(VALUE)) {
			return null;
		}
		if (!input.text().trim().isEmpty()) {
			return null;
		}
		return new Issue("checkFormControlHasDescription",
				"Check that form controls have a title or a value",
				Severity.ERROR, input);
	}
}