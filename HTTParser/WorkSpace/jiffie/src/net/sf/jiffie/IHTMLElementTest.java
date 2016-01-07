/*
 * file:       IHTMLElementTest.java
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

import java.util.LinkedList;


/**
 * Tests to exercise the functionality implemented in the IHTMLElement class.
 */
public class IHTMLElementTest extends JiffieDataDirTest
{
   /**
    * Exercise element methods.
    *
    * @throws Exception
    */
   public void testElement ()
      throws Exception
   {
      //
      // Open the browser
      //
      m_explorer.navigate(m_datadir + "/populated.htm", true);

      //
      // Retrieve an anchor element
      //
      IHTMLDocument2 doc = m_explorer.getDocument(true);
      IHTMLAnchorElement anchor = (IHTMLAnchorElement)doc.getElementByName("anchor1");
      assertNotNull(anchor);

      assertEquals("A", anchor.getTagName());
      assertTrue(anchor.getHref().endsWith("frametest.htm"));
      assertEquals("anchor1", anchor.getName());
      assertEquals("", anchor.getID());
      assertEquals("Anchor Test", anchor.getOuterText());
      assertEquals("Element's node name is the tag name", anchor.getTagName(), anchor.getNodeName());
      assertNull("Element's node value is null", anchor.getNodeValue());

      LinkedList<IHTMLElement> elements = new LinkedList<IHTMLElement>();
      IHTMLElement element = anchor;
      elements.add(element);
      element = element.getParentElement();
      elements.add(element);
      assertEquals("TD", element.getTagName());
      element = element.getParentElement();
      elements.add(element);
      assertEquals("TR", element.getTagName());
      element = element.getParentElement();
      elements.add(element);
      assertEquals("TBODY", element.getTagName());
      element = element.getParentElement();
      elements.add(element);
      assertEquals("TABLE", element.getTagName());
      element = element.getParentElement();
      elements.add(element);
      assertEquals("FORM", element.getTagName());
      element = element.getParentElement();
      elements.add(element);
      assertEquals("BODY", element.getTagName());
      element = element.getParentElement();
      elements.add(element);
      assertEquals("HTML", element.getTagName());
      element = element.getParentElement();
      assertNull(element);

     //
     // Release any objects we have used
     //
     for (IHTMLElement e : elements)
     {
         e.release();
     }

     doc.release();
   }
}
