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
package org.a11ytesting.test.wcag;

import static org.testng.Assert.assertNull;

import static org.a11ytesting.test.wcag.SharedTest.*;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;
import org.jsoup.nodes.Element;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
/**
 * Tests for the understandable aspects of WCAG guidelines.
 * 
 * @author dallison
 */
public class UnderstandableTest {

	@DataProvider(name = "okLanguageCode")
	Object[][] okLanguageCode() {
		List<String> codes = Arrays.asList(Locale.getISOLanguages());
		Object[][] result = new Object[codes.size()][1];
		int i = 0;
		for (String code : codes) {
			result[i++][0] = "<html lang=\""  + code + "\">" +
					"<body>null</body></html>";
		}
		return result;
	}

	@Test(dataProvider = "okLanguageCode")
	public void testHtmlHasValidLanguageCodeOk(String html) {
		HtmlHasValidLanguageCode understand =
				new HtmlHasValidLanguageCode();
		Issue issue = understand.check(
				selectElement(html, HTML));
		assertNull(issue, "Expeced no error for valid ISO country code");
	}

	@Test
	public void testHtmlHasValidLanguageCodeError() {
		// try and country code rather than a language
		String html = "<html lang=\"gb\"><body></body></html>";
		Element target = selectElement(html, HTML);
		HtmlHasValidLanguageCode understand =
				new HtmlHasValidLanguageCode();
		Issue result = understand.check(target);
		testError(result, target, Severity.ERROR,
				"Expected error when using invalid language code");
	}

	@Test
	public void testFieldsetHasLegendOk() {
		String html = "<html><body>" +
				"<fieldset><legend>Some stuff</legend></fieldset>" +
				"</body></html>";
		FieldsetHasLegend understand = new FieldsetHasLegend();
		Issue issue = understand.check(
				selectElement(html, FIELDSET));
		assertNull(issue, "Expected no error as fieldset has legend");
	}

	@Test
	public void testFieldsetHasLegendError() {
		String html = "<html><body>" +
		"<fieldset>There ain't no legend...</fieldset>" +
		"</body></html>";
		Element target = selectElement(html, FIELDSET);
		FieldsetHasLegend understand = new FieldsetHasLegend();
		Issue result = understand.check(target);
		testError(result, target, Severity.ERROR,
				"Expected error for fieldset without legend");
	}
	
	@Test
	public void testControlHasDescriptorError() {
		String html = "<html><body>" +
				"<form>" +
				"<input id=somesuch type=text name=boxyboxy></input>" +
				"</form>" +
				"</body></html>";
		Element target = selectElement(html, INPUT);
		ControlHasDescription understand = new ControlHasDescription();
		Issue result = understand.check(target);
		testError(result, target, Severity.ERROR,
				"Expected error for control without description");
	}
	
	@DataProvider(name = "okControl")
	Object[][] okControl() {
		return new Object[][]{
				{"<html><body>" +
					"<form>" +
					"<input id=somesuch type=text name=boxyboxy></input>" +
					"<label for=somesuch  >Wouldn't you like to know</label>" +
					"</form>" +
					"</body></html>"},
				{"<html><body>" +
					"<form>" +
					"<input id=somesuch type=text name=boxyboxy " +
						"title=\"Some titles are better than others\">" +
						"</input>" +
					"</form>" +
					"</body></html>"}};
	}
	
	@Test(dataProvider = "okControl")
	public void testContolHasDescriptorOk(String html) {
		ControlHasDescription understand = new ControlHasDescription();
		Issue result = understand.check(
				selectElement(html, INPUT));
		assertNull(result, "Expected no error for labelled control");
	}

	@DataProvider(name = "imageInput")
	Object[][] imageControl() {
		return new Object[][]{
				{"<html><body>" +
					"<form>" +
					"<input id=imagery type=image name=im src=\"\" " +
						"alt=\"there is an alt here\"></input>" +
					"</form>" +
					"</body></html>"},
				{"<html><body>" +
					"<form>" +
					"<input id=imagery type=image name=im src=\"\" " +
						"alt=\"there is an alt here\"></input>" +
					"</form>" +
					"</body></html>"}};
	}
	
