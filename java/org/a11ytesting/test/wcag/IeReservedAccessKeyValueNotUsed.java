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

import static org.a11ytesting.test.Shared.ACCESS_KEY;

import java.util.Arrays;
import java.util.List;

import org.jsoup.nodes.Element;

import org.a11ytesting.test.Filter;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;

public class IeReservedAccessKeyValueNotUsed extends AbstractOperableRule {

	@Override
	public String getRuleName() {
		return "checkIeReservedAccessKeyValueNotUsed";
	}

	@Override
	public Filter getFilter() {
		return Filter.ACCESS_KEY;
	}

	/**
	 * Check that access key attributes don't clash with IE shortcuts
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/2/
	 * 
	 * @param access element to check
	 * @return Issue or null
	 */
	@Override
	public Issue check(Element access) {
		List<String> internetExplorerKeys = Arrays.asList(new String[] {
				"a", "e", "f", "h", "t", "v"});
		if (internetExplorerKeys.contains(
					access.attr(ACCESS_KEY).toLowerCase())) {
			return new Issue("checkAccessKeyValueNotUsedInIe",
					"Check that access key attribute values are not already " +
					"used by internet explorer", Severity.ERROR, access);
		}
		return null;
	}

}
