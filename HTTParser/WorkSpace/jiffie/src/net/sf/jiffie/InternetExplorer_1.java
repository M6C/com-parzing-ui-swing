/*
 * file:       InternetExplorer.java
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

import java.util.LinkedList;
import com.jacob.com.Dispatch;
import com.jacob.com.DispatchEvents;
import com.jacob.com.Variant;


/**
 * This class represents an instance of an Microsoft Internet Explorer
 * web browser.
 */
public class InternetExplorer extends IDispatch
{
   /**
    * Constructor.
    *
    * @param dispatch internet explorer dispatch interface created elsewhere
    */
   public InternetExplorer (Dispatch dispatch)
   {
      super(dispatch);
      
      if (JiffieUtility.getEventHandlingEnabled() == true)
      {
         enableEventHandling();
      }
   }

   /**
    * Constructor.
    */
   public InternetExplorer ()
   {
      this(new Dispatch("InternetExplorer.Application"));
   }

   /**
    * This method is used to explicitly enable event handling for this
    * browser instance. Note that events can't be disabled once they have
    * been enabled.
    */
   public void enableEventHandling ()
   {
      if (m_events == null)
      {
         m_newWindowList = new LinkedList<InternetExplorer>();         
         m_events = JiffieUtility.getEventHandlerFactory().createEventHandler(this);
         m_dispatchEvents = new DispatchEvents(getDispatch(), m_events, "InternetExplorer.Application.1");         
      }
   }
   
   /**
    * Call this method to determine if event handling has been enabled
    * for this InternetExplorer instance.
    * 
    * @return boolean flag
    */
   public boolean eventHandlingEnabled ()
   {
      return (m_events != null);
   }
   
   /**
    * Set the flag indicating if the browser is visible.
    *
    * @param visible boolean flag
    */
   public void setVisible (boolean visible)
   {
      setBooleanProperty("Visible", visible);
   }

   /**
    * Retrieves the flag indicating whether the browser is visible
    *
    * @return boolean flag
    */
   public boolean getVisible ()
   {
      return (getBooleanProperty("Visible"));
   }

   /**
    * Set the flag that indicates if this instance is a top level
    * web browser.
    *
    * @param registerAsBrowser boolean flag
    */
   public void setRegisterAsBrowser (boolean registerAsBrowser)
   {
      setBooleanProperty("RegisterAsBrowser", registerAsBrowser);
   }

   /**
    * Retrieve the flag that indicates that this is a top level
    * web browser.
    *
    * @return boolean flag
    */
   public boolean getRegisterAsBrowser ()
   {
      return (getBooleanProperty("RegisterAsBrowser"));
   }

   /**
    * Retrieves a flag indicating whether the browser can show dialog boxes.
    * Returns false (Default) if all dialog boxes and messages will be shown or
    * returns true if they will be suppressed (Critical errors and security
    * alerts are not supressed).
    *
    * @return boolean flag indicating whether the browser can show dialog boxes
    */
   public boolean getSilent ()
   {
      return (getBooleanProperty("Silent"));
   }

   /**
    * Sets a flag indicating whether the browser can show dialog boxes.
    * Set to false (Default) to show all dialog boxes and messages or set to
    * true to suppress them (Critical errors and security alerts are not
    * supressed).
    *
    * @param silent boolean flag
    */
   public void setSilent (boolean silent)
   {
      setBooleanProperty("Silent", silent);
   }

   /**
    * Retrieves the URL of the resource that Microsoft
    * Internet Explorer is currently displaying
    *
    * @return a String URL of the resource that this Internet Explorer is
    *         currently displaying.
    */
   public String getLocationURL ()
   {
      return (getStringProperty("LocationURL"));
   }

   /**
    * Retrieves the handle of the Microsoft Internet Explorer main window.
    *
    * @return the int handle of the Internet Explorer main window.
    */
   public int getHWND ()
   {
      return (getIntegerProperty("HWND"));
   }

   /**
    * Sets the width of the main window for the object.
    *
    * @param width the new width, in pixels
    */
   public void setWidth (int width)
   {
      setIntegerProperty("Width", width);
   }

