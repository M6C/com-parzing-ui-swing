/*
 * file:       ElementContainer.java
 * author:     Jon Iles
 * copyright:  (c) Packwood Software Limited 2004
 * date:       Apr 22, 2004
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
 * This class implements a set of methods common to objects that contain
 * HTML elements. The common feature of these objects is that they have
 * an "all" collection, which can be retrieved and filtered in order
 * to retrieve the child HTML elements.
 *
 * Note that the get<type>ByName methods will retrieve elements from the
 * document where either the id or the name attribute of that element
 * matches the supplied name.
 */
public class ElementContainer extends IHTMLDOMNode
{
   /**
    * Constructor
    *
    * @param parentBrowser parent browser
    * @param object Jacob dispatch interface
    */
   public ElementContainer (InternetExplorer parentBrowser, Dispatch object)
   {
      super(parentBrowser, object);
   }

   /**
    * This method retrieves a collection of all document elements.
    *
    * @return all collection
    * @throws JiffieException if error creating element list
    */
   public ElementList getElementList ()
      throws JiffieException
   {
      Variant all = getVariantProperty("all");
      return (JiffieUtility.getElementFactory().createElementList(getParentBrowser(), all));
   }

   /**
    * Retrieves an ElementList of zero or more HTML elements by name.
    *
    * @param name element name
    * @return element list
    * @throws JiffieException if error creating element list
    */
   public ElementList getElementListByName (String name)
      throws JiffieException
   {
      Dispatch all = getDispatchProperty("all");
      m_argv[0] = new Variant(name);

      Variant v = call(all, "item", m_argv, m_argc);
      return (JiffieUtility.getElementFactory().createElementList(getParentBrowser(), v));
   }

   /**
    * Retrieves an ElementList of zero or more HTML elements by tag name.
    *
    * @param tag element tag name
    * @return element list
    * @throws JiffieException if error creating element list
    */
   public ElementList getElementListByTag (String tag)
      throws JiffieException
   {
      Dispatch all = getDispatchProperty("all");
      m_argv[0] = new Variant(tag);

      Variant v = call(all, "tags", m_argv, m_argc);
      return (JiffieUtility.getElementFactory().createElementList(getParentBrowser(), v));
   }

   /**
    * A convenience method to retrieve an element based on both its name
    * and its tag.
    *
    * @param name element name
    * @param tag element tag name
    * @return element list
    * @throws JiffieException if error creating element list
    */
   public ElementList getElementListByNameAndTag (String name, String tag)
      throws JiffieException
   {
      ElementList list = getElementListByName(name);

      try
      {
         return (list.tags(tag));
      }

      finally
      {
         list.release();
      }
   }

   /**
    * Retrieves a generic HTML element by id.
    *
    * @param elementId element id
    * @return HTML element or null if not found
    * @throws JiffieException if error creating element list
    */
   public IDispatch getElementById (String elementId)
      throws JiffieException
   {
      Variant variant = call("getElementById", elementId);

      IDispatch element = JiffieUtility.getElementFactory().createElement(getParentBrowser(), variant);

      return (element);
   }

   /**
    * This is a convenience method provided to simplify code which expects
    * to retrieve a single element by name.
    *
    * @param name element name
    * @return element
    * @throws JiffieException if error creating element list
    */
   public IDispatch getElementByName (String name)
      throws JiffieException
   {
      ElementList list = getElementListByName(name);

      try
      {
         return ((list.isEmpty() == true) ? null : list.get(0));
      }

      finally
      {
         list.release();
      }
   }

   /**
    * This is a convenience method provided to simplify code which expects
    * to retrieve a single element by tag name.
    *
    * @param name element tag name
    * @return element
    * @throws JiffieException if error creating element list
    */
   public IDispatch getElementByTag (String name)
      throws JiffieException
   {
      ElementList list = getElementListByTag(name);

      try
      {
         return ((list.isEmpty() == true) ? null : list.get(0));
      }

      finally
      {
         list.release();
      }
   }

   /**
    * This is a convenience method provided to simplify code which expects
    * to retrieve a single element by name and tag name.
    *
    * @param name element name
    * @param tag element tag name
    * @return element
    * @throws JiffieException if error creating element list
    */
   public IDispatch getElementByNameAndTag (String name, String tag)
      throws JiffieException
   {
      ElementList list = getElementListByNameAndTag(name, tag);

      try
      {
         return ((list.isEmpty() == true) ? null : list.get(0));
      }

      finally
      {
         list.release();
      }
   }

   private int[] m_argc = new int[1];
   private Variant[] m_argv = new Variant[1];
}
