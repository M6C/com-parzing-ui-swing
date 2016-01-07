/*
 * file:       IHTMLTableCell.java
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


/**
 * Implementation of the IHTMLTableCellElement interface.
 */
public class IHTMLTableCell extends IHTMLElement
{
   /**
    * Constructor
    *
    * @param parentBrowser parent browser
    * @param object Jacob dispatch interface
    */
   public IHTMLTableCell (InternetExplorer parentBrowser, Dispatch object)
   {
      super(parentBrowser, object);
   }
   
   /**
    * Retrieves the index of this cell in the cells collection of the 
    * parent row.
    * 
    * @return cell index
    */
   public int getCellIndex ()
   {
      return (getIntegerProperty("cellIndex"));
   }  
}
