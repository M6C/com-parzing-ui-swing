package httparser.thread;

import httparser.frame.FrmMain;

import java.util.Vector;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ThrdGetLink extends ThrdParseUrl {

  public ThrdGetLink(FrmMain parent) {
    super(parent);
  }

  public void run()
  {
    this.getLink();
    this.getParent().setRunnerLink(null);
  }

  protected void getLink() {

  	// Liste des liens de la premiere page à traiter
    Vector vListUrl = getListLink(this.getUrl(), this.getHtml(), null);

    if ( vListUrl.size() > 0 ) {
      // Affiche la liste des liens chez le parent
      getParent().showLink(vListUrl);
    }
  }
}