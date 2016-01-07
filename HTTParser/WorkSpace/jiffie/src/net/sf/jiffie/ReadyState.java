/*
 * file:       ReadyState.java
 * author:     Jon Iles
 * copyright:  (c) Packwood Software Limited 2005
 * date:       11/01/2005
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
 * Instances of this class represent enumerated ready state values.
 */
public final class ReadyState
{
   /**
    * Private constructor.
    * 
    * @param value ready state value
    */
   private ReadyState (int value)
   {
      m_value = value;
   }

   /**
    * Retrieves the int representation of the ready state
    * 
    * @return ready state value
    */
   public int getValue ()
   {
      return (m_value);
   }
   
   /**
    * Retrieve a ReadyState instance representing the supplied value.
    * 
    * @param value ready state  
    * @return ReadyState instance
    */
   public static ReadyState getInstance (int value)
   {
      ReadyState result;
      
      if (value < 0 || value >= OBJECT_VALUES.length)
      {
         result = COMPLETE;
      }
      else
      {
         result = OBJECT_VALUES[value];
      }
      
      return (result);
   }
   
   /**
    * Retrieve a ReadyState instance representing the supplied value.
    * 
    * @param value ready state  
    * @return ReadyState instance
    */
   public static ReadyState getInstance (String value)
   {
      ReadyState result = COMPLETE;
      if (value != null)
      {
         value = value.toLowerCase();
         for (int loop=0; loop < STRING_VALUES.length; loop++)
         {
            if (value.equals(STRING_VALUES[loop]) == true)
            {
               result = OBJECT_VALUES[loop];
               break;
            }
         }
      }
      return (result);
   }

   
   private int m_value;

   
     
   /**
    * Object is not initialized with data.
    */
   public static final ReadyState UNINITIALIZED = new ReadyState(0);

   /**
    * Object is loading its data.
    */
   public static final ReadyState LOADING = new ReadyState(1);

   /**
    * Object has finished loading its data.
    */
   public static final ReadyState LOADED = new ReadyState(2);

   /**
    * User can interact with the object even though it is not fully loaded.
    */
   public static final ReadyState INTERACTIVE = new ReadyState(3);

   /**
    * Object is completely initialized.
    */
   public static final ReadyState COMPLETE = new ReadyState(4);

   /**
    * Array of string values corresponding to integer ready state values.
    */
   private static final String[] STRING_VALUES = 
   {
      "uninitialized", 
      "loading", 
      "loaded", 
      "interactive", 
      "complete"
   };

   /**
    * Array of ReadyState instances corresponding to integer ready state values.
    */   
   private static final ReadyState[] OBJECT_VALUES =
   {
      UNINITIALIZED,
      LOADING,
      LOADED,
      INTERACTIVE,
      COMPLETE
   };
}
