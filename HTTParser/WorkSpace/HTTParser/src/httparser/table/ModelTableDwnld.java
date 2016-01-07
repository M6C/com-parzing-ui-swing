package httparser.table;

import javax.swing.table.*;
import java.util.*;
import java.text.*;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ModelTableDwnld extends AncestorModelTable
{
  protected String[] columnsName = {"Name", "Start","End","Type","Path"};
  protected int[] columnsSize = {50, 200, 200, 50, 100};
  protected boolean[] columnsBlock = {false, false, false, true, false};

  public ModelTableDwnld()
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