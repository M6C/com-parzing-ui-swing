public class TagBody {
  public static final boolean CONST_HAS_ENDTAG=true;

  public String content;
  public String attrBgcolor;
  public String attr_implied_;
  public String attrAlink;
  public String attrLink;
  public String attrOnload;
  public String attrText;
  public String attrVlink;

  public TagBody() {
  }

  public String getContent() {return content;}
  public void setContent(String content) {this.content=content;}

  public String getAttrBgcolor() {
    return attrBgcolor;
  }
  public void setAttrBgcolor(String attrBgcolor) {
    this.attrBgcolor = attrBgcolor;
  }
  public String getAttr_implied_() {
    return attr_implied_;
  }
  public void setAttr_implied_(String attr_implied_) {
    this.attr_implied_ = attr_implied_;
  }
  public String getAttrAlink() {
    return attrAlink;
  }
  public void setAttrAlink(String attrAlink) {
    this.attrAlink = attrAlink;
  }
  public String getAttrLink() {
    return attrLink;
  }
  public void setAttrLink(String attrLink) {
    this.attrLink = attrLink;
  }
  public String getAttrOnload() {
    return attrOnload;
  }
  public void setAttrOnload(String attrOnload) {
    this.attrOnload = attrOnload;
  }
  public String getAttrText() {
    return attrText;
  }
  public void setAttrText(String attrText) {
    this.attrText = attrText;
  }
  public String getAttrVlink() {
    return attrVlink;
  }
  public void setAttrVlink(String attrVlink) {
    this.attrVlink = attrVlink;
  }

  public String toHtml() {
    StringBuffer ret = new StringBuffer("<BODY ");
    if( (getAttrBgcolor()!=null)&&(!getAttrBgcolor().equals(""))) ret.append(" BGCOLOR=\"").append(getAttrBgcolor()).append("\"");
    if( (getAttr_implied_()!=null)&&(!getAttr_implied_().equals(""))) ret.append(" _IMPLIED_=\"").append(getAttr_implied_()).append("\"");
    if( (getAttrAlink()!=null)&&(!getAttrAlink().equals(""))) ret.append(" ALINK=\"").append(getAttrAlink()).append("\"");
    if( (getAttrLink()!=null)&&(!getAttrLink().equals(""))) ret.append(" LINK=\"").append(getAttrLink()).append("\"");
    if( (getAttrOnload()!=null)&&(!getAttrOnload().equals(""))) ret.append(" ONLOAD=\"").append(getAttrOnload()).append("\"");
    if( (getAttrText()!=null)&&(!getAttrText().equals(""))) ret.append(" TEXT=\"").append(getAttrText()).append("\"");
    if( (getAttrVlink()!=null)&&(!getAttrVlink().equals(""))) ret.append(" VLINK=\"").append(getAttrVlink()).append("\"");
    ret.append(" >");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</BODY>");
    return ret.toString();
  }
}
