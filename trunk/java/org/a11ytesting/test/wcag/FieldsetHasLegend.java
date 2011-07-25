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

import static org.a11ytesting.test.Shared.LEGEND;

import org.a11ytesting.test.Filter;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FieldsetHasLegend extends AbstractUnderstandableRule {

	@Override
	public String getRuleName() {
		return "checkFieldsetHasLegend";
	}

	@Override
	public Filter getFilter() {
		return Filter.FIELDSET;
	}

	/**
	 * Check that field set elements have legend elements.
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/73/
	 * 
	 * @param fieldset to check
	 * @return issue or null
	 */
	@Override
	public Issue check(Element fieldset) {
		Elements legends = fieldset.select(LEGEND);
		if (legends.isEmpty()) {
			return new Issue("checkFieldsetHasLegend",
					"Check that fieldset element has a legend",
					Severity.ERROR, fieldset);
		}
		return null;
	}

}
