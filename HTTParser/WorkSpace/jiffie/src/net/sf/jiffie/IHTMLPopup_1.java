/*
 * file:       IHTMLPopup.java
 * author:     Justin Law
 * copyright:  (c) Packwood Software Limited 2005
 * date:       07/01/2005
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
 * Implementation of the IHTMLPopup interface.
 */
public class IHTMLPopup extends BrowserDispatch
{
   /**
    * Constructor.
    *
    * @param parentBrowser parent browser
    * @param dispatch Jacob dispatch interface
    */
   public IHTMLPopup (InternetExplorer parentBrowser, Dispatch dispatch)
   {
      super(parentBrowser, dispatch);
   }

   /**
    * Retrieves the document contained by the popup window.
    *
    * @return document instance
    */
   public IHTMLDocument2 getDocument ()
   {
      return (new IHTMLDocument2(getParentBrowser(), getDispatchProperty("document")));
   }

   /**
    * Closes the popup window.
    */
   public void hide ()
   {
      call("hide");
   }

   /**
    * Retrieves a flag indicating if the popup is open.
    *
    * @return boolean flag
    */
   public boolean isOpen ()
   {
      return (getBooleanProperty("isOpen"));
   }

   /**
    * Shows the popup window.
    */
   public void show ()
   {
      call("show");
   }

   /**
    * Shows the popup window at the xy coordinates relative to the element.
    *
    * @param x x coordinate.
    * @param y y coordinate.
    * @param width width of the popup.
    * @param height height of the popup.
    * @param element element to which the x,y coordinates are relative.
    * @return return value
    */
   public boolean show (int x, int y, int width, int height, IHTMLElement element)
   {
      Variant[] argv = new Variant[1];
      int[] argc = new int[4];

      argv[0] = new Variant(element.getDispatch());
      argc[0] = x;
      argc[1] = y;
      argc[2] = width;
      argc[3] = height;

      Variant v = call("show", argv, argc);

      return (v.changeType(Variant.VariantBoolean).getBoolean());
   }
}
