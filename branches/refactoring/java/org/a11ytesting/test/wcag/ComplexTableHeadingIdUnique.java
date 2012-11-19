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

import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Element;

import org.a11ytesting.filter.TableFilter;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;

/**
 * Complex table heading unique rule.
 * 
 * @author dallison
 */
public class ComplexTableHeadingIdUnique extends AbstractPerceivableRule {

	@Override
	public String getRuleName() {
		return "checkComplexTableHeadingIdUnique";
	}

	@Override
	public Filter getFilter() {
		return new TableFilter();
	}

	/**
	 * Check that 'complex' table heading IDs are unique.
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/7/
	 * 
	 * @param table to check.
	 * @return issue identified or null
	 */
	@Override
	public Issue check(Element table) {
		if (notComplexTable(table)) {
			return null;
		}
		Set<String> idValues = new HashSet<String>();
		for (Element th : table.select(TH)) {
			if (!th.hasAttr(ID)) {
				continue; // Skip as already tested
			}
			if (idValues.contains(th.attr(ID))) {
				return new Issue("checkComplexTableHeadingIdUnique",
						"Check taht complex tables have unique ids on " +
						"table headings", Severity.ERROR, table);
			} else {
				idValues.add(th.attr(ID));
			}
		}
		return null;
	}
}