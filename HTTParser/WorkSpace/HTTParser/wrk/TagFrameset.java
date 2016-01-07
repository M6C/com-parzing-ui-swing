public class TagFrameset {
  public static final boolean CONST_HAS_ENDTAG=true;

  public String content;
  public String attrBorder;
  public String attrFramespacing;
  public String attrRows;
  public String attrCols;
  public String attrFrameborder;

  public TagFrameset() {
  }

  public String getContent() {return content;}
  public void setContent(String content) {this.content=content;}

  public String getAttrBorder() {
    return attrBorder;
  }
  public void setAttrBorder(String attrBorder) {
    this.attrBorder = attrBorder;
  }
  public String getAttrFramespacing() {
    return attrFramespacing;
  }
  public void setAttrFramespacing(String attrFramespacing) {
    this.attrFramespacing = attrFramespacing;
  }
  public String getAttrRows() {
    return attrRows;
  }
  public void setAttrRows(String attrRows) {
    this.attrRows = attrRows;
  }
  public String getAttrCols() {
    return attrCols;
  }
  public void setAttrCols(String attrCols) {
    this.attrCols = attrCols;
  }
  public String getAttrFrameborder() {
    return attrFrameborder;
  }
  public void setAttrFrameborder(String attrFrameborder) {
    this.attrFrameborder = attrFrameborder;
  }

  public String toHtml() {
    StringBuffer ret = new StringBuffer("<FRAMESET ");
    if( (getAttrBorder()!=null)&&(!getAttrBorder().equals(""))) ret.append(" BORDER=\"").append(getAttrBorder()).append("\"");
    if( (getAttrFramespacing()!=null)&&(!getAttrFramespacing().equals(""))) ret.append(" FRAMESPACING=\"").append(getAttrFramespacing()).append("\"");
    if( (getAttrRows()!=null)&&(!getAttrRows().equals(""))) ret.append(" ROWS=\"").append(getAttrRows()).append("\"");
    if( (getAttrCols()!=null)&&(!getAttrCols().equals(""))) ret.append(" COLS=\"").append(getAttrCols()).append("\"");
    if( (getAttrFrameborder()!=null)&&(!getAttrFrameborder().equals(""))) ret.append(" FRAMEBORDER=\"").append(getAttrFrameborder()).append("\"");
    ret.append(" >");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</FRAMESET>");
    return ret.toString();
  }
}
