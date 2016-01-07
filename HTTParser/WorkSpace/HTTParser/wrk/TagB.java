public class TagB {
  public static final boolean CONST_HAS_ENDTAG=true;

  public String content;

  public TagB() {
  }

  public String getContent() {return content;}
  public void setContent(String content) {this.content=content;}


  public String toHtml() {
    StringBuffer ret = new StringBuffer("<B ");
    ret.append(" >");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</B>");
    return ret.toString();
  }
}
