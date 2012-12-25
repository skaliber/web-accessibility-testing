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


import static org.a11ytesting.test.wcag.Shared.ID;
import static org.a11ytesting.test.wcag.Shared.TH;
import static org.a11ytesting.test.wcag.Shared.notComplexTable;

import org.a11ytesting.filter.TableFilter;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.HtmlVersion;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;
import org.jsoup.nodes.Element;

/**
 * Complex table heading ID rule.
 * 
 * @author dallison
 */
public class ComplexTableHeadingHasId extends AbstractPerceivableRule {

	@Override
	public String getRuleName() {
		return "checkComplexTableHeadingHasId";
	}

	@Override
	public Filter getFilter() {
		return new TableFilter();
	}

	/**
	 * Check that 'complex' data tables have IDs on the headers.
	 * @param table to inspect
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/6/
	 * 
	 * @return issue or null
	 */
	@Override
	public Issue check(HtmlVersion htmlVersion, Element table) {
		if (notComplexTable(table)) {
			return null;
		}
		// iterate over headings
		for (Element th : table.select(TH)) {
			if (!th.hasAttr(ID)) {
				return new Issue("checkComplexTableHeadingHasId",
						"Check that complex tables have id attributes" +
						"on table heading elements",
						Severity.ERROR, table);
			}
		}
		return null;
	}
}