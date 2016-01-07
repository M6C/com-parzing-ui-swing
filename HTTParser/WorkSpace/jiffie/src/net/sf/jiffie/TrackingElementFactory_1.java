/*
 * file:       TrackingElementFactory.java
 * author:     Jon Iles
 * copyright:  (c) Packwood Software Limited 2004
 * date:       16/12/2004
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

import java.io.PrintStream;
import java.util.Iterator;
import java.util.LinkedList;

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * This class extends the default element factory to include functionality
 * to track all entities created by the factory. When the release method of
 * this factory is called, any unreleased entities will have their release
 * method called. This is designed to be used for example as part of the 
 * test fixture setUp and tearDown methods supported by JUnit. After each
 * test this factory can be used to ensure that no entities have been
 * leaked due to their release methods not being called.
 * 
 * The second purpose of this class is to provide debugging information
 * about leaked entities. If the debug output flag is set to true, then
 * an instance of this factory will generate a list of the entities which
 * have not been released by the caller. This functionality can be extended
 * by setting the stack trace flag to true, which will capture stack trace
 * details each time an entity is created, and will output these as part of the
 * debug message allowing the user to determine where in their code they are
 * not cleaning up resources they have allocated.
 * 
 * Note that working in its default mode this class is not suitable for 
 * long-running processes which use Jiffie, as it holds on to references to all 
 * entities created by the underlying factory. To support longer running 
 * processes a watchdog thread has been implemented. This can be started and 
 * stopped from the start/stopWatchdogThread methods. It wakes up periodically
 * and removes any entities from the internal list which have 
 * already been released. By default the watchdog wakes up every 10 seconds,
 * but this time period can be set by calling the setWatchdogSleepTime method.
 * 
 * Obviously developers can rely on the finalize method of any leaked resources
 * being called by the JVM, assuming that they are eligible for 
 * garbage collection. However when dealing with Windows COM resources it is
 * probably best practice not to allow these resources to leak as you may end
 * up with Windows getting itself into difficulties if large numbers of entities
 * are not released.
 */
