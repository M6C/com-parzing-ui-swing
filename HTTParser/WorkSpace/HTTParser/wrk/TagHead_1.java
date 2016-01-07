public class TagHead {
  public static final boolean CONST_HAS_ENDTAG=true;

  public String content;
  public String attr_implied_;

  public TagHead() {
  }

  public String getContent() {return content;}
  public void setContent(String content) {this.content=content;}

  public String getAttr_implied_() {
    return attr_implied_;
  }
  public void setAttr_implied_(String attr_implied_) {
    this.attr_implied_ = attr_implied_;
  }

  public String toHtml() {
    StringBuffer ret = new StringBuffer("<HEAD ");
    if( (getAttr_implied_()!=null)&&(!getAttr_implied_().equals(""))) ret.append(" _IMPLIED_=\"").append(getAttr_implied_()).append("\"");
    ret.append(" >");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</HEAD>");
    return ret.toString();
  }
}
