/*
 * file:       EmptyElementList.java
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
 * This class represents an empty collection of IHTMLElements. New
 * instances of this class should not be created (hence the private constructor).
 * Instead references to the LIST member should be used where empty collections
 * are required.
 */
public final class EmptyElementList extends AbstractList<IDispatch> implements ElementList
{
   /**
    * Private constructor to prevent ad-hoc instantiation.
    */
   private EmptyElementList ()
   {
      // private constructor
   }
   
   /**
    * {@inheritDoc}
    */
   @Override
   public IDispatch get (int index)
   {
      throw new IndexOutOfBoundsException();
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public int size ()
   {
      return (0);
   }

   /**
    * {@inheritDoc}
    */
   public IDispatch item (int index)
   {
      return (get(index));
   }
   
   /**
    * {@inheritDoc}
    */
   public ElementList item (String name)
   {
      return (LIST);
   }
   
   /**
    * {@inheritDoc}
    */
   public ElementList tags (String name)
   {
      return (LIST);   
   }

   /**
    * {@inheritDoc}
    */
   public void release ()
   {
      // does nothing
   }

   /**
    * {@inheritDoc}
    */
   public boolean isReleased ()
   {
      return (true);
   }
   
   public static final EmptyElementList LIST = new EmptyElementList();
}
