public class TagScript {
  public static final boolean CONST_HAS_ENDTAG=true;

  public String content;

  public TagScript() {
  }

  public String getContent() {return content;}
  public void setContent(String content) {this.content=content;}


  public String toHtml() {
    StringBuffer ret = new StringBuffer("<SCRIPT ");
    ret.append(" >");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</SCRIPT>");
    return ret.toString();
  }
}
