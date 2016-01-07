/*
 * file:       IHTMLDivElement.java
 * author:     Mark Richter
 * copyright:  (c) Packwood Software Limited 2004
 * date:       28/09/2004
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
 * Implementation of the IHTMLDivElement interface.
 */
public class IHTMLDivElement extends IHTMLElement
{
   /**
    * Constructor
    *
    * @param parentBrowser parent browser
    * @param object Jacob dispatch interface
    */
   public IHTMLDivElement (InternetExplorer parentBrowser, Dispatch object)
   {
      super(parentBrowser, object);
   }

   /**
    * Retrieves the alignment of the object relative to the display or table.
    * Possible return values are:
    *
    * <UL>
    * <LI><B>center</B> - Aligns to the center.</LI>
    * <LI><B>justify</B> - Aligns to the left and right edge.</LI>
    * <LI><B>left</B> - Default. Aligns to the left edge.</LI>
    * <LI><B>right</B> - Aligns to the right edge.</LI>
    * <UL>
    *
    * @return String alignment of the object.
    */
   public String getAlign ()
   {
      return (getStringProperty("align"));
   }

   /**
    * Sets the alignment of the object relative to the display or table.
    * Possible values for align parameter are:
    *
    * <UL>
    * <LI><B>center</B> - Aligns to the center.</LI>
    * <LI><B>justify</B> - Aligns to the left and right edge.</LI>
    * <LI><B>left</B> - Default. Aligns to the left edge.</LI>
    * <LI><B>right</B> - Aligns to the right edge.</LI>
    * <UL>
    *
    * @param align String alignment
    */
   public void setAlign (String align)
   {
      setStringProperty("align", align);
   }

   /**
    * Retrieves whether the browser automatically performs wordwrap.
    *
    * @return boolean false if the Browser automatically wraps the text
    *         within this element or true if it does not.
    */
   public boolean getNoWrap ()
   {
      return (getBooleanProperty("noWrap"));
   }

   /**
    * Sets whether the browser automatically performs wordwrap.
    * The noWrap parameter should be false if the Browser is to
    * automatically wrap the text within this element or true if
    * it should not.
    *
    * @param noWrap false to wrap text or true to not.
    */
   public void setNoWrap (boolean noWrap)
   {
      setBooleanProperty("noWrap", noWrap);
   }
}
