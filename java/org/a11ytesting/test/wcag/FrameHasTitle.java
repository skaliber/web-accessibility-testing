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

import static org.a11ytesting.test.Shared.TITLE;

import org.jsoup.nodes.Element;

import org.a11ytesting.test.Filter;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;

public class FrameHasTitle extends AbstractOperableRule {

	@Override
	public String getRuleName() {
		return "checkFrameHasTitle";
	}

	@Override
	public Filter getFilter() {
		return Filter.FRAME;
	}

	/**
	 * Check that frame elements have a title attribute
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/10/
	 * 
	 * @param frame element to check
	 * @return Issue or null
	 */
	@Override
	public Issue check(Element frame) {
		if (!frame.hasAttr(TITLE) || frame.attr(TITLE).trim().isEmpty()) {
			return new Issue("checkFrameHasTitle",
					"Check that frame elements define a title attribute",
					Severity.ERROR, frame);
		}
		return null;
	}

}
