package httparser.thread;

import httparser.frame.FrmMain;
import httparser.table.ItemTableLink;

import java.util.Vector;
import java.util.regex.Pattern;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class OldThrdGetLink extends OldThrdParse2Url {

  public OldThrdGetLink(FrmMain parent) {
    super(parent);
  }

  public void run()
  {
    this.getLink();
    this.getParent().setRunnerLink(null);
  }

  protected void getLink() {

    // Liste des mask (RegEx) de liens à traiter
    Vector vListMaskUrlInc = null;
    // Liste des mask (RegEx) de liens à exclure
    Vector vListMaskUrlExc = null;
    if ( this.isUseIncludeListAsMask() )
    { // Parcoure les liens contenu dans la liste jTableLinkInclude
    	vListMaskUrlInc = new Vector();
      for ( int i=0 ; i<this.getParent().getUrlIncludeList().size() ; i++ )
      {
        ItemTableLink item = (ItemTableLink)this.getParent().getUrlIncludeList().elementAt(i);

        String szUrl = item.getUrl();
        Pattern pat = null;
        if (szUrl.endsWith("/i")) {
        	String szPat = szUrl.substring(0, szUrl.length()-2);
        	pat = Pattern.compile(szPat, Pattern.CASE_INSENSITIVE);
        }
        else
        	pat = Pattern.compile(szUrl);
        
        // Ajoute les liens ajouter dans la liste jTableLinkInclude
        vListMaskUrlInc.add(pat);
      }
      
      // Parcoure les liens contenu dans la liste jTableLinkInclude
    	vListMaskUrlExc = new Vector();
      for ( int i=0 ; i<this.getParent().getUrlExcludeList().size() ; i++ )
      {
        ItemTableLink item = (ItemTableLink)this.getParent().getUrlExcludeList().elementAt(i);

        String szUrl = item.getUrl();
        Pattern pat = null;
        if (szUrl.endsWith("/i")) {
        	String szPat = szUrl.substring(0, szUrl.length()-2);
        	pat = Pattern.compile(szPat, Pattern.CASE_INSENSITIVE);
        }
        else
        	pat = Pattern.compile(szUrl);
        
        // Ajoute les liens ajouter dans la liste jTableLinkInclude
        vListMaskUrlExc.add(pat);
      }
    }

    // Liste des liens de la premiere page à traiter
    Vector vListUrl = getListLink(this.getUrl(), this.getHtml(), vListMaskUrlInc, vListMaskUrlExc);

    if ( vListUrl.size() > 0 ) {
      // Affiche la liste des liens chez le parent
      getParent().showLink(vListUrl);
    }
  }
}