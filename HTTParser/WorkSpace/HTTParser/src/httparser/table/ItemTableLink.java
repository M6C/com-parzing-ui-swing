package httparser.table;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ItemTableLink extends AncestorItem {

  private boolean exclude = false;
  private String url = "";

  public ItemTableLink() {
  }
  public boolean isExclude() {
    return exclude;
  }
  public String getUrl() {
    return url;
  }
  public void setExclude(boolean exclude) {
    this.exclude = exclude;
  }
  public void setUrl(String url) {
    this.url = url;
  }
  public String[] getData()
  {
    return new String[] { " ", getUrl() };
  }
}