   /**
    * Retrieves the width of the main window for the object.
    *
    * @return the width of the main window of the object.
    */
   public int getWidth ()
   {
      return (getIntegerProperty("Width"));
   }

   /**
    * Sets the height of the main window for the object.
    *
    * @param height the new height, in pixels
    */
   public void setHeight (int height)
   {
      setIntegerProperty("Height", height);
   }

   /**
    * Retrieves the height of the main window for the object.
    *
    * @return the height of the main window of the object.
    */
   public int getHeight ()
   {
      return (getIntegerProperty("Height"));
   }

   /**
    * Sets the screen coordinate of the top edge of the main window of the object.
    *
    * @param top the new location for the top edge of the window
    */
   public void setTop (int top)
   {
      setIntegerProperty("Top", top);
   }

   /**
    * Retrieves the screen coordinate of the top edge of the main window of the object.
    *
    * @return the screen coordinate of the top edge of the main window of the object.
    */
   public int getTop ()
   {
      return (getIntegerProperty("Top"));
   }

   /**
    * Sets the screen coordinate of the left edge of the main window of the object.
    *
    * @param left the new location for the left edge of the window
    */
   public void setLeft (int left)
   {
      setIntegerProperty("Left", left);
   }

   /**
    * Retrieves the screen coordinate of the left edge of the main window of the object.
    *
    * @return the screen coordinate of the left edge of the main window of the object.
    */
   public int getLeft ()
   {
      return (getIntegerProperty("Left"));
   }

   /**
    * Navigate the web browser to a given url
    *
    * @param url target url
    */
   public void navigate (String url)
   {
      call("Navigate", new Variant[] {new Variant(url)});
   }

   /**
    * Navigate the web browser to a given url
    *
    * @param url target url
    * @param wait should method block until navigation complete
    */
   public void navigate (String url, boolean wait)
      throws JiffieException
   {
      call("Navigate", new Variant[] {new Variant(url)});

      if (wait == true)
      {
         waitWhileBusy();
      }
   }

   /**
    * Close the web browser.
    */
   public void quit ()
   {
      call("Quit", new Variant[0]);
   }

   /**
    * Navigate the web browser to the previous url in the browser history
    *
    * @param wait indicates if the method should block until navigation complete
    */
   public void goBack (boolean wait)
      throws JiffieException
   {
      call("GoBack", new Variant[0]);

      if (wait == true)
      {
         waitWhileBusy();

         IHTMLDocument2 document = getDocument(true);
         document.release();
      }
   }

   /**
    * Navigate the web browser to the next url in the browser history
    *
    * @param wait indicates if the method should block until navigation complete
    */
   public void goForward (boolean wait)
      throws JiffieException
   {
      call("GoForward", new Variant[0]);

      if (wait == true)
      {
         waitWhileBusy();
      }
   }

   /**
    * Refresh the current page
    *
    * @param wait indicates if the method should block until refresh complete
    */
   public void refresh (boolean wait)
      throws JiffieException
   {
      call("Refresh", new Variant[0]);

      if (wait == true)
      {
         waitWhileBusy();
      }
   }

   /**
    * Retrieve the flag that indicates if the browser is currently busy.
    *
    * @return boolean flag
    */
   public boolean getBusy ()
   {
      return (getBooleanProperty("Busy") || (getReadyState() != ReadyState.COMPLETE));
   }

   /**
    * Retrieves the ready state of the browser.
    *
    * @return ready state
    */
   public ReadyState getReadyState ()
   {
      return (ReadyState.getInstance(getIntegerProperty("ReadyState")));
   }

   /**
    * Cancels any pending navigation or download operation and stops any
    * dynamic page elements, such as background sounds and animations.
    */
   public void stop ()
   {
      call("Stop", new Variant[0]);
   }

   /**
    * This method is called to wait until the browser is not busy. Clients of
    * Jiffie should call this prior to attempting to use any of the browser
    * contents.
    *
    * @throws JiffieException
    */
   public void waitWhileBusy ()
      throws JiffieException
   {
      int count = 0;
      int pollTime = JiffieUtility.getPollTime();
      int maxPollCount = JiffieUtility.getMaxPollCount();

      try
      {
         Thread.sleep(100);
      }

      catch (InterruptedException ex)
      {
         // ignore interrupted sleep
      }

      while (getBusy() == true)
      {
         if (count == maxPollCount)
         {
            throw new JiffieException("Timeout waiting for idle browser");
         }

         try
         {
            Thread.sleep(pollTime);
         }

         catch (InterruptedException ex)
         {
            // ignore interrupted sleep
         }

         ++count;
      }
   }

