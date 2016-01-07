/*
 * file:       ElementList.java
 * author:     Piran Montford
 *             Jon Iles
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

import java.util.List;


/**
 * This interface represents a collection of IHTMLElements and is similar in
 * operation to an IHTMLElementCollection. It is used
 * to simplify the management of methods which can potentially return null,
 * a single object, or a list of objects. For these cases an instance of an
 * object implementing this interface is returned. These objects may represent
 * an empty list, a list containing a single object, or a list containing
 * multiple objects.
 */
public interface ElementList extends List<IDispatch>, Releaseable
{
   /**
    * This is a convenience method designed to emulate the behaviour of the
    * item method of the IHTMLElementCollection interface.
    *
    * @param index zero based array index
    * @return object at this index in the collection
    * @throws JiffieException if there is an error creating list
    */
   public IDispatch item (int index)
      throws JiffieException;

   /**
    * This is a convenience method designed to emulate the behaviour of the
    * item method of the IHTMLElementCollection interface.
    *
    * @param name name or id attribute of a tag
    * @return list of tags with id or name attributes matching the supplied value
    * @throws JiffieException if there is an error creating list
    */
   public ElementList item (String name)
      throws JiffieException;

   /**
    * This is a convenience method designed to emulate the behaviour of the
    * tags method of the IHTMLElementCollection interface.
    *
    * @param name tag type name
    * @return list of tags whose tage type matches the supplied value
    * @throws JiffieException if there is an error creating list
    */
   public ElementList tags (String name)
      throws JiffieException;
}
