public class TagTable {
  public static final boolean CONST_HAS_ENDTAG=true;

  public String content;
  public String attrBorder;
  public String attrCellspacing;
  public String attrCellpadding;

  public TagTable() {
  }

  public String getContent() {return content;}
  public void setContent(String content) {this.content=content;}

  public String getAttrBorder() {
    return attrBorder;
  }
  public void setAttrBorder(String attrBorder) {
    this.attrBorder = attrBorder;
  }
  public String getAttrCellspacing() {
    return attrCellspacing;
  }
  public void setAttrCellspacing(String attrCellspacing) {
    this.attrCellspacing = attrCellspacing;
  }
  public String getAttrCellpadding() {
    return attrCellpadding;
  }
  public void setAttrCellpadding(String attrCellpadding) {
    this.attrCellpadding = attrCellpadding;
  }

  public String toHtml() {
    StringBuffer ret = new StringBuffer("<TABLE ");
    if( (getAttrBorder()!=null)&&(!getAttrBorder().equals(""))) ret.append(" BORDER=\"").append(getAttrBorder()).append("\"");
    if( (getAttrCellspacing()!=null)&&(!getAttrCellspacing().equals(""))) ret.append(" CELLSPACING=\"").append(getAttrCellspacing()).append("\"");
    if( (getAttrCellpadding()!=null)&&(!getAttrCellpadding().equals(""))) ret.append(" CELLPADDING=\"").append(getAttrCellpadding()).append("\"");
    ret.append(" >");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</TABLE>");
    return ret.toString();
  }
}
