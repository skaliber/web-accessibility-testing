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
import static org.a11ytesting.test.wcag.Shared.isVisible;

import org.jsoup.nodes.Element;

import org.a11ytesting.filter.ImageFilter;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.HtmlVersion;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;


/**
 * Rule for invisible image with title.
 * 
 * @author dallison
 */
public class InvisibleImageNoTitle extends AbstractPerceivableRule {

	@Override
	public String getRuleName() {
		return "checkInvisibleImageNoTitle";
	}

	@Override
	public Filter getFilter() {
		return new ImageFilter();
	}

	/**
	 * Check that when an image is tagged as presentation that it doesn't
	 * have a title.
	 * @param image
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/31/
	 *  
	 * @return issue or null.
	 */
	@Override
	public Issue check(HtmlVersion htmlVersion, Element image) {
		// check that the image has alt text or
		if (!isVisible(image) && image.hasAttr(TITLE)) {
			return new Issue("checkInvisibleImageNoTitle",
					"Check when an image is not visible it does "
							+ "not have a title", Severity.ERROR, image);
		}
		return null;
	}
}