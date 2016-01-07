/*
 * file:       IHTMLFRameBase2.java
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
 * Implementation of the IHTMLFrameBase2 interface.
 */
public class IHTMLFrameBase2 extends IHTMLFrameBase
{
   /**
    * Constructor
    *
    * @param parentBrowser parent browser
    * @param object Jacob dispatch interface
    */
   public IHTMLFrameBase2 (InternetExplorer parentBrowser, Dispatch object)
   {
      super(parentBrowser, object);
   }

   /**
    * Retrieve the allow transparency property for this frame.
    * 
    * @return boolean flag
    */
   public boolean getAllowTransparency ()
   {
      return(getBooleanProperty("AllowTransparency"));
   }

   /**
    * Retrieve the frame's ready state. See the URL below for details of the
    * operation of this method.
    * 
    * http://msdn.microsoft.com/library/default.asp?url=/workshop/browser/mshtml/reference/ifaces/framebase/framebase.asp
    * 
    * @return String value that indicates the ready state of the object.
    */
   public ReadyState getReadyState ()
   {
      return (ReadyState.getInstance(getStringProperty("ReadyState")));
   }

   public static final String IID = "{3050F6DB-98B5-11CF-BB82-00AA00BDCE0B}";
}
