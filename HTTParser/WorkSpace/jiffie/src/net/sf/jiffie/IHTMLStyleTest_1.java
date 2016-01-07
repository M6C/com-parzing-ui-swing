/*
 * file:       IHTMLStyleTest.java
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
 * Tests to exercise the functionality implemented in the IHTMLStyle class.
 */
public class IHTMLStyleTest extends JiffieDataDirTest
{
   /**
    * Exercise IHTMLStyle.
    *
    * @throws JiffieException
    */
   public void testStyle ()
      throws JiffieException
   {
      m_explorer.navigate(m_datadir + "/style.htm", true);

      IHTMLDocument2 doc = m_explorer.getDocument(true);

      IHTMLDivElement div = (IHTMLDivElement)doc.getElementById("styleDiv");
      IHTMLStyle style = div.getStyle();
      assertEquals(style.getBackgroundColor().toLowerCase(), "blue");
      assertEquals(style.getColor(), "white");
      assertNotNull(style.getCssText());

      style.setColor("#FF0000");
      style.setBackgroundColor("#0000FF");
      style.setCssText("border: 2px solid #FF00FF;");
      
      style.release();
      div.release();
      doc.release();
   }   
}
