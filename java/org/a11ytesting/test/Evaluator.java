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

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.nodes.Element;
import org.reflections.Reflections;

/**
 * Support class providing functionality to invoke test groups
 * for given filter sets.
 * 
 * @author dallison
 */
public class Evaluator {
	
	private Set<Rule> forRules = new HashSet<Rule>();
	
	/**
	 * Add a package of rules in the class path to this evaluator
	 *  
	 * @param packageName to add
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
				forRules.add(instance);
			} catch (InstantiationException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			
		}
	}
	
	/**
	 * Collect the issues for the current loaded set of
	 * rules 
	 * @param root
	 * @return
	 */
	public List<Issue> collectIssues(Element root) {
		List<Issue> result = new ArrayList<Issue>();
		for (Rule rule : forRules) {
			Filter filter = rule.getFilter();
			for (Element target : filter.result(root)) {
				Issue issue = rule.check(target);
				if (null != issue) {
					result.add(issue);
				}
			}
		}
		return result;
	}
}
