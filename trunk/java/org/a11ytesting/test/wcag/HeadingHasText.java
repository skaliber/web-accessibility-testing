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

import static org.a11ytesting.test.Shared.containedText;

import org.jsoup.nodes.Element;

import org.a11ytesting.filter.HeadingFilter;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;

public class HeadingHasText extends AbstractOperableRule {

	@Override
	public String getRuleName() {
		return "checkHeadingHasText";
	}

	@Override
	public Filter getFilter() {
		return new HeadingFilter();
	}

	/**
	 * Check that heading elements have text.
	 * 
	 * @see getNodeTextRecursively
	 * 
	 * @param heading
	 * @return issue or null
	 */
	@Override
	public Issue check(Element heading) {
		if (containedText(heading).trim().isEmpty()) {
			return new Issue("checkHeadingHasText",
					"Check that heading elements have text",
					Severity.ERROR, heading);
		}
		return null;
	}

}
