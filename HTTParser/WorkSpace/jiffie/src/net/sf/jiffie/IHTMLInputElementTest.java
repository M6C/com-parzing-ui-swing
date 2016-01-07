/*
 * file:       IHTMLInputElementTest.java
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
 * IHTMLInputElement class.
 */
public class IHTMLInputElementTest extends JiffieDataDirTest
{
   /**
    * Exercise IHTMLInputElement functionality.
    *
    * @throws Exception
    */
   public void testInput ()
      throws Exception
   {
      //
      // Open the browser
      //
      m_explorer.navigate(m_datadir + "/input.htm", true);

      //
      // Retrieve the document
      //
      IHTMLDocument2 document = m_explorer.getDocument(true);
      assertEquals("Input Test", document.getTitle());

      //
      // Test the text input field
      //
      IHTMLInputElement text = (IHTMLInputElement)document.getElementByName("text_input");
      assertEquals("", text.getValue());
      assertEquals("text", text.getType());
      text.setValue("changed");
      assertEquals("changed", text.getValue());

      //
      // Test the checkbox input field. Note that in order to set the value
      // of a checkbox, we must call the click method, setting the
      // checked property or the value property does not appear to work.
      //
      IHTMLInputElement checkbox = (IHTMLInputElement)document.getElementByName("checkbox_input");
      assertEquals(true, checkbox.getChecked());
      assertEquals("checkbox", checkbox.getType());
      checkbox.click(true);
      assertEquals(false, checkbox.getChecked());

      //
      // Simple radio button test. Groups of radio buttons have the same name
      // so if we call the getInputByName method, we will retrieve the
      // first matching element, not the complete set we require.
      //
      IHTMLInputElement radioInput = (IHTMLInputElement)document.getElementByName("radio_input");
      assertEquals("1", radioInput.getValue());

      //
      // Useful radio button test. The calls below will
      // retrieve an array of input element matching the supplied name.
      // These are our radio buttons, which we can then manipulate.
      //
      ElementList radioList = document.getElementListByName("radio_input");
      IHTMLInputElement[] radioArray = radioList.toArray(new IHTMLInputElement[radioList.size()]);

      assertEquals(3, radioArray.length);
      assertEquals("1", radioArray[0].getValue());
      assertEquals("2", radioArray[1].getValue());
      assertEquals("3", radioArray[2].getValue());

      assertEquals(true, radioArray[0].getChecked());
      assertEquals(false, radioArray[1].getChecked());
      assertEquals(false, radioArray[2].getChecked());

      radioArray[1].click(true);

      assertEquals(false, radioArray[0].getChecked());
      assertEquals(true, radioArray[1].getChecked());
      assertEquals(false, radioArray[2].getChecked());
   }

   /**
    * Exercise the form attribute.
    * 
    * @throws Exception
    */
   public void testGetForm ()
      throws Exception
   {
      //
      // Open the browser
      //
      m_explorer.navigate(m_datadir + "/input.htm", true);

      //
      // Retrieve the document
      //
      IHTMLDocument2 document = m_explorer.getDocument(true);
      assertEquals("Input Test", document.getTitle());

      //
      // Get any input element
      //
      IHTMLInputElement text = (IHTMLInputElement)document.getElementByName("text_input");
      IHTMLFormElement form = text.getForm();
      assertEquals("form_input_test", form.getName());
   }

   /**
    * Test setting the value of a file input field.
    *
    * @throws Exception
    */
   public void testFileInput ()
      throws Exception
   {
      //
      // Open the browser
      //
      m_explorer.navigate(m_datadir + "/input.htm", true);

      //
      // Retrieve the document
      //
      IHTMLDocument2 document = m_explorer.getDocument(true);
      assertEquals("Input Test", document.getTitle());

      //
      // Test the text input field
      //
      IHTMLInputElement fileInput = (IHTMLInputElement)document.getElementByName("file_input");

      //
      // Ensure that we have a valid Windows path, with the correct
      // directory separator characters.
      //
      StringBuffer filename = new StringBuffer();
      char c;

      for (int loop = 0; loop < m_datadir.length(); loop++)
      {
         c = m_datadir.charAt(loop);

         if (c == '/')
         {
            filename.append("\\");
         }
         else
         {
            filename.append(c);
         }
      }

      filename.append("\\input.htm");

      //
      // Set the filename
      //
      BlockingClickThread blockingClick = new BlockingClickThread(fileInput);
      blockingClick.start();
      JiffieUtility.sendKeys("Choose File", filename.toString() + "{TAB}{TAB} ");

      fileInput.release();
      document.release();
   }
}
