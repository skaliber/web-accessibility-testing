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

import static org.a11ytesting.test.Shared.SUMMARY;

import org.a11ytesting.filter.TableFilter;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;
import org.jsoup.nodes.Element;


public class TableHasSummaryAttribute extends AbstractPerceivableRule {

	@Override
	public String getRuleName() {
		return "checkTableHasSummaryAttribute";
	}

	@Override
	public Filter getFilter() {
		return new TableFilter();
	}

	/**
	 * Check that data tables are using the summary attribute.
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/3/
	 * 
	 * @param table to check
	 * @return issue or null
	 */
	@Override
	public Issue check(Element table) {
		if (!table.hasAttr(SUMMARY)) {
			// @todo(dallison) Consider checking length
			return new Issue("checkTableHasSummaryAttribute",
					"Check that data table has a summary attribute",
					Severity.ERROR, table);
		}
		return null;
	}

}
