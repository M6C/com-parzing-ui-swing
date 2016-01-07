/*
 * file:       SingleElementList.java
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

/**
 * This class represents a collection of IHTMLElements which only contains
 * a single element. In some cases underlying interfaces exposed by Internet
 * Explorer can return an IHTMLElementCollection or am IHTMLElement. This
 * class, and indeed the classes implementing the ElementList interface
 * are designed to hide this complexity from the Java developer.
 */
public final class SingleElementList extends AbstractList<IDispatch> implements ElementList, Cloneable
{
   /**
     * Constructor.
     *
     * @param element Jacob dispatch interface
     */
   public SingleElementList (IDispatch element)
   {
      m_element = element;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public IDispatch get (int index)
   {
      try
      {
         return item(index);
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
      return (1);
   }

   /**
    * {@inheritDoc}
    */
   public IDispatch item (int index)
      throws JiffieException
   {
      if (index == 0)
      {
         try
         {
            return (IDispatch)m_element.clone();
         }
         
         catch (CloneNotSupportedException e)
         {
            throw new JiffieException("Couldn't clone list", e);
         }
      }
      
      throw new IndexOutOfBoundsException();
   }

   /**
    * {@inheritDoc}
    */
   public ElementList item (String name)
      throws JiffieException
   {
      ElementList result = null;

      if (m_element instanceof IHTMLElement == true)
      {
         IHTMLElement element = (IHTMLElement)m_element;

         if ((element.getName().equals(name) == true) || (element.getID().equals(name) == true))
         {
            try
            {
               result = (ElementList)this.clone();
            }
            catch (CloneNotSupportedException e)
            {
               throw new JiffieException("Failed to copy list", e);
            }
         }
      }

      if (result == null)
      {
         result = EmptyElementList.LIST;
      }

      return (result);
   }

   /**
    * {@inheritDoc}
    */
   public ElementList tags (String name)
      throws JiffieException
   {
      ElementList result = null;

      if (m_element instanceof IHTMLElement == true)
      {
         IHTMLElement element = (IHTMLElement)m_element;

         if (element.getTagName().equalsIgnoreCase(name) == true)
         {
            try
            {
               result = (ElementList)this.clone();
            }
            catch (CloneNotSupportedException e)
            {
               throw new JiffieException("Failed to copy list", e);
            }
         }
      }

      if (result == null)
      {
         result = EmptyElementList.LIST;
      }

      return (result);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Object clone ()
      throws CloneNotSupportedException
   {
      SingleElementList copy = (SingleElementList)super.clone();
      copy.m_element = (IDispatch)m_element.clone();
      return copy;
   }

   /**
    * @see ElementList#release()
    */
   public void release ()
   {
      m_element.release();
   }

   /**
    * This method returns a boolean flag indicating if this object
    * has already been released.
    * 
    * @return boolean flag
    */
   public boolean isReleased ()
   {
      return (m_element.isReleased());
   }
   
   private IDispatch m_element;
}
