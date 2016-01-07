public class TagCenter {
  public static final boolean CONST_HAS_ENDTAG=true;

  public String content;

  public TagCenter() {
  }

  public String getContent() {return content;}
  public void setContent(String content) {this.content=content;}


  public String toHtml() {
    StringBuffer ret = new StringBuffer("<CENTER ");
    ret.append(" >");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</CENTER>");
    return ret.toString();
  }
}
