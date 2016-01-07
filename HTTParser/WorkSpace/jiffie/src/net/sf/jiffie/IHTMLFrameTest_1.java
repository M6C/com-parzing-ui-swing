/*
 * file:       IHTMLFrameTest.java
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
 * IHTMLFrameBase, IHTMLFrameElement, and IHTMLIFrameElement classes
 */
public class IHTMLFrameTest extends JiffieDataDirTest
{
   /**
    * Exercise IHTMLFrameBase, IHTMLFrameElement, and IHTMLIFrameElement
    * functionality.
    *
    * @throws Exception
    */
   public void testFrames ()
      throws Exception
   {
      //
      // Open the browser
      //
      m_explorer.navigate(m_datadir + "/frametest.htm", true);

      //
      // Retrieve the document
      //
      IHTMLDocument2 document = m_explorer.getDocument(true);
      assertEquals("Jiffie Frame Test", document.getTitle());

      //
      // Test retrieval of a list of frames
      //
      ElementList frames = document.getFrames();
      assertEquals(1, frames.size());
      frames.release();
      frames = null;

      //
      // Retrieve the frame by name
      //
      IHTMLFrameElement frame = (IHTMLFrameElement)document.getElementByName("frame1");
      assertEquals("frame1", frame.getName());
      assertEquals("", frame.getID());
      assertEquals("FRAME", frame.getTagName());

      IHTMLDocument2 frameDocument = frame.getDocument(true);
      assertEquals("Jiffie Frame Content", frameDocument.getTitle());
      frame.release();
      frame = null;

      //
      // Test retrieval of a list of iframes
      //
      ElementList iframes = frameDocument.getFrames();
      assertEquals(1, iframes.size());
      iframes.release();
      iframes = null;

      //
      // Retrieve the iframe by name
      //
      IHTMLIFrameElement iframe = (IHTMLIFrameElement)frameDocument.getElementByName("iframe1");
      IHTMLDocument2 iframeDocument = iframe.getDocument(true);
      assertEquals("Jiffie IFrame Content", iframeDocument.getTitle());
      iframeDocument.release();
      iframeDocument = null;
      iframe.release();
      iframe = null;
      frameDocument.release();
      frameDocument = null;
      document.release();
   }   
}
