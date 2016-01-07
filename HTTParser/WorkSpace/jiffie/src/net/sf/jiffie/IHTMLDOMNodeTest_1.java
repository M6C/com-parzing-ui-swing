/*
 * file:       IHTMLDOMNodeTest.java
 * author:     Jon Iles
 * copyright:  (c) Packwood Software Limited 2005
 * date:       12/01/2005
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
 * Tests to exercise the functionality implemented in the IHTMLDOMNodeTest class.
 */
public class IHTMLDOMNodeTest extends JiffieDataDirTest
{
   /**
    * Test elements and properties related to the DOM model.
    * Note that the DOM elements are only supported in IE 6.
    *
    * @throws Exception
    */
   public void testDOMElements ()
      throws Exception
   {
      //
      // open the xml data test
      //
      m_explorer.navigate(m_datadir + "/xml.htm", true);

      IHTMLDocument2 doc = m_explorer.getDocument(true);
      assertEquals("XML Test", doc.getTitle());

      //
      // DOM properties of a document
      //
      assertEquals("Document's parent is null", null, doc.getParentNode());

      //
      // get an element's attribute collection
      //
      IHTMLAttributeCollection attributes = ((IHTMLDOMNode)doc.getElementById("text")).getAttributes();
      assertTrue("Attrib collection is not empty", attributes.getLength() != 0);

      //
      // get a single attribute
      //
      IHTMLDOMAttribute attr = attributes.item(0);
      assertEquals("Attribute's parent is null", null, attr.getParentNode());
      assertEquals("Attribute's owner is the original element (same id)", "text", attr.getOwnerElement().getStringProperty("id"));
      assertEquals("Attribute's node name is the attribute name", "language", attr.getNodeName());
      assertEquals("Attribute's node value is the attribute value", "", attr.getNodeValue());

      //
      // try getting the document's attribute collection (can have no attributes)
      //
      IHTMLAttributeCollection docAttributes = doc.getAttributes();
      assertEquals("Attrib collection is empty", 0, docAttributes.getLength());

      //
      // get a text element
      //
      IHTMLElement textContainer = (IHTMLElement)doc.getElementById("text");
      IHTMLDOMTextNode textNode = (IHTMLDOMTextNode)textContainer.getFirstChild();
      assertEquals("Text node's data", "This is ", textNode.getData());
      assertEquals("Text node's node name is #text", "#text", textNode.getNodeName());
      assertEquals("Text node's node value is the text", "This is ", textNode.getNodeValue());

      //
      // test a comment
      //
      IHTMLDOMNode commentContainer = (IHTMLDOMNode)doc.getElementById("comment");
      IHTMLCommentElement comment = (IHTMLCommentElement)commentContainer.getFirstChild();
      assertEquals("Comment's data", " This is a comment ", comment.getData());
      assertEquals("Comment's text", "<!-- This is a comment -->", comment.getText());
      assertEquals("Comment's node name is #comment", "#comment", comment.getNodeName());
      assertEquals("Comment's node value is the comment", " This is a comment ", comment.getNodeValue());
   }
}
