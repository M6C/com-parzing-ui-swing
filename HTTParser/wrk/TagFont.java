public class TagFont {
  public static final boolean CONST_HAS_ENDTAG=true;

  public String content;
  public String attrColor;
  public String attrSize;

  public TagFont() {
  }

  public String getContent() {return content;}
  public void setContent(String content) {this.content=content;}

  public String getAttrColor() {
    return attrColor;
  }
  public void setAttrColor(String attrColor) {
    this.attrColor = attrColor;
  }
  public String getAttrSize() {
    return attrSize;
  }
  public void setAttrSize(String attrSize) {
    this.attrSize = attrSize;
  }

  public String toHtml() {
    StringBuffer ret = new StringBuffer("<FONT ");
    if( (getAttrColor()!=null)&&(!getAttrColor().equals(""))) ret.append(" COLOR=\"").append(getAttrColor()).append("\"");
    if( (getAttrSize()!=null)&&(!getAttrSize().equals(""))) ret.append(" SIZE=\"").append(getAttrSize()).append("\"");
    ret.append(" >");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</FONT>");
    return ret.toString();
  }
}
