public class TagTitle {
  public static final boolean CONST_HAS_ENDTAG=true;

  public String content;

  public TagTitle() {
  }

  public String getContent() {return content;}
  public void setContent(String content) {this.content=content;}


  public String toHtml() {
    StringBuffer ret = new StringBuffer("<TITLE ");
    ret.append(" >");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</TITLE>");
    return ret.toString();
  }
}
