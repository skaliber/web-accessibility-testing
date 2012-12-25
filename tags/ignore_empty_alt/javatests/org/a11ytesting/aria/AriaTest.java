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

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class AriaTest {
	
    @Test
	public void testAlert(){	
		assertEquals(Role.ALERT.toString(), "alert");
	}
	
	@Test
	public void testAlertDialog() {
		assertEquals(Role.ALERT_DIALOG.toString(), "alertdialog");
	}
 
	@Test
	public void testApplication() {
		assertEquals(Role.APPLICATION.toString(), "application");
	}

	@Test
	public void testArticle() {
		assertEquals(Role.ARTICLE.toString(), "article");
	}

	@Test
	public void testBanner() {
		assertEquals(Role.BANNER.toString(), "banner");
	}

	@Test
	public void testButton() {
		assertEquals(Role.BUTTON.toString(), "button");
	}

	@Test
	public void testCheckbox() {
		assertEquals(Role.CHECKBOX.toString(), "checkbox");
	}

	@Test
	public void testColumnHeader() {
		assertEquals(Role.COLUMN_HEADER.toString(), "columnheader");
	}

	@Test
	public void testCombobox() {
		assertEquals(Role.COMBOBOX.toString(), "combobox");
	}

	@Test
	public void testComplementary() {
		assertEquals(Role.COMPLEMENTARY.toString(), "complementary");
	}

	@Test
	public void testContentInfo() {
		assertEquals(Role.CONTENT_INFO.toString(), "contentinfo");
	}

	@Test
	public void testDefinition() {
		assertEquals(Role.DEFINITION.toString(), "definition");
	}

	@Test
	public void testDialog() {
		assertEquals(Role.DIALOG.toString(), "dialog");
	}

	@Test
	public void testDirectory() {
		assertEquals(Role.DIRECTORY.toString(), "directory");
	}

	@Test
	public void testDocument() {
		assertEquals(Role.DOCUMENT.toString(), "document");
	}

	@Test
	public void testForm() {
		assertEquals(Role.FORM.toString(), "form");
	}

	@Test
	public void testGrid() {
		assertEquals(Role.GRID.toString(), "grid");
	}

	@Test
	public void testGridCell() {
		assertEquals(Role.GRID_CELL.toString(), "gridcell");
	}

	@Test
	public void testGroup() {
		assertEquals(Role.GROUP.toString(), "group");
	}

	@Test
	public void testHeading() {
		assertEquals(Role.HEADING.toString(), "heading");
	}

	@Test
	public void testImage() {
		assertEquals(Role.IMG.toString(), "img");
	}

	@Test
	public void testLink() {
		assertEquals(Role.LINK.toString(), "link");
	}

	@Test
	public void testList() {
		assertEquals(Role.LIST.toString(), "list");
	}

	@Test
	public void testListBox() {
		assertEquals(Role.LIST_BOX.toString(), "listbox");
	}

	@Test
	public void testListItem() {
		assertEquals(Role.LIST_ITEM.toString(), "listitem");
	}

	@Test
	public void testLog() {
		assertEquals(Role.LOG.toString(), "log");
	}

	@Test
	public void testMain() {
		assertEquals(Role.MAIN.toString(), "main");
	}

	@Test
	public void testMarquee() {
		assertEquals(Role.MARQUEE.toString(), "marquee");
	}

	@Test
	public void testMath() {
		assertEquals(Role.MATH.toString(), "math");
	}

	@Test
	public void testMenu() {
		assertEquals(Role.MENU.toString(), "menu");
	}

	@Test
	public void testMenuBar() {
		assertEquals(Role.MENU_BAR.toString(), "menubar");
	}

	@Test
	public void testMenuItem() {
		assertEquals(Role.MENU_ITEM.toString(), "menuitem");
	}

	@Test
	public void testMenuItemCheckbox() {
		assertEquals(Role.MENU_ITEM_CHECKBOX.toString(), "menuitemcheckbox");
	}

	@Test
	public void testMenuItemRadio() {
		assertEquals(Role.MENU_ITEM_RADIO.toString(), "menuitemradio");
	}

	@Test
	public void testNavigation() {
		assertEquals(Role.NAVIGATION.toString(), "navigation");
	}

	@Test
	public void testNote() {
		assertEquals(Role.NOTE.toString(), "note");
	}

	@Test
	public void testOption() {
		assertEquals(Role.OPTION.toString(), "option");
	}

	@Test
	public void testPresentation() {
		assertEquals(Role.PRESENTATION.toString(), "presentation");
	}

	@Test
	public void testProgressBar() {
		assertEquals(Role.PROGRESS_BAR.toString(), "progressbar");
	}

	@Test
	public void testRadioGroup() {
		assertEquals(Role.RADIO_GROUP.toString(), "radiogroup");
	}

	@Test
	public void testRegion() {
		assertEquals(Role.REGION.toString(), "region");
	}

	@Test
	public void testRow() {
		assertEquals(Role.ROW.toString(), "row");
	}

	@Test
	public void testRowGroup() {
		assertEquals(Role.ROW_GROUP.toString(), "rowgroup");
	}

	@Test
	public void testRowHeader() {
		assertEquals(Role.ROW_HEADER.toString(), "rowheader");
	}

	@Test
	public void testScrollBar() {
		assertEquals(Role.SCROLL_BAR.toString(), "scrollbar");
	}

	@Test
	public void testSearch() {
		assertEquals(Role.SEARCH.toString(), "search");
	}

	@Test
	public void testSeparator() {
		assertEquals(Role.SEPARATOR.toString(), "separator");
	}

	@Test
	public void testSlider() {
		assertEquals(Role.SLIDER.toString(), "slider");
	}

	@Test
	public void testSpinButton() {
		assertEquals(Role.SPIN_BUTTON.toString(), "spinbutton");
	}

	@Test
	public void testStatus() {
		assertEquals(Role.STATUS.toString(), "status");
	}

	@Test
	public void testTab() {
		assertEquals(Role.TAB.toString(), "tab");
	}

	@Test
	public void testTabList() {
		assertEquals(Role.TAB_LIST.toString(), "tablist");
	}

	@Test
	public void testTabPanel() {
		assertEquals(Role.TAB_PANEL.toString(), "tabpanel");
	}

	@Test
	public void testTextBox() {
		assertEquals(Role.TEXT_BOX.toString(), "textbox");
	}

	@Test
	public void testTimer() {
		assertEquals(Role.TIMER.toString(), "timer");
	}

	@Test
	public void testToolBar() {
		assertEquals(Role.TOOL_BAR.toString(), "toolbar");
	}

	@Test
	public void testToolTip() {
		assertEquals(Role.TOOL_TIP.toString(), "tooltip");
	}

	@Test
	public void testTree() {
		assertEquals(Role.TREE.toString(), "tree");
	}

	@Test
	public void testTreeGrid() {
		assertEquals(Role.TREE_GRID.toString(), "treegrid");
	}

	@Test
	public void testTreeItem() {
		assertEquals(Role.TREE_ITEM.toString(), "treeitem");
	}
}
