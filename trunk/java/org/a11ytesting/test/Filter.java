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
package org.a11ytesting.test;

import org.jsoup.nodes.Element;

/**
 * Interface for filtering document elements to be used by rules.
 * 
 * @author dallison
 */
public interface Filter {

	/**
	 * Filter an element set starting at a root element. The filter
	 * implementation should return a collection of matches, the evaluator
	 * will use that collection when invoking rules. The rule will be called
	 * once for each match in the filter results.
	 * 
	 * @param root The root element to filter from. This is normally the
	 * 		document root but could be a partial document fragment.
	 * @return Iterable<Element> set of elements that are matched by this
	 * 		filter..
	 */
	public Iterable<Element> result(Element root);
}
