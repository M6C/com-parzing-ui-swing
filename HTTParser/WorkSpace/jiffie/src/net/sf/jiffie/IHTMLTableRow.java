/*
 * file:       IHTMLTableRow.java
 * author:     Mark Richter
 * copyright:  (c) Packwood Software Limited 2004
 * date:       18/09/2004
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
 * Implementation of the IHTMLTableRowElement interface.
 */
public class IHTMLTableRow extends IHTMLElement
{
   /**
    * Constructor
    *
    * @param parentBrowser parent browser
    * @param object Jacob dispatch interface
    */
   public IHTMLTableRow (InternetExplorer parentBrowser, Dispatch object)
   {
      super(parentBrowser, object);
   }

   /**
    * Retrieve a collection of all cell elements.
    *
    * @return cell collection
    * @throws JiffieException if error create cell list
    */
   public ElementList getCells ()
      throws JiffieException
   {
      Variant cells = getVariantProperty("cells");
      return (JiffieUtility.getElementFactory().createElementList(getParentBrowser(), cells));
   }

   /**
    * Convenience method gets the count of cells in this table row.
    *
    * @return the number of cells in this table row.
    */
   public int getCellCount ()
   {
      Dispatch cells = getDispatchProperty("cells");
      return (Dispatch.get(cells, "length").changeType(Variant.VariantInt).getInt());
   }

   /**
    * Convenience method to obtain a cell at the specified index from this
    * table row, without creating a new ElementList instance to
    * represent the entirte collection of cells.
    *
    * @param index the zero-based index of the cell to be retrieved.
    * @return the cell element at the specified index
    */
   public IHTMLTableCell getCell (int index)
   {
      Dispatch cells = getDispatchProperty("cells");
      m_argv[0] = new Variant(index);

      Variant v = call(cells, "item", m_argv, m_argc);
      return (new IHTMLTableCell(getParentBrowser(), v.toDispatch()));
   }

   private int[] m_argc = new int[1];
   private Variant[] m_argv = new Variant[1];
}
