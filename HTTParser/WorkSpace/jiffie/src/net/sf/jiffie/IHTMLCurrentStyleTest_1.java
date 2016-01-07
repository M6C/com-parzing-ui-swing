/*
 * file:       IHTMLStyleCurrentTest.java
 * author:     David Carr
 * copyright:  (c) Commerce Technologies 2005
 * date:       18/01/2005
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
 * Tests to exercise the functionality implemented in the IHTMLCurrentStyle class.
 */
public class IHTMLCurrentStyleTest extends JiffieDataDirTest
{
   /**
    * Exercise IHTMLCurrentStyle.
    *
    * @throws JiffieException
    */
   public void testStyle ()
      throws JiffieException
   {
      m_explorer.navigate(m_datadir + "/style.htm", true);

      IHTMLDocument2 doc = m_explorer.getDocument(true);

      IHTMLDivElement div = (IHTMLDivElement)doc.getElementById("styleDiv");
      assertEquals(div.getCurrentStyle().getBackgroundColor().toLowerCase(), "blue");
      assertEquals(div.getCurrentStyle().getBorderBottomColor().toLowerCase(), "white");
      assertEquals(div.getCurrentStyle().getBorderBottomStyle().toLowerCase(), "none");
      assertEquals(div.getCurrentStyle().getBorderBottomWidth().toLowerCase(), "medium");
      assertEquals(div.getCurrentStyle().getBorderColor().toLowerCase(), "white");
      assertEquals(div.getCurrentStyle().getBorderLeftColor().toLowerCase(), "white");
      assertEquals(div.getCurrentStyle().getBorderLeftStyle().toLowerCase(), "none");
      assertEquals(div.getCurrentStyle().getBorderLeftWidth().toLowerCase(), "medium");
      assertEquals(div.getCurrentStyle().getBorderRightColor().toLowerCase(), "white");
      assertEquals(div.getCurrentStyle().getBorderRightStyle().toLowerCase(), "none");
      assertEquals(div.getCurrentStyle().getBorderRightWidth().toLowerCase(), "medium");
      assertEquals(div.getCurrentStyle().getBorderStyle().toLowerCase(), "none");
      assertEquals(div.getCurrentStyle().getBorderTopColor().toLowerCase(), "white");
      assertEquals(div.getCurrentStyle().getBorderTopStyle().toLowerCase(), "none");
      assertEquals(div.getCurrentStyle().getBorderTopWidth().toLowerCase(), "medium");
      assertEquals(div.getCurrentStyle().getBorderWidth().toLowerCase(), "medium");
      assertEquals(div.getCurrentStyle().getColor(), "white");

      div.release();
      doc.release();
   }
}
