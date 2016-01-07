/*
 * file:       EventHandler.java
 * author:     Mark Richter
 * copyright:  (c) Packwood Software Limited 2005
 * date:       20/01/2004
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
 * This interface represents the basic set of methods that a Jiffie event
 * handler must implement.
 */
public interface EventHandler
{
   /**
    * Adds a new navigation listener.
    *
    * @param listener the new listener to add
    * @return true if the listener was not already in the set of listeners
    */
   public boolean addNavigationListener (NavigationListener listener);

   /**
    * Removes a navigation listener from the set of listeners.
    *
    * @param listener the listener to remove
    * @return true if the listener was present in the set of listeners
    */
   public boolean removeNavigationListener (NavigationListener listener);

   /**
    * Adds a new window listener.
    *
    * @param listener the new listener to add
    * @return true if the listener was not already in the set of listeners
    */
   public boolean addWindowListener (WindowListener listener);

   /**
    * Removes a window listener from the set of listeners.
    *
    * @param listener the listener to remove
    * @return true if the listener was removed from the collection
    *         or false if the listener was not in the collection.
    */
   public boolean removeWindowListener (WindowListener listener);
}
