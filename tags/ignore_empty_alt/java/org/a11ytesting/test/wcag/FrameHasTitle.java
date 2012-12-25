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

import org.a11ytesting.filter.FrameFilter;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.HtmlVersion;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;
import org.jsoup.nodes.Element;

/**
 * Rule for frame having title.
 * The <frame> tag is not supported in HTML5.
 * 
 * @author dallison
 */
public class FrameHasTitle extends AbstractOperableRule {

	@Override
	public String getRuleName() {
		return "checkFrameHasTitle";
	}

	@Override
	public Filter getFilter() {
		return new FrameFilter();
	}

	/**
	 * Check that frame elements have a title attribute
	 * @param frame element to check
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/10/
	 * 
	 * @return Issue or null
	 */
	@Override
	public Issue check(HtmlVersion htmlVersion, Element frame) {
		switch (htmlVersion) {
		// frame tag is not supported by html5
		case HTML5: {
			if (frame.hasAttr(TITLE)) {
				return new Issue("checkFrameHasTitle",
						"Check that frame is not supported by html5",
						Severity.WARNING, frame);
			}
			return null;
		}
		default: {
			if (!frame.hasAttr(TITLE) || frame.attr(TITLE).trim().isEmpty()) {
				return new Issue("checkFrameHasTitle",
						"Check that frame elements define a title attribute",
						Severity.ERROR, frame);
			}
			return null;
		}
		}
	}
}