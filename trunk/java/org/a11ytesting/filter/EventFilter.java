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
package org.a11ytesting.filter;

import org.jsoup.nodes.Element;

/**
 * Subset of HTML events for standard events of interest to rules. 
 * 
 * @see http://en.wikipedia.org/wiki/DOM_events
 * 
 * @author dallison
 */
public class EventFilter extends ElementFilter {
	
	private static final String EVENT_ELEMENT_SELECTOR = "[onclick], " +
			"[onkeyup], [onkeydown], [onkeypress], [onmousedown], " + 
			"[onmouseup], [onmousemove], [onmouseout], [onmouseover], " +
			"[onfocus], [onblur]";

	@Override
	public Iterable<Element> result(Element element) {
		return element.select(EVENT_ELEMENT_SELECTOR);
	}
}