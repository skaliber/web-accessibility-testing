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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.jsoup.nodes.Element;

/**
 * Representation of an issue with an element returned by a test.
 * 
 * @todo(dallison) Consider converting to an error.
 * @todo(dallison) Add url reference for addressing the issue highlighted.
 */
public class Issue {

	public enum Severity {
		WARNING {
			@Override public String toString() {
				return "warning";
			}
		},
		ERROR {
			@Override public String toString() {
				return "error";
			}
		}
	};

	private static final String EMPTY = "\"\"";

	private String testName = null, description = null;
	private Severity severity;
	private Element element = null;

	public Issue(String testName, String description, Severity severity,
			Element element) {
		this.testName = testName;
		this.description = description;
		this.severity = severity;
		this.element = element;
	}
	
	public String getTestName() {
		return testName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Severity getSeverity() {
		return severity;
	}
	
	public Element getElement() {
		return element;
	}

	public String toString() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		JsonFactory factory = new JsonFactory();
		JsonGenerator builder;
		try {
			builder = factory.createJsonGenerator(out);
			builder.writeStartObject();
			builder.writeStringField("testName", (null == testName ? EMPTY :
					testName));
			builder.writeStringField("description", (null == description ? EMPTY :
					description));
			builder.writeStringField("severity", (null == severity ? EMPTY :
					severity.toString()));
			builder.writeStringField("elementCode", element.outerHtml());
			builder.writeEndObject();
			builder.close();
			return out.toString();
		} catch (IOException e) {
			throw new RuntimeException("Error building string representation of issue",
					e);
		}
	}
}