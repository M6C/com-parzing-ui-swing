/*
 * file:       IDispatch.java
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
import com.jacob.com.Variant;


/**
 * This class implements functionality common to all COM objects.
 */
public class IDispatch implements Cloneable, Releaseable
{
   /**
    * Constructor.
    *
    * @param dispatch Jacob dispatch interface
    */
   IDispatch (Dispatch dispatch)
   {
      m_dispatch = dispatch;
   }

   /**
    * Called to indicate that the caller no longer requires this object.
    */
   public void release ()
   {
      m_dispatch.safeRelease();
   }

   /**
    * This method returns a boolean flag indicating if this object
    * has already been released.
    * 
    * @return boolean flag
    */
   public boolean isReleased ()
   {
      return (!(m_dispatch != null && m_dispatch.m_pDispatch != 0));
   }

   /**
    * Retrieve a reference to this object's underlying dispatch interface.
    *
    * @return dispatch interface
    */
   public Dispatch getDispatch ()
   {
      return (m_dispatch);
   }
   
   /**
    * Convenience method for retrieving an arbitrary Variant property.
    *
    * @param name property name
    * @return property value
    */
   public Variant getVariantProperty (String name)
   {
      return (Dispatch.get(m_dispatch, name));
   }
   
   /**
    * Convenience method for retrieving an arbitrary Dispatch property.
    *
    * @param name property name
    * @return property value
    */
   public Dispatch getDispatchProperty (String name)
   {
      Dispatch dispatch = Dispatch.get(m_dispatch, name).toDispatch();

      if (dispatch.m_pDispatch == 0)
      {
         dispatch.safeRelease();
         dispatch = null;
      }

      return (dispatch);
   }

   /**
    * Convenience method for retrieving an arbitrary String property.
    *
    * @param name property name
    * @return property value
    */
   public String getStringProperty (String name)
   {
      Variant variant = Dispatch.get(m_dispatch, name);
      return ((variant == null || variant.isNull()) ? null : variant.toString());
   }

   /**
    * Convenience method for setting an arbitrary String property.
    *
    * @param name property name
    * @param value property value
    */
   public void setStringProperty (String name, String value)
   {
      Dispatch.put(m_dispatch, name, value);
   }

   /**
    * Convenience method for retrieving an arbitrary int property.
    *
    * @param name property name
    * @return property value
    */
   public int getIntegerProperty (String name)
   {
      return (Dispatch.get(m_dispatch, name).changeType(Variant.VariantInt).getInt());
   }

   /**
    * Convenience method for setting an arbitrary int property.
    *
    * @param name property name
    * @param value property value
    */
   public void setIntegerProperty (String name, int value)
   {
      Dispatch.put(m_dispatch, name, new Integer(value));
   }

   /**
    * Convenience method for retrieving an arbitrary boolean property.
    *
    * @param name property name
    * @return property value
    */
   public boolean getBooleanProperty (String name)
   {
      return (Dispatch.get(m_dispatch, name).changeType(Variant.VariantBoolean).getBoolean());
   }

   /**
    * Convenience method for setting an arbitrary boolean property.
    *
    * @param name property name
    * @param value property value
    */
   public void setBooleanProperty (String name, boolean value)
   {
      Dispatch.put(m_dispatch, name, ((value == true) ? Boolean.TRUE : Boolean.FALSE));
   }

   /**
    * Convenience method for calling a method.
    * 
    * @param name method name
    * @return variant return value
    */
   public Variant call(String name)
   {
      return (Dispatch.call(m_dispatch, name));
   }

   /**
    * Convenience method for calling a method.
    * 
    * @param name method name
    * @param arg mathod argument
    * @return variant return value
    */
   public Variant call(String name, Object arg)
   {
      return (Dispatch.call(m_dispatch, name, arg));
   }
   
   /**
    * Convenience method for calling a method.
    * 
    * @param name method name
    * @param args method arguments
    * @return variant return value
    */
   public Variant call(String name, Object[] args)
   {
      return (Dispatch.callN(m_dispatch, name, args));
   }

   /**
    * Convenience method for calling a method of an arbitrary dispatch
    * interface.
    * 
    * @param dispatch target dispatch interface
    * @param name method name
    * @param argv method arguments
    * @param argc method arguments
    * @return variant return value
    */   
   public Variant call(Dispatch dispatch, String name, Variant[] argv, int[] argc)
   {
      return (Dispatch.invoke(dispatch, name, Dispatch.Method, argv, argc));
   }
   
   /**
    * Convenience method for calling a method.
    * 
    * @param name method name
    * @param argv method arguments
    * @param argc method arguments
    * @return variant return value
    */   
   public Variant call(String name, Variant[] argv, int[] argc)
   {
      return (call(m_dispatch, name, argv, argc));
   }
      
   /**
    * Attempt to retrieve the interface identified by the iid parameter.
    * 
    * @param iid IID
    * @return dispatch interface
    */
   public Dispatch queryInterface (String iid)
   {
      return (m_dispatch.QueryInterface(iid));      
   }
   
   /**
    * Create a new Dispatch object with a reference to this Dispatch object's 
    * underlying interface. The two Dispatches can be independently released 
    * without harm.
    * 
    * @param dispatch dispatch object
    * @return new dispatch object.
    */
   public static Dispatch newDispatch (Dispatch dispatch)
   {
      return (dispatch.QueryInterface(IDispatch.IID));
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Object clone ()
      throws CloneNotSupportedException
   {
      IDispatch copy = (IDispatch)super.clone();
      copy.m_dispatch = newDispatch(m_dispatch);
      return copy;
   }

   /**
    * @see Object#finalize()
    */
   @Override
   protected void finalize ()
   {
      if (m_dispatch != null)
      {
         m_dispatch.safeRelease();
      }
   }

   public static final String IID = "{00020400-0000-0000-C000-000000000046}";
   private Dispatch m_dispatch;
}
