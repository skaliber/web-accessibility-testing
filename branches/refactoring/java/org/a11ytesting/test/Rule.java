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

import java.util.EnumSet;
import java.util.Set;

import org.jsoup.nodes.Element;

/**
 * The rule interface for a checker or test that can raise an issue. The
 * evaluator supports bulk loading of rule implementations given package names.
 * Implementations of the rule interface define the filter they use for
 * processing document elements. The rule will then be called once for each
 * of the instances returned for the filter. The intention of this behaviour
 * is that the rule tests a single node in context and can return only one
 * issue per element or null if no issue exists. The intention is that a rule
 * will check for only one condition.
 * 
 * @author dallison
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
	 * Get the filter definition used by the evaluator to select elements of
	 * the document or fragment being processed.
	 * 
	 * @return filter used to select elements which will be passed to this
	 * 		rule.
	 */
	public Filter getFilter();
	
	/**
	 * Check an element to see if there is an issue.
	 * @param htmlVersion TODO
	 * @param the element to check. The element will be one of the elements
	 * 		selected by the filter returned by getFilter().
	 * 
	 * @return the issue identified or null
	 */
	public Issue check(HtmlVersion htmlVersion, Element element);
}