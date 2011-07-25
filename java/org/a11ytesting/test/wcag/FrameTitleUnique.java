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
import static org.a11ytesting.test.Shared.getRootElement;

import org.jsoup.nodes.Element;

import org.a11ytesting.filter.ElementFilter;
import org.a11ytesting.filter.FrameFilter;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;

public class FrameTitleUnique extends AbstractOperableRule {

	@Override
	public String getRuleName() {
		return "checkFrameTitleUnique";
	}

	@Override
	public Filter getFilter() {
		return Filter.FRAME;
	}

	/**
	 * Check that frame title values are unique.
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/11/
	 * 
	 * @param frame to check
	 * @return Issue or null.
	 */
	@Override
	public Issue check(Element frame) {
		// if the frame doesn't have a title skip
		if (!frame.hasAttr(TITLE)) {
			return null;
		}
		Element root = getRootElement(frame);
		ElementFilter filter = new FrameFilter(root);
		for (Element otherFrame : filter.result()) {
			// skip self
			if (frame.equals(otherFrame)) {
				continue;
			}
			// @todo (dallison) Consider normalising the string.
			if (otherFrame.hasAttr(TITLE) && frame.attr(TITLE).equals(
					otherFrame.attr(TITLE))) {
				return new Issue("checkFrameTitleUnique",
						"Check that a frame title is unique",
						Severity.ERROR, frame);
			}
		}
		return null;
	}

}
