/*
 * file:       BrowserDispatch.java
 * author:     Jon Iles
 * copyright:  (c) Packwood Software Limited 2004
 * date:       30/08/2004
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
 * This class specialises the IDispatch interface for use with COM
 * objects related to Internet Explorer. Specifically this class provides
 * a way of tracking the parent browser and document for each object.
 */
public class BrowserDispatch extends IDispatch
{
   /**
    * Constructor.
    *
    * @param parentBrowser parent browser
    * @param dispatch Jacob dispatch interface
    */
   BrowserDispatch (InternetExplorer parentBrowser, Dispatch dispatch)
   {
      super (dispatch);
      m_parentBrowser = parentBrowser;
   }


   /**
    * Retrieves the parent browser object
    *
    * @return parent broweser object
    */
   public final InternetExplorer getParentBrowser ()
   {
      return (m_parentBrowser);
   }

   /**
    * Retrieve an IHTMLElement property.
    *
    * @param name property name
    * @return IHTMLElement instance
    */
   public IHTMLElement getElementProperty (String name)
      throws JiffieException
   {
      IHTMLElement element;

      Dispatch dispatch = getDispatchProperty(name);

      if (dispatch == null)
      {
         element = null;
      }
      else
      {
         element = (IHTMLElement)JiffieUtility.getElementFactory().createElement(m_parentBrowser, dispatch);
      }

      return (element);
   }
   
   private InternetExplorer m_parentBrowser;
}
