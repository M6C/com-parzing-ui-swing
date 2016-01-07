package httparser.table;

import httparser.frame.FrmMain;

import java.awt.Container;
import java.awt.event.MouseEvent;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class TableDwnld extends AncestorTableListSerial {

	private static final long serialVersionUID = -4915699945966191306L;

	protected String     szFileName = "Script.sav";

  public TableDwnld()
  {
    setDataModel(new ModelTableDwnld());
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  protected void jbInit() throws Exception
  {
    super.jbInit();
    this.addMouseListener(new java.awt.event.MouseAdapter()
    {
      public void mouseClicked(MouseEvent e)
      {
        this_mouseClicked(e);
      }
    });
  }

  void this_mouseClicked(MouseEvent e)
  {
    if ( e.getClickCount()==1 )
    {
      int row = this.getSelectedRow();
      ItemTableDwnld item = (ItemTableDwnld)getMessageList().get(row);
      Container parent = (Container)this;
      while ( (parent!=null) && !(parent instanceof FrmMain) )
        parent = parent.getParent();
      if ( parent != null )
      {
        ((FrmMain)parent).setTxtName(item.getTextName());
        ((FrmMain)parent).setTxtTextStart(item.getTextStart());
        ((FrmMain)parent).setTxtTextEnd(item.getTextEnd());
        ((FrmMain)parent).setTxtPath(item.getTextPath());
        ((FrmMain)parent).setCboxType(item.getTextType());
        ((FrmMain)parent).enableBtnUpDown(row);
      }
    }
  }
  public String getFileName()
  {
    return szFileName;
  }
  public void setFileName(String szFileName) {
    this.szFileName = szFileName;
  }
}