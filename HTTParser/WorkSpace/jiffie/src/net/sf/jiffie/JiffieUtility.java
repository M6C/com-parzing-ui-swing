/*
 * file:       JiffieUtility.java
 * author:     Jon Iles
 * copyright:  (c) Packwood Software Limited 2004
 * date:       18/04/2004
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
 * This class contains utility methods used within jiffie.
 */
public final class JiffieUtility
{
   /**
    * This method waits until the dispatch interface indicates that it
    * is "complete" (i.e. it has finished loading, rendering etc.). The default
    * is to time out after 30 seconds and throw an exception.
    *
    * @see #setPollTime(int)
    * @see #setMaxPollCount(int)
    *
    * @param dispatch dispatch interface
    * @throws JiffieException
    */
   public static void waitWhileIncomplete (IDispatch dispatch)
      throws JiffieException
   {
      int count = 0;

      while (ReadyState.getInstance(dispatch.getStringProperty("readyState")) != ReadyState.COMPLETE)
      {
         if (count == MAX_POLL_COUNT)
         {
            throw new JiffieException("Timeout waiting for complete element");
         }

         try
         {
            Thread.sleep(POLL_TIME);
         }

         catch (InterruptedException ex)
         {
            // ignore interrupted sleep
         }

         ++count;
      }
   }

   /**
    * This method is used to send keystrokes to an arbitrary application. It
    * uses the Windows Scripting Host to achieve this. This is used in Jiffie to
    * allow users of the library to deal with modal dialog boxes. If for
    * example a user clicks a link which causes a Javascript dialog to be
    * displayed, the click method will block until the dialog is dismissed.
    * To get around this, the click method must be called from a separate
    * thread (see the BlockingClickThread class), and the main thread can then
    * use this method to send keystrokes to the modal dialog to perform the
    * appropriated actions.
    *
    * Details of the keystrokes that can be sent using this method can be
    * found at the following URL:
    *
    * http://msdn.microsoft.com/library/en-us/script56/html/wsmthsendkeys.asp
    *
    * Note that if the call to "AppActivate" fails, i.e. we have not been able
    * to set focus to the expected window, this method will poll to repeat the
    * operation, and eventually time out and throw an exception. This will
    * indicate to the caller that the keystrokes have not been successfully
    * sent to the named window.
    *
    * @param title expected dialog title
    * @param keys keystrokes to send to the dialog
    */
   public static void sendKeys (String title, String keys)
      throws JiffieException
   {
      try
      {
         Thread.sleep(100);
      }

      catch (Exception ex)
      {
         // ignore interrupted sleep
      }

      Variant[] argv = new Variant[1];
      Dispatch wsh = new Dispatch("WScript.Shell");
      argv[0] = new Variant(title);

      int count = 0;

      while (true)
      {
         if (count == MAX_POLL_COUNT)
         {
            throw new JiffieException("Failed to set focus to window with title: " + title);
         }

         if (Dispatch.callN(wsh, "AppActivate", argv).changeType(Variant.VariantBoolean).getBoolean() == true)
         {
            break;
         }

         try
         {
            Thread.sleep(POLL_TIME);
         }

         catch (InterruptedException ex)
         {
            // ignore interrupted sleep
         }

         ++count;
      }

      argv[0] = new Variant(keys);
      Dispatch.callN(wsh, "SendKeys", argv);
      wsh.safeRelease();

      try
      {
         Thread.sleep(100);
      }

      catch (Exception ex)
      {
         // ignore interrupted sleep
      }
   }

   /**
    * As per the sendKeys method this method sends keystrokes to an application.
    * In this case we assume that the application is the parent browser of the
    * supplied document object, and we wait for the document object to be
    * marked as complete before we return control back to the caller.
    *
    * @param title expected dialog title
    * @param keys keystrokes to send to the dialog
    * @param document document object
    * @throws JiffieException
    */
   public static void sendKeys (String title, String keys, IHTMLDocument2 document)
      throws JiffieException
   {
      sendKeys(title, keys);
      document.waitWhileIncomplete();
   }

