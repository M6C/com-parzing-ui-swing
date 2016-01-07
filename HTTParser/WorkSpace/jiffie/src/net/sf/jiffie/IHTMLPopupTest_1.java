/*
 * file:       IHTMLPopupTest.java
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
 * Tests to exercise the functionality implemented in the IHTMLPopup class.
 */
public class IHTMLPopupTest extends JiffieDataDirTest
{
   /**
    * Exercise IHTMLPopup.
    *
    * @throws JiffieException
    */
   public void testPopup ()
      throws JiffieException
   {
      m_explorer.navigate(m_datadir + "/popup.htm", true);

      IHTMLDocument2 doc = m_explorer.getDocument(true);

      IHTMLDivElement elm = (IHTMLDivElement)doc.getElementById("x");

      IHTMLPopup popup = new IHTMLPopup(elm.getParentBrowser(), elm.getDispatchProperty("popup"));

      assertEquals("I am a popup!", popup.getDocument().getBody().getInnerText());
      assertFalse(popup.isOpen());

      IHTMLElement button = (IHTMLElement)doc.getElementById("butt");
      button.click(true);

      assertTrue(popup.isOpen());

      IHTMLElement button2 = (IHTMLElement)doc.getElementById("butt2");
      button2.click(true);

      assertFalse(popup.isOpen());

      popup.show();

      assertTrue(popup.isOpen());

      popup.hide();
      assertFalse(popup.isOpen());

      popup.hide();
      popup.show(100, 100, 180, 25, doc.getBody());
      assertTrue(popup.isOpen());
   }   
}
