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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import static org.a11ytesting.test.wcag.SharedTest.*;
import static org.a11ytesting.test.wcag.Shared.getRootElement;

import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Tests for WCAG Operable aspects.
 * 
 * @author dallison
 */
public class OperableTest {
	
	private static final String LONG_TITLE = "<html><head><title>" +
	"SOME VERY LONG AND EXTREMELY BORING TITLE THAT NO SANE PERSON " +
	"WOULD EVER WANT TO READ, FOR GOODNESS SAKE, CAN YOU EVEN " +
	"BELIEVE A BROWSER WOULD HAVE A CHANCE OF RENDERING THIS CRAZY " +
	"TITLE IN A SENSIBLE WAY WITHOUT USING SOME HORRIBLE MARQEE OR SUCH" +
	"</title></head></html>";

	@Test
	public void testMouseoverAndFocusError() {
		String onClickHtmlBad = "<html><body onmouseover=\"alert('ok')\">" +
				"</body></html>";
		OnMouseoverAndOnFocus operable = new OnMouseoverAndOnFocus();
		Element target = selectElement(onClickHtmlBad, BODY);
		Issue result = operable.check(target);
		testError(result, target, Severity.ERROR,
				"Expected error for onmouseover with no on " +
				"focus");
	}

	@Test
	public void testMouseOverAndFocusOk() {
		String onClickHtmlOK = "<body onmouseover=\"alert('ok')\" " +
				"onfocus=\"alert('done')\"></body>";
		OnMouseoverAndOnFocus operable = new OnMouseoverAndOnFocus();
		Element target = selectElement(onClickHtmlOK, BODY);
		Issue result = operable.check(target);
		assertNull(result, "Expected no error for onmouseover and on focus " +
				"element");
	}

	@Test
	public void testMouseOutAndBlurError() {
		String onOutAndBlurBad = "<html><body onmouseout=\"alert('hi')\">" +
				"</body></html>";
		OnMouseOutAndOnBlur operable = new OnMouseOutAndOnBlur();
		Element target = selectElement(onOutAndBlurBad, BODY);
		Issue result = operable.check(target);
		testError(result, target, Severity.ERROR,
				"Expected error for mouse out with no on" +
				"blur");
	}

	@Test
	public void testMouseOutAndBlurOk() {
		String onOutAndBlurOk = "<html><body onmouseout=\"alert('okdokie')\" " +
				"onblur=\"alert('all is well')\"></body></html>";
		OnMouseOutAndOnBlur operable = new OnMouseOutAndOnBlur();
		Element target = selectElement(onOutAndBlurOk, BODY);
		Issue result = operable.check(target);
		assertNull(result, "Expected no error for on mouse out and on blur" +
				"element");
	}

	@Test
	public void testOnClickIsFocusableError() {
		String onClickNotFocusable = "<html><body onclick=\"alert('hi')\">" +
				"</body></html";
		OnClickIsFocusable operable = new OnClickIsFocusable();
		Element target = selectElement(onClickNotFocusable, BODY);
		Issue result = operable.check(target);
		testError(result, target, Severity.ERROR,
				"Expected error for mouse on click on non " +
				"focusable element");
	}
	
	@DataProvider(name = "onClickOk")
	public Object[][] onClickOk() {
		return new Object[][]{
				{"<html><body><form><input type=text onclick=\"\" >" +
					"</input></form></body></html>", INPUT},
				{"<html><body><form><button type=button onclick=\"\" >" +
						"</button></form></body></html>", BUTTON},
				{"<html><body><form><select onclick=\"\" >" +
						"<option value=testvalue >Test</option></select>" +
						"</button></form></body></html>", SELECT},
				{"<html><body><form>" +
						"<textarea onclick=\"\" >Some Text</textarea>" +
						"</form></body></html>", TEXTAREA},
				{"<html><body><a href=#test onclick=\"\" ></a>" +
						"</body></html>", ANCHOR}};
	}

	@Test(dataProvider = "onClickOk")
	public void testOnClickIsFocusableOk(String html, String select) {
		OnClickIsFocusable operable = new OnClickIsFocusable();
		Element target = selectElement(html, select);
		Issue result = operable.check(target);
		assertNull(result, "Expected no error for on mouse with focusable " +
				"input element");
	}