   /**
    * Retrieves the top level document displayed in the browser.
    *
    * @param wait flag indicating if the method should block until the document is complete
    * @return document
    * @throws JiffieException
    */
   public IHTMLDocument2 getDocument (boolean wait)
      throws JiffieException
   {
      IHTMLDocument2 document = (IHTMLDocument2)(JiffieUtility.getElementFactory().createElement(this, getDispatchProperty("Document")));

      if (wait == true)
      {
         document.waitWhileIncomplete();
      }

      return (document);
   }

   /**
    * Retrieves the number of new windows that have been created by
    * this browser.
    *
    * @return number of new windows
    */
   public int getNewWindowCount ()
      throws JiffieException
   {
      if (m_events == null)
      {
         throw new JiffieException ("Event handling not enabled");
      }
      return (m_newWindowList.size());
   }

   /**
    * Retrieves a reference to the last new window created by this
    * browser.
    *
    * @param wait indicates if the method should block until new window not busy
    * @return InternetExplorer instance representing the new window
    */
   public InternetExplorer getLastNewWindow (boolean wait)
      throws JiffieException
   {
      if (m_events == null)
      {
         throw new JiffieException ("Event handling not enabled");
      }
      
      InternetExplorer browser = m_newWindowList.removeFirst();

      if (wait == true)
      {
         browser.waitWhileBusy();
      }

      return (browser);
   }

   /**
    * Adds a new navigation listener to the set of listeners to
    * be notifed when navigation events occur.
    *
    * @param listener the new listener to add
    * @return true if the listener was not already in the set
    */
   public boolean addNavigationListener (NavigationListener listener)
      throws JiffieException
   {
      if (m_events == null)
      {
         throw new JiffieException ("Event handling not enabled");
      }
      return (m_events.addNavigationListener(listener));
   }

   /**
    * Removes a navigation listener from the set of listeners.
    *
    * @param listener the listener to remove
    * @return true if the listener was in the set
    */
   public boolean removeNavigationListener (NavigationListener listener)
      throws JiffieException
   {
      if (m_events == null)
      {
         throw new JiffieException ("Event handling not enabled");
      }
      return (m_events.removeNavigationListener(listener));
   }

   /**
    * Adds a new window listener to the set of listeners to
    * be notifed when window events occur.
    *
    * @param listener the new listener to add
    * @return true if the listener was not already in the set
    */
   public boolean addWindowListener (WindowListener listener)
      throws JiffieException
   {
      if (m_events == null)
      {
         throw new JiffieException ("Event handling not enabled");
      }
      return (m_events.addWindowListener(listener));
   }

   /**
    * Removes a window listener from the set of listeners.
    *
    * @param listener the listener to remove
    * @return true if the listener was in the set
    */
   public boolean removeWindowListener (WindowListener listener)
      throws JiffieException
   {
      if (m_events == null)
      {
         throw new JiffieException ("Event handling not enabled");
      }
      return (m_events.removeWindowListener(listener));
   }

   /**
    * This method is used internally by the EventHandler class to
    * record the fact that a new browser window has been opened.
    *
    * @param ie new Internet Explorer window
    */
   void addNewWindow (InternetExplorer ie)
   {
      m_newWindowList.addFirst(ie);
   }

   /**
    * @see IDispatch#release()
    *
    * In theory we want to do this, however in practice this is likely to
    * provoke problems with Jacob. There are some issues with Jiffie/Jacob
    * which appear to be problems relating to releasing event handlers for
    * browser windows that have closed.
    */
   @Override
   public void release ()
   {
      if (m_dispatchEvents != null)
      {
         //m_dispatchEvents.safeRelease();     
         m_dispatchEvents = null;
      }
      //super.release();      
   }
   
   private EventHandler m_events;
   private DispatchEvents m_dispatchEvents;
   private LinkedList<InternetExplorer> m_newWindowList;
}
