/*
 * file:       IHTMLDOMNode.java
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
 * Implementation of the IHTMLDOMAttribute interface. This is a partial
 * implementation of the W3C HTML DOM specification for a node. Only those
 * elements so far required have been implemented.
 *
 * @see org.w3c.dom.Node
 */
public abstract class IHTMLDOMNode extends BrowserDispatch
{
   /**
    * Constructor.
    *
    * @param parentBrowser parent browser
    * @param dispatch Jacob dispatch interface
    */
   protected IHTMLDOMNode (InternetExplorer parentBrowser, Dispatch dispatch)
   {
      super(parentBrowser, dispatch);
   }

   /**
    * Get the document that this Node is part of.
    *
    * @see org.w3c.dom.Node#getOwnerDocument()
    *
    * @return document
    * @throws JiffieException
    */
   public IHTMLDocument2 getOwnerDocument ()
      throws JiffieException
   {
      return (IHTMLDocument2)createNodeFromProperty("ownerDocument");
   }

   /**
    * Get the collection of attributes for this Node.
    *
    * @see org.w3c.dom.Node#getAttributes()
    *
    * @return attribute collection
    */
   public IHTMLAttributeCollection getAttributes ()
   {
      Dispatch dispatch = getDispatchProperty("attributes");

      return new IHTMLAttributeCollection(getParentBrowser(), this, dispatch);
   }

   /**
    * Get the first child node of this Node.
    *
    * @see org.w3c.dom.Node#getFirstChild()
    *
    * @return Node or null
    * @throws JiffieException
    */
   public IHTMLDOMNode getFirstChild ()
      throws JiffieException
   {
      return createNodeFromProperty("firstChild");
   }

   /**
    * Get the last child node of this Node.
    *
    * @see org.w3c.dom.Node#getLastChild()
    *
    * @return Node or null
    * @throws JiffieException
    */
   public IHTMLDOMNode getLastChild ()
      throws JiffieException
   {
      return createNodeFromProperty("lastChild");
   }

   /**
    * Get the next sibling node of this Node.
    *
    * @see org.w3c.dom.Node#getNextSibling()
    *
    * @return Node or null
    * @throws JiffieException
    */
   public IHTMLDOMNode getNextSibling ()
      throws JiffieException
   {
      return createNodeFromProperty("nextSibling");
   }

   /**
    * Get the previous sibling node of this Node.
    *
    * @see org.w3c.dom.Node#getPreviousSibling()
    *
    * @return Node or null
    * @throws JiffieException
    */
   public IHTMLDOMNode getPreviousSibling ()
      throws JiffieException
   {
      return createNodeFromProperty("previousSibling");
   }

   /**
    * Get the parent node of this Node.
    *
    * @see org.w3c.dom.Node#getParentNode()
    *
    * @return Node or null
    * @throws JiffieException
    */
   public IHTMLDOMNode getParentNode ()
      throws JiffieException
   {
      return createNodeFromProperty("parentNode");
   }

   /**
    * Create a new node from the named property.
    *
    * @param propertyName The property's name.
    * @return Node or null
    * @throws JiffieException
    */
   protected IHTMLDOMNode createNodeFromProperty (String propertyName)
      throws JiffieException
   {
      Variant variant = getVariantProperty(propertyName);
      return (IHTMLDOMNode)(JiffieUtility.getElementFactory().createElement(getParentBrowser(), variant));
   }

   /**
    * Append the newNode as a child.
    *
    * @param newNode New child node that is to be appended.
    * @return new IHTMLDOMNode instance
    * @throws JiffieException
    */
   public IHTMLDOMNode appendChild (IHTMLDOMNode newNode)
      throws JiffieException
   {
      int[] argc = new int[1];
      Variant[] argv = new Variant[1];
      argv[0] = new Variant(newNode.getDispatch());

      Variant v = call("appendChild", argv, argc);
      return ((IHTMLDOMNode)(JiffieUtility.getElementFactory().createElement(getParentBrowser(), v)));
   }

   /**
    * Retrieves the name of a particular type of node.
    *
    * @return #text if it's a text node, the tag name if it's an element, the attribute name if it's an attribute
    */
   public String getNodeName ()
   {
      return (getStringProperty("nodeName"));
   }

   /**
    * Retrieves the value of a node.
    *
    * @return the text if it's a text node, null if it's an element, the attribute value if it's an attribute
    */
   public String getNodeValue ()
   {
      return (getStringProperty("nodeValue"));
   }
}
