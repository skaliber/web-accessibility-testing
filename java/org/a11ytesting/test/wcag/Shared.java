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

import java.util.Arrays;
import java.util.List;

import org.a11ytesting.aria.Role;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

/**
 * Shared variables for common HTML element selectors.
 * @author dallison
 *
 */
public abstract class Shared {
	
	static final String ACCESS_KEY = "accesskey";
	static final String ALT_TEXT = "alt";
	static final String ANCHOR = "a";
	static final String ARIA_PRESENTATION = "presentation";
	static final String ARIA_ROLE = "role";
	static final String BLUR = "onblur";
	static final String BUTTON = "button";
	static final String COLSPAN = "colspan";
	static final String FOCUS = "onfocus";
	static final String HEAD = "head";
	static final String HEADERS = "headers";
	static final String HREF = "href";
	static final String ID = "id";
	static final String IMAGE = "image";
	static final String INPUT = "input";
	static final String KEY_DOWN = "onkeydown";
	static final String KEY_PRESS = "onkeypress";
	static final String KEY_UP = "onkeyup";
	static final String LANG = "lang";
	static final String LEGEND = "legend";
	static final String ON_CHANGE = "onchange";
	static final String ROLE = "role";
	static final String ROWSPAN = "rowspan";
	static final String SELECT = "select";
	static final String SUMMARY = "summary";
	static final String TAB_INDEX = "tabindex";
	static final String TD = "td";
	static final String TH = "th";
	static final String TITLE = "title";
	static final String TYPE = "type";
	static final String VALUE = "value";
	
	
	

	/**
	 * Get the root element by navigating to the top parent
	 * 
	 * @param element starting point
	 * @return the root element or the passed element if no parents
	 */
	static Element getRootElement(Element element) {
		Elements parents = element.parents();
		if (parents.isEmpty()) {
			return element;
		} else {
			return parents.last();
		}
	}
	
	/**
	 * Extract the contained text from text and alt nodes.
	 * 
	 * @see http://code.google.com/p/ainspector/source/browse/trunk/chrome/content/ainspector/OAA/scripts/util.js?spec=svn116&r=116
	 * 
	 * @param root node to traverse from.
	 * @return String with white space normalised as single spaces.
	 */
	static String containedText(Element root) {
		// strip leading and trailing spaces added by building
		StringBuilder builder = new StringBuilder();
		recurseNode(root, builder);
		return builder.toString().trim().replaceAll("\\W+", " ");
		//return recurseNode(root).trim().replaceAll("\\W+", " ");
	}

	// @todo(dallison) handle rtl text
	private static void recurseNode(Node node, StringBuilder builder) {
		// boundry case for text
		if (node instanceof TextNode) {
			builder.append(((TextNode) node).text());
			builder.append(" ");
			return;
		}

    // check for title text
    if (node.hasAttr(TITLE)) {
      builder.append(node.attr(TITLE));
      builder.append(" ");
    }
		
		// if the current node has alt text append it
		if (node.hasAttr(ALT_TEXT)) {
			builder.append(node.attr(ALT_TEXT));
			// Add trailing white space to separate elements
			builder.append(" ");
		}
		
		// recurse into the child nodes.
		for (Node child : node.childNodes()) {
			recurseNode(child, builder);
		}
	}
	
	// TODO(jharty) This has moved to NonInteractiveElementHasRole, could you
	// move the test also?
	static boolean hasAriaRole(Element element) {
		if (!element.hasAttr(ROLE)) {
			return false;
		}
		// search for role in
		for (Role role : Role.values()) {
			if (element.attr(ROLE).contains(role.toString())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Determines whether an element is not focusable using keys.
	 * 
	 * TODO(jharty): We need to determine which elements are generally
	 * focusable (where focus is disabled by setting tabindex to a negative
	 * number) and which ones generally are NOT focusable, but may become
	 * focusable by setting a positive tabindex.
	 * TODO(jharty): This could move to OnClickIsFocusable?
	 * 
	 * @param element the element to test
	 * @return true when the element is not focusable, else false.
	 */
	static boolean notFocusable(Element element) {
		List<String> focusable = Arrays.asList(new String[]{
				"a", "button", "input", "select", "textarea" 
		});
		if (focusable.contains(element.tagName())){
			if (element.hasAttr(TAB_INDEX) && 
					-1 >= Integer.parseInt(element.attr(TAB_INDEX))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check if an element is visible based on whether it has an aria
	 * presentation tag.
	 * 
	 * @param element
	 * @return true if the element is visible rather than just presentation.
	 * @todo(dallison) check other aria roles for visible intentions
	 */
	static boolean isVisible(Element element) {
		Attributes attributes = element.attributes();
		if (attributes.hasKey("role")) {
			if (attributes.get(ARIA_ROLE).equals(ARIA_PRESENTATION)) {
				return false;
			} else {
				return true;
			}
		}
		return true;
	}
	
	/**
	 * Check if a HTML table is not complex. Typically a complex table has at
	 * least one cell which is a multi column or multi row. In those cases it
	 * is required that the table use heading id alignment to aid screen
	 * readers.
	 * 
	 * @param table to check
	 * @return true if the table is not complex.
	 */
	static boolean notComplexTable(Element table) {
		// @todo(dallison) Define a heuristic for this starting with > 1
		for (Element cell : table.select(TH + ", " + TD)) {
			if (cell.hasAttr(COLSPAN) || cell.hasAttr(ROWSPAN)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Check if an HTML input is an image input type.
	 * @param element
	 * @return
	 */
	static boolean isImageInput(Element element) {
		return element.nodeName().equals(INPUT) && 
				element.hasAttr(TYPE) && element.attr(TYPE).equals(IMAGE);
	}
}
