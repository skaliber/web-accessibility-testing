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

import org.jsoup.nodes.Element;

import org.a11ytesting.filter.AccessKeyFilter;
import org.a11ytesting.filter.ActiveTextFilter;
import org.a11ytesting.filter.ButtonFilter;
import org.a11ytesting.filter.ClickFilter;
import org.a11ytesting.filter.ElementFilter;
import org.a11ytesting.filter.EventFilter;
import org.a11ytesting.filter.FieldsetFilter;
import org.a11ytesting.filter.FormControlFilter;
import org.a11ytesting.filter.FrameFilter;
import org.a11ytesting.filter.HeadFilter;
import org.a11ytesting.filter.HeadingFilter;
import org.a11ytesting.filter.HtmlFilter;
import org.a11ytesting.filter.ImageFilter;
import org.a11ytesting.filter.InputControlFilter;
import org.a11ytesting.filter.LinkFilter;
import org.a11ytesting.filter.MouseDownFilter;
import org.a11ytesting.filter.MouseEventFilter;
import org.a11ytesting.filter.MouseOutFilter;
import org.a11ytesting.filter.MouseOverFilter;
import org.a11ytesting.filter.MouseUpFilter;
import org.a11ytesting.filter.SelectFilter;
import org.a11ytesting.filter.StyleElementFilter;
import org.a11ytesting.filter.SummaryFilter;
import org.a11ytesting.filter.TableFilter;
import org.a11ytesting.filter.TitleFilter;

/**
 * Collection of all filters currently supported.
 * 
 * @author dallison
 */
public enum Filter {
	ACCESS_KEY {
		@Override
		public ElementFilter newInstance(Element document) {
			return new AccessKeyFilter(document);
		}
	},
	ACTIVE_TEXT {
		@Override
		public ElementFilter newInstance(Element document) {
			return new ActiveTextFilter(document);
		}
	},
	BUTTON {
		@Override
		public ElementFilter newInstance(Element document) {
			return new ButtonFilter(document);
		}
	},
	ELEMENT { // All elements
		@Override
		public ElementFilter newInstance(Element document) {
			return new ElementFilter(document);
		}
	},
	EVENT {
		@Override
		public ElementFilter newInstance(Element document) {
			return new EventFilter(document);
		}
	},
	FIELDSET {
		@Override
		public ElementFilter newInstance(Element document) {
			return new FieldsetFilter(document);
		}
	},
	FORM_CONTROL {
		@Override
		public ElementFilter newInstance(Element document) {
			return new FormControlFilter(document);
		}
	},
	FRAME {
		@Override
		public ElementFilter newInstance(Element document) {
			return new FrameFilter(document);
		}
	},
	HEAD {
		@Override
		public ElementFilter newInstance(Element document) {
			return new HeadFilter(document);
		}
	},
	HEADING {
		@Override
		public ElementFilter newInstance(Element document) {
			return new HeadingFilter(document);
		}
	},
	HTML {
		@Override
		public ElementFilter newInstance(Element document) {
			return new HtmlFilter(document);
		}
	},
	IMAGE {
		@Override
		public ElementFilter newInstance(Element document) {
			return new ImageFilter(document);
		}
	},
	INPUT_CONTROL {
		@Override
		public ElementFilter newInstance(Element document) {
			return new InputControlFilter(document);
		}
	},
	LINK {
		@Override
		public ElementFilter newInstance(Element document) {
			return new LinkFilter(document);
		}
	},
	MOUSE_DOWN {
		@Override
		public ElementFilter newInstance(Element document) {
			return new MouseDownFilter(document);
		}
	},
	MOUSE_EVENT {
		@Override
		public ElementFilter newInstance(Element document) {
			return new MouseEventFilter(document);
		}
	},
	MOUSE_OUT {
		@Override
		public ElementFilter newInstance(Element document) {
			return new MouseOutFilter(document);
		}
	},
	MOUSE_OVER {
		@Override
		public ElementFilter newInstance(Element document) {
			return new MouseOverFilter(document);
		}
	},
	MOUSE_UP {
		@Override
		public ElementFilter newInstance(Element document) {
			return new MouseUpFilter(document);
		}
	},
	CLICK {
		@Override
		public ElementFilter newInstance(Element document) {
			return new ClickFilter(document);
		}
	},
	SELECT {
		@Override
		public ElementFilter newInstance(Element document) {
			return new SelectFilter(document);
		}
	},
	STYLE {
		@Override
		public ElementFilter newInstance(Element document) {
			return new StyleElementFilter(document);
		}
	},
	SUMMARY {
		@Override
		public ElementFilter newInstance(Element document) {
			return new SummaryFilter(document);
		}
	},
	TABLE {
		@Override
		public ElementFilter newInstance(Element document) {
			return new TableFilter(document);
		}
	},
	TITLE {
		@Override
		public ElementFilter newInstance(Element document) {
			return new TitleFilter(document);
		}
	};
	// template method
	public abstract ElementFilter newInstance(Element element);
}
