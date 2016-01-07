/*
 * file:       JiffieXPath.java
 * author:     Piran Montford
 * copyright:  (c) Packwood Software Limited 2004
 * date:       1/12/2004
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

package net.sf.jiffie.xpath;

import org.jaxen.BaseXPath;
import org.jaxen.JaxenException;


/**
 * An XPath implementation for the Jiffie/InternetExplorer DOM model
 *
 * <p>
 * This is the main entry point for matching an XPath against Internet Explorer, 
 * as accessed through the Jiffie library. You create a compiled XPath object, 
 * then match it against one or more context nodes using the 
 * {@link BaseXPath#selectNodes} method, as in the following example:
 * </p>
 *
 * <pre>
 * XPath path = new DOMXPath(&quot;a/b/c&quot;);
 * List results = path.selectNodes(domNode);
 * </pre>
 *
 * @see BaseXPath
 */
public class JiffieXPath extends BaseXPath
{
   /**
    * Construct given an XPath expression string.
    *
    * @param xpathExpr The XPath expression.
    * @throws JaxenException if there is a syntax error while parsing the expression.
    */
   public JiffieXPath (String xpathExpr)
      throws JaxenException
   {
      super(xpathExpr, DocumentNavigator.getInstance());
   }
}
