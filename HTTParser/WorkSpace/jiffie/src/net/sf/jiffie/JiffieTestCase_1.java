/*
 * file:       JiffieTestCase.java
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

import junit.framework.TestCase;
import com.jacob.com.ComFailException;
import com.jacob.com.Variant;


/**
 * This class provides a standard base on which test cases that require
 * access to Internet Explorer via Jiffie may be constructed. It also
 * provides a number of convenient assertion methods for handling the
 * Variant type.
 */
public class JiffieTestCase extends TestCase
{
   /**
    * Make the browser visible.
    *
    * @throws Exception
    */
   @Override
   public void setUp ()
      throws Exception
   {
      super.setUp();
      m_explorer.setVisible(true);
   }

   /**
    * Shut down the browser and release any resources it holds. Occasionally
    * the quit method throws an exception, despite the fact that it has
    * actually successfully closed the browser. We'll silently ignore these
    * exceptions for now.
    *
    * @throws Exception
    */
   @Override
   public void tearDown ()
      throws Exception
   {
      try
      {
         m_explorer.quit();
      }

      catch (ComFailException ex)
      {
         // silently ignore
      }

      m_explorer.release();

      super.tearDown();
   }

   /**
    * Asserts that the variant value is true.
    *
    * @param variant Variant value to be asserted
    */
   protected void assertTrue (Variant variant)
   {
      assertTrue(variant.getBoolean());
   }

   /**
    * Asserts that the variant value is false.
    *
    * @param variant Variant value to be asserted
    */
   protected void assertFalse (Variant variant)
   {
      assertFalse(variant.getBoolean());
   }

   /**
    * Asserts that the variant value is false.
    *
    * @param variant Variant value to be asserted
    */
   protected void assertNull (Variant variant)
   {
      assertTrue(variant.isNull());
   }

   /**
    * Main Internet Explorer instance.
    */
   protected InternetExplorer m_explorer = new InternetExplorer();
}
