package httparser.table;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ModelTableLinkExclude extends AncestorModelTable
{
	private static final long serialVersionUID = 320625046372487628L;

	protected String[] columnsName = new String[]{" ", "Link Excluded"};
  protected int[] columnsSize = new int[]{10, 200};
  protected boolean[] columnsBlock = {true, false};

  public ModelTableLinkExclude()
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