/*
 * file:       MultipleElementList.java
 * author:     Piran Montford
 *             Jon Iles
 * copyright:  (c) Packwood Software Limited 2004
 * date:       06/10/2004
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

import java.util.AbstractList;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;


/**
 * This class represents a collection of IHTMLElements. This is in effect a thin
 * wrapper around an underlying IHTMLElementCollection instance.
 */
public final class MultipleElementList extends AbstractList<IDispatch> implements ElementList
{
   /**
    * Constructor.
    *
    * @param parentBrowser parent browser
    * @param dispatch Jacob dispatch interface
    */
   public MultipleElementList (InternetExplorer parentBrowser, Dispatch dispatch)
   {
      m_parentBrowser = parentBrowser;
      m_dispatch = dispatch;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public IDispatch get (int index)
   {
      try
      {
         return (item(index));
      }
      catch (JiffieException e)
      {
         throw new UnsupportedOperationException("JiffieException: " + e.getMessage());
      }
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public int size ()
   {
      return (Dispatch.get(m_dispatch, "length").changeType(Variant.VariantInt).getInt());
   }

   /**
    * {@inheritDoc}
    */
   public IDispatch item (int index)
      throws JiffieException
   {
      Variant itemVariant = Dispatch.call(m_dispatch, "item", new Integer(index));
      Dispatch itemDispatch = itemVariant.toDispatch();
      return (JiffieUtility.getElementFactory().createElement(m_parentBrowser, itemDispatch));
   }

   /**
    * {@inheritDoc}
    */
   public ElementList item (String name)
      throws JiffieException
   {
      return (JiffieUtility.getElementFactory().createElementList(m_parentBrowser, Dispatch.call(m_dispatch, "item", name)));
   }

   /**
    * {@inheritDoc}
    */
   public ElementList tags (String name)
      throws JiffieException
   {
      return (JiffieUtility.getElementFactory().createElementList(m_parentBrowser, Dispatch.call(m_dispatch, "tags", name)));
   }

   /**
    * This method releases the COM object associated with this collection.
    */
   public void release ()
   {
      m_dispatch.safeRelease();
   }

   /**
    * This method returns a boolean flag indicating if this object
    * has already been released.
    * 
    * @return boolean flag
    */
   public boolean isReleased ()
   {
      return (!(m_dispatch != null && m_dispatch.m_pDispatch != 0));
   }
   
   private InternetExplorer m_parentBrowser;
   private Dispatch m_dispatch;
}
