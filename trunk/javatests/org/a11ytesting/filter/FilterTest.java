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

import static org.testng.Assert.fail;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.testng.annotations.Test;

public class FilterTest {
	
	private ElementFilter filter;
	private Elements elements;
	
	public FilterTest(ElementFilter filter, Elements expect) {
		this.filter = filter;
		this.elements = expect;
	}

	@Test
	public void testAllElementsReturned() {
		int filterCount = 0;
		for (Element check : filter.result()) {
			filterCount++;
			if (!elements.contains(check)) {
				fail("Filtered element not in expected elements for filter " +
						filter.getClass().getName());
			}
		}
		if (filterCount != elements.size()) {
			fail("Filter returned a different number of elements than " +
					"expected for filter " + filter.getClass().getName());
		}
	}
}