	@Test(dataProvider = "imageInput")
	public void testImageInputHasDescriptionOk(String html) {
		ImageInputHasDescription understand = new ImageInputHasDescription();
		Issue result = understand.check(
				selectElement(html, INPUT));
		assertNull(result, "Expected no error as image input has description");
	}

	@Test
	public void testImageInputHasDescriptionError() {
		String html = "<html><body>" +
				"<form>" +
				"<input id=imagery type=image name=im src=\"\" " +
					"></input>" +
				"</form>" +
				"</body></html>";
		Element target = selectElement(html, INPUT);
		ImageInputHasDescription understand = new ImageInputHasDescription();
		Issue result = understand.check(target);
		testError(result, target, Severity.ERROR,
				"Expected error for image input without description");
	}

	@Test
	public void testButtonHasContentError() {
		String html = "<html><body>" +
				"<button type=button></button>" +
				"</body></html>";
		Element target = selectElement(html, BUTTON);
		ButtonHasContent understand = new ButtonHasContent();
		Issue result = understand.check(target);
		testError(result, target, Severity.ERROR,
				"Expected error on empty button");
	}

	@DataProvider(name = "okButton")
	Object[][] okButton() {
		return new Object[][] {
				{"<html><body>" +
					"<button type=button >Text</button>" +
					"</body></html>"},
				{"<html><body>" +
					"<button type=button><img src=/img.png></img></button>" +
					"</body></html>"},
				{"<html><body>" +
					"<button type=button><div id=c /></button>" +
					"</body></html>"},
		};
	}
	@Test(dataProvider = "okButton")
	public void testButtonHasContentOk(String html) {
		ButtonHasContent understand = new ButtonHasContent();
		Issue result = understand.check(
				selectElement(html, BUTTON));
		assertNull(result, "Expected no error as button has content");
	}

	@DataProvider(name = "badDecription")
	Object[][] badDescription() {
		return new Object[][]{
				{"<html><body><label></label></body></html>"},
				{"<html><body><label /></body></html>"},
				{"<html><body><legend /></body></html>"},
				{"<html><body><legend></legend></body></html>"}};
	}

	@Test(dataProvider = "badDescription")
	public void testDescriptionHasTextError(String html) {
		Element target = selectElement(html, LABEL + ", " + LEGEND);
		DescriptionHasText understand = new DescriptionHasText();
		Issue result = understand.check(target);
		testError(result, target, Severity.ERROR,
				"Expected error for empty description");
	}

	@DataProvider(name = "okDecription")
	Object[][] okDescription() {
		return new Object[][]{
				{"<html><body><label>Test</label></body></html>"},
				{"<html><body><legend>This</legend></body></html>"}};
	}

	@Test(dataProvider = "okDescription")
	public void testDescriptionHasTextOk(String html) {
		DescriptionHasText understand = new DescriptionHasText();
		Issue result = understand.check(
				selectElement(html, LABEL + ", " + LEGEND));
		assertNull(result, "Expected no error for OK description");
	}

	@Test
	public void testControlIdUniqueError() {
		String html = "<html><body><form><input id=thesame type=text ></input>" +
				"<input type=text id=thesame ></input></form></body></html>";
		Element target = selectElement(html, INPUT);
		ControlIdUnique understand = new ControlIdUnique();
		Issue result = understand.check(target);
		testError(result, target, Severity.ERROR,
				"Expected error on duplicate input control id");
	}

	@Test
	public void testControlIdUniqueOk() {
		String html = "<html><body><form><input id=abc type=text ></input>" +
				"<input type=text id=sesameStreet ></input></form></body></html>";
		ControlIdUnique understand = new ControlIdUnique();
		Issue result = understand.check(
				selectElement(html, INPUT));
		assertNull(result, "Expected no error for inputs with different id");
	}
}
