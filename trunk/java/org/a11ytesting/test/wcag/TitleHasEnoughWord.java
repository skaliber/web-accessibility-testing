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

import org.a11ytesting.test.Filter;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;

public class TitleHasEnoughWord extends AbstractOperableRule {

	private static final int MIN_TITLE_WORDS = 2;

	@Override
	public String getRuleName() {
		return "checkTitleHasEnoughWord";
	}

	@Override
	public Filter getFilter() {
		return Filter.TITLE;
	}

	@Override
	public Issue check(Element title) {
		// split the string on non word characters.
		if (title.hasText() && MIN_TITLE_WORDS > title.text().split("\\W").length) {
			return new Issue("checkTitleHasMoreThanOneWord",
					"Check that the title element text has more than one word",
					Severity.ERROR, title);
		}
		return null;
	}

}
