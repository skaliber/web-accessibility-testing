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

import static org.a11ytesting.test.wcag.Shared.TITLE;
import static org.a11ytesting.test.wcag.Shared.getRootElement;

import org.jsoup.nodes.Element;

import org.a11ytesting.filter.ElementFilter;
import org.a11ytesting.filter.FrameFilter;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.HtmlVersion;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;

/**
 * Rule for frame title uniqueness. 
 * The <frame> tag is not supported in HTML5.
 * 
 * @author dallison
 */
public class FrameTitleUnique extends AbstractOperableRule {

	@Override
	public String getRuleName() {
		return "checkFrameTitleUnique";
	}

	@Override
	public Filter getFilter() {
		return new FrameFilter();
	}

	/**
	 * Check that frame title values are unique.
	 * @param frame to check
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/11/
	 * 
	 * @return Issue or null.
	 */
	@Override
	public Issue check(HtmlVersion htmlVersion, Element frame) {

		switch (htmlVersion) {
		// frame tag is not supported by html5
		case HTML5: {
			if (frame.hasAttr(TITLE)) {
				return new Issue("checkFrameTitleUnique",
						"Check that frame is not supported by html5",
						Severity.WARNING, frame);
			}
			return null;
		}
		default: {

			// if the frame doesn't have a title skip
			if (!frame.hasAttr(TITLE)) {
				return null;
			}
			Element root = getRootElement(frame);
			ElementFilter filter = new FrameFilter();
			for (Element otherFrame : filter.result(root)) {
				// skip self
				if (frame.equals(otherFrame)) {
					continue;
				}
				// @todo (dallison) Consider normalising the string.
				if (otherFrame.hasAttr(TITLE)
						&& frame.attr(TITLE).equals(otherFrame.attr(TITLE))) {
					return new Issue("checkFrameTitleUnique",
							"Check that a frame title is unique",
							Severity.ERROR, frame);
				}
			}
			return null;
		}
		}
	}
}