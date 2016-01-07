/*
 * file:       NewWindowEventHandlerFactory.java
 * author:     Jon Iles
 * copyright:  (c) Packwood Software Limited 2005
 * date:       20/01/2005
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


/**
 * This class creates instances of the NewWindowEventHandler class.
 */
public class NewWindowEventHandlerFactory implements EventHandlerFactory
{
   /**
    * {@inheritDoc}
    */
   public EventHandler createEventHandler (InternetExplorer parentBrowser)
   {
      return (new NewWindowEventHandler(parentBrowser));
   }
}
