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
package org.a11ytesting.filter;

import org.a11ytesting.test.Filter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

public class FilterTestFactory {
	
	private static final String HTML = " <!DOCTYPE html> <html>" +
			"<head><title>Some things are just blah</title></head>" +
			"<body>" +
			"<a accesskey=y>FirstLink</a>" +
			"<div onclick=\"alert('hi')\">eat me</div>" +
			"<div onclick=\"alert('hi')\">drink me</div>" +
			"<a accesskey=r>SecondLink</a>" +
			"<div ondblclick=\"alert('badness');\">Interact</div>" +
			"<div onkeyup=\"alert('badness');\">Interact</div>" +
			"<div onkeydown=\"alert('badness');\">Interact</div>" +
			"<div onkeypress=\"alert('badness');\">Interact</div>" +
			"<div onmousedown=\"alert('badness');\">Interact</div>" +
			"<div onmouseup=\"alert('badness');\">Interact</div>" +
			"<div onmousemove=\"alert('badness');\">Interact</div>" +
			"<div onmouseout=\"alert('badness');\">Interact</div>" +
			"<div onmouseover=\"alert('badness');\">Interact</div>" +
			"<div onselect=\"alert('badness');\">Interact</div>" +
			"<div onchange=\"alert('badness');\">Interact</div>" +
			"<div onsubmit=\"alert('badness');\">Interact</div>" +
			"<div onreset=\"alert('badness');\">Interact</div>" +
			"<div onfocus=\"alert('badness');\">Interact</div>" +
			"<div onblur=\"alert('badness');\">Interact</div>" +
			"<form action=\"/\"><input type=submit />" +
				"<fieldset>" +
				"<input type=\"button\" />" +
				"<textarea>text</textarea>" +
				"</fieldset>" +
				"<input type=\"reset\" />" +
				"<button>click me</button>" +
			"</form>" +
			"<blink>I'm going to flash</blink>" +
			"<marquee>I'm going to scroll, classy eh?</marquee>" +
			"<iframe src=\"http://www.google.com/\" />" +
			"<frame src=\"http://www.google.com/\" />" +
			"<h1>Heading 1</h1>" + 
			"<h2>Heading 2</h2>" +
			"<h3>Heading 3</h3>" +
			"<h4>Heading 4</h4>" +
			"<img src=\"/nowhere.gif\" />" +
			"<select><option>a</option><option>b</option></select>" +
			"<table><tr><td>I'm a cell</td></tr></table>" +
			"</body></html>";
	private static final String EVENTS = "[onclick], " +
			"[ondblclick], " +
			"[onkeyup], [onkeydown], [onkeypress], [onmousedown], " + 
			"[onmouseup], [onmousemove], [onmouseout], [onmouseover], " +
			"[onselect], [onchange], [onsubmit], [onreset], [onfocus], " +
			"[onblur]";
	private static final String MOUSE_EVENT = "[onclick], " +
			"[onmousedown], [onmouseup], [onmousemove], [onmouseout], " +
			"[onmouseover]";
	private static final String FORM_CONTROLS = "input[type=button], " +
			"input[type=submit], input[type=reset]";
	private static final String INPUT_CONTROLS =
			"intput[type=checkbox], input[type=file], input[type=password], " +
			"input[type=image], input[type=radio], input[type=text], " +
			"select, textarea";
	
	@DataProvider(name = "filterConfig")
	Object[][] getFilterConfigs() {
		Document doc = Jsoup.parse(HTML);
		return new Object[][] {
				{new AccessKeyFilter(), doc, doc.select("[accesskey]")},
				{new ActiveTextFilter(), doc, doc.select("blink, marquee")},
				{new ButtonFilter(), doc, doc.select("button")},
				{new ClickFilter(), doc, doc.select("[onclick]")},
				{new ElementFilter(), doc, new Elements(doc)},
				{new EventFilter(), doc, doc.select(EVENTS)},
				{new FieldsetFilter(), doc, doc.select("fieldset")},
				{new FormControlFilter(), doc, doc.select(FORM_CONTROLS)},
				{new FrameFilter(), doc, doc.select("frame, iframe")},
				{new HeadFilter(), doc, doc.select("head")},
				{new HeadingFilter(), doc, doc.select("h1, h2, h3, h4, h5, h6")},
				{new HtmlFilter(), doc, doc.select("html")},
				{new ImageFilter(), doc, doc.select("img")},
				{new InputControlFilter(), doc, doc.select(INPUT_CONTROLS)},
				{new LinkFilter(), doc, doc.select("a")},
				{new MouseDownFilter(), doc, doc.select("[mousedown]")},
				{new MouseEventFilter(), doc, doc.select(MOUSE_EVENT)},
				{new MouseOutFilter(), doc, doc.select("[mouseout]")},
				{new MouseOverFilter(), doc, doc.select("[mouseover]")},
				{new MouseUpFilter(), doc, doc.select("[mouseup]")},
				{new SelectFilter(), doc, doc.select("select")},
				{new TableFilter(), doc, doc.select("table")},
				{new TitleFilter(), doc, doc.select("title")}};
	}
	
	@Factory(dataProvider = "filterConfig")
	public Object[] getFilterTests(ElementFilter filter,
			Element root, Elements elements) {
		
		Object[] result = new Object[]{
				new FilterTest(filter, root, elements)};
		return result;
	}

}
