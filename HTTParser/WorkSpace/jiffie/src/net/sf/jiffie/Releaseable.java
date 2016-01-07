/*
 * file:       Releaseable.java
 * author:     Jon Iles
 *             Piran Montford
 * copyright:  (c) Packwood Software Limited 2004
 * date:       17/10/2004
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
 * This interface represents a an object that supports the release method.
 * Generally speaking, if you retrieve an object from Jiffie which supports
 * this interface, you should be calling the release method to clean
 * up its resources once you have finished with it.
 */
public interface Releaseable
{
   /**
    * This method releases the COM object associated with this object.
    */
   public void release ();
   
   /**
    * This method returns a boolean flag indicating if this object
    * has already been released.
    * 
    * @return boolean flag
    */
   public boolean isReleased ();  
}
