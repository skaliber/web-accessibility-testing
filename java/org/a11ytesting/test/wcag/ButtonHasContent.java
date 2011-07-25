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

import org.a11ytesting.test.Filter;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;
import org.jsoup.nodes.Element;

public class ButtonHasContent extends AbstractUnderstandableRule {

	@Override
	public String getRuleName() {
		return "checkButtonHasContent";
	}

	@Override
	public Filter getFilter() {
		return Filter.BUTTON;
	}

	/**
	 * Check that button elements have content
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/78/
	 * 
	 * @param button to check
	 * @return issue or null
	 * @todo(dallison) Consider implementing a button filter
	 */
	@Override
	public Issue check(Element button) {
		if ((button.hasText() && !button.text().trim().isEmpty())
				| !button.children().isEmpty()) {
			return null;
		}
		return new Issue("checkButtonHasContent",
				"Check that button elements have content",
				Severity.ERROR, button);
	}

}
