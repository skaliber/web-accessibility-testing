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

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;

/**
 * Default element filter which returns the root. This class should be sub
 * classed by specific filter types.
 * 
 * When sub classing this implementation ensure overriding of the iterator
 * method to return an iterator for the correct element type. 
 * 
 * @author dallison
 *
 */
public class ElementFilter {

	protected Element element;
	
	/**
	 * Use private constructor to avoid sub classes calling and overriding the iterator.
	 */
	public ElementFilter(Element element){
		this.element = element;
	};
	
	public Iterable<Element> result() {
		List<Element> result = new ArrayList<Element>(1);
		result.add(element);
		return result;
	}
}
