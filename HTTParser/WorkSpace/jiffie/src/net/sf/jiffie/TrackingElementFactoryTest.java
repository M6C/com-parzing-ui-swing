/*
 * file:       TRackingElementFactoryTest.java
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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Tests to exercise the functionality implemented in the 
 * TrackingElementFactory class.
 */
public class TrackingElementFactoryTest extends JiffieDataDirTest
{
   /**
    * Test TrackingElementFactory functionality.
    *
    * @throws Exception
    */
   public void testTrackingElementFactory ()
      throws Exception
   {
      //
      // Set up the tracking element factory. Note that we are configuring
      // the factory to carry out extensive debugging. We are also using
      // out own output print stream rather than the default (System.err),
      // so that we can test the output.
      //
      TrackingElementFactory factory = new TrackingElementFactory();
      factory.setDebugOutput(true);
      factory.setStackTrace(true);

      ByteArrayOutputStream debugOutput = new ByteArrayOutputStream();
      factory.setDebugPrintStream(new PrintStream(debugOutput));

      //
      // Replace the default factory
      //
      ElementFactory oldElementFactory = JiffieUtility.setElementFactory(factory);

      //
      // Open the browser
      //
      m_explorer.navigate(m_datadir + "/blank.htm", true);

      //
      // Open a blank document and retrieve the document object
      //
      IHTMLDocument2 doc = m_explorer.getDocument(true);
      assertEquals("Blank Document", doc.getTitle());

      //
      // Replace the original factory instance
      //      
      JiffieUtility.setElementFactory(oldElementFactory);

      //
      // Check the number of unreleased objects
      //
      assertEquals(1, factory.flushReleasedAndCount());

      //
      // Release everything created by this factory
      //
      factory.release();

      //
      // Test the debug output
      //
      debugOutput.flush();

      String output = new String(debugOutput.toByteArray());
      assertEquals(0, output.indexOf("Unreleased net.sf.jiffie.IHTMLDocument2"));
   }   
}
