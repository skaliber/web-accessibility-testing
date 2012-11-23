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

import static org.a11ytesting.test.wcag.Shared.SUMMARY;
import static org.a11ytesting.test.wcag.Shared.getRootElement;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Element;

import org.a11ytesting.filter.ElementFilter;
import org.a11ytesting.filter.TableFilter;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.HtmlVersion;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;

/**
 * Rule for table summary uniqueness.
 * 
 * @author dallison
 */
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
	 * @param table element to test
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/5/
	 * 
	 * @return issue or null
	 */
	@Override
	public Issue check(HtmlVersion htmlVersion, Element table) {
		
		switch (htmlVersion) {
		// summary attribute is not supported by html5
		case HTML5: {
			if (table.hasAttr(SUMMARY)) {
				return new Issue("checkTableSummaryUnique",
						"Check that table summary is not supported by html5",
						Severity.WARNING, table);
			}
			return null;
		}

		default: {
			if (!table.hasAttr(SUMMARY)) {
				return null;
			}
			Element root = getRootElement(table);
			ElementFilter tableFilter = new TableFilter();

			for (Element otherTable : tableFilter.result(root)) {
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
	}

}