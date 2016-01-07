package httparser.table;

import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class AncestorTableListSerial extends AncestorTableList {

	private static final long serialVersionUID = -4033617682586353641L;

	protected String     szFileName = "";

  public AncestorTableListSerial()
  {
  }

  protected void jbInit() throws Exception
  {
    readMessageList();
    super.jbInit();
  }

  public void saveAskDestination()
  {
    JFileChooser fc = new JFileChooser();
    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    fc.setSelectedFile(new File(this.getFileName()));
    int returnVal = fc.showSaveDialog(this);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = fc.getSelectedFile();
      String strFileName = file.getAbsolutePath();
      this.setFileName(strFileName);
      this.saveMessageList();
    }
  }

  public void readAskSource()
  {
    JFileChooser fc = new JFileChooser();
    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    fc.setSelectedFile(new File(this.getFileName()));
    int returnVal = fc.showOpenDialog(this);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = fc.getSelectedFile();
      String strFileName = file.getAbsolutePath();
      this.setFileName(strFileName);
      this.readMessageList();
      Show();
    }
  }

/**
 * <p>Serialize la liste des Messages dans un Fichier sur le Disk</p>
 */
  public void saveMessageList()
  {
    try
    {
      FileOutputStream fOutStream = new FileOutputStream(getFileName());
      ObjectOutputStream oOutStream = new ObjectOutputStream(fOutStream);
      oOutStream.writeObject(getMessageList());
      oOutStream.flush();
      oOutStream.close();
    }
    catch (IOException ex)
    {
      ex.printStackTrace();
    }
  }

/**
 * <p> Lecture de la liste des Messages dans un Fichier sur le Disk</p>
 */
  public void readMessageList()
  {
    try
    {
      FileInputStream fInStream = new FileInputStream(getFileName());
      ObjectInputStream oOInStream = new ObjectInputStream(fInStream);
      Vector msgList = (Vector)oOInStream.readObject();
      if ( msgList == null )
        msgList = new Vector();
      setMessageList(msgList);
      oOInStream.close();
    }
    catch (FileNotFoundException ex)
    {
      return;
    }
    catch (ClassNotFoundException ex)
    {
      ex.printStackTrace();
    }
    catch (IOException ex)
    {
      ex.printStackTrace();
    }
  }
  public void Show()
  {
    if (( getMessageList() != null ) && ( getDataModel() != null ))
    {
      getDataModel().clearAll();
      for (Iterator i = getMessageList().iterator(); i.hasNext(); )
      {
        AncestorItem item = (AncestorItem)i.next();
        this.add(item, false);
      }
    }
  }
  public void add(AncestorItem item)
  {
    add(item, true);
  }
  public void add(AncestorItem item, boolean bSave)
  {
    if ( bSave )
    {
      super.add(item);
      saveMessageList();
    }
    else
    {
      super.add(item.getData());
    }
  }
  public void modify(int index, AncestorItem item)
  {
    super.modify(index, item);
    saveMessageList();
  }
  public void moveRow(int indexSrc, int indexDst)
  {
    super.moveRow(indexSrc, indexDst);
    saveMessageList();
  }
  public void del(int index)
  {
    super.del(index);
    saveMessageList();
  }
  public String getFileName()
  {
    return szFileName;
  }
  public void setFileName(String szFileName) {
    this.szFileName = szFileName;
  }
}