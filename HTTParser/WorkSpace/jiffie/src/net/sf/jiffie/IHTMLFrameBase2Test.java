/*
 * file:       IHTMLFrameBase2Test.java
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
 * IHTMLFrameBase2 class.
 */
public class IHTMLFrameBase2Test extends JiffieDataDirTest
{
   /**
    * Exercise IHTMLFrameBase2 methods.
    *
    * @throws Exception
    */
   public void testFrameBase2 ()
      throws Exception
   {
      //
      // Open the browser
      //
      m_explorer.navigate(m_datadir + "/frametest.htm");
      m_explorer.waitWhileBusy();

      //
      // Retrieve the document
      //
      IHTMLDocument2 document = m_explorer.getDocument(false);
      assertEquals("Jiffie Frame Test", document.getTitle());

      ElementList frameList = document.getElementListByTag("frame");
      assertEquals(1, frameList.size());

      IHTMLFrameElement frame = (IHTMLFrameElement)frameList.get(0);

      assertFalse(frame.getAllowTransparency());
      assertEquals(ReadyState.COMPLETE, frame.getReadyState());
   }   
}
