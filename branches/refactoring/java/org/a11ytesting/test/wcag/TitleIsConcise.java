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

import org.jsoup.nodes.Element;

import org.a11ytesting.filter.TitleFilter;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.HtmlVersion;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;


/**
 * Rule for concise title text.
 * 
 * @author dallison
 */
public class TitleIsConcise extends AbstractOperableRule {

	private static final int MAX_TITLE_LENGTH = 60;
	
	@Override
	public String getRuleName() {
		return "checkkTitleIsConcise";
	}

	@Override
	public Filter getFilter() {
		return new TitleFilter();
	}

	/**
	 * Check that title text is concise.
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/24/
	 * 
	 * @param title element to check.
	 * @return issue or null
	 */
	@Override
	public Issue check(HtmlVersion htmlVersion, Element title) {
		if (title.hasText() && MAX_TITLE_LENGTH < title.text().length()) {
			return new Issue("checkTitleIsConcise",
					"Check that the title element text is concise",
					Severity.ERROR, title);
		}
		return null;
	}
}