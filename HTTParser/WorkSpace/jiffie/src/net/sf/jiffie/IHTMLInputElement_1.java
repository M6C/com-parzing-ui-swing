/*
 * file:       IHTMLInputElement.java
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


/**
 * Implementation of the IHTMLInputElement interface.
 */
public class IHTMLInputElement extends IHTMLElement
{
   /**
    * Constructor
    *
    * @param parentBrowser parent browser
    * @param object Jacob dispatch interface
    */
   public IHTMLInputElement (InternetExplorer parentBrowser, Dispatch object)
   {
      super(parentBrowser, object);
   }

   /**
    * Retrieves the checked property
    *
    * @return boolean value
    */
   public boolean getChecked ()
   {
      return (getBooleanProperty("checked"));
   }

   /**
    * Sets the checked property.
    *
    * @param checked boolean value
    */
   public void setChecked (boolean checked)
   {
      setBooleanProperty("checked", checked);
   }

   /**
    * Retrieves the maxLength property
    *
    * @return max length
    */
   public int getMaxLength ()
   {
      return (getIntegerProperty("maxLength"));
   }

   /**
    * Sets the maxLength property
    *
    * @param maxLength max length value
    */
   public void setMaxLength (int maxLength)
   {
      setIntegerProperty("maxLength", maxLength);
   }

   /**
    * Retrieves the value property
    *
    * @return input value
    */
   public String getValue ()
   {
      return (getStringProperty("value"));
   }

   /**
    * Sets the value property
    *
    * @param value new value
    */
   public void setValue (String value)
   {
      setStringProperty("value", value);
   }

   /**
    * Retrieves the type property
    *
    * @return input type
    */
   public String getType ()
   {
      return (getStringProperty("type"));
   }

   /**
    * Sets the type property
    *
    * @param type type value
    */
   public void setType (String type)
   {
      setStringProperty("type", type);
   }

   /**
    * Retrieves the form property
    *
    * @return form property
    */
   public IHTMLFormElement getForm ()
   {
      IHTMLFormElement element;

      Dispatch dispatch = getDispatchProperty("form");

      if (dispatch == null)
      {
         element = null;
      }
      else
      {
         element = new IHTMLFormElement(getParentBrowser(), dispatch);
      }

      return (element);
   }
}
