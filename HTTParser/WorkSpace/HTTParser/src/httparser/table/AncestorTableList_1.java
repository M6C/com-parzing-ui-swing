package httparser.table;

import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class AncestorTableList extends AncestorTable {

  protected Vector     messageList = new Vector();

  public AncestorTableList() {
  }
  public Vector getMessageList()
  {
    return messageList;
  }
  public void setMessageList(Vector messageList)
  {
    this.messageList = messageList;
  }
  public void add(AncestorItem item)
  {
    String[] data = item.getData();
    if ( getMessageList()!=null )
      getMessageList().add(item);
    super.add(data);
  }
  public void modify(int index, AncestorItem item)
  {
    String[] data = item.getData();
    if ( getMessageList() != null )
      getMessageList().setElementAt(item, index);
    super.modify(index, data);
  }
  public void del(int index)
  {
    if ( (getMessageList()!=null) && (index<getMessageList().size()) )
      getMessageList().remove(index);
    super.del(index);
  }
  public void moveRow(int indexSrc, int indexDst)
  {
    if ( (getMessageList()!=null) && (indexSrc<getMessageList().size()) )
    {
      AncestorItem item = (AncestorItem)getMessageList().remove(indexSrc);
      getMessageList().insertElementAt(item, indexDst);
    }
    super.moveRow(indexSrc, indexDst);
  }
  public void Show()
  {
    if (( getMessageList() != null ) && ( getDataModel() != null ))
    {
      getDataModel().clearAll();
      for (Iterator i = getMessageList().iterator(); i.hasNext(); )
      {
        AncestorItem item = (AncestorItem)i.next();
        this.add(item);
      }
    }
  }
}