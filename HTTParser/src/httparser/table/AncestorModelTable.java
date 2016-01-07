package httparser.table;

import javax.swing.table.DefaultTableModel;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class AncestorModelTable extends DefaultTableModel {

	private static final long serialVersionUID = 7493037510327007132L;

	protected String[] columnsName = new String[] {""};
  protected int[] columnsSize = new int[] {1};
  protected boolean[] columnsBlock = {false};

  protected Vector dataVector;

  public AncestorModelTable() {
    dataVector = new Vector();
  }
  public int getRowCount() {
    return getData()!=null ? getData().toArray().length : 0 ;
  }
  public String getColumnName(int column) {
    return getColumnsName()!=null ? getColumnsName()[column] : "" ;
  }
  public void setColumnName(String[] column) {
    this.columnsName = column;
  }
  public int getColumnCount()
  {
    return getColumnsName()!=null ? getColumnsName().length : 0 ;
  }
  public Object getValueAt(int row, int col)
  {
    return ( (getData()!=null) && (getData().toArray().length!=0) ) ? ((Object[])getData().get(row))[col].toString() : "";
  }
  public void add(Object[] data)
  {
    if (getData()!=null) getData().addElement(data);
// Surement pour le jdk 1.3
//    this.setRowCount(this.getRowCount()+1);
//    this.addRow(data);

  }
  public void del(int index)
  {
    if (getData()!=null) getData().remove(index);
// Surement pour le jdk 1.3
//    this.removeRow(index);
  }
  public void modify(int index, Object[] data)
  {
    if (getData()!=null) getData().setElementAt(data, index);
/*
    this.removeRow(index);
    this.insertRow(index, data);
*/
  }
  public void move(int indexSrc, int indexDst)
  {
/*
  	Vector data = (Vector)this.getDataVector().elementAt(indexSrc);

    if ( data != null ) {
      this.removeRow(indexSrc);
      this.insertRow(indexDst, data);
    }
*/
    if (getData()!=null) {
      Object[] item = (Object[])getData().remove(indexSrc);
      getData().insertElementAt(item, indexDst);
    }
  }
  public synchronized void clearAll()
  {
    int numrows = this.getRowCount();
    for(int i = numrows - 1; i >=0; i--)
      this.del(i);
  }
  public int getColumnSize(int index)
  {
    int ret = 0;
    if ( (getColumnsSize()!=null) && (index>=0) && (index<getColumnsSize().length) )
        ret = getColumnsSize()[index];
    return ret;
  }
  public boolean getColumnBlock(int index)
  {
    boolean ret = false;
    if ( (getColumnsBlock()!=null) && (index>=0) && (index<getColumnsBlock().length) )
        ret = getColumnsBlock()[index];
    return ret;
  }
  public void setColumnSize(int[] columnsSize)
  {
    this.columnsSize = columnsSize;
  }
  public void setValueAt(Object aValue, int row, int column)
  {
    if (getData()!=null) ((Object[])getData().get(row))[column] = aValue;
  }
  public String[] getColumnsName()
  {
    return this.columnsName;
  }
  public int[] getColumnsSize()
  {
    return this.columnsSize;
  }
  public boolean[] getColumnsBlock()
  {
    return this.columnsBlock;
  }
  public Vector getData()
  {
    return dataVector;
  }
  public void setData(Vector dataVector)
  {
    this.dataVector = dataVector;
  }
}