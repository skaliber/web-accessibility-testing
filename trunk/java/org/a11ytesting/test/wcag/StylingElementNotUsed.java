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

import org.a11ytesting.filter.StyleElementFilter;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;
import org.jsoup.nodes.Element;

public class StylingElementNotUsed extends AbstractRobustRule {

	@Override
	public String getRuleName() {
		return "checkStylingElementNotUsed";
	}

	@Override
	public Filter getFilter() {
		return new StyleElementFilter();
	}

	/**
	 * Check for style elements in the document
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/67/
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/69/
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/70/
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/71/
	 * 
	 * @param style element
	 * @return issue or null
	 * 
	 * @todo(dallison) Check the implementation for 69-71 to understand
	 * 		the purpose of the same type iteration.
	 */
	@Override
	public Issue check(Element style) {
		return new Issue("checkStylingElementNotUsed",
				"Check that style elements are not used",
				Severity.ERROR, style);
	}

}
