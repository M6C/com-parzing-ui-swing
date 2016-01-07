/*
 * file:       JiffieTest.java
 * author:     Jon Iles
 * copyright:  (c) Packwood Software Limited 2004
 * date:       23-Apr-2004
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

import net.sf.jiffie.xpath.JiffieXPathTest;
import junit.framework.TestSuite;



/**
 * This class contains a set of tests to exercise Jiffie's functionality
 * and provide examples of how Jiffie can be used in various situations.
 * Note that this is not yet a comprehensive set of tests... contributions
 * welcome! Note that to use this class you will need to set the property
 * jiffie.junit.datadir to the location of the junit directory in the
 * Jiffie distribution. e.g. from the command line use an argument like:
 *
 *    -Djiffie.junit.datadir=c:/java/jiffie/junit
 *
 * Note that with the advent of Windows XP Service Pack 2, HTML files loaded
 * from the local file system have JavaScript disabled automatically,
 * with a warning informtaion bar presented to the user. The following URL
 * describes a workaround to this, which has been employed in a couple of
 * the HTML files used by these tests.
 *
 *    http://weblogs.asp.net/jgalloway/archive/2004/08/20/218123.aspx
 */
public class JiffieTest extends TestSuite
{
   /**
    * Construcor.
    */
   public JiffieTest ()
   {
      addTestSuite (ElementContainerTest.class);
      addTestSuite (IHTMLElementTest.class);
      addTestSuite (IHTMLDocument2Test.class);
      addTestSuite (IHTMLFrameTest.class);
      addTestSuite (IHTMLAnchorElementTest.class);
      addTestSuite (IHTMLSelectElementTest.class);
      addTestSuite (IHTMLInputElementTest.class);
      addTestSuite (IHTMLFormElementTest.class);
      addTestSuite (IHTMLFrameBase2Test.class);
      addTestSuite (SingleElementListTest.class);
      addTestSuite (IHTMLDOMNodeTest.class);
      addTestSuite (TrackingElementFactoryTest.class);
      addTestSuite (IHTMLPopupTest.class);
      addTestSuite (IHTMLStyleTest.class);
      addTestSuite (IHTMLCurrentStyleTest.class);
      addTestSuite (JiffieUtilityTest.class);
      addTestSuite (JiffieXPathTest.class);      
      addTestSuite (InternetExplorerTest.class);
   }

   /**
    * Dummy test used to ensure the test suites added in the constructor
    * are run.
    */
   public void testAll ()
   {
      // dummy test
   }   
}
