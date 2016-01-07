/*
 * file:       DocumentNavigator.java
 * author:     Piran Montford
 * copyright:  (c) Packwood Software Limited 2004
 * date:       1/12/2004
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

package net.sf.jiffie.xpath;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import net.sf.jiffie.IHTMLAttributeCollection;
import net.sf.jiffie.IHTMLCommentElement;
import net.sf.jiffie.IHTMLDOMAttribute;
import net.sf.jiffie.IHTMLDOMNode;
import net.sf.jiffie.IHTMLDOMTextNode;
import net.sf.jiffie.IHTMLDocument2;
import net.sf.jiffie.IHTMLElement;
import net.sf.jiffie.JiffieException;

import org.jaxen.DefaultNavigator;
import org.jaxen.FunctionCallException;
import org.jaxen.JaxenException;
import org.jaxen.XPath;



/**
 * Interface for navigating around the Jiffie/Internet Explorer object model.
 *
 * <p>
 * This class is not intended for direct usage, but is used by the Jaxen engine 
 * during evaluation. Please see {@link JiffieXPath}.
 * </p>
 *
 * <p>
 * This class implements the {@link org.jaxen.DefaultNavigator}interface for 
 * the Jaxen XPath library, version 1.0-FCS (it is not guaranteed to work with 
 * subsequent releases). This adapter allows the Jaxen library to be used to 
 * execute XPath queries against any object tree from Internet Explorer, 
 * accessed by the Jiffie library.
 * </p>
 *
 * <p>
 * This implementation is based in part on the DOM2 DocumentNavigator, which is 
 * part of Jaxen.
 * </p>
 *
 * @see XPath
 */
