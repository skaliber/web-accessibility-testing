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

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.nodes.Element;
import org.reflections.Reflections;

/**
 * Support class providing functionality to invoke test groups
 * by loading a package containing implementations of the Rule
 * interface.
 * 
 * @author dallison
 */
public class Evaluator {
	
	private Set<Rule> forRules = new HashSet<Rule>();
	
	private int elementsChecked = 0;
	
	
	/**
	 * Get the number of elements checked in the last call to collect issues.
	 *
	 * @return the count of elements checked.
	 */
	public int getElementsCheckedCount() {
	  return elementsChecked;
	}
	
	/**
	 * Add a package containing rule implementations to the evaluator. Every
	 * class implementing the Rule interface in the given package will be added
	 * to the evaluator.
	 *  
	 * @param packageName of package name in the current classpath to add.
	 */
	public void addPackage(String packageName) {
		Reflections reflection = new  Reflections(packageName);
		Set<Class<? extends Rule>> classes =
				reflection.getSubTypesOf(Rule.class);
		for (Class<? extends Rule> rule : classes) {
			if (Modifier.isAbstract(rule.getModifiers())) {
				continue;
			}
			try {
				Rule instance = rule.newInstance();
				addRule(instance);
			} catch (InstantiationException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			
		}
	}
	
	/**
	 * Add a single rule implementation to the evaluator.
	 * 
	 * @param rule to add to the evaluator.
	 */
	public void addRule(Rule rule) {
		forRules.add(rule);
	}
	
	/**
	 * Collect the issues for the current loaded set of
	 * rules 
	 * @param root element for analysis.
	 * @return the collection of issues identified.
	 */
	public List<Issue> collectIssues(Element root) {
		List<Issue> result = new ArrayList<Issue>();
		elementsChecked = 0;
		for (Rule rule : forRules) {
			Filter filter = rule.getFilter();
			for (Element target : filter.result(root)) {
				Issue issue = rule.check(target);
				elementsChecked++;
				if (null != issue) {
					result.add(issue);
				}
			}
		}
		return result;
	}
}
