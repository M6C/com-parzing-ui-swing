/*
 * file:       InternetExplorerTest.java
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
 * Tests to exercise the functionality implemented in the InternetExplorer class.
 */
public class InternetExplorerTest extends JiffieDataDirTest
{
   /**
    * This method demonstrates how to tell if the Internet Explorer window
    * has successfully closed after calling the quit method. The window may
    * not have closed if, for example, a modal dialog has been displayed.
    * If this is the case then the caller will need to send keystrokes to clear
    * the dialog before the window actually closes. This method tests the
    * ready state of the browser in order to determine if the window has
    * really closed. Note that we allow a little time for the browser to
    * clean up before testing its state.
    * 
    * I've commented this test out as it has proved to be a little flaky.
    * The code below has been left to give an idea of what can be done.
    *
    * @throws Exception
    */
//   public void testWindowState ()
//      throws Exception
//   {
//      ReadyState readyState;
//
//      //
//      // Open the browser and navigate to the anchor test document
//      //
//      m_explorer.navigate(m_datadir + "/anchor.htm", true);
//
//      IHTMLDocument2 document = m_explorer.getDocument(true);
//      assertEquals("Anchor Test", document.getTitle());
//
//      //
//      // Close the browser window and allow time for the browser to disappear
//      //
//      m_explorer.quit();
//      Thread.sleep(1000);
//
//      //
//      // Test the ready state of the window
//      //
//      try
//      {
//         readyState = m_explorer.getReadyState();
//      }
//
//      catch (ComFailException ex)
//      {
//         readyState = ReadyState.UNINITIALIZED;
//      }
//
//      assertTrue(readyState != ReadyState.COMPLETE);
//
//      //
//      // Now repeat the process, but this time, launch a modal dialog,
//      // which will prevent the window from closing.
//      //
//      m_explorer = new InternetExplorer();
//      m_explorer.navigate(m_datadir + "/anchor.htm", true);
//      document = m_explorer.getDocument(true);
//      assertEquals("Anchor Test", document.getTitle());
//
//      //
//      // Click the link which blocks with an alert dialog
//      //
//      IHTMLAnchorElement anchor = (IHTMLAnchorElement)document.getElementByName("anchor3");
//
//      // start a new thread to perform the click, the call to the
//      // click method will block when the alert dialog is shown
//      // but we can continue in the main thread and send key presses
//      // to the dialog to dismiss it
//      BlockingClickThread thread = new BlockingClickThread(anchor);
//      thread.start();
//
//      //
//      // Close the browser window and allow time for the browser to disappear
//      //      
//      m_explorer.quit();
//      Thread.sleep(1000);
//
//      //
//      // Test the ready state of the window
//      //
//      try
//      {
//         readyState = m_explorer.getReadyState();
//      }
//
//      catch (ComFailException ex)
//      {
//         readyState = ReadyState.UNINITIALIZED;
//      }
//
//      assertTrue(readyState == ReadyState.COMPLETE);
//
//      //
//      // now send a space to the dialog to press the button
//      //
//      JiffieUtility.sendKeys("Internet Explorer", " ");
//      anchor.release();
//      anchor = null;
//   }

   /**
    * This method demonstrates access to information about the IE window,
    * including size, location, and HWND.
    */
   public void testWindowSize ()
   {
      assertTrue(m_explorer.getHWND() != 0);
      assertTrue(m_explorer.getHeight() > 0);
      assertTrue(m_explorer.getWidth() > 0);
      assertTrue(m_explorer.getLeft() >= 0);
      assertTrue(m_explorer.getTop() >= 0);
      m_explorer.setHeight(400);
      assertEquals(400, m_explorer.getHeight());
      m_explorer.setWidth(600);
      assertEquals(600, m_explorer.getWidth());
      m_explorer.setTop(21);
      assertEquals(21, m_explorer.getTop());
      m_explorer.setLeft(20);
      assertEquals(20, m_explorer.getLeft());
   }
}
