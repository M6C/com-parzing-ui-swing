public class TagForm {
  public static final boolean CONST_HAS_ENDTAG=true;

  public String content;
  public String attrName;
  public String attrAction;

  public TagForm() {
  }

  public String getContent() {return content;}
  public void setContent(String content) {this.content=content;}

  public String getAttrName() {
    return attrName;
  }
  public void setAttrName(String attrName) {
    this.attrName = attrName;
  }
  public String getAttrAction() {
    return attrAction;
  }
  public void setAttrAction(String attrAction) {
    this.attrAction = attrAction;
  }

  public String toHtml() {
    StringBuffer ret = new StringBuffer("<FORM ");
    if( (getAttrName()!=null)&&(!getAttrName().equals(""))) ret.append(" NAME=\"").append(getAttrName()).append("\"");
    if( (getAttrAction()!=null)&&(!getAttrAction().equals(""))) ret.append(" ACTION=\"").append(getAttrAction()).append("\"");
    ret.append(" >");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</FORM>");
    return ret.toString();
  }
}
