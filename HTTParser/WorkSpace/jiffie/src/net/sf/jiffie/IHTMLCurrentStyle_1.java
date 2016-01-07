/*
 * file:       IHTMLCurrentStyle.java
 * author:     David Carr
 * copyright:  (c) Commerce Technologies 2005
 * date:       18/01/2005
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
 * Partial implementation of the IHTMLCurrentStyle interface.
 */
public class IHTMLCurrentStyle extends BrowserDispatch
{
   /**
    * Constructor
    *
    * @param parentBrowser parent browser object
    * @param object Jacob dispatch interface
    */
   public IHTMLCurrentStyle (InternetExplorer parentBrowser, Dispatch object)
   {
      super(parentBrowser, object);
   }

   /**
    * Retrieves the color behind the content of the object.
    *
    * @return the background color
    */
   public String getBackgroundColor ()
   {
      return (getStringProperty("backgroundColor"));
   }

   /**
    * Retrieves the style of the borders of the object.
    *
    * @return the border style
    */
   public String getBorderStyle ()
   {
      return (getStringProperty("borderStyle"));
   }

   /**
    * Retrieves the style of the bottom border of the object.
    *
    * @return the border style
    */
   public String getBorderBottomStyle ()
   {
      return (getStringProperty("borderBottomStyle"));
   }

   /**
    * Retrieves the style of the left border of the object.
    *
    * @return the border style
    */
   public String getBorderLeftStyle ()
   {
      return (getStringProperty("borderLeftStyle"));
   }

   /**
    * Retrieves the style of the right border of the object.
    *
    * @return the border style
    */
   public String getBorderRightStyle ()
   {
      return (getStringProperty("borderRightStyle"));
   }

   /**
    * Retrieves the style of the top border of the object.
    *
    * @return the border style
    */
   public String getBorderTopStyle ()
   {
      return (getStringProperty("borderTopStyle"));
   }

   /**
    * Retrieves the color of the borders of the object.
    *
    * @return the border color
    */
   public String getBorderColor ()
   {
      return (getStringProperty("borderColor"));
   }

   /**
    * Retrieves the color of the bottom border of the object.
    *
    * @return the border color
    */
   public String getBorderBottomColor ()
   {
      return (getStringProperty("borderBottomColor"));
   }

   /**
    * Retrieves the color of the left border of the object.
    *
    * @return the border color
    */
   public String getBorderLeftColor ()
   {
      return (getStringProperty("borderLeftColor"));
   }

   /**
    * Retrieves the color of the right border of the object.
    *
    * @return the border color
    */
   public String getBorderRightColor ()
   {
      return (getStringProperty("borderRightColor"));
   }

   /**
    * Retrieves the color of the top border of the object.
    *
    * @return the border color
    */
   public String getBorderTopColor ()
   {
      return (getStringProperty("borderTopColor"));
   }

   /**
    * Retrieves the width of the borders of the object.
    *
    * @return the border width
    */
   public String getBorderWidth ()
   {
      return (getStringProperty("borderWidth"));
   }

   /**
    * Retrieves the width of the bottom border of the object.
    *
    * @return the border width
    */
   public String getBorderBottomWidth ()
   {
      return (getStringProperty("borderBottomWidth"));
   }

   /**
    * Retrieves the width of the left border of the object.
    *
    * @return the border width
    */
   public String getBorderLeftWidth ()
   {
      return (getStringProperty("borderLeftWidth"));
   }

   /**
    * Retrieves the width of the right border of the object.
    *
    * @return the border width
    */
   public String getBorderRightWidth ()
   {
      return (getStringProperty("borderRightWidth"));
   }

   /**
    * Retrieves the width of the top border of the object.
    *
    * @return the border width
    */
   public String getBorderTopWidth ()
   {
      return (getStringProperty("borderTopWidth"));
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
}
