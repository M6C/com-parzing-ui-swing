/*
 * file:       IHTMLFormElement.java
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
 * Implementation of the IHTMLFormElement interface.
 */
public class IHTMLFormElement extends IHTMLElement
{
   /**
    * Constructor
    *
    * @param parentBrowser parent browser
    * @param object Jacob dispatch interface
    */
   public IHTMLFormElement (InternetExplorer parentBrowser, Dispatch object)
   {
      super (parentBrowser, object);
   }

   /**
    * Retrieves the action attribute of this form.
    *
    * @return action attribute
    */
   public String getAction ()
   {
      return (getStringProperty("action"));
   }

   /**
    * Retrieves the dir attribute of this form.
    *
    * @return dir attribute
    */
   public String getDir ()
   {
      return (getStringProperty("dir"));
   }

   /**
    * Retrieves the encoding attribute of this form.
    *
    * @return encoding attribute
    */
   public String getEncoding ()
   {
      return (getStringProperty("encoding"));
   }

   /**
    * Retrieves the method attribute of this form.
    *
    * @return method attribute
    */
   public String getMethod ()
   {
      return (getStringProperty("method"));
   }

   /**
    * Call the reset method of the element.
    */
   public void reset ()
   {
      call("reset");
   }

   /**
    * Call the submit method of the form.
    *
    * @param wait determines if the method blocks until the window is idle
    */
   public void submit (boolean wait)
      throws JiffieException
   {
      call("submit");
      if (wait == true)
      {
         getParentBrowser().waitWhileBusy();
      }
   }

   /**
    * Retrieves the target attribute of this form.
    *
    * @return target attribute
    */
   public String getTarget ()
   {
      return (getStringProperty("target"));
   }
}
