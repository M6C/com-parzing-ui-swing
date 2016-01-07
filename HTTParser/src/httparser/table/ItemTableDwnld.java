package httparser.table;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ItemTableDwnld extends AncestorItem {

	private static final long serialVersionUID = 6332019985882025211L;

	private String textStart;
  private String textEnd;
  private String textPath;
  private String textType;
  private String textName;

  public ItemTableDwnld() {
  }
  public String getTextStart() {
    return textStart;
  }
  public void setTextStart(String textStart) {
    this.textStart = textStart;
  }
  public void setTextEnd(String textEnd) {
    this.textEnd = textEnd;
  }
  public String getTextEnd() {
    return textEnd;
  }
  public String getTextPath() {
    return textPath;
  }
  public void setTextPath(String textPath) {
    this.textPath = textPath;
  }
  public void setTextType(String textType) {
    this.textType = textType;
  }
  public String getTextType() {
    return textType;
  }
  public void setTextName(String textName) {
    this.textName = textName;
  }
  public String getTextName() {
    return textName;
  }
  public String[] getData()
  {
    String[] data = {getTextName(),getTextStart(),getTextEnd(),getTextType(),getTextPath(),"",""};
    return data;
  }
}