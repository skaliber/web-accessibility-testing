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

import static org.a11ytesting.test.wcag.SharedTest.IMG;
import static org.a11ytesting.test.wcag.SharedTest.TABLE;
import static org.a11ytesting.test.wcag.SharedTest.selectElement;
import static org.a11ytesting.test.wcag.SharedTest.testError;
import static org.testng.Assert.assertNull;

import org.a11ytesting.test.HtmlVersion;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;
import org.jsoup.nodes.Element;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Tests for WCAG perceivable aspects.
 *  
 * @author dallison
 */
public class PerceivableTest {

	@DataProvider(name = "imageNoAlt")
	Object[][] imageNoAlt() {
		return new Object[][]{
				{"<html><body><img src=\"./img.png\">" + 
					"</img></body></html>"},
				{"<html><body><img src=\"./img.png\" title=\"\">" + 
						"</img></body></html>"}
		};
	}
	
	@DataProvider(name = "imageOkEmptyAlt")
	Object[][] imageEmptyAlt() {
		return new Object[][]{
				{"<html><body><img src=\"./img.png\" alt =\"\">" + 
					"</img></body></html>"},
				{"<html><body><img src=\"./img.png\" title=\"\" alt =\"\">" + 
						"</img></body></html>"}
		};
	}

	@Test(dataProvider = "imageNoAlt")
	public void testAltTextOnImageError(String html) {
		Element target = selectElement(html, IMG);
		AltTextOnImage perceivable = new AltTextOnImage();
		Issue result = perceivable.check(HtmlVersion.UNKNOWN, target);
		testError(result, target, Severity.ERROR,
				"Expected error for image with no alt text");
	}

	@DataProvider(name = "imageOk")
	Object[][] imageOk() {
		return new Object[][] {
				{"<html><body><img src=\"./img.png\" alt=\"short text\">" + 
					"</img></body></html>"},
				{"<html><body><img src=\"./img.png\" alt=\"longer alt text" +
						"\"></img></body></html>"},
				{"<html><body><img src=\"./img.png\" role=\"presentation\">" + 
							"</img></body></html>"},
				{"<html><body><img src=\"./img.png\" alt=\"" +
							"012345678901234567890123456789" +
							"012345678901234567890123456789" +
							"012345678901234567890123456789" +
							"\"></img></body></html>"}};
	}

	@Test(dataProvider = "imageOk")
	public void testAltTextOnImageOk(String html) {
		AltTextOnImage perceivable = new AltTextOnImage();
		Issue result = perceivable.check(HtmlVersion.UNKNOWN, selectElement(html,
				IMG));
		assertNull(result, "Expected no error from image with alternative" +
				"text");
	}
	
	@Test(dataProvider = "imageOkEmptyAlt")
	public void testEmptyAltTextOnImageOk(String html) {
		AltTextOnImage perceivable = new AltTextOnImage();
		Issue result = perceivable.check(HtmlVersion.UNKNOWN, selectElement(html,
				IMG));
		assertNull(result, "Expected no error from image with alternative" +
				"text");
	}

	@DataProvider(name = "badAltText")
	Object[][] badAltText() {
		return new Object[][] {
				{"<html><body><img src=\"./img.png\" alt=\"image\">" + 
						"</img></body></html>"},
				{"<html><body><img src=\"./img.png\" alt=\"picture\">" + 
						"</img></body></html>"},
				{"<html><body><img src=\"./img.png\" alt=\"graph\">" + 
							"</img></body></html>"},
				{"<html><body><img src=\"./img.png\" alt=\"photo\">" + 
							"</img></body></html>"}};
	}
	
	@Test(dataProvider = "badAltText")
	public void testAltTextOnImageNotBadError(String html) {
		Element target = selectElement(html, IMG);
		AltTextOnImageNotBad perceivable = new AltTextOnImageNotBad();
		Issue result = perceivable.check(HtmlVersion.UNKNOWN, target);
		testError(result, target, Severity.ERROR,
				"Expected error for bad image alt text value");
	}

	@Test(dataProvider = "imageOk")
	public void testAltTextOnImageNotBadOk(String html) {
		AltTextOnImageNotBad perceivable = new AltTextOnImageNotBad();
		Issue result = perceivable.check(
				null, selectElement(html, IMG));
		assertNull(result, "Expected no error for valid alt text when" +
				"checking for bad alt text values");
	}
	
	@DataProvider(name = "altLengthBad")
	Object[][] altLengthBad() {
		return new Object[][] {
				{"<html><body><img src=\"./img.png\" alt=\"shrt\">" + 
						"</img></body></html>"},
				{"<html><body><img src=\"./img.png\" alt=\"123456\">" + 
						"</img></body></html>"},
				{"<html><body><img src=\"./img.png\" alt=\"" +
							"012345678901234567890123456789" +
							"012345678901234567890123456789" +
							"012345678901234567890123456789" +
							"0" +
							"\"></img></body></html>"}};
	}
	
