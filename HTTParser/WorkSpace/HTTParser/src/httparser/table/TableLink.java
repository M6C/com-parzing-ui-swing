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

public class TableLink extends AncestorTableList {

  public TableLink()
  {
    setDataModel(new ModelTableLink());
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
    this.resizeColumn();
  }
}