	@Test
	public void testOnClickIsFocusableAnchorTabindex() {
		String onClickFocusable = "<html><body><a tabindex=0 ></a>" +
				"</body></html>";
		OnClickIsFocusable operable = new OnClickIsFocusable();
		Element target = selectElement(onClickFocusable, ANCHOR);
		Issue result = operable.check(target);
		assertNull(result, "Expected no error for on mouse with focusable " +
				"anchor element");
	}

	@Test
	public void testSelectOnChangeError() {
		String html = "<html><body><form><select onchange=\"alert('bad')\" " +
				"><option value=t >v</option></select></form></body></html>";
		SelectNotOnChange operable = new SelectNotOnChange();
		Element target = selectElement(html, SELECT);
		Issue result = operable.check(target);
		testError(result, target, Severity.ERROR,
				"Expected error for on change event on " +
				"select element");
	}
	
	@Test
	public void testSelectOnChangeOk() {
		String html = "<html><body><form><select> " +
				"<option value=t >v</option></select></form></body></html>";
		SelectNotOnChange operable = new SelectNotOnChange();
		Element target = selectElement(html, SELECT);
		Issue result = operable.check(target);
		assertNull(result, "Expected error for on change event on " +
				"select element");
	}

	@Test
	public void testMouseDownHasKeyEquivalentError() {
		String html = "<html><body onmousedown=\"alert('mhah')\">" +
				"</body></html>";
		MouseDownEventHasKeyEquivalent operable = 
				new MouseDownEventHasKeyEquivalent();
		Element target = selectElement(html, BODY);
		Issue result = operable.check(target);
		testError(result, target, Severity.ERROR,
				"Expected an error for element with mouse " +
				"down with no key down");
	}

	@Test
	public void testMouseDownHasKeyEquivalentOk() {
		String html = "<html><body onmousedown=\"alert('mhah')\" " +
				"onkeydown=\"alert('woot')\">" +
				"</body></html>";
		MouseDownEventHasKeyEquivalent operable =
				new MouseDownEventHasKeyEquivalent();
		Element target = selectElement(html, BODY);
		Issue result = operable.check(target);
		assertNull(result, "Expected no error with valid element with mouse " +
				"down and key down");
	}

	@Test
	public void testMouseUpHasKeyEquivalentError() {
		String html = "<html><body onmouseup=\"alert('mhah')\">" +
				"</body></html>";
		MouseUpEventHasKeyEquivalent operable = new MouseUpEventHasKeyEquivalent();
		Element target = selectElement(html, BODY);
		Issue result = operable.check(target);
		testError(result, target, Severity.ERROR,
				"Expected an error for element with mouse " +
				"up with no key down");
	}

	@Test
	public void testMouseUpHasKeyEquivalentOk() {
		String html = "<html><body onmouseup=\"alert('mhah')\" " +
				"onkeyup=\"alert('woot')\">" +
				"</body></html>";
		MouseUpEventHasKeyEquivalent operable = new MouseUpEventHasKeyEquivalent();
		Element target = selectElement(html, BODY);
		Issue result = operable.check(target);
		assertNull(result, "Expected no error with valid element with mouse " +
				"up and key up");
	}
	
	@Test
	public void testNonInteractiveElementsWithEventHasRoleError() {
		String interactiveNoRole = "<html><body onmouseup=\"alert('mhah')\" " +
				"onkeyup=\"alert('woot')\">" +
				"</body></html>";
		NonInteractiveElementWithEventHasRole operable =
				new NonInteractiveElementWithEventHasRole();
		Element target = selectElement(interactiveNoRole, BODY);
		Issue result =
				operable.check(target);
		testError(result, target, Severity.ERROR,
				"Expected error for non interactive element " +
				"without aria role");
	}
	
	@Test
	public void testNonInteractiveElementWithEventHasRoleOk() {
		String interactiveNoRole = "<html><body onmouseup=\"alert('mhah')\" " +
				"onkeyup=\"alert('woot')\" role=\"link\" >" +
				"</body></html>";
		NonInteractiveElementWithEventHasRole operable =
				new NonInteractiveElementWithEventHasRole();
		Element target = selectElement(interactiveNoRole, BODY);
		Issue result =
				operable.check(target);
		assertNull(result, "Expected no error for interactive element " +
				"with aria role");
	}
	
