package httparser.table;

import javax.swing.JTable;
import java.io.*;
import java.util.*;
import javax.swing.table.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class AncestorTable extends JTable {

  protected AncestorModelTable dataModel   = null;

  public AncestorTable() {
  }
  protected void jbInit() throws Exception
  {
    this.resizeColumn();
    Show();
  }
  public void Show()
  {
  }
  public void add(Object[] data)
  {
    if ( getDataModel() != null ) getDataModel().add(data);
  }
  public void del(int index)
  {
    if ( getDataModel() != null ) getDataModel().del(index);
    this.Show();
  }
  public void moveRow(int indexSrc, int indexDst)
  {
    if ( getDataModel() != null ) getDataModel().move(indexSrc, indexDst);
    this.Show();
  }
  public void modify(int index, Object[] msg)
  {
    if ( getDataModel() != null ) getDataModel().modify(index, msg);
    this.Show();
  }
  public boolean isCellEditable(int row, int column)
  {
    return false;
  }
  public AncestorModelTable getDataModel()
  {
    return dataModel;
  }
  public void setDataModel(AncestorModelTable dataModel)
  {
    this.dataModel = dataModel;
    this.setModel(dataModel);
  }
  protected void resizeColumn()
  {
    for (int i = 0; i < this.getModel().getColumnCount(); i++) {
      setColumnSize(getColumnModel().getColumn(i), getDataModel().getColumnSize(i), getDataModel().getColumnBlock(i));
    }
  }
  protected void setColumnSize(TableColumn column, int width, boolean isBloked)
  {
    column.setWidth(width);
    column.setPreferredWidth(width);
    column.setMinWidth(width);
    if( isBloked )
    {
      column.setMaxWidth(width);
    }
  }
  public void setColumnSize(int[] columnsSize)
  {
    this.getDataModel().setColumnSize( columnsSize );
  }
  public void setColumnName(String[] columnsName)
  {
    this.getDataModel().setColumnName( columnsName );
  }
}