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

import org.a11ytesting.filter.TableFilter;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.HtmlVersion;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;

/**
 * Rule for table heading presence.
 * 
 * @author dallison
 */
public class TableHasHeadings extends AbstractPerceivableRule {

	@Override
	public String getRuleName() {
		return "checkTableHasHeadings";
	}

	@Override
	public Filter getFilter() {
		return new TableFilter();
	}

	/**
	 * Check that data tables are using headers.
	 * @param table element to test
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/4/
	 * 
	 * @return issue or null
	 */
	@Override
	public Issue check(HtmlVersion htmlVersion, Element table) {
		if (table.select("th").isEmpty()) {
			// @todo(dallison) Consider checking headers have content / number
			return new Issue("checkTableHasHeadings",
					"Check that data table has a at least one heading",
					Severity.ERROR, table);
		}
		return null;
	}
}