   /**
    * In order to allow Internet Explorer to finish whatever operation Jiffie
    * has just asked it to do, we need to be able to poll the application's
    * status. Jiffie achieves this by testing the state of Internet Explorer,
    * and if it is not ready, sleeping for a period, then testing again.
    * This polling operation repeats for a number of iterations, and if
    * Internet Explorer is still not in the desired state, Jiffie throws an
    * exception.
    *
    * This method allows the sleep time between testing Internet Explorer's
    * state to be set. The default setting is to test the status every
    * 500ms for 60 attempts (i.e. a total of 30 seconds) then abort if the
    * desired status has not been achieved.
    *
    * This method is not designed to be thread safe, when using Jiffie from
    * multiple threads, this method should be called before the threads
    * are launched.
    *
    * @param pollTime sleep time between status tests in milliseconds
    */
   public static void setPollTime (int pollTime)
   {
      POLL_TIME = pollTime;
   }

   /**
    * In order to allow Internet Explorer to finish whatever operation Jiffie
    * has just asked it to do, we need to be able to poll the application's
    * status. Jiffie achieves this by testing the state of Internet Explorer,
    * and if it is not ready, sleeping for a period, then testing again.
    * This polling operation repeats for a number of iterations, and if
    * Internet Explorer is still not in the desired state, Jiffie throws an
    * exception.
    *
    * This method allows the number of attempts made to test Internet Explorer's
    * state to be set. The default setting is to test the status 60 times,
    * once every 500ms (i.e. a total of 30 seconds) then abort if the
    * desired status has not been achieved.
    *
    * This method is not designed to be thread safe, when using Jiffie from
    * multiple threads, this method should be called before the threads
    * are launched.
    *
    * @param maxPollCount maximum number of status tests before aborting
    */
   public static void setMaxPollCount (int maxPollCount)
   {
      MAX_POLL_COUNT = maxPollCount;
   }

   /**
    * Retrieves the poll time.
    *
    * @return poll time
    */
   public static int getPollTime ()
   {
      return (POLL_TIME);
   }

   /**
    * Retrieves the maximum poll count
    *
    * @return maximum poll count
    */
   public static int getMaxPollCount ()
   {
      return (MAX_POLL_COUNT);
   }

   /**
    * Retrieves a flag indicating if event handling is enabled by default.
    * 
    * @return boolean flag
    */
   public static boolean getEventHandlingEnabled ()
   {
      return (EVENT_HANDLING_ENABLED);
   }
   
   /**
    * Sets a flag indicating if event handling is enabled by default.
    * If you do not require event handling then disabling event handling by 
    * setting this flag to false will improve Jiffie's performance and 
    * stability. Note that if you want to handle any new browser windows 
    * launched when using Jiffie, you'll need to leave this flag set to true.
    * 
    * @param enabled event handling enabled flag
    */
   public static void setEventHandlingEnabled (boolean enabled)
   {
      EVENT_HANDLING_ENABLED = enabled;    
   }

   /**
    * Retrieve the event handler factory used by this instance of Jiffie.
    *
    * @return event handler factory
    */
   public static EventHandlerFactory getEventHandlerFactory ()
   {
      return (EVENT_HANDLER_FACTORY);
   }

   /**
    * Sets the event handler factory used by this instance of Jiffie.
    *
    * @param factory event handler factory
    * @return returns the previous event handler factory instance
    */
   public static EventHandlerFactory setEventHandlerFactory (EventHandlerFactory factory)
   {
      EventHandlerFactory old = EVENT_HANDLER_FACTORY;
      EVENT_HANDLER_FACTORY = factory;
      return (old);
   }
   
   /**
    * Retrieve the element factory used by this instance of Jiffie.
    *
    * @return element factory
    */
   public static ElementFactory getElementFactory ()
   {
      return (ELEMENT_FACTORY);
   }

