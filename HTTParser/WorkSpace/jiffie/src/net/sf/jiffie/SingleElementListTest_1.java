/*
 * file:       SingleElementListTest.java
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
 * Tests to exercise the functionality implemented in the 
 * SingleElementList class.
 */
public class SingleElementListTest extends JiffieDataDirTest
{
   /**
    * Exercise SingleElementList (in particular release())
    *
    * @throws Exception
    */
   public void testSingleElementList ()
      throws Exception
   {
      //
      // From the document containing various elements, find a single form as a list
      //
      m_explorer.navigate(m_datadir + "/populated.htm", true);

      IHTMLDocument2 doc = m_explorer.getDocument(false);
      SingleElementList list = (SingleElementList)doc.getElementListByName("form1");
      assertEquals(1, list.size());

      //
      // Show that we can get an element by tag
      //
      ElementList testList = list.tags("FORM");
      assertEquals(1, testList.size());
      testList.release();
      testList = list.tags("INPUT");
      assertEquals(0, testList.size());
      testList.release();

      //
      // Show that we can get an element by item
      //
      testList = list.item("form1");
      assertEquals(1, testList.size());
      testList.release();
      testList = list.item("form2");
      assertEquals(0, testList.size());
      testList.release();

      //
      // Show we can get the form from the list when list not released
      //
      IHTMLFormElement form = (IHTMLFormElement)list.item(0);
      assertEquals("FORM", form.getTagName());
      form.release();

      //
      // Show we can get the form from the list when list is released
      //
      form = (IHTMLFormElement)list.item(0);
      list.release();
      assertEquals("FORM", form.getTagName());
      form.release();
   }   
}
