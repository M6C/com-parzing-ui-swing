package httparser.table;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class TableLinkInclude extends AncestorTableListSerial {

  protected String     szFileName = "LinkInclude.sav";

  public TableLinkInclude() {
    setDataModel(new ModelTableLinkInclude());
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
  }
  public String getFileName()
  {
    return szFileName;
  }
  public void setFileName(String szFileName) {
    this.szFileName = szFileName;
  }
}