/*
 * file:       JiffieDataDirTest.java
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
 * This class extends the basic JiffieTestCase class to provide a member
 * variable which contains the location of the test data required to support 
 * Jiffie's own JUnit tests.
 */
public class JiffieDataDirTest extends JiffieTestCase
{
   /**
    * Called as part of JUnit initialisation to set up the test fixture.
    * In this case we populate the m_datadir member variable with the location
    * of the Jiffie test data.
    */
   @Override
   public void setUp ()
      throws Exception
   {
      super.setUp();
      
      m_datadir = System.getProperty("jiffie.junit.datadir");

      if ((m_datadir == null) || (m_datadir.length() == 0))
      {
         throw new Exception("missing jiffie.junit.datadir property");
      }
   }

   /**
    * Path to the Jiffie test data
    */
   protected String m_datadir;
}
