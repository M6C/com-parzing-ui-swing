/*
 * file:       IHTMLDOMAttribute.java
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

package net.sf.jiffie;

import com.jacob.com.Dispatch;


/**
 * Implementation of the IHTMLDOMAttribute interface.
 */
public class IHTMLDOMAttribute extends IHTMLDOMNode
{
   /**
    * Constructor. Because InternetExplorer's DOM model does not implement 
    * ownerElement on attributes, we have to record the owner when the attribute 
    * is recorded.
    *
    * @param parentBrowser parent browser.
    * @param ownerElement the Node to which this attribute belongs.
    * @param dispatch Jacob dispatch interface.
    */
   public IHTMLDOMAttribute (InternetExplorer parentBrowser, IHTMLDOMNode ownerElement, Dispatch dispatch)
   {
      super(parentBrowser, dispatch);
      m_ownerElement = ownerElement;
   }

   /**
    * Has the attribute value been specified (otherwise it is implied).
    *
    * @see org.w3c.dom.Attr#getSpecified()
    *
    * @return was the attribute's value explicitely specified.
    */
   public boolean getSpecified ()
   {
      return getBooleanProperty("specified");
   }

   /**
    * Get the name of this attribute.
    *
    * @see org.w3c.dom.Attr#getName()
    *
    * @return name.
    */
   public String getName ()
   {
      return getStringProperty("nodeName");
   }

   /**
    * Get the value of this attribute.
    *
    * @see org.w3c.dom.Attr#getValue()
    *
    * @return name.
    */
   public String getValue ()
   {
      return getStringProperty("nodeValue");
   }

   /**
    * Get the Node to which the attribute belongs.
    *
    * @see org.w3c.dom.Attr#getOwnerElement()
    *
    * @return node.
    */
   public IHTMLDOMNode getOwnerElement ()
   {
      return m_ownerElement;
   }

   private final IHTMLDOMNode m_ownerElement;
   public static final String IID = "{3050F4B0-98B5-11CF-BB82-00AA00BDCE0B}";
}