public class TrackingElementFactory extends DefaultElementFactory
{
   /**
    * {@inheritDoc}
    */
   @Override
   public IDispatch createElement (InternetExplorer parentBrowser, Dispatch dispatch)
      throws JiffieException
   {
      IDispatch element = super.createElement(parentBrowser, dispatch);
      track(element);
      return (element);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public ElementList createElementList (InternetExplorer parentBrowser, Variant variant)
      throws JiffieException
   {
      ElementList list = super.createElementList(parentBrowser, variant);
      track(list);
      return (list);
   }

   /**
    * Set this flag to true to produce debug output listng unreleased entities.
    * 
    * @param debugOutput boolean flag
    */
   public void setDebugOutput (boolean debugOutput)
   {
      m_debugOutput = debugOutput;
   }

   /**
    * Retrieves a flag indicating if debug output is being generated.
    * 
    * @return boolean flag
    */
   public boolean getDebugOutput ()
   {
      return (m_debugOutput);
   }
   
   /**
    * This method is called to set the destination for any debugging output
    * produced by this class. Note that the default destination for this
    * output is System.err
    * 
    * @param ps print stream
    */
   public void setDebugPrintStream (PrintStream ps)
   {
      m_debugPrintStream = ps;
   }

   /**
    * Retrieves the destination print stream for debug output.
    * 
    * @return debug print stream
    */
   public PrintStream getDebugPrintStream ()
   {
      return (m_debugPrintStream);
   }
   
   /**
    * Set this flag to true to generate stack traces as part of the
    * debug output.
    * 
    * @param stackTrace boolean flag
    */
   public void setStackTrace (boolean stackTrace)
   {
      m_stackTrace = stackTrace;
   }
   
   /**
    * Retrieves a flag indicating if stack traces are produced as part 
    * of the debug output.
    * 
    * @return boolean flag
    */
   public boolean getStackTrace ()
   {
      return (m_stackTrace);
   }
   
   /**
    * Set the watchdog thread sleep time in seconds. By default this
    * value is set to 10 seconds.
    * 
    * @param sleepTimeInSeconds sleep time in seconds
    */
   public void setWatchdogSleepTime (int sleepTimeInSeconds)
   {
      m_watchdogSleepTime = 1000 * sleepTimeInSeconds;
   }
   
   /**
    * Retrieves the watchdog sleep time.
    * 
    * @return sleep time in seconds
    */
   public int getWatchdogSleepTime ()
   {
      return (m_watchdogSleepTime / 1000);
   }
   
   /**
    * Launches the watchdog thread.
    */
   public void startWatchdogThread ()
   {
      m_runWatchdog = true;
      if (m_watchdog == null)
      {
         m_watchdog = new WatchdogThread();
      }
      m_watchdog.start();
   }

   /**
    * Stops the watchdog thread.
    */
   public void stopWatchdogThread ()
   {
      m_runWatchdog = false;
      m_watchdog.interrupt();
   }
   
   /**
    * This method is called to begin tracking an entity.
    * 
    * @param object entity t be tracked
    */
   protected void track (Releaseable object)
   {
      synchronized (m_items)
      {               
         m_items.add(new TrackedItem(object));      
      }
   }

   /**
    * This method is used to remove any entries from the list of items
    * being tracked by this factory which have already been released,
    * and will return a count of the number of unreleased items.
    * 
    * @return count of the number of unreleased items
    */
   public int flushReleasedAndCount ()
   {
      int result;
      
      synchronized (m_items)
      {
         Iterator<TrackedItem> iter = m_items.iterator();         
         while (iter.hasNext() == true)
         {
            TrackedItem item = iter.next();            
            if (item.m_object.isReleased() == true)
            {
               iter.remove();
            }
         }                        
         result = m_items.size();
      }      
      
      return (result);
   }
   
   /**
    * This method is called to release any previously unreleased entities.
    * Optionally it will produce debugging output describing what has
    * not been released, and where it was initially allocated.
    */
   public void release ()
   {      
      synchronized (m_items)
      {
         for (TrackedItem item : m_items)
         {         
            if (item.m_object.isReleased() == false)
            {
               if (m_debugOutput == true)
               {
                  writeDebugOutput(item);
               }
               item.m_object.release();
            }
         }
         
         m_items.clear();
      }
   }
   
   /**
    * This method is designed to be overloaded by subclasses to allow developers
    * to produce their own diagnostic ouput if any is required.
    * 
    * @param item item which has not been released
    */
   protected void writeDebugOutput (TrackedItem item)
   {
      m_debugPrintStream.println ("Unreleased " + item.m_object.getClass().getName());
      if (item.m_stackFrames != null)
      {
         m_debugPrintStream.println ("Allocation:");            
         for (int loop=0; loop < item.m_stackFrames.length; loop++)
         {
            m_debugPrintStream.println("\tat " + item.m_stackFrames[loop]);
         }      
      }
   }
   
   /**
    * Simple container for entities being tracked.
    */
   protected class TrackedItem
   {
      /**
       * Constructor.
       * 
       * @param object entity to be tracked
       */
      public TrackedItem (Releaseable object)
      {
         m_object = object;
         if (m_stackTrace == true)
         {
            Exception ex = new Exception();
            ex.fillInStackTrace();
            m_stackFrames = ex.getStackTrace();
         }
      }
      
      
      public Releaseable m_object;
      public StackTraceElement[] m_stackFrames;
   }
   
   /**
    * Simple watchdog thread used to remove released items from the list.
    */
   protected class WatchdogThread extends Thread
   {
      /**
       * {@inheritDoc}
       */
      @Override
      public void run ()
      {
         while (m_runWatchdog == true)
         {
            try
            {
               Thread.sleep(m_watchdogSleepTime);
            }
            
            catch (InterruptedException ex)
            {
               // ignore interrupted sleep
            }
                   
            if (m_runWatchdog == false)
            {
               break;
            }

            flushReleasedAndCount();
         }         
      }
   }
   
   protected boolean m_stackTrace;
   private boolean m_debugOutput;
   private PrintStream m_debugPrintStream = System.err;
   protected boolean m_runWatchdog;
   private WatchdogThread m_watchdog;
   protected int m_watchdogSleepTime = 10 * 1000;
   private LinkedList<TrackedItem> m_items = new LinkedList<TrackedItem>();
}
