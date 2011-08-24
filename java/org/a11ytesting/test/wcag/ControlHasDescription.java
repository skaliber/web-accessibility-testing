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

import static org.a11ytesting.test.Shared.ID;
import static org.a11ytesting.test.Shared.TITLE;
import static org.a11ytesting.test.Shared.getRootElement;
import static org.a11ytesting.test.Shared.isImageInput;

import org.a11ytesting.filter.InputControlFilter;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ControlHasDescription extends AbstractUnderstandableRule {

	@Override
	public String getRuleName() {
		return "checkControlHasDescription";
	}

	@Override
	public Filter getFilter() {
		return new InputControlFilter();
	}

	/**
	 * Check that control elements have either a label referencing them via a
	 * for attribute or a title.
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/75/
	 * 
	 * @param control to check
	 * @return issue or null
	 */
	@Override
	public Issue check(Element control) {
		// skip image input elements
		if (isImageInput(control)) {
			return null;
		}
		if (control.hasAttr(TITLE) &&
				!control.attr(TITLE).trim().isEmpty()) {
			return null;
		}
		if (control.hasAttr(ID)) {
			Element root = getRootElement(control);
			Elements label = root.select("label[for=" + control.attr(ID) +
					"]");
			if (!label.isEmpty()) {
				return null;
			}
		}
		return new Issue("checkControlHasDescriptor",
				"Check that input control elements have a for label",
				Severity.ERROR, control);
	}
}
