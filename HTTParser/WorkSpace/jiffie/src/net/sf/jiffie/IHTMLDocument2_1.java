/*
 * file:       IHTMLDocument2.java
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
 * Implementation of the IHTMLDocument2 interface.
 */
public class IHTMLDocument2 extends ElementContainer
{
   /**
    * Constructor
    *
    * @param parentBrowser parent browser
    * @param object Jacob dispatch interface
    */
   public IHTMLDocument2 (InternetExplorer parentBrowser, Dispatch object)
   {
      super(parentBrowser, object);
   }

   /**
    * Retrieves the document title.
    *
    * @return document title
    */
   public String getTitle ()
   {
      return (getStringProperty("title"));
   }

   /**
    * Retrieves the document url.
    *
    * @return document url
    */
   public String getUrl ()
   {
      return (getStringProperty("url"));
   }

   /**
    * Retrieve the body of the document.
    *
    * @return element inner HTML
    */
   public IHTMLBodyElement getBody ()
   {
      IHTMLBodyElement element;

      Dispatch dispatch = getDispatchProperty("body");

      if (dispatch == null)
      {
         element = null;
      }
      else
      {
         element = new IHTMLBodyElement(getParentBrowser(), dispatch);
      }

      return (element);
   }

   /**
    * Writes HTML text into the document.
    *
    * @param html HTML text
    */
   public void write (String html)
   {
      call("write", html);
   }

   /**
    * Writes HTML text into the document.
    *
    * @param html HTML text
    */
   public void writeln (String html)
   {
      call("writeln", html);
   }

   /**
    * Retrieves the cookie associated with this document.
    *
    * @return document cookie
    */
   public String getCookie ()
   {
      return (getStringProperty("cookie"));
   }

   /**
    * Sets the cookie associated with this document.
    *
    * @param cookie document cookie
    */
   public void setCookie (String cookie)
   {
      setStringProperty("cookie", cookie);
   }

   /**
    * Retrieves the security domain of the document.
    *
    * @return security domain of the document.
    */
   public String getDomain ()
   {
      return (getStringProperty("domain"));
   }

   /**
    * Sets the security domain of the document.
    *
    * @param domain security domain of the document
    */
   public void setDomain (String domain)
   {
      setStringProperty("domain", domain);
   }

   /**
    * Retrieve the document element. This retrieves the root node of the
    * document, typically the <html> tag for an HTML document, but may
    * differ depending on the document type.
    *
    * @return root node of the document as an IHTMLElement
    */
   public IHTMLElement getDocumentElement ()
      throws JiffieException
   {
      return (getElementProperty("documentElement"));
   }

   /**
    * Creates an element given the tagName.
    *
    * @param tagName tag name of the element to be created.
    * @return A refference to the element that was created.
    * @throws JiffieException
    */
   public IHTMLElement createElement (String tagName)
      throws JiffieException
   {
      return ((IHTMLElement)(JiffieUtility.getElementFactory().createElement(getParentBrowser(), call("createElement", tagName))));
   }

   /**
    * Executes a command against the current page. The list of available
    * commands is described at the following URL:
    * 
    * http://msdn.microsoft.com/library/default.asp?url=/workshop/author/dhtml/reference/commandids.asp
    * 
    * @param command name of the command to execute
    * @param showUI flag indicating if the user interface associated with the command should be shown
    * @param argument argument data
    * @return boolean flag indicating if the command was successful
    */
   public boolean execCommand (String command, boolean showUI, Variant argument)
   {
      Variant[] argv = new Variant[4];
      int[] argc = new int[4];
            
      argv[0] = new Variant(command);
      argv[1] = new Variant(showUI);
      argv[2] = argument;
      argv[3] = null;
      
      return (call("execCommand", argv, argc).getBoolean());
   }
   
   /**
    * Retrieves a list of frames if the current document contains a 
    * frameset tag, or a list of iframes if this document contains a body tag.
    * 
    * @return list of frames or iframes
    * @throws JiffieException
    */
   public ElementList getFrames ()
      throws JiffieException
   {
      //
      // We should be able to execute the code below to retrieve the
      // document's frames collection directly, However this fails
      // withh a "No such interface supported" error. This means we 
      // have had to implement the required functionality ourselves.
      //
      //Variant frames = getVariantProperty("frames");
      //return (JiffieUtility.getElementFactory().createElementList(getParentBrowser(), frames));
      
      ElementList frames;
      IHTMLElement frameset = (IHTMLElement)getElementByTag("frameset");
      if (frameset != null)
      {
         frameset.release();
         frameset = null;
         frames = getElementListByTag("frame");
      }
      else
      {
         frames = getElementListByTag("iframe");
      }
      
      return (frames);
   }
   
   /**
    * This method is called to wait until the document is complete. Clients of
    * Jiffie should call this prior to attempting to use any of the document
    * contents.
    *
    * @throws JiffieException
    */
   public void waitWhileIncomplete ()
      throws JiffieException
   {
      JiffieUtility.waitWhileIncomplete(this);
   }

   public static final String IID = "{626FC520-A41E-11CF-A731-00A0C9082637}";
}
