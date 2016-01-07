/*
 * file:       JiffieXmlTest.java
 * author:     Piran Montford
 * copyright:  (c) Packwood Software Limited 2004
 * date:       1/12/2004
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

package net.sf.jiffie.xpath;

import java.util.List;

import net.sf.jiffie.IHTMLDocument2;
import net.sf.jiffie.IHTMLInputElement;
import net.sf.jiffie.JiffieDataDirTest;

import org.jaxen.XPath;



/**
 * Tests to exercise the functionality implemented in the JiffieXPath class.
 */
public class JiffieXPathTest extends JiffieDataDirTest
{
   /**
    * Test DocumentNavigator functionality.
    *
    * @throws Exception
    */
   public void testDocumentNavigator ()
      throws Exception
   {
      m_explorer.navigate(m_datadir + "/xml.htm", true);

      IHTMLDocument2 doc = m_explorer.getDocument(true);
      assertEquals("XML Test", doc.getTitle());

      DocumentNavigator docNav = DocumentNavigator.getInstance();
      assertEquals("This is some text", docNav.getElementStringValue(doc.getElementById("text")));

      assertEquals("Document's parent is null", null, doc.getParentNode());
   }

   /**
    * Test XPath operations to meet the original requirements for XPath support.
    * 
    * @throws Exception
    */
   @SuppressWarnings("unchecked")
   public void testInitialRequestRequirements ()
      throws Exception
   {
      m_explorer.navigate(m_datadir + "/xml.htm", true);

      IHTMLDocument2 doc = m_explorer.getDocument(true);
      assertEquals("XML Test", doc.getTitle());

      // Look for the radio button after Yes
      XPath xpathForYes = new JiffieXPath("descendant::text()[contains(.,'Yes')]/following::INPUT[1]");
      List<IHTMLInputElement> yesList = xpathForYes.selectNodes(doc);
      assertEquals("Only one yes element", 1, yesList.size());

      IHTMLInputElement yesElement = yesList.get(0);
      assertEquals("Has yes name", "register", yesElement.getName());
      assertEquals("Has yes value", "y", yesElement.getValue());

      // Look for the radio button after No
      XPath xpathForNo = new JiffieXPath("descendant::text()[contains(.,'No')]/following::INPUT[1]");
      List<IHTMLInputElement> noList = xpathForNo.selectNodes(doc);
      assertEquals("Only one no element", 1, noList.size());

      IHTMLInputElement noElement = noList.get(0);
      assertEquals("Has no name", "register", noElement.getName());
      assertEquals("Has no value", "n", noElement.getValue());

      // Look for inputs starting with money
      XPath xpathForMoney = new JiffieXPath("//INPUT[starts-with(@name,'money')]");
      List<IHTMLInputElement> moneyList = xpathForMoney.selectNodes(doc);
      assertEquals("Find two money items", 2, moneyList.size());
      assertEquals("First should have name money1", "money2", (moneyList.get(0)).getName());
      assertEquals("Second should have name money2", "money1", (moneyList.get(1)).getName());
   }
}
