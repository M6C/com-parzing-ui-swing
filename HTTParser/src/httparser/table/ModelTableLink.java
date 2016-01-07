package httparser.table;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ModelTableLink extends AncestorModelTable
{
	private static final long serialVersionUID = -9025819632629868725L;

	protected String[] columnsName = new String[]{" ", "Link"};
  protected int[] columnsSize = new int[]{10, 200};
  protected boolean[] columnsBlock = {true, false};

  public ModelTableLink()
  {
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception
  {
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
}