   /**
    * Sets the element factory used by this instance of Jiffie.
    *
    * @param factory element factory
    * @return returns the previous element factory instance
    */
   public static ElementFactory setElementFactory (ElementFactory factory)
   {
      ElementFactory old = ELEMENT_FACTORY;
      ELEMENT_FACTORY = factory;
      return (old);
   }

   /**
    * Executes the jsCode in the context of m_explorer.
    * <p>
    * It does this by creating the following html in the body of the document:
    *
    * <xmp><script>
    * function jiffieUnitExecuteScript()
    * {
    *     var code = document.getElementById("jiffie_jscode").value;
    *     var d=document.getElementById("jiffie_result");
    *     d.retVal = eval(code);
    * }
    * document.getElementById("jiffie_result").onclick=jiffieUnitExecuteScript;");
    * </script>
    *
    * <input id="jiffie.jscode" type="text"/>
    * <DIV id="jiffie.retVal"/>
    *
    * </xmp>
    *
    * The value of the input box is set with the jsCode, and executed by
    * triggering an onclick event on the div. The return value is then obtained
    * from the div element and returned.
    *
    * @param doc target document for javascript execution
    * @param jsCode Javascript code to be executed.
    * @return The object (dispatch if it is a dom element) returned by the
    *         javascript.
    */
   public static Variant executeScript (IHTMLDocument2 doc, String jsCode)
      throws JiffieException
   {
      IHTMLElement divElm;
      IHTMLInputElement inputElm;
      
      //
      // If the required elements don't exist in the document
      // we create them here, otherwise we can retrieve them by name.
      //
      if (doc.getElementById(ELM_RESULT_ID) == null)
      {
         IHTMLBodyElement body = doc.getBody();
         
         //
         // Create the div
         //
         divElm = doc.createElement("div");
         divElm.setID(ELM_RESULT_ID);
         body.appendChild(divElm);

         //
         // Create the script block
         //
         IHTMLScriptElement scriptElm = (IHTMLScriptElement)doc.createElement("script");
         scriptElm.setText("function jiffieUnitExecuteScript() {var code = document.getElementById('" + ELM_JSCODE_ID + "').value; var d=document.getElementById('" + ELM_RESULT_ID + "'); d.retVal = eval(code);} document.getElementById('" + ELM_RESULT_ID + "').onclick=jiffieUnitExecuteScript;");
         body.appendChild(scriptElm);

         //
         // Create the input
         //
         inputElm = (IHTMLInputElement)doc.createElement("input");
         inputElm.setID(ELM_JSCODE_ID);
         inputElm.setType("text");
         body.appendChild(inputElm);
         body.release();
      }
      else
      {
         divElm = (IHTMLElement)doc.getElementById(ELM_RESULT_ID);
         inputElm = (IHTMLInputElement)doc.getElementById(ELM_JSCODE_ID);
      }

      //
      // Execute the script
      //
      inputElm.setValue(jsCode);
      divElm.click(true);

      //
      // Retrieve the result
      //
      Variant retVal = null;
      retVal = divElm.getVariantProperty("retVal");
      inputElm.release();
      divElm.release();
      
      return (retVal);
   }

   /**
    * ID used to identify the input text box used to hold JavaScript code for 
    * execution by the executeScript method.
    */
   private static final String ELM_JSCODE_ID = "jiffie_jscode";

   /**
    * ID used to identify the div element used to hold the return value from 
    * the evaluated JavaScript expression.
    */
   private static final String ELM_RESULT_ID = "jiffie_result";
   
   /**
    * Default poll time
    */
   private static int POLL_TIME = 500;
   
   /**
    * Default poll count
    */
   private static int MAX_POLL_COUNT = 60;

   /**
    * Event handling enabled
    */
   private static boolean EVENT_HANDLING_ENABLED = true;
   
   /**
    * Default element factory
    */
   private static ElementFactory ELEMENT_FACTORY = new DefaultElementFactory();
   
   /**
    * Default event handler factory
    */
   private static EventHandlerFactory EVENT_HANDLER_FACTORY = new FullEventHandlerFactory();
}
