/*
 * file:       JiffieUtilityTest.java
 * author:     Justin Q. Law
 * copyright:  (c) Packwood Software Limited 2005
 * date:       03/01/2005
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

import com.jacob.com.Variant;

/**
 * Tests to exercise the functionality implemented in the JiffieUtility class.
 */
public class JiffieUtilityTest extends JiffieDataDirTest
{
   /**
    * Test a executeScript on a function that returns a Dispatch.
    * @throws JiffieException
    */
   public void testExecuteScriptWithDispatchReturn ()
      throws JiffieException
   {
      m_explorer.navigate(m_datadir + "/execscript.htm");

      IHTMLDocument2 document = m_explorer.getDocument(true);
      Variant v = JiffieUtility.executeScript(document, "getBody()");
      IHTMLElement body = (IHTMLElement)JiffieUtility.getElementFactory().createElement(m_explorer, v.toDispatch());
      assertEquals(body.getTagName().toLowerCase(), "body");
      body.release();
      document.release();
   }

   /**
    * Test executeScript on a function that returns true.
    * @throws JiffieException
    */
   public void testAssertTrue ()
      throws JiffieException
   {
      m_explorer.navigate(m_datadir + "/execscript.htm");

      IHTMLDocument2 document = m_explorer.getDocument(true);
      Variant v = JiffieUtility.executeScript(document, "getTrue()");
      assertTrue(v);
      v.safeRelease();
      document.release();
   }

   /**
    * Test executeScript on a function that returns false.
    * @throws JiffieException
    */
   public void testAssertFalse ()
      throws JiffieException
   {
      m_explorer.navigate(m_datadir + "/execscript.htm");

      IHTMLDocument2 document = m_explorer.getDocument(true);
      Variant v = JiffieUtility.executeScript(document, "getFalse()");
      assertFalse(v);
      v.safeRelease();
      document.release();
   }

   /**
    * Test executeScript on a function that returns null.
    * @throws JiffieException
    */
   public void testAssertNull ()
      throws JiffieException
   {
      m_explorer.navigate(m_datadir + "/execscript.htm");

      IHTMLDocument2 document = m_explorer.getDocument(true);
      Variant v = JiffieUtility.executeScript(document, "getNull()");
      assertNull(v);
      v.safeRelease();
      document.release();
   }

   /**
    * Test execute script on a js function that returns void.
    * @throws JiffieException
    */
   public void testVoidFunction ()
      throws JiffieException
   {
      m_explorer.navigate(m_datadir + "/execscript.htm");

      IHTMLDocument2 document = m_explorer.getDocument(true);
      Variant v = JiffieUtility.executeScript(document, "populateInnerText()");
      assertNull(v);
      v.safeRelease();

      IHTMLBodyElement body = document.getBody();
      assertEquals("populate", body.getInnerText());
      body.release();
      document.release();
   }
}
