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

import static org.a11ytesting.test.wcag.Shared.ACCESS_KEY;
import static org.a11ytesting.test.wcag.Shared.getRootElement;

import org.jsoup.nodes.Element;

import org.a11ytesting.filter.AccessKeyFilter;
import org.a11ytesting.filter.ElementFilter;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;

/**
 * Rule for access key uniqueness in a page.
 * 
 * @author dallison
 */
public class AccessKeyValueUnique extends AbstractOperableRule {

	@Override
	public String getRuleName() {
		return "checkAccessKeyValueUnique";
	}

	@Override
	public Filter getFilter() {
		return new AccessKeyFilter();
	}

	/**
	 * Check that access key attributes are unique.
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/1/
	 * 
	 * @param access element to check.
	 * @return Issue or null.
	 */
	@Override
	public Issue check(Element access) {
		Element root = getRootElement(access);
		ElementFilter filter = new AccessKeyFilter();
		for (Element other : filter.result(root)) {
			// if self then don't check.
			if (other.equals(access)) {
				continue;
			}
			if (access.attr(ACCESS_KEY).equals(other.attr(ACCESS_KEY))) {
				return new Issue("checkAccessKeyValueUnique",
						"Check that access key attribute elements use a " +
								"unique key id",
						Severity.ERROR,
						access);
			}
		}
		return null;
	}
}