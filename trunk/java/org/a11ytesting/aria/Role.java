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
package org.a11ytesting.aria;

/**
 * Aria role set definitions.
 *
 * @author dallison
 */
public enum Role {
	ALERT {
		@Override
		public String toString() {
			return "alert";
		}
	},
	ALERT_DIALOG {
		@Override
		public String toString() {
			return "alertdialog";
		}
	},
	APPLICATION {
		@Override
		public String toString() {
			return "application";
		}
	},
	ARTICLE {
		@Override
		public String toString() {
			return "article";
		}
	},
	BANNER {
		@Override
		public String toString() {
			return "banner";
		}
	},
	BUTTON {
		@Override
		public String toString() {
			return "button";
		}
	},
	CHECKBOX {
		@Override
		public String toString() {
			return "checkbox";
		}
	},
	COLUMN_HEADER {
		@Override
		public String toString() {
			return "columnheader";
		}
	},
	COMBOBOX {
		@Override
		public String toString() {
			return "combobox";
		}
	},
	COMPLEMENTARY {
		@Override
		public String toString() {
			return "complementary";
		}
	},
	CONTENT_INFO {
		@Override
		public String toString() {
			return "contentinfo";
		}
	},
	DEFINITION {
		@Override
		public String toString() {
			return "definition";
		}
	},
	DIALOG {
		@Override
		public String toString() {
			return "dialog";
		}
	},
	DIRECTORY {
		@Override
		public String toString() {
			return "directory";
		}
	},
	DOCUMENT {
		@Override
		public String toString() {
			return "document";
		}
	},
	FORM {
		@Override
		public String toString() {
			return "form";
		}
	},
	GRID {
		@Override
		public String toString() {
			return "grid";
		}
	},
	GRID_CELL {
		@Override
		public String toString() {
			return "gridcell";
		}
	},
	GROUP {
		@Override
		public String toString() {
			return "group";
		}
	},
	HEADING {
		@Override
		public String toString() {
			return "heading";
		}
	},
	IMG {
		@Override
		public String toString() {
			return "img";
		}
	},
	LINK {
		@Override
		public String toString() {
			return "link";
		}
	},
	LIST {
		@Override
		public String toString() {
			return "list";
		}
	},
	LIST_BOX {
		@Override
		public String toString() {
			return "listbox";
		}
	},
	LIST_ITEM {
		@Override
		public String toString() {
			return "listitem";
		}
	},
	LOG {
		@Override
		public String toString() {
			return "log";
		}
	},
	MAIN {
		@Override
		public String toString() {
			return "main";
		}
	},
	MARQUEE {
		@Override
		public String toString() {
			return "marquee";
		}
	},
	MATH {
		@Override
		public String toString() {
			return "math";
		}
	},
	MENU {
		@Override
		public String toString() {
			return "menu";
		}
	},
	MENU_BAR {
		@Override
		public String toString() {
			return "menubar";
		}
	},
	MENU_ITEM {
		@Override
		public String toString() {
			return "menuitem";
		}
	},
	MENU_ITEM_CHECKBOX {
		@Override
		public String toString() {
			return "menuitemcheckbox";
		}
	},
	MENU_ITEM_RADIO {
		@Override
		public String toString() {
			return "menuitemradio";
		}
	},
	NAVIGATION {
		@Override
		public String toString() {
			return "navigation";
		}
	},
	NOTE {
		@Override
		public String toString() {
			return "note";
		}
	},
	OPTION {
		@Override
		public String toString() {
			return "option";
		}
	},
	PRESENTATION {
		@Override
		public String toString() {
			return "presentation";
		}
	},
	PROGRESS_BAR {
		@Override
		public String toString() {
			return "progressbar";
		}
	},
	RADIO_GROUP {
		@Override
		public String toString() {
			return "radiogroup";
		}
	},
	REGION {
		@Override
		public String toString() {
			return "region";
		}
	},
	ROW {
		@Override
		public String toString() {
			return "row";
		}
	},
	ROW_GROUP {
		@Override
		public String toString() {
			return "rowgroup";
		}
	},
	ROW_HEADER {
		@Override
		public String toString() {
			return "rowheader";
		}
	},
	SCROLL_BAR {
		@Override
		public String toString() {
			return "scrollbar";
		}
	},
	SEARCH {
		@Override
		public String toString() {
			return "search";
		}
	},
	SEPARATOR {
		@Override
		public String toString() {
			return "separator";
		}
	},
	SLIDER {
		@Override
		public String toString() {
			return "slider";
		}
	},
	SPIN_BUTTON {
		@Override
		public String toString() {
			return "spinbutton";
		}
	},
	STATUS {
		@Override
		public String toString() {
			return "status";
		}
	},
	TAB {
		@Override
		public String toString() {
			return "tab";
		}
	},
	TAB_LIST {
		@Override
		public String toString() {
			return "tablist";
		}
	},
	TAB_PANEL {
		@Override
		public String toString() {
			return "tabpanel";
		}
	},
	TEXT_BOX {
		@Override
		public String toString() {
			return "textbox";
		}
	},
	TIMER {
		@Override
		public String toString() {
			return "timer";
		}
	},
	TOOL_BAR {
		@Override
		public String toString() {
			return "toolbar";
		}
	},
	TOOL_TIP {
		@Override
		public String toString() {
			return "tooltip";
		}
	},
	TREE {
		@Override
		public String toString() {
			return "tree";
		}
	},
	TREE_GRID {
		@Override
		public String toString() {
			return "treegrid";
		}
	},
	TREE_ITEM {
		@Override
		public String toString() {
			return "treeitem";
		}
	}
}