	@DataProvider(name = "mouseEventHtmlOk")
	public Object[][] getMouseEventHtmlOk() {
		String[] events =  {"onclick", "onmousedown", "onmouseup",
				"onmousemove", "onmouseout", "onmouseover"};
		Object[][] result = new Object[events.length][1];
		for (int i = 0; i < events.length; i++) {
			result[i][0] = "<html><body " + events[i] + "=\"alert('mhah')\" " +
				// just use any old on key event
				"onkeypress=\"alert('woot')\" role=\"link\" >" +
				"</body></html>";
		}
		return result;
	}
	
	@Test(dataProvider = "mouseEventHtmlOk")
	public void testCheckMouseEventElementHasKeyboardEventOk(String html) {
		Element target = selectElement(html, BODY);
		MouseEventHasKeyboardEvent operable = new MouseEventHasKeyboardEvent();
		Issue result = operable.check(target);
		assertNull(result, "Expected no error from mouse event that also " +
				"has keyboard event");
	}
	
	@DataProvider(name = "mouseEventHtmlBad")
	public Object[][] getMouseEventHtmlBad() {
		String[] events =  {"onclick", "onmousedown", "onmouseup",
				"onmousemove", "onmouseout", "onmouseover"};
		Object[][] result = new Object[events.length][1];
		for (int i = 0; i < events.length; i++) {
			result[i][0] = "<html><body " + events[i] +
					"=\"alert('mhah')\" ></body></html>";
		}
		return result;
	}

	@Test(dataProvider = "mouseEventHtmlBad")
	public void testCheckMouseEventElementHasKeyboardEventBad(String html) {
		Element target = selectElement(html, BODY);
		MouseEventHasKeyboardEvent operable = new MouseEventHasKeyboardEvent();
		Issue result = operable.check(target);
		testError(result, target, Severity.ERROR,
				"Expected error for element with mouse event" +
				"but no keyboard handler");
	}
	
	@Test
	public void testAccessKeyValueUniqueError() {
		String html = "<html><body><a href=\"#someplace\" accesskey=\"t\">" +
				"T</a><a href=\"#other\" accesskey=\"t\"></a></body></html>";
		AccessKeyValueUnique operable = new AccessKeyValueUnique();
		Element target = selectElement(html, ANCHOR);
		Issue result = operable.check(target);
		testError(result, target, Severity.ERROR,
				"Expected issue due to duplicate access key");
	}

	@Test
	public void testAccessKeyValueUniqueOk() {
		String html = "<html><body><a href=\"#someplace\" accesskey=\"t\">" +
		"T</a><a href=\"#other\" accesskey=\"l\"></a></body></html>";
		AccessKeyValueUnique operable = new AccessKeyValueUnique();
		Element target = selectElement(html, ANCHOR);
		Issue result = operable.check(target);
		assertNull(result, "Expected no error for unique access keys");
	}
	
	@DataProvider(name = "ieAccessKeysBad")
	public Object[][] getIeAccessKeysBad() {
		String[] ieKeys = {"a", "e", "f", "h", "t", "v"};
		Object[][] result = new Object[ieKeys.length][1];
		for (int i = 0; i < ieKeys.length; i++) {
			result[i][0] = "<html><body>" +
					"<a href=\"#someplace\" accesskey=\"" + ieKeys[i] +
					"\">T</a></body></html>";
		}
		return result;
	}

	@Test(dataProvider = "ieAccessKeysBad")
	public void testIeReservedAccessKeyValueNotUsed(String html) {
		Element target = selectElement(html, ANCHOR);
		IeReservedAccessKeyValueNotUsed operable = new IeReservedAccessKeyValueNotUsed();
		Issue result = operable.check(target);
		testError(result, target, Severity.ERROR,
				"Expected error using IE reserved access key");
	}
	
	@Test
	public void testMarqeeTextError() {
		String html = "<html><body><marqee>Some silly scrolling text" +
				"</marqee></body></html>";
		ActiveTextElementNotPresent operable = 
				new ActiveTextElementNotPresent();
		Element target = selectElement(html, MARQEE);
		Issue result = operable.check(target);
		testError(result, target, Severity.ERROR,
				"Expected error using marqee text");
	}

	@Test
	public void testBlinkingTextError() {
		String html = "<html><body><blink>Some annoying blinking text" +
				"</blink></body></html>";
		ActiveTextElementNotPresent operable =
				new ActiveTextElementNotPresent();
		Element target = selectElement(html, BLINK);
		Issue result = operable.check(target);
		testError(result, target, Severity.ERROR,
				"Expected error using blinking text");
	}
	
