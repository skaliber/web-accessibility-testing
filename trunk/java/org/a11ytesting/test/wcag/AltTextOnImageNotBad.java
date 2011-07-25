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

import java.util.Arrays;
import java.util.List;

import org.a11ytesting.test.Filter;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;
import org.jsoup.nodes.Element;

public class AltTextOnImageNotBad extends AbstractPerceivableRule {

	private static final List<String> BAD_ALT_WORDS = Arrays.asList(
			new String[] {"image", "picture", "graph", "photo"});

	@Override
	public String getRuleName() {
		return "checkAltTextOnImageNotBad";
	}

	@Override
	public Filter getFilter() {
		return Filter.IMAGE;
	}

	/**
	 * Check for images with invalid alt text value.
	 *
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/28/
	 * 
	 * @param element to inspect.
	 * @return Issue identified or null if none.
	 * 
	 * @todo(dallison) check that not just prefixes of file names.
	 */
	@Override
	public Issue check(Element image) {
		if (image.hasAttr(ALT_TEXT) && !image.attr(ALT_TEXT).trim().isEmpty()
				&& BAD_ALT_WORDS.contains(
				image.attr(ALT_TEXT).toLowerCase())) {
			return new Issue("checkAltTextOnImagesNotBad",
					"Find elements that have an invalid alt text value",
					Severity.ERROR, image);
		}
		return null;
	}

}
