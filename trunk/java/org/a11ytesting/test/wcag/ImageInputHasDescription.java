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
import static org.a11ytesting.test.Shared.TITLE;
import static org.a11ytesting.test.Shared.isImageInput;

import org.a11ytesting.test.Filter;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;
import org.jsoup.nodes.Element;

public class ImageInputHasDescription extends AbstractUnderstandableRule {

	@Override
	public String getRuleName() {
		return "checkImageInputHasDescription";
	}

	@Override
	public Filter getFilter() {
		return Filter.INPUT_CONTROL;
	}

	/**
	 * Check that image input elements have an alt or title attribute.
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/76/
	 * 
	 * @param image input to check
	 * @return issue or null
	 */
	@Override
	public Issue check(Element image) {
		// skip other input control types
		if (!isImageInput(image)) {
			return null;
		}
		if (image.hasAttr(ALT_TEXT) || image.hasAttr(TITLE)) {
			return null;
		}
		return new Issue("checkImageInputHasDescription",
				"Check that image input element has either alt or title",
				Severity.ERROR, image);
	}

}
