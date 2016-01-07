public class TagP {
  public static final boolean CONST_HAS_ENDTAG=true;

  public String content;

  public TagP() {
  }

  public String getContent() {return content;}
  public void setContent(String content) {this.content=content;}


  public String toHtml() {
    StringBuffer ret = new StringBuffer("<P ");
    ret.append(" >");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</P>");
    return ret.toString();
  }
}
