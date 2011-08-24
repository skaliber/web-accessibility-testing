package org.a11ytesting.test;

import org.jsoup.nodes.Element;

/**
 * Interface for filtering document elements to be used by rules.
 * 
 * @author dallison
 */
public interface Filter {

	/**
	 * Filter an element set starting at a root element.
	 * 
	 * @param root The root element to filter at and under.
	 * @return Iterable<Element> set of elements.
	 */
	public Iterable<Element> result(Element root);
}
