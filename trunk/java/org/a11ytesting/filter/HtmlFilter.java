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
package org.a11ytesting.filter;

import org.jsoup.nodes.Element;

/**
 * Html top level element filter.
 * 
 * @author dallison
 */
public class HtmlFilter extends ElementFilter {

	private static final String HTML_SELECT = "html"; 

	@Override
	public Iterable<Element> result(Element element) {
		return element.select(HTML_SELECT);
	}
}
