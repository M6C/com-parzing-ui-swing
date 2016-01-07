/*
 * file:       IHTMLDocument2Test.java
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
 * Tests to exercise the functionality implemented in the IHTMLDocument2 class.
 */
public class IHTMLDocument2Test extends JiffieDataDirTest
{
   /**
    * Exercise IHTMLDocument2 functionality.
    *
    * @throws Exception
    */
   public void testDocument ()
      throws Exception
   {
      //
      // Open the browser
      //
      m_explorer.navigate(m_datadir + "/frametest.htm", true);

      //
      // Test document property retrieval
      //
      IHTMLDocument2 doc = m_explorer.getDocument(true);
      assertEquals("Jiffie Frame Test", doc.getTitle());
      assertTrue(doc.getUrl().indexOf("frametest.htm") != -1);

      //
      // Test retrieval of the document body
      //
      IHTMLBodyElement body = doc.getBody();
      assertTrue(body.getInnerHtml().indexOf("frame.htm") != -1);

      //
      // Release the document
      //
      body.release();
      doc.release();
   }
   
   /**
    * Exercise the IHTMLDocument2.getDocumentElement method
    * 
    * @throws JiffieException
    */
   public void testGetDocumentElement ()
      throws JiffieException
   {
      m_explorer.navigate(m_datadir + "/blank.htm", true);

      IHTMLDocument2 doc = m_explorer.getDocument(true);

      IHTMLElement element = doc.getDocumentElement();
      assertEquals(doc.getTitle(), ((IHTMLElement)element.getElementByTag("title")).getInnerText());
      assertEquals(doc.getBody().getParentElement().getOuterHtml(), element.getOuterHtml());

      element.release();
      doc.release();
   }   
   
   /**
    * Test demonstrating the use of the execCommand method. Note that
    * this test is disabled by default as it requires the user to deal with
    * the "save as" dialog. Note that in the specific case of the "save as" 
    * command, Internet Explorer ignores the shoUI execCommand parameter and
    * always dispays a dialog. This is a security feature to prevent malicious
    * web pages from overwriting system files without a user's knowledge.
    * 
    * @throws JiffieException
    */
//   public void testExecCommand ()
//      throws JiffieException
//   {
//      m_explorer.navigate(m_datadir + "/populated.htm", true);
//
//      IHTMLDocument2 doc = m_explorer.getDocument(true);
//      
//      boolean result = doc.execCommand("SaveAs", false, new Variant("jiffie.htm"));
//      
//      assertTrue(result);
//      
//      doc.release();
//   }   
}
