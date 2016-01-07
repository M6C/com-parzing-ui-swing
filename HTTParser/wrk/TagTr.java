public class TagTr {
  public static final boolean CONST_HAS_ENDTAG=true;

  public String content;
  public String attrValign;

  public TagTr() {
  }

  public String getContent() {return content;}
  public void setContent(String content) {this.content=content;}

  public String getAttrValign() {
    return attrValign;
  }
  public void setAttrValign(String attrValign) {
    this.attrValign = attrValign;
  }

  public String toHtml() {
    StringBuffer ret = new StringBuffer("<TR ");
    if( (getAttrValign()!=null)&&(!getAttrValign().equals(""))) ret.append(" VALIGN=\"").append(getAttrValign()).append("\"");
    ret.append(" >");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</TR>");
    return ret.toString();
  }
}
