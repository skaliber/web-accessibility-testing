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

import org.jsoup.nodes.Element;

/**
 * Element checking rule defines methods implemented for a
 * given rule.
 * 
 * @author dallison
 *
 */
public interface Rule {

	/**
	 * Get the rule suite name
	 * 
	 * @return the suite name
	 */
	public String getSuiteName();

	/**
	 * Get the grouping of the rule
	 * 
	 * @return the rule grouping
	 */
	public String getGroupName();

	/**
	 * Get the rule name
	 * 
	 * @return the rule name
	 */
	public String getRuleName();

	/**
	 * Get the filter definition to use for the element type requirement
	 * 
	 * @return the filter needed by this rule
	 */
	public Filter getFilter();
	
	/**
	 * Check whether the rule is valid.
	 * 
	 * @param the element to check
	 * @return the issue identified or null
	 */
	public Issue check(Element element);
}
