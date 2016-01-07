/*
 * file:       DefaultElementFactory.java
 * author:     Jon Iles
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

import java.lang.reflect.Constructor;
import java.util.HashMap;
import com.jacob.com.ComException;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;


/**
 * This is the default implementation of the ElementFactory interface.
 */
public class DefaultElementFactory implements ElementFactory
{
   /**
    * {@inheritDoc}
    */
   public IDispatch createElement (InternetExplorer parentBrowser, Dispatch dispatch)
      throws JiffieException
   {
      try
      {
         Dispatch elementDispath = safeQueryInterface(dispatch, IHTMLElement.IID);

         if (elementDispath != null)
         {
            Variant varElement = Dispatch.get(elementDispath, "tagName");
            String elementTag;

            if (varElement != null)
            {
               elementTag = varElement.toString().toUpperCase();
            }
            else
            {
               elementTag = null;
            }

            return (createElementFromTag(elementTag, parentBrowser, elementDispath));
         }

         Dispatch textDispatch = safeQueryInterface(dispatch, IHTMLDOMTextNode.IID);

         if (textDispatch != null)
         {
            return new IHTMLDOMTextNode(parentBrowser, textDispatch);
         }

         Dispatch docDispatch = safeQueryInterface(dispatch, IHTMLDocument2.IID);

         if (docDispatch != null)
         {
            return new IHTMLDocument2(parentBrowser, docDispatch);
         }

         throw new JiffieException("Failed to find type of dispatch item");
      }
      catch (ComException ce)
      {
         throw new JiffieException("Failed to create jiffie dispatch", ce);
      }
      finally
      {
         dispatch.safeRelease();
      }
   }

   /**
    * This method is provided as a hook to allow classes derived from the 
    * DefaultElementFactory class to override the default behaviour on a 
    * tag-by-tag basis if required.
    *
    * @param elementTag element tag name, may be null
    * @param parentBrowser parent browser instance
    * @param dispatch object dispatch interface
    * @return HTML element object
    * @throws JiffieException if an error occurs creating a element
    */
   protected IDispatch createElementFromTag (String elementTag, InternetExplorer parentBrowser, Dispatch dispatch)
      throws JiffieException
   {
      Class<? extends IDispatch> elementClass = ELEMENT_MAP.get(elementTag);

      if (elementClass == null)
      {
         elementClass = IHTMLElement.class;
      }

      IDispatch element;

      try
      {
         Constructor<? extends IDispatch> elementConstructor = elementClass.getConstructor(ELEMENT_CTOR);
         element = elementConstructor.newInstance(new Object[] {parentBrowser, dispatch});
      }

      catch (Exception ex)
      {
         throw new JiffieException("Failed to create instance of " + elementClass, ex);
      }

      return (element);
   }

   /**
    * {@inheritDoc}
    */
   public IDispatch createElement (InternetExplorer parentBrowser, Variant dispatch)
      throws JiffieException
   {
      IDispatch result;

      if ((dispatch == null) || (dispatch.isNull() == true))
      {
         result = null;
      }
      else
      {
         result = createElement(parentBrowser, dispatch.toDispatch());
      }

      return (result);
   }

   /**
    * {@inheritDoc}
    */
   public ElementList createElementList (InternetExplorer parentBrowser, Variant variant)
      throws JiffieException
   {
      ElementList result;

      if ((variant == null) || variant.isNull())
      {
         result = EmptyElementList.LIST;
      }
      else
      {
         Dispatch dispatch = variant.toDispatch();

         try
         {
            Dispatch multipleElementListDispatch = safeQueryInterface(dispatch, IHTMLElementCollection.IID);

            if (multipleElementListDispatch != null)
            {
               result = new MultipleElementList(parentBrowser, multipleElementListDispatch);
            }
            else
            {
               IDispatch element = createElement(parentBrowser, dispatch);
               result = new SingleElementList(element);
            }
         }

         catch (ComException ex)
         {
            throw new JiffieException("Couldn't create list from " + dispatch, ex);
         }
      }

      return (result);
   }

   /**
    * A version of {@link Dispatch#QueryInterface(java.lang.String)}that will 
    * return null if the interface is not implemented, rather than throwing 
    * an exception.
    *
    * @param dispatch The dispatch to cast to the new interface.
    * @param iid The id of the new interface to cast to.
    * @return A new dispatch object (that will need to be released), or null 
    *         if the dispatch item could not be coerced to the new interface.
    * @throws ComException Thrown in case of COM errors.
    */
   private Dispatch safeQueryInterface (Dispatch dispatch, String iid)
      throws ComException
   {
      try
      {
         return dispatch.QueryInterface(iid);
      }
      
      catch (ComException ce)
      {
         if (ce.getHResult() == E_NOINTERFACE)
         {
            return null;
         }
         
         throw ce;
      }
   }

   private static final int E_NOINTERFACE = 0x80004002;
   
   @SuppressWarnings("unchecked")
   private static final Class[] ELEMENT_CTOR = {InternetExplorer.class, Dispatch.class};
   
   private static final HashMap<String, Class<? extends IDispatch>> ELEMENT_MAP = new HashMap<String, Class<? extends IDispatch>>();

   static
   {
         ELEMENT_MAP.put("A", IHTMLAnchorElement.class);
         ELEMENT_MAP.put("BODY", IHTMLBodyElement.class);
         ELEMENT_MAP.put("FORM", IHTMLFormElement.class);
         ELEMENT_MAP.put("FRAME", IHTMLFrameElement.class);
         ELEMENT_MAP.put("IFRAME", IHTMLIFrameElement.class);
         ELEMENT_MAP.put("IMG", IHTMLImgElement.class);
         ELEMENT_MAP.put("INPUT", IHTMLInputElement.class);
         ELEMENT_MAP.put("OPTION", IHTMLOptionElement.class);
         ELEMENT_MAP.put("SCRIPT", IHTMLScriptElement.class);      
         ELEMENT_MAP.put("SELECT", IHTMLSelectElement.class);
         ELEMENT_MAP.put("TABLE", IHTMLTable.class);
         ELEMENT_MAP.put("TEXTAREA", IHTMLTextAreaElement.class);
         ELEMENT_MAP.put("TR", IHTMLTableRow.class);
         ELEMENT_MAP.put("TD", IHTMLTableCell.class);
         ELEMENT_MAP.put("TH", IHTMLTableCell.class);
         ELEMENT_MAP.put("DIV", IHTMLDivElement.class);
         ELEMENT_MAP.put("HTML", IHTMLHtmlElement.class);
         ELEMENT_MAP.put("!", IHTMLCommentElement.class);
   }
}
