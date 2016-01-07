/*
 * file:       IHTMLTable.java
 * author:     Jon Iles
 * copyright:  (c) Packwood Software Limited 2004
 * date:       21/03/2004
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
 * Implementation of the IHTMLTable interface.
 */
public class IHTMLTable extends IHTMLElement
{
   /**
    * Constructor
    *
    * @param parentBrowser parent browser
    * @param object Jacob dispatch interface
    */
   public IHTMLTable (InternetExplorer parentBrowser, Dispatch object)
   {
      super(parentBrowser, object);
   }

   /**
    * Retrieve a collection of all row elements.
    *
    * @return row collection
    * @throws JiffieException if error creating row list
    */
   public ElementList getRows ()
      throws JiffieException
   {
      Variant rows = getVariantProperty("rows");
      return (JiffieUtility.getElementFactory().createElementList(getParentBrowser(), rows));
   }

   /**
    * Convenience method gets the count of rows in this table.
    *
    * @return the number of rows in this table.
    */
   public int getRowCount ()
   {
      Dispatch rows = getDispatchProperty("rows");
      return (Dispatch.get(rows, "length").changeType(Variant.VariantInt).getInt());
   }

   /**
    * Convenience method to obtain a row at the specified index from this
    * table.
    *
    * @param index the zero-based index of the row to be retrieved.
    * @return the row element at the specified index
    */
   public IHTMLTableRow getRow (int index)
   {
      Dispatch rows = getDispatchProperty("rows");
      m_argv[0] = new Variant(index);

      Variant v = call(rows, "item", m_argv, m_argc);
      return (new IHTMLTableRow(getParentBrowser(), v.toDispatch()));
   }

   private int[] m_argc = new int[1];
   private Variant[] m_argv = new Variant[1];
}
