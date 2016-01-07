public class TagTd {
  public static final boolean CONST_HAS_ENDTAG=true;

  public String content;
  public String attrStyle;
  public String attrId;
  public String attrWidth;
  public String attrColspan;
  public String attrNowrap;
  public String attrAlign;
  public String attrValign;
  public String attrOnclick;
  public String attrBgcolor;

  public TagTd() {
  }

  public String getContent() {return content;}
  public void setContent(String content) {this.content=content;}

  public String getAttrStyle() {
    return attrStyle;
  }
  public void setAttrStyle(String attrStyle) {
    this.attrStyle = attrStyle;
  }
  public String getAttrId() {
    return attrId;
  }
  public void setAttrId(String attrId) {
    this.attrId = attrId;
  }
  public String getAttrWidth() {
    return attrWidth;
  }
  public void setAttrWidth(String attrWidth) {
    this.attrWidth = attrWidth;
  }
  public String getAttrColspan() {
    return attrColspan;
  }
  public void setAttrColspan(String attrColspan) {
    this.attrColspan = attrColspan;
  }
  public String getAttrNowrap() {
    return attrNowrap;
  }
  public void setAttrNowrap(String attrNowrap) {
    this.attrNowrap = attrNowrap;
  }
  public String getAttrAlign() {
    return attrAlign;
  }
  public void setAttrAlign(String attrAlign) {
    this.attrAlign = attrAlign;
  }
  public String getAttrValign() {
    return attrValign;
  }
  public void setAttrValign(String attrValign) {
    this.attrValign = attrValign;
  }
  public String getAttrOnclick() {
    return attrOnclick;
  }
  public void setAttrOnclick(String attrOnclick) {
    this.attrOnclick = attrOnclick;
  }
  public String getAttrBgcolor() {
    return attrBgcolor;
  }
  public void setAttrBgcolor(String attrBgcolor) {
    this.attrBgcolor = attrBgcolor;
  }

  public String toHtml() {
    StringBuffer ret = new StringBuffer("<TD ");
    if( (getAttrStyle()!=null)&&(!getAttrStyle().equals(""))) ret.append(" STYLE=\"").append(getAttrStyle()).append("\"");
    if( (getAttrId()!=null)&&(!getAttrId().equals(""))) ret.append(" ID=\"").append(getAttrId()).append("\"");
    if( (getAttrWidth()!=null)&&(!getAttrWidth().equals(""))) ret.append(" WIDTH=\"").append(getAttrWidth()).append("\"");
    if( (getAttrColspan()!=null)&&(!getAttrColspan().equals(""))) ret.append(" COLSPAN=\"").append(getAttrColspan()).append("\"");
    if( (getAttrNowrap()!=null)&&(!getAttrNowrap().equals(""))) ret.append(" NOWRAP=\"").append(getAttrNowrap()).append("\"");
    if( (getAttrAlign()!=null)&&(!getAttrAlign().equals(""))) ret.append(" ALIGN=\"").append(getAttrAlign()).append("\"");
    if( (getAttrValign()!=null)&&(!getAttrValign().equals(""))) ret.append(" VALIGN=\"").append(getAttrValign()).append("\"");
    if( (getAttrOnclick()!=null)&&(!getAttrOnclick().equals(""))) ret.append(" ONCLICK=\"").append(getAttrOnclick()).append("\"");
    if( (getAttrBgcolor()!=null)&&(!getAttrBgcolor().equals(""))) ret.append(" BGCOLOR=\"").append(getAttrBgcolor()).append("\"");
    ret.append(" >");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</TD>");
    return ret.toString();
  }
}
