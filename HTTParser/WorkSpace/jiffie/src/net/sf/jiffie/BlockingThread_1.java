/*
 * file:       BlockingThread.java
 * author:     Jon Iles
 * copyright:  (c) Packwood Software Limited 2004
 * date:       29/12/2004
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
 * This implements a new thread in which a blocking operation is carried
 * out on an IHTMLElement. This is used where the call to a method blocks, 
 * normally because a modal dialog has been displayed by the web page. Invoking
 * the blocking method in a separate thread allows the main thread to
 * deal with the dialog and continue processing.
 */
public abstract class BlockingThread extends Thread
{
   /**
    * Constructor.
    *
    * @param element target element
    */
   public BlockingThread (IHTMLElement element)
   {
      m_element = element;
   }
   
   /**
    * Thread entry point.
    */
   @Override
   public void run ()
   {
      try
      {
         runBlockingMethod();
      }

      catch (JiffieException ex)
      {
         // we should never see an exception thrown as we
         // are not blocking on the click
      }
      
      runBlockingMethodComplete ();
   }

   /**
    * This method is called to execute the actual method which is expected
    * to block.
    * 
    * @throws JiffieException
    */
   protected abstract void runBlockingMethod ()
      throws JiffieException;

   /**
    * This method is called when the blocking method has completed. It
    * allows resources to be freed, and the state of the ready flag
    * to be set.
    */
   protected void runBlockingMethodComplete ()
   {
      m_element.release();
      m_ready = true;                        
   }
   
   /**
    * This flag can be used to test whether the thread is still blocked
    * waiting for the method to complete. This is useful in situations where
    * it takes a variable length of time for the blocking dialog to appear,
    * and there is therefore a danger that the keystrokes sent to the browser
    * in the main thread will "miss" the dialog, and leave the Internet
    * Explorer instance waiting for keystrokes which will never arrive.
    * In these cases this method can be polled by the user to ensure that
    * the thread is successfully unblocked, and take appropriate action
    * (e.g. send the keystrokes again) if the thread reamins blocked.
    * 
    * @return true if the thread is no longer blocked, false if it is still waiting
    */
   public boolean getReady ()
   {
      return (m_ready);
   }
   
   protected IHTMLElement m_element;
   private boolean m_ready;
}
