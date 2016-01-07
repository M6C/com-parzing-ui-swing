/*
 * file:       IHTMLOptionElement.java
 * author:     Tim Langford
 * copyright:  (c) Packwood Software Limited 2004
 * date:       01/05/2004
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
 * Implementation of the IHTMLFormElement interface.
 */
public class IHTMLOptionElement extends IHTMLElement
{
   /**
    * Constructor
    *
    * @param parentBrowser parent browser
    * @param object Jacob dispatch interface
    */
   public IHTMLOptionElement (InternetExplorer parentBrowser, Dispatch object)
   {
      super(parentBrowser, object);
   }

   /**
    * Retrieves the default selected property.
    *
    * @return index of the default select option
    */
   public boolean getDefaultSelected ()
   {
      return (getBooleanProperty("defaultSelected"));
   }

   /**
    * Retrieves the selected property.
    *
    * @return whether the option is selected
    */
   public boolean getSelected ()
   {
      return (getBooleanProperty("selected"));
   }

   /**
    * Sets the selected property.
    * 
    * @param selected boolean flag
    */
   public void setSelected (boolean selected)
   {
      setBooleanProperty ("selected", selected);
   }
   
   /**
    * Retrieves the ordinal position of the option.
    *
    * @return ordinal index of the option
    */
   public int getIndex ()
   {
      return (getIntegerProperty("index"));
   }

   /**
    * Retrieves the text string specified by the option tag.
    *
    * @return text string
    */
   public String getText ()
   {
      return (getStringProperty("text"));
   }

   /**
    * Sets the text string specified by the option tag.
    *
    * @param text text string
    */
   public void setText (String text)
   {
      setStringProperty("text", text);
   }

   /**
    * Retrieves the value which is returned to the server by this option
    * when the form control is submitted.
    *
    * @return option value
    */
   public String getValue ()
   {
      return getStringProperty("value");
   }
}
