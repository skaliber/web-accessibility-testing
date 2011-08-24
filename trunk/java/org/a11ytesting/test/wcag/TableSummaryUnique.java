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
import static org.a11ytesting.test.Shared.getRootElement;

import org.jsoup.nodes.Element;

import org.a11ytesting.filter.ElementFilter;
import org.a11ytesting.filter.TableFilter;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;

public class TableSummaryUnique extends AbstractPerceivableRule {

	@Override
	public String getRuleName() {
		return "checkTableSummaryUnique";
	}

	@Override
	public Filter getFilter() {
		return new TableFilter();
	}

	/**
	 * Check that table summary content is unique.
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/5/
	 * 
	 * @param table element to test
	 * @return issue or null
	 */
	@Override
	public Issue check(Element table) {
		if (!table.hasAttr(SUMMARY)) {
			return null;
		}
		Element root = getRootElement(table);
		ElementFilter filter = new TableFilter();
		for (Element otherTable : filter.result(root)) {
			if (table.equals(otherTable)) { // Skip self
				continue;
			}
			if (!otherTable.hasAttr(SUMMARY)) {
				continue;
			}
			if (table.attr(SUMMARY).equals(otherTable.attr(SUMMARY))) {
				return new Issue("checkTableSummaryUnique",
						"Check that table summary is unique",
						Severity.ERROR, table);
			}
		}
		return null;
	}

}
