package org.a11ytesting.test;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import org.jsoup.nodes.DocumentType;

/**
 * Test HtmlVersion generates correct versions according to the publicid 
 * 
 * @author panteha Saeedi
 *
 */

public class HtmlVersionTest {

	@Test
	public void testCreate() {
		innerTestCreate("", HtmlVersion.HTML5);
		innerTestCreate("-//W3C//DTD HTML 4.01 Transitional//E",
				HtmlVersion.HTML4_TRANSITIONAL);
		innerTestCreate(null, HtmlVersion.UNKNOWN);
	}

	public void innerTestCreate(String publicId, HtmlVersion expected) {
		DocumentType type = new DocumentType("", publicId, "", "");
		assertEquals(HtmlVersion.create(type), expected);
	}

}
