package httparser.thread;

import httparser.frame.FrmMain;

import java.net.URL;
import java.util.Vector;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class OldAncestorThread extends Thread {

  public static final String DESTINATION_XML = "Xml";
  public static final String DESTINATION_DATABASE = "Database";
  
  public static final String METHODE_SEQUENCE = "Séquence";
  public static final String METHODE_TEXT_REGEX = "Regex";
  public static final String METHODE_TEXT_LITTERALE = "Littérale";

  public static final String BROWSER_JAVA = "JavaBrowser";
  public static final String BROWSER_JDIC = "JdicBrowser";

  private FrmMain parent = null;
  private boolean bSubLink = false;
  private boolean bIndent = false;
  private boolean bClean = false;
  private boolean bCorrectHtml = true;
  private boolean bUseIncludeList = false;
  private boolean bUseTimeOut = false;
  private boolean bShowPage = false;
  private boolean bParse = false;
  private boolean bCloseAtEnd = false;
  private boolean bUseIncludeListAsMask = false;
  private String Html = "";
  private String Url = "";
  private String destination = "";
  private Vector UrlIncludeList = null;
  private Vector UrlExcludeList = null;
  private Vector DownloadList = null;
  private URL urlContext = null;
  private int iMaxSubLevel = 0;

  public OldAncestorThread(FrmMain parent) {
    super();
    this.setParent(parent);
    this.setSubLink(parent.isSubLink());
    this.setIndent(parent.isIndent());
    this.setClean(parent.isClean());
    this.setCorrectHtml(parent.isCorrectHtml());
    this.setUseIncludeList(parent.isUseIncludeList());
    this.setUseTimeOut(parent.isUseTimeOut());
    this.setUseIncludeListAsMask(parent.isUseIncludeListAsMask());
    this.setShowPage(parent.isShowPage());
    this.setParse(parent.isParse());
    this.setCloseAtEnd(parent.isCloseAtEnd());
    this.setHtml( ((isClean()) ? parent.getTxtSource() : parent.getTxtHtml()) );
    this.setUrl(parent.getTxtUrl());
    this.setUrlIncludeList(parent.getUrlIncludeList());
    this.setUrlExcludeList(parent.getUrlExcludeList());
    this.setDownloadList(parent.getDownloadList());
    this.setUrlContext(parent.getURLContext());
    this.setDestination(parent.getDestination());
    this.setMaxSubLevel(parent.getMaxSubLevel());
  }

  public boolean isClean() {
    return bClean;
  }
  public boolean isIndent() {
    return bIndent;
  }
  public boolean isCorrectHtml() {
  	return bCorrectHtml;
  }
  public boolean isSubLink() {
    return bSubLink;
  }
  public boolean isUseIncludeListAsMask() {
  	return bUseIncludeListAsMask;
  }
  public boolean isUseIncludeList() {
    return bUseIncludeList;
  }
  public boolean isUseTimeOut() {
    return bUseTimeOut;
  }
  public boolean isShowPage(){
    return bShowPage;
  }
  public String getHtml() {
    return Html;
  }
  public FrmMain getParent() {
    return parent;
  }
  public String getUrl() {
    return Url;
  }
  public Vector getUrlExcludeList() {
    return UrlExcludeList;
  }
  public Vector getUrlIncludeList() {
    return UrlIncludeList;
  }
  public void setClean(boolean bClean) {
    this.bClean = bClean;
  }
  public void setIndent(boolean bIndent) {
    this.bIndent = bIndent;
  }
  public void setCorrectHtml(boolean bCorrectHtml) {
    this.bCorrectHtml = bCorrectHtml;
  }
  public void setSubLink(boolean bSubLink) {
    this.bSubLink = bSubLink;
  }
  public void setUseIncludeListAsMask(boolean bUseIncludeListAsMask) {
    this.bUseIncludeListAsMask = bUseIncludeListAsMask;
  }
  public void setUseIncludeList(boolean bUseIncludeList) {
    this.bUseIncludeList = bUseIncludeList;
  }
  public void setUseTimeOut(boolean bUseTimeOut) {
    this.bUseTimeOut = bUseTimeOut;
  }
  public void setShowPage(boolean bShowPage) {
    this.bShowPage = bShowPage;
  }
  public void setHtml(String Html) {
    this.Html = Html;
  }
  public void setParent(FrmMain parent) {
    this.parent = parent;
  }
  public void setUrl(String Url) {
    this.Url = Url;
  }
  public void setUrlExcludeList(Vector UrlExcludeList) {
    this.UrlExcludeList = UrlExcludeList;
  }
  public void setUrlIncludeList(Vector UrlIncludeList) {
    this.UrlIncludeList = UrlIncludeList;
  }
  public URL getUrlContext() {
    return urlContext;
  }
  public void setUrlContext(URL urlContext) {
    this.urlContext = urlContext;
  }
  public Vector getDownloadList() {
    return DownloadList;
  }
  public void setDownloadList(Vector DownloadList) {
    this.DownloadList = DownloadList;
  }
  public String getDestination() {
    return destination;
  }
  public void setDestination(String destination) {
    this.destination = destination;
  }

	public boolean isCloseAtEnd() {
		return bCloseAtEnd;
	}

	public void setCloseAtEnd(boolean closeAtEnd) {
		bCloseAtEnd = closeAtEnd;
	}

	public boolean isParse() {
		return bParse;
	}

	public void setParse(boolean parse) {
		bParse = parse;
	}

	public int getMaxSubLevel() {
		return iMaxSubLevel;
	}

	public void setMaxSubLevel(int maxSubLevel) {
		iMaxSubLevel = maxSubLevel;
	}
}