/*
 * file:       IHTMLScriptElement.java
 * author:     Justin Q. Law
 * copyright:  (c) Packwood Software Limited 2005
 * date:       Jan 6, 2005
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
 * Implementation of the IHTMLScriptElement interface.
 */
public class IHTMLScriptElement extends IHTMLElement
{
   /**
    * Constructor.
    *
    * @param parentBrowser parent browser
    * @param dispatch Jacob dispatch interface
    */
   public IHTMLScriptElement (InternetExplorer parentBrowser, Dispatch dispatch)
   {
      super(parentBrowser, dispatch);
   }

   /**
    * This method is not supported, UnsupportedOperationException will be
    * thrown.
    * <p>
    * When trying to set the innerText of a script tag from javascript, IE will
    * error with the message "Unknown runtime error".
    * 
    * @param text inner text
    */
   @Override
   public void setInnerText (String text)
   {
      throw new UnsupportedOperationException("Operation not supported. Use setText instead.");
   }

   /**
    * This method is not supported, UnsupportedOperationException will be
    * thrown thrown.
    * <p>
    * When trying to set the innerHTML of a script tag from javascript, IE will
    * error with the message "Unknown runtime error".
    * 
    * @param html inner html
    */
   @Override
   public void setInnerHtml (String html)
   {
      throw new UnsupportedOperationException("Operation not supported. Use setText instead.");
   }

   /**
    * Sets the text of the object as a string.
    * 
    * @param text script text
    */
   public void setText (String text)
   {
      setStringProperty("text", text);
   }

   /**
    * Retrieves the text of the object as a string.
    * 
    * @return script text
    */
   public String getText ()
   {
      return (getStringProperty("text"));
   }

   /**
    * Retrieves a value that indicates the current state of the object.
    * 
    * @return ready state
    */
   public ReadyState getReadyState ()
   {
      return (ReadyState.getInstance(getStringProperty("ReadyState")));
   }

   /**
    * Retrieves the URL to an external file that contains the source code or
    * data.
    * 
    * @return source URL
    */
   public String getSrc ()
   {
      return (getStringProperty("src"));
   }

   /**
    * Retrieves the MIME type for the associated scripting engine.
    * 
    * @return MIME type
    */
   public String getType ()
   {
      return (getStringProperty("type"));
   }

   /**
    * Object is not initialized with data.
    */
   public static final String STATUS_UNINITIALIZED = "uninitialized";

   /**
    * Object is loading its data.
    */
   public static final String STATUS_LOADING = "loading";

   /**
    * Object has finished loading its data.
    */
   public static final String STATUS_LOADED = "loaded";

   /**
    * User can interact with the object even though it is not fully loaded.
    */
   public static final String STATUS_INTERACTIVE = "interactive";

   /**
    * Object is completely initialized.
    */
   public static final String STATUS_COMPLETE = "complete";
}
