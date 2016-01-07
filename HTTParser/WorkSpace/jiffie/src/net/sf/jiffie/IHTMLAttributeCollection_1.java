/*
 * file:       IHTMLAttributeCollection.java
 * author:     Piran Montford
 * copyright:  (c) Packwood Software Limited 2004
 * date:       01/12/2004
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

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;


/**
 * A collection of attributes for a single Node.
 */
public class IHTMLAttributeCollection extends BrowserDispatch
{
   /**
    * Constructor.
    *
    * @param parentBrowser parent browser
    * @param ownerElement the node to which this collection belongs.
    * @param dispatch Jacob dispatch interface
    */
   public IHTMLAttributeCollection (InternetExplorer parentBrowser, IHTMLDOMNode ownerElement, Dispatch dispatch)
   {
      super(parentBrowser, dispatch);
      m_ownerElement = ownerElement;
   }

   /**
    * Get the length of attribute collection.
    *
    * @see org.w3c.dom.NamedNodeMap#getLength()
    *
    * @return number of attributes.
    */
   public int getLength ()
   {
      int result;
      
      if (getDispatch() != null)
      {
         result = getIntegerProperty("length");
      }
      else
      {
         result = 0;
      }
      
      return (result);
   }

   /**
    * Get the <i>n<sup>th</sup></i> attribute from the list.
    *
    * @see org.w3c.dom.NamedNodeMap#item(int)
    *
    * @param index The number of attribute (zero based).
    * @return attribute.
    */
   public IHTMLDOMAttribute item (int index)
   {
      Variant itemVariant = call("item", new Integer(index));
      Dispatch itemDispatch = itemVariant.toDispatch();
      return new IHTMLDOMAttribute(getParentBrowser(), getOwnerElement(), itemDispatch);
   }

   /**
    * Get the owning element of this collection.
    *
    * @return node.
    */
   private IHTMLDOMNode getOwnerElement ()
   {
      return (m_ownerElement);
   }

   private final IHTMLDOMNode m_ownerElement;
}
