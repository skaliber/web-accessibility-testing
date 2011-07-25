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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


/**
 * Example usage for evaluator based test case running.
 * 
 * @author dallison
 */
public class Example {
	
	private static final int TIMEOUT = 20000;
	
	public static void main(String[] args) throws Exception {
		Document document = null;
		try {
			document = Jsoup.parse(new URL("http://www.ebay.com/"), TIMEOUT);
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error due to malformed URL parameter", e);
		} catch (IOException e) {
			throw new RuntimeException("IO Exception fetching page.", e);
		}
		
		Evaluator evaluator = new Evaluator();
		evaluator.addPackage("org.a11ytesting.test.wcag");
		List<Issue> result = evaluator.collectIssues(document);
		
		for (Issue issue : result) {
			System.out.println(issue.toString());
		}
	}
}