package httparser.XML;

import java.util.*;
import java.io.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class XMLDocument {

  protected String version = "\"1.0\"";
  protected StringBuffer document = new StringBuffer();
  protected String begin = "<body>\r\n";
  protected String end = "</body>\r\n";
  protected String extension = ".xml";

  public XMLDocument() {
    this.getDocument().append("<?xml version=");
    this.getDocument().append(getVersion());
    this.getDocument().append("?>\r\n");
    this.getDocument().append(begin);
  }
  public void close()
  {
    this.getDocument().append(end);
  }
  public void add(String szName, Vector lstData)
  {
    for( int i=0 ; i<lstData.size() ; i++ )
    {
      String item = (String)lstData.elementAt(i);
      this.add(szName, item);
    }
  }
  public void add(String szName, String szData)
  {
    this.addBegin(szName);
    this.addData(szData);
    this.addEnd(szName);
  }
  public void addBegin(String szName)
  {
    this.getDocument().append("<");
    this.getDocument().append(szName);
    this.getDocument().append(">");
  }
  public void addEnd(String szName)
  {
    this.getDocument().append("</");
    this.getDocument().append(szName);
    this.getDocument().append(">\r\n");
  }
  public void addData(String szData)
  {
    this.getDocument().append(java.net.URLEncoder.encode(szData));
  }
  public void write(String szFileName, boolean bClose)
  {
    if ( bClose ) this.close();
    this.write(szFileName);
  }
  public void write(String szFileName)
  {
    // Ajoute l'extension .XML si il faut
    if ( !szFileName.substring(szFileName.length()-extension.length()).equalsIgnoreCase(extension) ) szFileName = szFileName + extension;
    try
    {
      FileWriter fl = new FileWriter(szFileName);
      fl.write(this.getDocument().toString().toCharArray());
      fl.close();
    }
    catch (IOException ex)
    {
      ex.printStackTrace();
    }
  }
  public StringBuffer getDocument() {
    return document;
  }
  public String getVersion() {
    return version;
  }
  public void setDocument(StringBuffer document) {
    this.document = document;
  }
  public void setVersion(String version) {
    this.version = version;
  }
}