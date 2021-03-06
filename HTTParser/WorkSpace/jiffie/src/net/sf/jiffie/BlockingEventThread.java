/*
 * file:       BlockingEventThread.java
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
 * This implements a new thread in which an event is fired for an element.
 * This is used where the call to the fire event method blocks, normally
 * because a modal dialog has been displayed by the web page. Invoking
 * the event in a separate thread allows the main thread to
 * deal with the dialog and continue processing.
 */
public class BlockingEventThread extends BlockingThread
{
   /**
    * Constructor.
    *
    * @param element target element
    * @param event name of the event to fire
    */
   public BlockingEventThread (IHTMLElement element, String event)
   {
      super(element);
      m_event = event;
   }

   /**
    * @see BlockingThread#runBlockingMethod()
    */
   @Override
   protected void runBlockingMethod ()
      throws JiffieException
   {
      m_element.fireEvent(m_event, false);
   }   

   private String m_event;
}
