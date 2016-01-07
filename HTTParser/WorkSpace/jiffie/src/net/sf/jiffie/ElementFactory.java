/*
 * file:       ElementFactory.java
 * author:     Jon Iles
 * copyright:  (c) Packwood Software Limited 2004
 * date:       01/09/2004
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
import com.jacob.com.Variant;


/**
 * Classes implementing this interface are used to create object instances
 * representing various HTML elements.
 */
public interface ElementFactory
{
   /**
    * Create an object representing an HTML element.
    *
    * @param parentBrowser parent browser
    * @param dispatch dispatch interface for HTML element COM object
    * @return HTML element object
    * @throws JiffieException if an exception occurs creating the element
    */
   public IDispatch createElement (InternetExplorer parentBrowser, Dispatch dispatch)
      throws JiffieException;

   /**
    * Create an object representing an HTML element.
    *
    * @param parentBrowser parent browser
    * @param dispatch variant representing the dispatch interface for HTML element COM object
    * @return HTML element object
    * @throws JiffieException if an exception occurs creating the element
    */
   public IDispatch createElement (InternetExplorer parentBrowser, Variant dispatch)
      throws JiffieException;

   /**
    * Create a class implementing the ElementList interface which may contain,
    * zero, one or many IHTMLElement instances.
    *
    * @param parentBrowser parent browser
    * @param variant variant representing a dispatch interface
    * @return object implementing the ElementList interface
    * @throws JiffieException if an exception occurs creating the list
    */
   public ElementList createElementList (InternetExplorer parentBrowser, Variant variant)
      throws JiffieException;
}