	@Test
	public void testFrameHasTitleError() {
		String html = "<html><body><iframe ></iframe>" +
				"</body><html>";
		FrameHasTitle operable = new FrameHasTitle();
		Element target = selectElement(html, IFRAME);
		Issue result = operable.check(target);
		testError(result, target, Severity.ERROR,
				"Expected error for frame with no title");
	}
	
	@Test
	public void testFrameHasTitleOk() {
		String html = "<html><body><iframe title=\"sometitle\"></iframe>" +
				"</body><html>";
		FrameHasTitle operable = new FrameHasTitle();
		Element target = selectElement(html, IFRAME);
		Issue result = operable.check(target);
		assertNull(result, "Expected no error for frame with title");
	}

	@Test
	public void testFrameTitleUniqueError() {
		String html = "<html><body><iframe title=\"sometitle\"></iframe>" +
				"<iframe title=\"sometitle\"></iframe>" +
				"</body><html>";
		FrameTitleUnique operable = new FrameTitleUnique();
		Element target = selectElement(html, IFRAME);
		Issue result = operable.check(target);
		testError(result, target, Severity.ERROR,
				"Expected error on duplicate frame title");
	}

	@Test
	public void testFrameTitleUniqueOk() {
		String html = "<html><body><iframe title=\"sometitle\"></iframe>" +
				"<iframe title=\"othertitle\"></iframe>" +
				"</body><html>";
		FrameTitleUnique operable = new FrameTitleUnique();
		Element target = selectElement(html, IFRAME);
		Issue result = operable.check(target);
		assertNull(result, "Expected no error for non duplicate frame");
	}

	@DataProvider(name = "emptyTitle")
	Object[][] emptyTitle() {
		return new Object[][] {
				{"<html><head></head></html>"},
				{"<html><head><title></title></head></html>"},
				{"<html><head><title> </title></head></html>"},
				{"<HTML><body></body></html>"},
				{"<html><body><title>Some value</title></body></html>"}};
	}
	
	@Test(dataProvider = "emptyTitle")
	public void testCheckTitleIsNotEmptyError(String html) {
		TitleIsNotEmpty operable = new TitleIsNotEmpty();
		Element target = selectElement(html, HTML);
		Issue result = operable.check(target);
		testError(result, target, Severity.ERROR,
				"Expected error for empty title");
	}
	
	@Test
	public void testCheckTitleIsNotEmptyOk() {
		String html = "<html><head><title>This is OK for me</title>" +
				"</head></html>";
		TitleIsNotEmpty operable = new TitleIsNotEmpty();
		Element target = selectElement(html, HTML);
		Issue result = operable.check(target);
		assertNull(result, "Expected no error for non empty title");
	}

	@Test
	public void testTitleIsConciseError() {
		Element target = selectElement(LONG_TITLE, TITLE);
		TitleIsConcise operable = new TitleIsConcise();
		Issue result = operable.check(target);
		testError(result, target, Severity.ERROR,
				"Expected error for non concise title");
	}

	@Test
	public void testCheckTitleIsConciseOk() {
		String html = "<html><head><title>This is OK for me</title>" +
				"</head></html>";
		TitleIsConcise operable = new TitleIsConcise();
		Element target = selectElement(html, TITLE);
		Issue result = operable.check(target);
		assertNull(result, "Expected no error for concise title");
	}
	
	@Test
	public void testTitleHasMoreThanOneWordError() {
		String html = "<html><head><title>Bad</title></head></html>";
		TitleHasEnoughWord operable = new TitleHasEnoughWord();
		Element target = selectElement(html, TITLE);
		Issue result = operable.check(target);
		testError(result, target, Severity.ERROR,
				"Expected error for title with a single word");
	}
	
	@Test
	public void testTitleHasMoreThanOneWordOk() {
		Element target = selectElement(LONG_TITLE, TITLE);
		TitleHasEnoughWord operable = new TitleHasEnoughWord();
		Issue result = operable.check(target);
		assertNull(result, "Expect no error for title with more than one " + 
				"word");
	}
	
