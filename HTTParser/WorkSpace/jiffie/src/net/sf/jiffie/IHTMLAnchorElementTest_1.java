/*
 * file:       IHTMLAnchorElementTest.java
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
 * IHTMLAnchorElement class.
 * 
 * Note that for some of these tests to complete successfully, you will
 * need to set the Internet Explorer security option 
 * "Allow Web Sites to prompt for information using scripted windows"
 */
public class IHTMLAnchorElementTest extends JiffieDataDirTest
{
   /**
    * Exercise IHTMLAnchorElement functionality.
    *
    * @throws Exception
    */
   public void testAnchors ()
      throws Exception
   {
      //
      // Open the browser
      //
      m_explorer.navigate(m_datadir + "/anchor.htm", true);

      //
      // Retrieve the document
      //
      IHTMLDocument2 document = m_explorer.getDocument(true);
      assertEquals("Anchor Test", document.getTitle());

      //
      // Find the simple anchor and click
      //
      IHTMLAnchorElement anchor = (IHTMLAnchorElement)document.getElementByName("anchor1");
      anchor.click(true);
      assertEquals("Anchor Target", document.getTitle());
      anchor.release();
      anchor = null;

      //
      // Navigate back and try the anchor that has an onclick handler
      //
      m_explorer.goBack(true);
      assertEquals("Anchor Test", document.getTitle());
      anchor = (IHTMLAnchorElement)document.getElementByName("anchor2");
      anchor.click(true);
      assertEquals("Anchor Target", document.getTitle());
      anchor.release();
      anchor = null;

      //
      // Navigate back and try the anchor that blocks on an alert dialog
      //
      m_explorer.goBack(true);
      assertEquals("Anchor Test", document.getTitle());
      anchor = (IHTMLAnchorElement)document.getElementByName("anchor3");

      // start a new thread to perform the click, the call to the
      // click method will block when the alert dialog is shown
      // but we can continue in the main thread and send key presses
      // to the dialog to dismiss it
      BlockingClickThread thread = new BlockingClickThread(anchor);
      thread.start();
      
      // send a space to the dialog to press the button
      JiffieUtility.sendKeys("Internet Explorer", " ", document);
      assertEquals("Anchor Target", document.getTitle());
      anchor.release();
      anchor = null;

      //
      // Navigate back and try the anchor that blocks on a confirm dialog
      // First time through we will press the OK button
      //
      m_explorer.goBack(true);
      assertEquals("Anchor Test", document.getTitle());
      anchor = (IHTMLAnchorElement)document.getElementByName("anchor4");

      // start a new thread to perform the click, the call to the
      // click method will block when the alert dialog is shown
      // but we can continue in the main thread and send key presses
      // to the dialog to dismiss it
      thread = new BlockingClickThread(anchor);
      thread.start();

      //
      // Send a space to the dialog to press the ok button
      //
      JiffieUtility.sendKeys("Internet Explorer", " ");
      document.waitWhileIncomplete();
      assertEquals("Anchor Target", document.getTitle());
      anchor.release();
      anchor = null;

      //
      // Navigate back and try the anchor that blocks on a confirm dialog
      // Second time through, we will press the cancel button
      // to stop the navigation.
      //
      m_explorer.goBack(true);
      assertEquals("Anchor Test", document.getTitle());
      anchor = (IHTMLAnchorElement)document.getElementByName("anchor4");

      // start a new thread to perform the click, the call to the
      // click method will block when the alert dialog is shown
      // but we can continue in the main thread and send key presses
      // to the dialog to dismiss it
      thread = new BlockingClickThread(anchor);
      thread.start();

      // send a tab to change to the cancel button, then send a space
      // to press the button
      JiffieUtility.sendKeys("Internet Explorer", "{TAB} ", document);
      assertEquals("Anchor Test", document.getTitle());
      anchor.release();
      anchor = null;

      //
      // Try the anchor that navigates to a new window via a target attribute
      //
      anchor = (IHTMLAnchorElement)document.getElementByName("anchor5");
      anchor.click(true);

      // our main window document should not have changed
      assertEquals("Anchor Test", document.getTitle());
      anchor.release();
      anchor = null;

      // we should have a single new window
      assertEquals(1, m_explorer.getNewWindowCount());

      InternetExplorer newWindow = m_explorer.getLastNewWindow(true);
      IHTMLDocument2 newDocument = newWindow.getDocument(true);
      assertEquals("Anchor Target", newDocument.getTitle());
      newDocument.release();
      newDocument = null;
      newWindow.quit();
      newWindow.release();
      newWindow = null;

      //
      // Try the anchor that navigates to a new window via an on click handler
      //
      anchor = (IHTMLAnchorElement)document.getElementByName("anchor6");
      anchor.click(true);

      //
      // our main window document should not have changed
      //
      assertEquals("Anchor Test", document.getTitle());
      anchor.release();
      anchor = null;

      // we should have a single new window, however
      // under Windows XP SP2, this test will fail due to the new popup
      // protection which IE6 implements
      assertEquals(1, m_explorer.getNewWindowCount());
      newWindow = m_explorer.getLastNewWindow(true);
      newDocument = newWindow.getDocument(true);
      assertEquals("Anchor Target", newDocument.getTitle());
      newDocument.release();
      newDocument = null;
      newWindow.quit();
      newWindow.release();
      newWindow = null;

      //
      // Try the anchor that blocks on a prompt dialog. Enter some
      // text at the prompt and check that we get the expected result.
      // Note that you should be able to use the same technique as shown
      // to complete authentication dialogs etc.
      //
      anchor = (IHTMLAnchorElement)document.getElementByName("anchor7");

      // start a new thread to perform the click, the call to the
      // click method will block when the dialog is shown
      // but we can continue in the main thread and send key presses
      // to the dialog to dismiss it
      thread = new BlockingClickThread(anchor);
      thread.start();

      // send some text to populate the prompt field,
      // a tab to change to the ok button, then send a space
      // to press the button
      JiffieUtility.sendKeys("Explorer User Prompt", "test text{TAB} ", document);

      IHTMLInputElement input = (IHTMLInputElement)document.getElementByName("input1");
      assertEquals("test text", input.getValue());
      anchor.release();
      anchor = null;
      input.release();
      input = null;

      document.release();
      document = null;
   }      
   
