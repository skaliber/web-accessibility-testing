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

import static org.a11ytesting.test.Shared.ALT_TEXT;
import static org.a11ytesting.test.Shared.isVisible;

import org.a11ytesting.test.Filter;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;
import org.jsoup.nodes.Element;

public class AltTextOnImage extends AbstractPerceivableRule {

	@Override
	public String getRuleName() {
		return "checkAltTextOnImage";
	}

	@Override
	public Filter getFilter() {
		return Filter.IMAGE;
	}

	/**
	 * Image has alt text.
	 *
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/26/
	 * 
	 * @param image to inspect.
	 * @return Issue identified or null if no issue was identified
	 * 
	 * @todo(dallison) If aria role is presentation must have length > 0
	 */
	@Override
	public Issue check(Element image) {
		// check that the image has alt text or
		if (isVisible(image) && (!image.hasAttr(ALT_TEXT))) {
			return new Issue("checkAltTextOnImage",
					"Check that visible images have alt text",
					Severity.ERROR, image);
		}
		return null;
	}

}
