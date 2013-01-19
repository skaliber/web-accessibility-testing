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

import static org.a11ytesting.test.wcag.Shared.HEAD;
import static org.a11ytesting.test.wcag.Shared.TITLE;

import org.jsoup.nodes.Element;

import org.a11ytesting.filter.HtmlFilter;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.HtmlVersion;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;


/**
 * Rule for empty title element.
 * 
 * @author dallison
 */
public class TitleIsNotEmpty extends AbstractOperableRule {

	@Override
	public String getRuleName() {
		return "checkTitleIsNotEmpty";
	}

	@Override
	public Filter getFilter() {
		return new HtmlFilter();
	}

	/**
	 * Check that document has a title node.
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/13/
	 * 
	 * @param the document
	 * @return issue or null
	 */
	@Override
	public Issue check(HtmlVersion htmlVersion, Element doc) {
		String IN_HEAD = HEAD + " > " + TITLE;
		if (1 != doc.select(IN_HEAD).size() ||
				(!doc.select(IN_HEAD).first().hasText() ||
				doc.select(IN_HEAD).first().text()
					.trim().isEmpty())) {
			return new Issue("checkTitleIsNotEmpty",
					"Check that the page has a title and it has text",
					Severity.ERROR, doc);
		}
		return null;
	}
}