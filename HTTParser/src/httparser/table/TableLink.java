package httparser.table;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class TableLink extends AncestorTableList {

	private static final long serialVersionUID = 3451856125486688090L;

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