   /**
    * This test demonstrates how a modal dialog can be managed.
    * In order for this to work, the dialog needs to pass a reference to the
    * document it contains back to the document in the browser that opened 
    * the dialog. Jiffie can then retrieve this reference and work with the
    * document in the dialog window. 
    * 
    * Unfortunately this means that if you don't have the ability to change
    * the web pages you want to test to add this kind of code, modal dialogs
    * will remain out of bounds for Jiffie. 
    * 
    * The files anchor.htm and dialogtarget.htm show how this technique
    * works using an onload function on the dialog document, and a div
    * in anchor.htm which receives a reference to the dialog's document as
    * an attribute.
    * 
    * @throws Exception
    */   
   public void testModalDialog ()
      throws Exception
   {
      //
      // Open the browser
      //
      m_explorer.navigate(m_datadir + "/anchor.htm", true);
   
      //
      // Retrieve the document
      //
      IHTMLDocument2 document = m_explorer.getDocument(true);
      assertEquals("Anchor Test", document.getTitle());
      
      //
      // Find and click the anchor which launches the modal dialog
      //
      IHTMLAnchorElement anchor = (IHTMLAnchorElement)document.getElementByName("anchor8");
      BlockingClickThread thread = new BlockingClickThread(anchor);
      thread.start();
      anchor = null;
      
      //
      // Find the div to which the dialog window has attached a reference
      // to its document, and create an IHTMLDocument2 instance from it.
      //
      IHTMLElement div = (IHTMLElement)document.getElementById("dialogDocumentDiv");         
      IHTMLDocument2 dialogDocument = new IHTMLDocument2(m_explorer, div.getVariantProperty("dialogDocument").toDispatch());
      assertEquals("Dialog Target", dialogDocument.getTitle());
      
      //
      // Click the anchor which closes the dialog and clean up
      //
      anchor = (IHTMLAnchorElement)dialogDocument.getElementByName("closeAnchor");
      anchor.click(true);
      anchor.release();
      anchor = null;
      
      dialogDocument.release();
      dialogDocument = null;   
      
      document.release();
      document = null;
   }
   
   /**
    * This test illustrates how modeless dialogs are managed. The only 
    * difference between this case abd the previous test is that the
    * anchor which launches the dialog does not block when clicked.
    * 
    * @throws Exception
    */
   public void testModelessDialog ()
      throws Exception
   {
      //
      // Open the browser
      //
      m_explorer.navigate(m_datadir + "/anchor.htm", true);
   
      //
      // Retrieve the document
      //
      IHTMLDocument2 document = m_explorer.getDocument(true);
      assertEquals("Anchor Test", document.getTitle());
      
      //
      // Find and click the anchor which launches the modal dialog
      //
      IHTMLAnchorElement anchor = (IHTMLAnchorElement)document.getElementByName("anchor9");
      anchor.click(true);
      anchor.release();
      anchor = null;
      
      //
      // Find the div to which the dialog window has attached a reference
      // to its document, and create an IHTMLDocument2 instance from it.
      //
      IHTMLElement div = (IHTMLElement)document.getElementById("dialogDocumentDiv");         
      IHTMLDocument2 dialogDocument = new IHTMLDocument2(m_explorer, div.getVariantProperty("dialogDocument").toDispatch());
      assertEquals("Dialog Target", dialogDocument.getTitle());
      
      //
      // Click the anchor which closes the dialog and clean up
      //
      anchor = (IHTMLAnchorElement)dialogDocument.getElementByName("closeAnchor");
      anchor.click(true);
      anchor.release();
      anchor = null;
      
      dialogDocument.release();
      dialogDocument = null;    
      
      document.release();
   }
   
}
