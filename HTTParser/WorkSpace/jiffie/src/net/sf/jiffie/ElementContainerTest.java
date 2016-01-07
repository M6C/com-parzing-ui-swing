/*
 * file:       ElementContainerTest.java
 * author:     Jon Iles
 * copyright:  (c) Packwood Software Limited 2005
 * date:       12/01/2005
 */

/*
 * This file is part of JIFFIE.
 *
 * JIFFIE is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * JIFFIE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JIFFIE; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package net.sf.jiffie;


/**
 * Tests to exercise the functionality implemented in the ElementContainer class.
 */
public class ElementContainerTest extends JiffieDataDirTest
{
   /**
    * Test ElementContainer functionality.
    *
    * @throws Exception
    */
   public void testElementContainer ()
      throws Exception
   {
      //
      // Open the browser
      //
      m_explorer.navigate(m_datadir + "/blank.htm", true);

      //
      // Try retrieving various collections and objects from a blank document
      //
      IHTMLDocument2 doc = m_explorer.getDocument(true);
      assertEquals("Blank Document", doc.getTitle());

      ElementList all = doc.getElementList();
      assertEquals(4, all.size());
      all.release();
      all = null;

      ElementList elementsByTag = doc.getElementListByTag("x");
      assertEquals(0, elementsByTag.size());
      elementsByTag.release();
      elementsByTag = null;

      ElementList elements = doc.getElementListByName("x");
      assertEquals(0, elements.size());
      elements.release();
      elements = null;

      IHTMLElement element = (IHTMLElement)doc.getElementByName("x");
      assertNull(element);

      element = (IHTMLElement)doc.getElementByTag("x");
      assertNull(element);

      element = (IHTMLElement)doc.getElementByNameAndTag("x", "x");
      assertNull(element);

      element = (IHTMLElement)doc.getElementById("x");
      assertNull(element);

      //
      // Now test a document containing various elements
      //
      m_explorer.navigate(m_datadir + "/populated.htm", true);

      //
      // Test get element by id
      //
      element = (IHTMLInputElement)doc.getElementById("yes");
      assertNotNull(element);
      element.release();

      //
      // Forms
      //
      ElementList forms = doc.getElementListByTag("form");
      assertEquals(1, forms.size());
      forms.release();

      forms = doc.getElementListByName("form1");
      assertEquals(1, forms.size());
      forms.release();
      forms = null;

      //
      // Images
      //
      ElementList images = doc.getElementListByTag("img");
      assertEquals(1, images.size());
      images.release();

      images = doc.getElementListByName("image1");
      assertEquals(1, images.size());
      images.release();
      images = null;

      //
      // Anchors
      //
      ElementList anchors = doc.getElementListByTag("a");
      assertEquals(1, anchors.size());
      anchors.release();

      anchors = doc.getElementListByName("anchor1");
      assertEquals(1, anchors.size());
      anchors.release();
      anchors = null;

      //
      // Inputs
      //
      ElementList inputs = doc.getElementListByTag("input");
      assertEquals(4, inputs.size());
      inputs.release();

      inputs = doc.getElementListByName("text_input");
      assertEquals(1, inputs.size());
      inputs.release();
      inputs = null;

      //
      // Selects
      //
      ElementList selects = doc.getElementListByTag("select");
      assertEquals(1, selects.size());
      selects.release();

      selects = doc.getElementListByName("select1");
      assertEquals(1, selects.size());
      selects.release();
      selects = null;

      //
      // Options
      //
      ElementList options = doc.getElementListByTag("option");
      assertEquals(3, options.size());
      options.release();

      options = doc.getElementListByName("option1");
      assertEquals(1, options.size());
      options.release();
      options = null;

      //
      // Tables
      //
      ElementList tables = doc.getElementListByTag("table");
      assertEquals(1, tables.size());
      tables.release();

      tables = doc.getElementListByName("table1");
      assertEquals(1, tables.size());
      tables.release();
      tables = null;

      //
      // TextAreas
      //
      ElementList textAreas = doc.getElementListByTag("textArea");
      assertEquals(1, textAreas.size());
      textAreas.release();

      textAreas = doc.getElementListByName("textarea1");
      assertEquals(1, textAreas.size());
      textAreas.release();
      textAreas = null;

      // 
      // HTML tag
      //
      element = (IHTMLHtmlElement)doc.getElementByTag("html");
      assertNotNull(element);
      assertTrue(element.getOuterHtml().indexOf("<HEAD>") != -1);

      //
      // Test to ensure that the item method of the ElementList
      // class is working as expected
      //
      m_explorer.navigate(m_datadir + "/same.htm", true);
      assertEquals("Same Name or Tag Test", doc.getTitle());

      ElementList byName = doc.getElementListByName("firstname");
      assertEquals(2, byName.size());

      element = (IHTMLElement)byName.item(0);
      assertEquals("firstname", element.getName());

      ElementList byTag = byName.tags("input");
      assertEquals(1, byTag.size());

      IHTMLInputElement input = (IHTMLInputElement)byTag.item(0);
      assertEquals("first input value", input.getValue());
      byName.release();
      byTag.release();

      byTag = doc.getElementListByTag("input");
      assertEquals(2, byTag.size());
      byName = byTag.item("secondname");
      assertEquals(1, byName.size());
      input = (IHTMLInputElement)byName.item(0);
      assertEquals("second input value", input.getValue());
      byName.release();
      byTag.release();

      ElementList byNameAndTag = doc.getElementListByNameAndTag("firstname", "input");
      input = (IHTMLInputElement)byNameAndTag.item(0);
      assertEquals("first input value", input.getValue());
      byNameAndTag.release();

      byNameAndTag = doc.getElementListByNameAndTag("secondname", "input");
      input = (IHTMLInputElement)byNameAndTag.item(0);
      assertEquals("second input value", input.getValue());
      byNameAndTag.release();

      doc.release();
      doc = null;
   }   
}
