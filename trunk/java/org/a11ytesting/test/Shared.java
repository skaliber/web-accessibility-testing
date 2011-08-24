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
package org.a11ytesting.test;

import java.util.Arrays;
import java.util.List;

import org.a11ytesting.aria.Role;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

/**
 * Shared variables for common html element selectors.
 * @author dallison
 *
 */
public abstract class Shared {
	
	public static final String ACCESS_KEY = "accesskey";
	public static final String ALT_TEXT = "alt";
	public static final String ANCHOR = "a";
	public static final String ARIA_PRESENTATION = "presentation";
	public static final String ARIA_ROLE = "role";
	public static final String BLUR = "onblur";
	public static final String BUTTON = "button";
	public static final String COLSPAN = "colspan";
	public static final String FOCUS = "onfocus";
	public static final String HEAD = "head";
	public static final String HEADERS = "headers";
	public static final String HREF = "href";
	public static final String ID = "id";
	public static final String IMAGE = "image";
	public static final String INPUT = "input";
	public static final String KEY_DOWN = "onkeydown";
	public static final String KEY_PRESS = "onkeypress";
	public static final String KEY_UP = "onkeyup";
	public static final String LANG = "lang";
	public static final String LEGEND = "legend";
	public static final String ON_CHANGE = "onchange";
	public static final String ROLE = "role";
	public static final String ROWSPAN = "rowspan";
	public static final String SELECT = "select";
	public static final String SUMMARY = "summary";
	public static final String TAB_INDEX = "tabindex";
	public static final String TD = "td";
	public static final String TH = "th";
	public static final String TITLE = "title";
	public static final String TYPE = "type";
	public static final String VALUE = "value";
	
	
	

	/**
	 * Get the root element by navigating to the top parent
	 * 
	 * @param element starting point
	 * @return the root element or the passed element if no parents
	 */
	public static Element getRootElement(Element element) {
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
	 * @param root of the construction
	 * @return String with normalised spaces
	 */
	public static String containedText(Element root) {
		// strip leading and trailing spaces added by building
		return recurseNode(root).trim().replaceAll("\\W+", " ");
	}
	
	// recurse into nodes and construct spaced string
	// @todo(dallison) handle rtl text
	private static String recurseNode(Node node) {
		StringBuilder builder = new StringBuilder();
		if (node instanceof TextNode) {
			builder.append(((TextNode) node).text());
			builder.append(" ");
			return builder.toString();
		}
		
		if (node.hasAttr(ALT_TEXT)) {
			builder.append(node.attr(ALT_TEXT));
			// Add trailing white space to separate elements
			builder.append(" ");
		}
		for (Node child : node.childNodes()) {
			builder.append(recurseNode(child));
		}
		return builder.toString();
	}
	
	public static boolean hasAriaRole(Element element) {
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
	 * 
	 * @param element the element to test
	 * @return true when the element is not focusable, else false.
	 */
	public static boolean notFocusable(Element element) {
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
	
	// @todo(dallison) check other aria roles for visible intentions
	public static boolean isVisible(Element element) {
		Attributes attributes = element.attributes();
		if (attributes.hasKey("role")) {
			if(attributes.get(ARIA_ROLE).equals(ARIA_PRESENTATION)) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}
	
	// place holder for heuristic decision of whether is 'complex' table.
	public static boolean notComplexTable(Element table) {
		// @todo(dallison) Define a heuristic for this starting with > 1
		for (Element cell : table.select(TH + ", " + TD)) {
			if (cell.hasAttr(COLSPAN) || cell.hasAttr(ROWSPAN)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isImageInput(Element element) {
		return element.nodeName().equals(INPUT) && 
				element.hasAttr(TYPE) && element.attr(TYPE).equals(IMAGE);
	}
}