public class DocumentNavigator extends DefaultNavigator
{   
   /**
    * Get a singleton DocumentNavigator for efficiency.
    *
    * @return A singleton instance of a DocumentNavigator.
    */   
   public static DocumentNavigator getInstance ()
   {
      return (SINGLETON);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Iterator<IHTMLDOMNode> getChildAxisIterator (Object contextNode)
   {
      return new NodeIterator((IHTMLDOMNode)contextNode)
      {
         @Override
         protected IHTMLDOMNode getFirstNode (IHTMLDOMNode node)
         {
            try
            {
               return node.getFirstChild();
            }
            catch (JiffieException e)
            {
               throw handleJiffieException(e);
            }
         }
   
         @Override
         protected IHTMLDOMNode getNextNode (IHTMLDOMNode node)
         {
            try
            {
               return node.getNextSibling();
            }
            catch (JiffieException e)
            {
               throw handleJiffieException(e);
            }
         }
      };
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Iterator<IHTMLDOMNode> getParentAxisIterator (Object contextNode)
   {
      IHTMLDOMNode node = (IHTMLDOMNode)contextNode;
      NodeIterator result;
      
      if (node instanceof IHTMLDOMAttribute)
      {
         result = new NodeIterator(node)
         {
            @Override
            protected IHTMLDOMNode getFirstNode (IHTMLDOMNode n)
            {
               return ((IHTMLDOMAttribute)n).getOwnerElement();
            }

            @Override
            protected IHTMLDOMNode getNextNode (IHTMLDOMNode n)
            {
               return null;
            }
         };
      }
      else
      {
         result = new NodeIterator(node)
         {
            @Override
            protected IHTMLDOMNode getFirstNode (IHTMLDOMNode n)
            {
               try
               {
                  return n.getParentNode();
               }
               catch (JiffieException e)
               {
                  throw handleJiffieException(e);
               }
            }

            @Override
            protected IHTMLDOMNode getNextNode (IHTMLDOMNode n)
            {
               return null;
            }
         };
      }
      
      return (result);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Iterator<IHTMLDOMNode> getFollowingSiblingAxisIterator (Object contextNode)
   {
      return new NodeIterator((IHTMLDOMNode)contextNode)
      {
         @Override
         protected IHTMLDOMNode getFirstNode (IHTMLDOMNode node)
         {
            return getNextNode(node);
         }

         @Override
         protected IHTMLDOMNode getNextNode (IHTMLDOMNode node)
         {
            try
            {
               return node.getNextSibling();
            }
            catch (JiffieException e)
            {
               throw handleJiffieException(e);
            }
         }
      };
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Iterator<IHTMLDOMNode> getPrecedingSiblingAxisIterator (Object contextNode)
   {
      return new NodeIterator((IHTMLDOMNode)contextNode)
      {
         @Override
         protected IHTMLDOMNode getFirstNode (IHTMLDOMNode node)
         {
            return getNextNode(node);
         }

         @Override
         protected IHTMLDOMNode getNextNode (IHTMLDOMNode node)
         {
            try
            {
               return node.getPreviousSibling();
            }
            catch (JiffieException e)
            {
               throw handleJiffieException(e);
            }
         }
      };
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Iterator<IHTMLDOMNode> getFollowingAxisIterator (Object contextNode)
   {
      return new NodeIterator((IHTMLDOMNode)contextNode)
      {
         @Override
         protected IHTMLDOMNode getFirstNode (IHTMLDOMNode node)
         {
            try
            {
               IHTMLDOMNode result = null;
               if (node != null)
               {
                  IHTMLDOMNode sibling = node.getNextSibling();

                  if (sibling == null)
                  {
                     result = getFirstNode(node.getParentNode());
                  }
                  else
                  {
                     result = sibling;
                  }
               }
               return (result);
            }
            catch (JiffieException e)
            {
               throw handleJiffieException(e);
            }
         }

         @Override
         protected IHTMLDOMNode getNextNode (IHTMLDOMNode node)
         {
            try
            {
               IHTMLDOMNode result = null;
               
               if (node != null)
               {
                  result = node.getFirstChild();

                  if (result == null)
                  {
                     result = node.getNextSibling();
                  }

                  if (result == null)
                  {
                     result = getFirstNode(node.getParentNode());
                  }
               }
               
               return (result);
            }
            catch (JiffieException e)
            {
               throw handleJiffieException(e);
            }
         }
      };
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Iterator<IHTMLDOMNode> getPrecedingAxisIterator (Object contextNode)
   {
      return new NodeIterator((IHTMLDOMNode)contextNode)
      {
         @Override
         protected IHTMLDOMNode getFirstNode (IHTMLDOMNode node)
         {
            try
            {
               IHTMLDOMNode result = null;
               
               if (node != null)
               {
                  result = node.getPreviousSibling();

                  if (result == null)
                  {
                     result = getFirstNode(node.getParentNode());
                  }
               }
               
               return (result);
            }
            catch (JiffieException e)
            {
               throw handleJiffieException(e);
            }
         }

         @Override
         protected IHTMLDOMNode getNextNode (IHTMLDOMNode node)
         {
            try
            {
               IHTMLDOMNode result = null;
               if (node != null)
               {
                  result = node.getLastChild();

                  if (result == null)
                  {
                     result = node.getPreviousSibling();
                  }

                  if (result == null)
                  {
                     result = getFirstNode(node.getParentNode());
                  }
               }
               
               return (result);
            }
            catch (JiffieException e)
            {
               throw handleJiffieException(e);
            }
         }
      };
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Iterator<IHTMLDOMNode> getAttributeAxisIterator (Object contextNode)
   {
      Iterator<IHTMLDOMNode> result;
      
      if (isElement(contextNode))
      {
         result = new AttributeIterator((IHTMLDOMNode)contextNode);
      }
      else
      {
         result = EMPTY_ITERATOR;
      }
      
      return result;
   }

   /**
    * {@inheritDoc}
    *
    * <p>
    * Note: this iterator is not live: it takes a snapshot and that snapshot
    * remains static during the life of the iterator (i.e. it won't reflect
    * subsequent changes to the DOM).
    * </p>
    */
   @Override
   public Iterator<IHTMLDOMNode> getNamespaceAxisIterator (Object contextNode)
   {
      return (EMPTY_ITERATOR);
   }

   /**
    * {@inheritDoc}
    */
   public XPath parseXPath (String xpath)
      throws JaxenException
   {
      return new JiffieXPath(xpath);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Object getDocumentNode (Object contextNode)
   {
      Object result;
      
      if (isDocument(contextNode))
      {
         result = contextNode;
      }
      else
      {
         try
         {
            result = ((IHTMLDOMNode)contextNode).getOwnerDocument();
         }
         catch (JiffieException e)
         {
            throw handleJiffieException(e);
         }
      }
      
      return (result);
   }

   /**
    * {@inheritDoc}
    */
   public String getElementNamespaceUri (Object object)
   {
      return ("");
   }

   /**
    * {@inheritDoc}
    */
   public String getElementName (Object object)
   {
      return (((IHTMLElement)object).getTagName());
   }

   /**
    * {@inheritDoc}
    */
   public String getElementQName (Object object)
   {
      return (null);
   }

   /**
    * {@inheritDoc}
    */
   public String getAttributeNamespaceUri (Object object)
   {
      return (null);
   }

   /**
    * {@inheritDoc}
    */
   public String getAttributeName (Object object)
   {
      return (((IHTMLDOMAttribute)object).getName());
   }

   /**
    * {@inheritDoc}
    */
   public String getAttributeQName (Object object)
   {
      return (null);
   }

   /**
    * {@inheritDoc}
    */
   public boolean isDocument (Object object)
   {
      return (object instanceof IHTMLDocument2);
   }

   /**
    * {@inheritDoc}
    */
   public boolean isNamespace (Object object)
   {
      return (false);
   }

   /**
    * {@inheritDoc}
    */
   public boolean isElement (Object object)
   {
      return (object instanceof IHTMLElement);
   }

   /**
    * {@inheritDoc}
    */
   public boolean isAttribute (Object object)
   {
      return (object instanceof IHTMLDOMAttribute);
   }

   /**
    * {@inheritDoc}
    */
   public boolean isComment (Object object)
   {
      return (object instanceof IHTMLCommentElement);
   }

   /**
    * {@inheritDoc}
    */
   public boolean isText (Object object)
   {
      return (object instanceof IHTMLDOMTextNode);
   }

   /**
    * {@inheritDoc}
    */
   public boolean isProcessingInstruction (Object object)
   {
      return (false);
   }

   /**
    * {@inheritDoc}
    */
   public String getElementStringValue (Object object)
   {
      String result = null;
      
      if (isElement(object))
      {
         result = (((IHTMLElement)object).getInnerText());
      }
      
      return (result);
   }

   /**
    * {@inheritDoc}
    */
   public String getAttributeStringValue (Object object)
   {
      String result = null;
      
      if (isAttribute(object))
      {
         result = (((IHTMLDOMAttribute)object).getValue());
      }
      
      return (result);
   }

   /**
    * {@inheritDoc}
    */
   public String getTextStringValue (Object object)
   {
      String result = null;
      
      if (isText(object))
      {
         result = (((IHTMLDOMTextNode)object).getData());
      }
      
      return (result);
   }

   /**
    * {@inheritDoc}
    */
   public String getCommentStringValue (Object object)
   {
      String result = null;
      
      if (isComment(object))
      {
         result = (((IHTMLCommentElement)object).getData());
      }
      
      return (result);
   }

   /**
    * Get the string value of a Namespace node.
    *
    * @param object The target node.
    * @return The Namespace URI as a (possibly empty) string if the node is a 
    *         namespace node, null otherwise.
    */
   public String getNamespaceStringValue (Object object)
   {
      return null;
   }

   /**
    * {@inheritDoc}
    */
   public String getNamespacePrefix (Object object)
   {
      return (null);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String translateNamespacePrefixToUri (String prefix, Object element)
   {
      return (null);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Object getDocument (String uri)
      throws FunctionCallException
   {
      throw new FunctionCallException("Failed to parse document for URI: " + uri);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String getProcessingInstructionTarget (Object obj)
   {
      return (null);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String getProcessingInstructionData (Object obj)
   {
      return (null);
   }

   /**
    * A generic iterator over DOM nodes.
    *
    * <p>
    * Concrete subclasses must implement the {@link #getFirstNode} and
    * {@link #getNextNode} methods for a specific iteration strategy.
    * </p>
    */
   abstract class NodeIterator implements Iterator<IHTMLDOMNode>
   {
      /**
       * Constructor.
       *
       * @param contextNode The starting node.
       */
      public NodeIterator (IHTMLDOMNode contextNode)
      {
         m_node = getFirstNode(contextNode);

         while (!isXPathNode(m_node))
         {
            m_node = getNextNode(m_node);
         }
      }

      /**
       * {@inheritDoc}
       */
      public boolean hasNext ()
      {
         return (m_node != null);
      }

      /**
       * {@inheritDoc}
       */
      public IHTMLDOMNode next ()
      {
         if (m_node == null)
         {
            throw new NoSuchElementException();
         }

         IHTMLDOMNode ret = m_node;
         m_node = getNextNode(m_node);

         while (!isXPathNode(m_node))
         {
            m_node = getNextNode(m_node);
         }

         return ret;
      }

      /**
       * {@inheritDoc}
       */
      public void remove ()
      {
         throw new UnsupportedOperationException();
      }

      /**
       * Get the first node for iteration.
       *
       * <p>
       * This method must derive an initial node for iteration from a context node.
       * </p>
       *
       * @param contextNode The starting node.
       * @return The first node in the iteration.
       * @see #getNextNode
       */
      protected abstract IHTMLDOMNode getFirstNode (IHTMLDOMNode contextNode);

      /**
       * Get the next node for iteration.
       *
       * <p>
       * This method must locate a following node from the current context node.
       * </p>
       *
       * @param contextNode The current node in the iteration.
       * @return The following node in the iteration, or null if there is none.
       * @see #getFirstNode
       */
      protected abstract IHTMLDOMNode getNextNode (IHTMLDOMNode contextNode);

      /**
       * Test whether a DOM node is usable by XPath.
       *
       * @param node The DOM node to test.
       * @return true if the node is usable, false if it should be skipped.
       */
      private boolean isXPathNode (IHTMLDOMNode node)
      {
         return (!(node instanceof IHTMLDocument2));
      }

      private IHTMLDOMNode m_node;
   }

   ////////////////////////////////////////////////////////////////////
   // Inner class: iterate over a DOM named node map.
   ////////////////////////////////////////////////////////////////////

   /**
    * An iterator over an attribute list.
    */
   class AttributeIterator implements Iterator<IHTMLDOMNode>
   {
      /**
       * Constructor.
       *
       * @param parent The parent DOM element for the attributes.
       */
      AttributeIterator (IHTMLDOMNode parent)
      {
         this.m_map = parent.getAttributes();
         this.m_pos = 0;
      }

      /**
       * {@inheritDoc}
       */
      public boolean hasNext ()
      {
         return m_pos < m_map.getLength();
      }

      /**
       * {@inheritDoc}
       */
      public IHTMLDOMNode next ()
      {
         IHTMLDOMNode attr = m_map.item(m_pos++);

         if (attr == null)
         {
            throw new NoSuchElementException();
         }
         
         return attr;
      }

      /**
       * @see Iterator#remove
       */
      public void remove ()
      {
         throw new UnsupportedOperationException();
      }

      private IHTMLAttributeCollection m_map;
      private int m_pos;
   }

   /**
    * Returns the element whose ID is given by elementId. If no such element 
    * exists, returns null. Attributes with the name "ID" are not of type ID 
    * unless so defined. Atribute types are only known if when the parser 
    * understands DTD's or schemas that declare attributes of type ID. When JAXP 
    * is used, you must call <code>setValidating(true)</code> on the
    * DocumentBuilderFactory.
    *
    * @param object a node from the document in which to look for the id
    * @param elementId id to look for
    *
    * @return element whose ID is given by elementId, or null if no such element 
    *         exists in the document or if the implementation does not know 
    *         about attribute types
    * @see javax.xml.parsers.DocumentBuilderFactory
    */
   @Override
   public Object getElementById (Object object, String elementId)
   {
      Object result = null;
      IHTMLDocument2 doc = (IHTMLDocument2)getDocumentNode(object);

      if (doc != null)
      {
         try
         {
            result = doc.getElementById(elementId);
         }
         catch (JiffieException e)
         {
            throw handleJiffieException(e);
         }
      }
      
      return (result);
   }

   /**
    * Handle a Jiffie Exception, wrapping in an runtime exception so as 
    * not to change the interface.
    *
    * @param je The JiffieExcetpion
    * @return a runtime exception
    */
   protected UnsupportedOperationException handleJiffieException (JiffieException je)
   {
      return new UnsupportedOperationException("JiffieException: " + je.getMessage());
   }

   /**
    * Constant: empty iterator.
    */
   private static final Iterator<IHTMLDOMNode> EMPTY_ITERATOR = new LinkedList<IHTMLDOMNode>().iterator();

   /**
    * Constant: singleton navigator.
    */
   private static final DocumentNavigator SINGLETON = new DocumentNavigator();
}
