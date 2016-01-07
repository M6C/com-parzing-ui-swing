/*
 * file:       IHTMLElement.java
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
 * Implementation of the IHTMLElement interface.
 */
public class IHTMLElement extends ElementContainer
{
   /**
    * Constructor
    *
    * @param parentBrowser parent browser object
    * @param object Jacob dispatch interface
    */
   public IHTMLElement (InternetExplorer parentBrowser, Dispatch object)
   {
      super(parentBrowser, object);
   }

   /**
    * Call the click method of the element.
    *
    * @param wait indicates if the method should block until browser not busy
    */
   public void click (boolean wait)
      throws JiffieException
   {
      call("click");

      if (wait == true)
      {
         getParentBrowser().waitWhileBusy();
      }
   }

   /**
    * Fire a named event for this element.
    *
    * @param name event name
    * @param wait determines if the method blocks until the browser is idle
    */
   public void fireEvent (String name, boolean wait)
      throws JiffieException
   {
      call("fireevent", name);

      if (wait == true)
      {
         getParentBrowser().waitWhileBusy();
      }
   }

   /**
    * Retrieve the ID of this element.
    *
    * @return element name
    */
   public String getID ()
   {
      return (getStringProperty("id"));
   }

   /**
    * Sets the ID of this element
    *
    * @param id element ID
    */
   public void setID (String id)
   {
      setStringProperty("id", id);
   }

   /**
    * Retrieve the name of this element.
    *
    * @return element name
    */
   public String getName ()
   {
      return (getStringProperty("name"));
   }

   /**
    * Retrieve the class name of this element.
    *
    * @return element class name
    */
   public String getClassName ()
   {
      return (getStringProperty("className"));
   }

   /**
    * Retrieve the inner HTML of this element.
    *
    * @return element inner HTML
    */
   public String getInnerHtml ()
   {
      return (getStringProperty("innerHtml"));
   }

   /**
    * Sets the inner HTML of this element.
    *
    * @param html inner HTML
    */
   public void setInnerHtml (String html)
   {
      setStringProperty("innerHTML", html);
   }

   /**
    * Retrieve the inner text of this element.
    *
    * @return element inner text
    */
   public String getInnerText ()
   {
      return (getStringProperty("innerText"));
   }

   /**
    * Sets the inner text of this element.
    *
    * @param text inner text
    */
   public void setInnerText (String text)
   {
      setStringProperty("innerText", text);
   }

   /**
    * Retrieve the outer HTML of this element.
    *
    * @return element outer HTML
    */
   public String getOuterHtml ()
   {
      return (getStringProperty("outerHtml"));
   }

   /**
    * Retrieve the outer text of this element.
    *
    * @return element outer text
    */
   public String getOuterText ()
   {
      return (getStringProperty("outerText"));
   }

   /**
    * Retrieve the tagName of this element.
    *
    * @return element tagName
    */
   public String getTagName ()
   {
      return (getStringProperty("tagName"));
   }

   /**
    * Retrieve the parent element of this element.
    *
    * @return element parent element
    */
   public IHTMLElement getParentElement ()
      throws JiffieException
   {
      return (getElementProperty("parentElement"));
   }

   /**
    * Retrives the style node of this element.
    *
    * @return IHTMLStyle for this element.
    */
   public IHTMLStyle getStyle ()
   {
      return (new IHTMLStyle(getParentBrowser(), getDispatchProperty("style")));
   }

   /**
    * Retrives the computed style node of this element.
    *
    * @return IHTMLStyle for this element.
    */
   public IHTMLCurrentStyle getCurrentStyle ()
   {
      return (new IHTMLCurrentStyle(getParentBrowser(), getDispatchProperty("currentStyle")));
   }

   /**
    * This method is called to wait until this element is complete. Clients of
    * Jiffie should call this prior to attempting to use any of the element
    * contents.
    *
    * @throws JiffieException
    */
   public void waitWhileIncomplete ()
      throws JiffieException
   {
      JiffieUtility.waitWhileIncomplete(this);
   }

   public static final String IID = "{3050F1FF-98B5-11CF-BB82-00AA00BDCE0B}";
}
