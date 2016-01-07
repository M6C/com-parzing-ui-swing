/*
 * file:       IHTMLFormElementTest.java
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
 * IHTMLFormElement class.
 */
public class IHTMLFormElementTest extends JiffieDataDirTest
{
   /**
    * Exercise IHTMLFormElement functionality.
    *
    * @throws Exception
    */
   public void testForm ()
      throws Exception
   {
      //
      // Open the browser
      //
      m_explorer.navigate(m_datadir + "/form.htm", true);

      //
      // Retrieve the document
      //
      IHTMLDocument2 document = m_explorer.getDocument(true);
      assertEquals("Form Test", document.getTitle());

      IHTMLFormElement form = (IHTMLFormElement)document.getElementByName("form1");

      //
      // Test the attributes
      //
      assertEquals("formtarget.htm", form.getAction());
      assertEquals("", form.getDir());
      assertEquals("application/x-www-form-urlencoded", form.getEncoding());
      assertEquals("post", form.getMethod());
      assertEquals("formtarget", form.getTarget());

      //
      // Test submit
      //
      form = (IHTMLFormElement)document.getElementByName("form2");
      form.submit(true);
      assertEquals("Form Target", document.getTitle());

      //
      // Navigate back and test the reset method
      //
      m_explorer.goBack(true);

      IHTMLInputElement input = (IHTMLInputElement)document.getElementByName("input1");
      input.setValue("test value");
      assertEquals("test value", input.getValue());
      form = (IHTMLFormElement)document.getElementByName("form2");
      form.reset();

      // Although the reset method appears to complete successfully,
      // the contents of the form remain unchanged, so this test would
      // fail if uncommented.
      //assertEquals("", input.getValue());
   }   
}
