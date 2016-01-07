/*
 * file:       IHTMLTextAreaElementTest.java
 * author:     Joe Ferner
 * copyright:  (c) Packwood Software Limited 2005
 * date:       14/11/2005
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
 * IHTMLTextAreaElement class.
 */
public class IHTMLTextAreaElementTest extends JiffieDataDirTest
{
   /**
    * Exercise IHTMLTextAreaElement functionality.
    *
    * @throws Exception
    */
   public void testTextArea ()
      throws Exception
   {
      //
      // Open the browser
      //
      m_explorer.navigate(m_datadir + "/textarea.htm", true);

      //
      // Retrieve the document
      //
      IHTMLDocument2 document = m_explorer.getDocument(true);
      assertEquals("TextArea Test", document.getTitle());

      //
      // Exercise a textarea element
      //
      IHTMLTextAreaElement textarea = (IHTMLTextAreaElement)document.getElementByName("textarea1");
      assertEquals("value 1", textarea.getValue());
      textarea.setValue("new value");
      assertEquals("new value", textarea.getValue());
   }

   /**
    * Exercise the textarea form attribute.
    * 
    * @throws Exception
    */
   public void testGetForm ()
      throws Exception
   {
      //
      // Open the browser
      //
      m_explorer.navigate(m_datadir + "/textarea.htm", true);

      //
      // Retrieve the document
      //
      IHTMLDocument2 document = m_explorer.getDocument(true);
      assertEquals("TextArea Test", document.getTitle());

      //
      // Get select element
      //
      IHTMLTextAreaElement textarea = (IHTMLTextAreaElement)document.getElementByName("textarea1");
      IHTMLFormElement form = textarea.getForm();
      assertEquals("form_select_test", form.getName());
   }
}
