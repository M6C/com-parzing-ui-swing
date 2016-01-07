/*
 * file:       IHTMLStyle.java
 * author:     Justin Law
 * copyright:  (c) Packwood Software Limited 2005
 * date:       07/01/2005
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
 * Implementation of the IHTMLStyle interface.
 */
public class IHTMLStyle extends BrowserDispatch
{
   /**
    * Constructor.
    *
    * @param parentBrowser parent browser
    * @param dispatch Jacob dispatch interface
    */   
   public IHTMLStyle (InternetExplorer parentBrowser, Dispatch dispatch)
   {
      super(parentBrowser, dispatch);
   }

   /**
    * Retrieves the background color.
    * 
    * @return background color
    */
   public String getBackgroundColor ()
   {
      return (getStringProperty("backgroundColor"));
   }

   /**
    * Set the background color.
    * 
    * @param backgroundColor background color
    */
   public void setBackgroundColor (String backgroundColor)
   {
      setStringProperty("backgroundColor", backgroundColor);
   }
   
   /**
    * Retrieves the text color.
    * 
    * @return text color
    */
   public String getColor ()
   {
      return (getStringProperty("color"));
   }

   /**
    * Set the color.
    * 
    * @param color foreground color
    */
   public void setColor (String color)
   {
      setStringProperty("color", color);
   }

   /**
    * Retrieves the persisted representation of the style rule.
    *
    * @return css text
    */
   public String getCssText ()
   {
      return (getStringProperty("cssText"));
   }
   
   /**
    * Set the css text.
    * 
    * @param cssText css text
    */
   public void setCssText (String cssText)
   {
      setStringProperty("cssText", cssText);
   }
}
