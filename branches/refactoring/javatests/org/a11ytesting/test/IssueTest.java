package org.a11ytesting.test;

import static org.testng.Assert.assertEquals;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.testng.annotations.Test;

import org.a11ytesting.test.Issue.Severity;

/**
 * Basic tests for the issue class.
 * 
 * @author dallison
 */
public class IssueTest {
	
	private static final String NAME = "Erm, there was an issue",
			DESCRIPTION = "like, er, something is broken";
	private final Severity severity = Severity.WARNING;
	private final Element doc = Jsoup.parse("<html><body><p>Im a body</p>" +
			"</body></html>");
	private Issue testIssue = new Issue(NAME, DESCRIPTION, severity, doc);
	
	@Test
	public void testName() {
		assertEquals(testIssue.getTestName(), NAME);
	}
	
	@Test
	public void testDescription() {
		assertEquals(testIssue.getDescription(), DESCRIPTION);
	}
	
	@Test
	public void testSeverity() {
		assertEquals(testIssue.getSeverity(), severity);
	}
	
	@Test
	public void testElement() {
		assertEquals(testIssue.getElement(), doc);
	}
	
	@Test
	// @todo(dallison) Fix this brittle test which relies on the jsoup outer html
	public void testStringEncoding() {
		assertEquals(testIssue.toString(),
				"{\"testName\":\"" + NAME + "\",\"description\":\"" +
				DESCRIPTION + "\",\"severity\":\"warning\",\"elementCode\":\"" +
				"<html>\\n <head></head>\\n <body>\\n  <p>Im a body</p>\\n </body>\\n</html>\"}");
	}
}