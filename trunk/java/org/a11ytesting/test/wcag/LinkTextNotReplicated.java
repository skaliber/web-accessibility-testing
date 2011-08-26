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

import static org.a11ytesting.test.wcag.Shared.HREF;
import static org.a11ytesting.test.wcag.Shared.containedText;
import static org.a11ytesting.test.wcag.Shared.getRootElement;

import org.jsoup.nodes.Element;

import org.a11ytesting.filter.ElementFilter;
import org.a11ytesting.filter.LinkFilter;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;

/**
 * Rule for link text not replicated on different targets.
 * 
 * @author dallison
 */
public class LinkTextNotReplicated extends AbstractOperableRule {

	@Override
	public String getRuleName() {
		return "checkLinkTextNotReplicated";
	}

	@Override
	public Filter getFilter() {
		return new LinkFilter();
	}

	/**
	 * Check that links with the same text don't point to different hrefs
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/40/
	 * 
	 * @param link to check
	 * @return Issue or null
	 * 
	 * @todo(dallison) Consider refactoring to include image areas: area[href]
	 * @todo(dallison) Consider combining 40, 39, 41
	 */
	@Override
	public Issue check(Element link) {
		Element root = getRootElement(link);
		ElementFilter linkFilter = new LinkFilter();
		for (Element otherLink : linkFilter.result(root)) {
			// skip self
			if (link.equals(otherLink)) {
				continue;
			}
			if (containedText(link).equalsIgnoreCase(
					containedText(otherLink)) &&
					!sameHref(link, otherLink)) {
				return new Issue("checkLinkTextNotReplicated",
						"Check that there are not two links with the same" +
						"text but different href",
						Severity.ERROR,
						link);
			}
		}
		return null;
	}
	
	private boolean sameHref(Element link, Element otherLink) {
		if (link.hasAttr(HREF) && otherLink.hasAttr(HREF)) {
			return link.attr(HREF).equals(otherLink.attr(HREF));
		} else {
			return false;
		}
		
	}
}