	@Test(dataProvider = "altLengthBad")
	public void testAltTextLengthReasonableBad(String html) {
		Element target = selectElement(html, IMG);
		AltTextLengthReasonable perceivable = new AltTextLengthReasonable();
		Issue result = perceivable.check(HtmlVersion.UNKNOWN, target);
		testError(result, target, Severity.WARNING,
				"Expected error for bad length alt text");
	}
	
	@Test(dataProvider = "imageOk")
	public void testAltTextLengthReasonableOk(String html) {
		AltTextLengthReasonable perceivable = new AltTextLengthReasonable();
		Issue result = perceivable.check(
				null, selectElement(html, IMG));
		assertNull(result, "Expected no error for OK length alt text");
	}

	@Test
	public void testInvisibleImageNoTitleError() {
		String html = "<html><body><img src=\"./img.png\" title=\"foo\" " + 
		"role=\"presentation\"></img></body></html>";
		Element target = selectElement(html, IMG);
		InvisibleImageNoTitle perceivable = new InvisibleImageNoTitle();
		Issue result = perceivable.check(HtmlVersion.UNKNOWN, target);
		testError(result, target, Severity.ERROR,
				"Expected error for invisible element with title");
	}
	
	@Test
	public void testInvisibleImageNoTitleOk() {
		String html = "<html><body><img src=\"./img.png\" " + 
			"role=\"presentation\"></img></body></html>";
		InvisibleImageNoTitle perceivable = new InvisibleImageNoTitle();
		Issue result = perceivable.check(
				null, selectElement(html, IMG));
		assertNull(result, "Expected no error for invisible image " +
				"without title");
	}
	
	@DataProvider(name = "badTable")
	Object[][] badTable() {
		return new Object[][] {
				{"<html><body>" +
				"<table>" +
				"<tr><td>Nothing to say</td></tr>" +
				"</table>" +
				"</body><html>"}
		};
	}

	@Test(dataProvider = "badTable")
	public void testTableHasSummaryError(String html) {
		Element target = selectElement(html, TABLE);
		TableHasSummaryAttribute perceivable = new TableHasSummaryAttribute();
		Issue result = perceivable.check(HtmlVersion.UNKNOWN, target);
		testError(result, target, Severity.ERROR, 
				"Exepcted error for table without summary");
	}

	@Test
	public void testTableHasSummaryOK() {
		String html = "<html><body>" +
				"<table summary=\"Table of nil\">" +
				"<tr><td>Nothing to say</td></tr>" +
				"</table>" +
				"</body><html>";
		TableHasSummaryAttribute perceivable = new TableHasSummaryAttribute();
		Issue result = perceivable.check(
				HtmlVersion.UNKNOWN, selectElement(html, TABLE));
		assertNull(result, "Expected no error for table with summary");
	}

	@Test(dataProvider = "badTable")
	public void testTableHasHeadingsError(String html) {
		Element target = selectElement(html, TABLE);
		TableHasHeadings perceivable = new TableHasHeadings();
		Issue result = perceivable.check(HtmlVersion.UNKNOWN, target);
		testError(result, target, Severity.ERROR,
				"Expected error for table without headers");
	}
	
	@Test
	public void testTableHasHeadingsOk() {
		String html = "<html><body>" +
				"<table>" +
				"<tr><th>I am a heading</th></tr>" +
				"<tr><td>Nothing to say</td></tr>" +
				"</table>" +
				"</body><html>";
		TableHasHeadings perceivable = new TableHasHeadings();
		Issue result = perceivable.check(
				null, selectElement(html, TABLE));
		assertNull(result, "Expected no error for table with headings");
	}

	@Test
	public void testTableSummaryUniqueError() {
		String html = "<html><body>" +
				"<table summary=\"Table of nil\">" +
				"<tr><td>Nothing to say</td></tr>" +
				"</table>" +
				"<table summary=\"Table of nil\">" +
				"<tr><td>Different stuff but same summary</td></tr>" +
				"</table>" +
				"</body><html>";
		Element target = selectElement(html, TABLE);
		TableSummaryUnique perceivable = new TableSummaryUnique();
		Issue result = perceivable.check(HtmlVersion.UNKNOWN, target);
		testError(result, target, Severity.ERROR,
				"Expected error for duplicate table summary");
	}

	@Test
	public void testTableSummaryUniqueOk() {
		String html = "<html><body>" +
				"<table summary=\"Table of nil\">" +
				"<tr><td>Nothing to say</td></tr>" +
				"</table>" +
				"<table summary=\"Table of null\">" +
				"<tr><td>Different stuff but same summary</td></tr>" +
				"</table>" +
				"</body><html>";
		TableSummaryUnique perceivable = new TableSummaryUnique();
		Issue result = perceivable.check(
				HtmlVersion.UNKNOWN, selectElement(html, TABLE));
		assertNull(result, "Expected no error for tables with unique" +
				"summary values");
	}
	
