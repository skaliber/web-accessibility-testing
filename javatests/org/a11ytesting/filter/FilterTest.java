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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;


import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.testng.annotations.Test;

public class FilterTest {
	
	private ElementFilter filter;
	private Element root;
	private Elements elements;
	
	public FilterTest(ElementFilter filter, Element root, Elements expect) {
		this.filter = filter;
		this.root = root;
		this.elements = expect;
	}

	@Test
	public void testAllElementsReturned() {
		int filterCount = 0;
		for (Element check : filter.result(root)) {
			filterCount++;
			if (!elements.contains(check)) {
				fail("Filtered element not in expected elements for filter " +
						filter.getClass().getName());
			}
		}
		assertEquals(filterCount, elements.size(),
				"Filter returned a different number of elements than " +
				"expected for filter " + filter.getClass().getName());
	}
}
