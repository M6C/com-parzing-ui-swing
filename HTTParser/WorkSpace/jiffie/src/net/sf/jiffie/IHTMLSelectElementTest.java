/*
 * file:       IHTMLSelectElementTest.java
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
 * IHTMLSelectElement class.
 */
public class IHTMLSelectElementTest extends JiffieDataDirTest
{
   /**
    * Exercise IHTMLSelectElement functionality.
    *
    * NOTE: security settings in XP SP2 prevent this from running correctly.
    * 
    * @throws Exception
    */
/*   
   public void testSelect ()
      throws Exception
   {
      //
      // Open the browser
      //
      m_explorer.navigate(m_datadir + "/select.htm", true);

      //
      // Retrieve the document
      //
      IHTMLDocument2 document = m_explorer.getDocument(true);
      assertEquals("Select Test", document.getTitle());

      //
      // Exercise a select element
      //
      IHTMLSelectElement select = (IHTMLSelectElement)document.getElementByName("select1");
      assertEquals(0, select.getSelectedIndex());
      assertEquals("1", select.getValue());
      select.setSelectedIndex(1);
      assertEquals(1, select.getSelectedIndex());
      assertEquals("2", select.getValue());

      //
      // Exercise an option element.
      // Note that the default selected property will return the same value
      // for all option elements as specified by the parent select element.
      //
      ElementList optionsList = select.getElementListByTag("option");
      IHTMLOptionElement[] optionsArray = (IHTMLOptionElement[])optionsList.toArray(new IHTMLOptionElement[optionsList.size()]);

      assertEquals(true, optionsArray[0].getDefaultSelected());
      assertEquals(0, optionsArray[0].getIndex());
      assertEquals("a", optionsArray[0].getText());
      assertEquals("1", optionsArray[0].getValue());

      assertEquals(false, optionsArray[1].getDefaultSelected());
      assertEquals(1, optionsArray[1].getIndex());
      assertEquals("b", optionsArray[1].getText());
      assertEquals("2", optionsArray[1].getValue());

      //
      // Exercise a select element with an onchange handler.
      // The significance of this test is that calling the click
      // method on a select element does not appear to raise an
      // on change event, hence we have to manually fire the
      // event ourselves.
      //
      IHTMLInputElement input = (IHTMLInputElement)document.getElementByName("input1");
      select = (IHTMLSelectElement)document.getElementByName("select2");
      assertEquals("", input.getValue());
      select.setSelectedIndex(1);
      select.fireEvent("onchange", true);
      assertEquals("changed", input.getValue());
   }
*/
   
   /**
    * Exercise the form property.
    * 
    * @throws Exception
    */
   public void testGetForm ()
      throws Exception
   {
      //
      // Open the browser
      //
      m_explorer.navigate(m_datadir + "/select.htm", true);

      //
      // Retrieve the document
      //
      IHTMLDocument2 document = m_explorer.getDocument(true);
      assertEquals("Select Test", document.getTitle());

      //
      // Get select element
      //
      IHTMLSelectElement select = (IHTMLSelectElement)document.getElementByName("select1");
      IHTMLFormElement form = select.getForm();
      assertEquals("form_select_test", form.getName());
   }
}
