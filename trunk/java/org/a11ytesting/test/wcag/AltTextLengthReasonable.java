/* Copyright 2011 Ebay Inc.
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

import org.a11ytesting.filter.ImageFilter;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;
import org.jsoup.nodes.Element;

public class AltTextLengthReasonable extends AbstractPerceivableRule {

	private static final int MIN_ALT_LENGTH = 7;
	private static final int MAX_ALT_LENGTH = 90;
	
	@Override
	public String getRuleName() {
		return "checkAltTextLengthReasonable";
	}

	@Override
	public Filter getFilter() {
		return new ImageFilter();
	}

	/**
	 * Check the length of the alt text is of reasonable length.
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/29/
	 * 
	 * @param image to test
	 * @return Issue if identified or null
	 */
	@Override
	public Issue check(Element image) {
		if (isVisible(image) && image.hasAttr(ALT_TEXT)
				&& (image.attr(ALT_TEXT).length() < MIN_ALT_LENGTH || 
						image.attr(ALT_TEXT).length() > MAX_ALT_LENGTH)) {
		return new Issue("checkAltTextLengthReasonable",
				"Check that visible images use good alt text length",
				Severity.WARNING, image);
	}
	return null;
	}

}
