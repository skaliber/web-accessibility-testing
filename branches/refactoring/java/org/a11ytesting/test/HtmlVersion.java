/**
 * 
 */
package org.a11ytesting.test;

import java.util.EnumSet;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Node;

/**
 * The <!DOCTYPE> declaration;
 * 
 * @author panteha Saeedi
 * 
 */
public enum HtmlVersion {
	HTML5 {
		@Override
		public String toString() {
			return "HTML 5";
		}
	},
	HTML4_FRAMESET {
		@Override
		public String toString() {
			return "HTML 4.01 Frameset";
		}
	},
	HTML4_STRICT {
		@Override
		public String toString() {
			return "HTML 4.01";
		}
	},
	HTML4_TRANSITIONAL {
		@Override
		public String toString() {
			return "HTML 4.01 Transitional";
		}
	},
	XHTML1_FRAMESET {
		@Override
		public String toString() {
			return "XHTML 1.0 Frameset";
		}
	},
	XHTML1_STRICT {
		@Override
		public String toString() {
			return "XHTML 1.0 Strict";
		}
	},
	XHTML1_TRANSITIONAL {
		@Override
		public String toString() {
			return "XHTML 1.0 Transitional";
		}
	},
	XHTML1_1 {
		@Override
		public String toString() {
			return "XHTML 1.1";
		}
	},
	UNKNOWN {
		@Override
		public String toString() {
			return "unknown";
		}
	};

	/**
	 * TODO add comments
	 * 
	 * @param document
	 * @return
	 */
	public static DocumentType parseDocumentType(Document document) {
		for (Node node : document.childNodes()) {
			if (node instanceof DocumentType) {
				return (DocumentType) node;
			}
		}
		return null;
	}

	public static HtmlVersion create(Document document) {
		DocumentType type = parseDocumentType(document);
		return create(type);
	}

	public static HtmlVersion create(DocumentType type) {
		if (type == null) {
			return UNKNOWN;
		}

		String htmlVersion = type.attr("publicid");
		if (htmlVersion.matches("-//W3C//DTD XHTML 1.0 Frameset//")) {
			return XHTML1_FRAMESET;
		} else if (htmlVersion.matches("-//W3C//DTD XHTML 1.0 Strict//")) {
			return XHTML1_STRICT;
		} else if (htmlVersion.matches("-//W3C//DTD XHTML 1.0 Transitional//")) {
			return XHTML1_TRANSITIONAL;
		} else if (htmlVersion.matches("-//W3C//DTD XHTML 1.1//")) {
			return XHTML1_1;
		} else if (htmlVersion.matches("-//W3C//DTD HTML 4.01 Frameset//")) {
			return HTML4_FRAMESET;
		} else if (htmlVersion.matches("-//W3C//DTD HTML 4.01//")) {
			return HTML4_STRICT;
		} else if (htmlVersion.matches("-//W3C//DTD HTML 4.01 Transitional//")) {
			return HTML4_TRANSITIONAL;
		} else if ("".equals(htmlVersion)) {
			return HTML4_FRAMESET;
		} else {
			return HTML5;
		}
	}

	public static EnumSet<HtmlVersion> anyHtml4() {
		return EnumSet.of(HTML4_FRAMESET, HTML4_STRICT, HTML4_TRANSITIONAL);
	}

	public static EnumSet<HtmlVersion> allHtmlVersions() {
		return EnumSet.allOf(HtmlVersion.class);
	}

}
