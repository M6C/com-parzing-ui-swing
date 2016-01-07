/*
 * file:       IHTMLFrameBase.java
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
 * Implementation of the IHTMLFrameBase interface.
 */
public class IHTMLFrameBase extends IHTMLElement
{
   /**
    * Constructor
    *
    * @param parentBrowser parent browser
    * @param object Jacob dispatch interface
    */
   public IHTMLFrameBase (InternetExplorer parentBrowser, Dispatch object)
   {
      super(parentBrowser, object);
   }

   /**
    * Method to retrieve the document contained within this frame.
    *
    * @param wait flag indicating if this method should wait until the document is complete
    * @return document instance
    */
   public IHTMLDocument2 getDocument (boolean wait)
      throws JiffieException
   {
      Dispatch iwb = queryInterface(IWebBrowser2.IID);

      try
      {
         IHTMLDocument2 doc = (IHTMLDocument2)(JiffieUtility.getElementFactory().createElement(getParentBrowser(), Dispatch.get(iwb, "Document")));

         if (wait == true)
         {
            doc.waitWhileIncomplete();
         }

         return (doc);
      }
      finally
      {
         iwb.safeRelease();
      }
   }
}
