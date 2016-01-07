/*
 * file:       IHTMLCommentElement.java
 * author:     Piran Montford
 * copyright:  (c) Packwood Software Limited 2004
 * date:       01/12/2004
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


/**
 * Implementation of the IHTMLCommentElement interface.
 */
public class IHTMLCommentElement extends IHTMLDOMNode
{
   /**
    * Constructor.
    *
    * @param parentBrowser parent browser
    * @param dispatch Jacob dispatch interface
    */
   public IHTMLCommentElement (InternetExplorer parentBrowser, Dispatch dispatch)
   {
      super(parentBrowser, dispatch);
   }

   /**
    * Returns the comment text in a comment, including white space around the 
    * tag. For example,
    * <code>&lt;!--&nbsp;This&nbsp;is&nbsp;a&nbsp;comment&nbsp;--&gt;</code> 
    * would return
    * <code>"&nbsp;This&nbsp;is&nbsp;a&nbsp;comment&nbsp;"</code>.
    *
    * @see org.w3c.dom.CharacterData#getData()
    *
    * @return text inside the comment
    */
   public String getData ()
   {
      return getStringProperty("data");
   }

   /**
    * Returns the full text of the comment, including the tag parts of the 
    * comment. For example,
    * <code>&lt;!--&nbsp;This&nbsp;is&nbsp;a&nbsp;comment&nbsp;--&gt;</code>.
    *
    * @return full text of the comment
    */
   public String getText ()
   {
      return getStringProperty("text");
   }
}
