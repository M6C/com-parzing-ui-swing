public class TagNoframes {
  public static final boolean CONST_HAS_ENDTAG=true;

  public String content;

  public TagNoframes() {
  }

  public String getContent() {return content;}
  public void setContent(String content) {this.content=content;}


  public String toHtml() {
    StringBuffer ret = new StringBuffer("<NOFRAMES ");
    ret.append(" >");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</NOFRAMES>");
    return ret.toString();
  }
}
