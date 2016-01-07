public class TagA {
  public static final boolean CONST_HAS_ENDTAG=true;

  public String content;
  public String attrHref;
  public String attrClass;
  public String attrId;

  public TagA() {
  }

  public String getContent() {return content;}
  public void setContent(String content) {this.content=content;}

  public String getAttrHref() {
    return attrHref;
  }
  public void setAttrHref(String attrHref) {
    this.attrHref = attrHref;
  }
  public String getAttrClass() {
    return attrClass;
  }
  public void setAttrClass(String attrClass) {
    this.attrClass = attrClass;
  }
  public String getAttrId() {
    return attrId;
  }
  public void setAttrId(String attrId) {
    this.attrId = attrId;
  }

  public String toHtml() {
    StringBuffer ret = new StringBuffer("<A ");
    if( (getAttrHref()!=null)&&(!getAttrHref().equals(""))) ret.append(" HREF=\"").append(getAttrHref()).append("\"");
    if( (getAttrClass()!=null)&&(!getAttrClass().equals(""))) ret.append(" CLASS=\"").append(getAttrClass()).append("\"");
    if( (getAttrId()!=null)&&(!getAttrId().equals(""))) ret.append(" ID=\"").append(getAttrId()).append("\"");
    ret.append(" >");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</A>");
    return ret.toString();
  }
}
