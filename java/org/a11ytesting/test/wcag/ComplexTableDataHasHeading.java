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

import static org.a11ytesting.test.Shared.HEADERS;
import static org.a11ytesting.test.Shared.TD;
import static org.a11ytesting.test.Shared.notComplexTable;

import org.jsoup.nodes.Element;

import org.a11ytesting.test.Filter;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;

public class ComplexTableDataHasHeading extends AbstractPerceivableRule {

	@Override
	public String getRuleName() {
		return "checkComplexTableDataHasHeading";
	}

	@Override
	public Filter getFilter() {
		return Filter.TABLE;
	}

	/**
	 * Check that complex table with colspan or rowspan headers also use table
	 * data element ids referring to the header they are aligned to.
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/8/
	 * 
	 * @param table to check
	 * @return issues identified.
	 */
	@Override
	public Issue check(Element table) {
		if (notComplexTable(table)) {
			return null;
		}
		for (Element td: table.select(TD)) {
			// @todo(dallison) consider checking alignment and order
			if (!td.hasAttr(HEADERS)) {
				return new Issue("checkComplexTableDataHasHeading",
						"Check that complex tables have heading id " +
						"references on data elements",
						Severity.ERROR, table);
			}
		}
		return null;
	}

}