	@Test
	public void testTableSummaryHtml5() {
		String html = "<html><body>" + "<table summary=\"Table of nil\">"
				+ "<tr><td>Nothing to say</td></tr>" + "</table>"
				+ "<table summary=\"Table of nil\">"
				+ "<tr><td>Different stuff but same summary</td></tr>"
				+ "</table>" + "</body><html>";
		Element target = selectElement(html, TABLE);
		TableSummaryUnique perceivable = new TableSummaryUnique();
		Issue result = perceivable.check(HtmlVersion.HTML5, target);
		testError(result, target, Severity.WARNING,
				"html5 table summary is not supported");
	}

	@DataProvider(name = "badComplexTable")
	Object[][] badComplexTable() {
		return new Object[][]{
				{"<html><body>" +
				"<table summary=\"complex\">" +
				"<tr><th>hc1</th><th>hc2</th><th>hc3</th>" +
				"<tr><td colspan=2>Nothing to say</td><td>more</td></tr>" +
				"<tr><td>d1</td><td>d2</td><td>d3</td></tr>" +
				"</table>" +
				"</body><html>"},
				{"<html><body>" +
				"<table summary=\"complex\">" +
				"<tr><th>hc1</th><th>hc2</th><th>hc3</th></tr>" +
				"<tr><td rowspan=2>Nothing to say</td><td>v2</td><td>more</td></tr>" +
				"<tr><td>d2</td><td>d3</td></tr>" +
				"</table>" +
				"</body><html>"}};
	}
	
	@Test(dataProvider = "badComplexTable")
	public void testComplexTableHeadingHasIdError(String html) {
		Element target = selectElement(html, TABLE);
		ComplexTableHeadingHasId perceivable = new ComplexTableHeadingHasId();
		Issue result = perceivable.check(HtmlVersion.UNKNOWN, target);
		testError(result, target, Severity.ERROR,
				"Expected error for invalid complex table");
	}

	// @note The c\\d column id naming convention is used by tests rewriting
	// ids for certain test cases.
	@DataProvider(name = "okComplexTable")
	Object[][] okComplexTable() {
		return new Object[][]{
				{"<html><body>" +
				"<table summary=\"complex colspan\">" +
				"<tr><th id=c1 >hc1</th><th id=c2 >hc2</th>" +
					"<th id=c3 >hc3</th>" +
				"<tr><td colspan=2 headers=\"c1,c2\">Nothing to say</td>" +
					"<td headers=c3>more</td></tr>" +
				"<tr><td headers=c1>d1</td><td headers=c2>d2</td>" +
					"<td headers=c3>d3</td></tr>" +
				"</table>" +
				"</body><html>"},
				{"<html><body>" +
				"<table summary=\"complex rowspan\">" +
				"<tr><th id=c1>hc1</th><th id=c2>hc2</th>" +
					"<th id=c3>hc3</th></tr>" +
				"<tr><td rowspan=2 headers=c1>Nothing to say</td>" +
					"<td headers=c2>v2</td><td headers=c3>more</td></tr>" +
				"<tr><td headers=c2>d2</td><td headers=c3>d3</td></tr>" +
				"</table>" +
				"</body><html>"}};
	}

	@Test(dataProvider = "okComplexTable")
	public void testComplexTableheadngHasIdOk(String html) {
		ComplexTableHeadingHasId perceivable = new ComplexTableHeadingHasId();
		Issue issue = perceivable.check(
				null, selectElement(html, TABLE));
		assertNull(issue, "Expected no error for complex table with heading IDs");
	}
	
	@Test(dataProvider = "okComplexTable")
	public void testComplexTableHeadingIdUniqueError(String html) {
		// @note rewrite all column ids to same value
		String sameId = html.replaceAll("c\\d", "cDuplicate");
		Element target = selectElement(sameId, TABLE);
		ComplexTableHeadingIdUnique perceivable =
				new ComplexTableHeadingIdUnique();
		Issue result = perceivable.check(HtmlVersion.UNKNOWN, target);
		testError(result, target, Severity.ERROR,
				"Expected error for duplicate column id on complex table");
	}
	
	@Test(dataProvider = "okComplexTable")
	public void testComplexTableHeadingIdUniqueOk(String html) {
		ComplexTableHeadingIdUnique perceivable =
				new ComplexTableHeadingIdUnique();
		Issue result = perceivable.check(
				null, selectElement(html, TABLE));
		assertNull(result, "Expected no error for unique id on complex table");
	}
	
	@Test(dataProvider = "badComplexTable")
	public void testComplexTableDataHasHeadingError(String html) {
		Element target = selectElement(html, TABLE);
		ComplexTableDataHasHeading perceivable =
				new ComplexTableDataHasHeading();
		Issue result = perceivable.check(HtmlVersion.UNKNOWN, target);
		testError(result, target, Severity.ERROR,
				"Expected error for complex table with no data headings " +
				"reference");
	}

	@Test(dataProvider = "okComplexTable")
	public void testComplexTableDataHasHeadingOk(String html) {
		ComplexTableDataHasHeading perceivable =
				new ComplexTableDataHasHeading();
		Issue result = perceivable.check(
				null, selectElement(html, TABLE));
		assertNull(result, "Expected no error for complex data table with " +
				"data headings");
	}
}
