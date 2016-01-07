public class TagStyle {
  public static final boolean CONST_HAS_ENDTAG=true;

  public String content;

  public TagStyle() {
  }

  public String getContent() {return content;}
  public void setContent(String content) {this.content=content;}


  public String toHtml() {
    StringBuffer ret = new StringBuffer("<STYLE ");
    ret.append(" >");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</STYLE>");
    return ret.toString();
  }
}
