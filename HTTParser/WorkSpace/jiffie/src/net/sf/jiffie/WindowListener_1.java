/*
 * file:       WindowListener.java
 * author:     Mark Richter
 * copyright:  (c) Packwood Software Limited 2004
 * date:       21/09/2004
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
 * Interface which must be implemented by classes wishing to receive
 * window events from Internet Explorer. The events themselves are described 
 * in detail at:
 * 
 * http://msdn.microsoft.com/library/default.asp?url=/workshop/browser/webbrowser/reflist_vb.asp
 */
public interface WindowListener
{
   /**
    * ClientToHostWindow event handler.
    * 
    * @param width window width
    * @param height window height
    */
   public void clientToHostWindow(int width, int height);   
   
   /**
    * CommandStateChange event handler.
    * 
    * @param command command
    * @param enabled enabled flag
    */
   public void commandStateChange(int command, boolean enabled);   
   

   /**
    * OnFullScreen event handler
    * 
    * @param fullScreen true if window is full screen
    */
   public void onFullScreen(boolean fullScreen);   
   
   /**
    * OnMenuBar event handler.
    * 
    * @param menuBarVisible true if menu bar is visible
    */
   public void onMenuBar(boolean menuBarVisible);   

   /**
    * OnStatusBar event handler.
    * 
    * @param statusBarVisible true if status bar is visible
    */
   public void onStatusBar(boolean statusBarVisible);   
   
   /**
    * OnTheatreMode event handler.
    * 
    * @param theatreMode true if browser is in theatre mode.
    */
   public void onTheatreMode(boolean theatreMode);   
      
   /**
    * ToolBarVisible event handler.
    * 
    * @param toolBarVisible true if tool bar is visible
    */
   public void onToolBar(boolean toolBarVisible);   
      
   /**
    * OnVisible event handler.
    * 
    * @param visible true if object is visible.
    */
   public void onVisible(boolean visible);   
   
   /**
    * OnQuit event handler.
    */
   public void onQuit ();
   
   /**
    * PrintTemplateInstantiation event handler.
    */
   public void printTemplateInstantiation(); 
   
   /**
    * PrintTemplateTeardown event handler.
    */
   public void printTemplateTeardown();   
   
   /**
    * PrivacyImpactedStateChange event handler.
    * 
    * @param impacted true if privacy has been impacted.
    */
   public void privacyImpactedStateChange(boolean impacted);   
   
   /**
    * PropertyChange event handler.
    * 
    * @param property property name
    */
   public void propertyChange(String property);   
   
   /**
    * SetSecureLockIcon event handler.
    * 
    * @param flag status flag
    */
   public void setSecureLockIcon(int flag);   
   
   /**
    * StatusTextChange event handler.
    *
    * @param statusText String that specifies the new status bar text.
    */
   public void statusTextChange (String statusText);
   
   /**
    * TitleChange event handler.
    *
    * @param title String that specifies the new document title.
    */
   public void titleChange (String title);
   
   /**
    * WindowClosing event handler.
    *
    * @param isChildWindow flag indicating if this window was created by a script
    * @return flag indicating if the window is allowed to close
    */
   public boolean windowClosing (boolean isChildWindow);   

   /**
    * WindowSetHeight event handler.
    * 
    * @param height window height
    */
   public void windowSetHeight(int height); 
   
   /**
    * WindowSetLeft event handler.
    * 
    * @param left window left position
    */
   public void windowSetLeft(int left);   
   
   
   /**
    * WindowSetResizable window resizable flag.
    * 
    * @param resizable true if window is resizable
    */
   public void windowSetResizable(boolean resizable);   
   
   /**
    * WindowSetTop event handler.
    * 
    * @param top window top position
    */
   public void windowSetTop(int top);   
   
   /**
    * WindowSetWidth event handler.
    * 
    * @param width window width
    */
   public void windowSetWidth(int width);   
}
