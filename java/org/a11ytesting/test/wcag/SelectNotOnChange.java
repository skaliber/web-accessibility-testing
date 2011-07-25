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

import static org.a11ytesting.test.Shared.ON_CHANGE;

import org.jsoup.nodes.Element;

import org.a11ytesting.test.Filter;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;

public class SelectNotOnChange extends AbstractOperableRule {

	@Override
	public String getRuleName() {
		return "checkSelectNotOnChange";
	}

	@Override
	public Filter getFilter() {
		return Filter.SELECT;
	}

	/**
	 * On change should not be used with the select element.
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/62/
	 * 
	 * @param select to scan
	 * @return issues with the document
	 */
	@Override
	public Issue check(Element select) {
		if (select.hasAttr(ON_CHANGE)) {
			return new Issue("checkSelectNotOnChange",
					"Check that select element doesn't use on change",
					Severity.ERROR, select);
		}
		return null;
	}

}