	@DataProvider(name = "repeatedLinkText")
	public Object[][] repeatedLinkText() {
		return new Object[][] {
				{"<html><body>" + 
						"<a href=\"www.google.com\">Google</a>" +
						"<a href=\"www.facebook.com\">Google</a>" +
						"</body></html>"},
				{"<html><body>" +
						"<a href=\"www.google.com\">Google</a>" +
						"<a href=\"www.linkedin.com\">" +
						"<img src=\"./linkedin.png\" alt=\"Google\"></img>" +
						"</a>" +
						"</body></html>"},
				{"<html><body>" +
						"<a href=\"www.google.com\">Google A</a>" +
						"<a href=\"twitter.com\">Google   " +
						"<img src=\"./aBirdie.png\" alt=\" A \"></img>" +
						"</a>" +
						"</body></html>"},
		};
	}

	@Test(dataProvider = "repeatedLinkText")
	public void testLinkTextNotReplicatedError(String html) {
		Element target = selectElement(html, ANCHOR);
		LinkTextNotReplicated operable = new LinkTextNotReplicated();
		Issue result = operable.check(target);
		testError(result, target, Severity.ERROR,
				"Expected error for same text with different target");
	}
	
	@DataProvider(name = "nonRepeatedLinkText")
	Object[][] nonRepeatedLinkText() {
		return new Object[][] {
				{"<html><body>" +
					"<a href=\"www.google.com\">Home</a>" +
					"<a href=\"www.google.com\">   " +
					"<img src=\"./aBirdie.png\" alt=\"Homepage\"></img>" +
					"</a>" +
					"</body></html>"},
				// catch bug where indicating duplicate of self
				{"<html><body>" +
						"<a href=\"www.google.com\">Home</a>" +
						"</body></html>"}};
	}

  @DataProvider(name = "mixedLinks")
  Object[][] pagesWithMixedAbsoluteAndRelativeLinks() {
    return new Object[][] {
      {"<html><body>" +
        "<a href=\"http://www.google.com/somepage.html\">Page</a>" +
        "<a href=\"/somepage.html\">Page</a>" +
        "</body></html>"}};
  }
  
  @DataProvider(name = "allRelativeLinks")
  Object[][] pagesWithAllAbsoluteAndRelativeLinks() {
    return new Object[][] {
      {"<html><body>" +
        "<a href=\"/somepage.html\">Page</a>" +
        "<a href=\"/somepageElse.html\">Page</a>" +
        "</body></html>"}};
  }

  @Test(dataProvider = "allRelativeLinks")
  public void testDocumentRootEmptyWhenNoneSet(String html) {
    Element link = selectElement(html, ANCHOR);
    assertEquals(link.baseUri(), "", "Expected empty base uri"); // check that the base is null
    assertEquals(link.absUrl("href"), "", "Expected empty absUrl"); // and that the abs is also null
  }

  @Test(dataProvider = "mixedLinks")
  public void testRelativeLinksResolvedAbsolutely(String html) {
    Document doc = Jsoup.parse(html, "http://www.google.com/");
    doc.setBaseUri("http://www.google.com/");
    for (Element link : doc.select("a")) {
      assertEquals(link.absUrl("href"), "http://www.google.com/somepage.html",
          "Expected all links including relative to resolve to same url");
    }
  }


  @Test(dataProvider = "mixedLinks")
  public void testLinkTextNotDuplicatedRelativeMixed(String html) {
    LinkTextNotReplicated test = new LinkTextNotReplicated();
    Document doc = Jsoup.parse(html, "http://www.google.com/");
    for (Element link : doc.select("a")) {
      Issue result = test.check(link);
      assertNull(result, "Expected use of absolute url to avoid mixed " +
          "relative and absolute url where same");
    }
  }
	
	@Test(dataProvider = "nonRepeatedLinkText")
	public void testLinkTextNotReplicatedOk(String html) {
		Element target = selectElement(html, ANCHOR);
		LinkTextNotReplicated operable = new LinkTextNotReplicated();
		Issue result = operable.check(target);
		assertNull(result, "Expected no error for alternate link text" +
				"both targeting same page");
	}
	
	@Test
	public void testContainedText() {
		String html = "<html><body><a href=\"#some\">Text First" +
				"<img alt=\"Alt Second\" src=\"./img.png\"></img></a>" +
				"Text Third" +
				"<div><img alt=\"Alt  Fourth  \"></img></div>" +
				"</body></html>";
		Element link = selectElement(html, BODY);
		assertEquals(Shared.containedText(link), "Text First Alt Second " +
				"Text Third Alt Fourth");
	}
}
