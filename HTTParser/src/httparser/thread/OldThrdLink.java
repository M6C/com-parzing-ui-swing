package httparser.thread;

import ressource.image.*;
import ressource.XML.*;
import java.util.*;
import httparser.table.*;
import httparser.frame.*;
import java.net.*;
import httparser.XML.*;
//import httparser.XML.XMLDocument;
import ressource.XML.XMLDocument;
import ressource.*;
import java.io.*;
import java.awt.*;
import com.sun.image.codec.jpeg.*;
import java.awt.image.*;
import java.lang.reflect.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class OldThrdLink extends OldAncestorThread {

  public OldThrdLink(FrmMain parent) {
    super(parent);
  }

  public void run()
  {
    if ( this.isSubLink() || this.isUseIncludeList() )
      this.getLink();
    else
      // Analyse seulement la page actuelle
      this.parseUrl();
    this.getParent().setRunnerLink(null);
  }

  protected void getLink()
  {
    int iNiveauMax = (this.isSubLink()) ? 3 : 0;
    int iNiveauActuel = 0;

    // Liste des Url de la page à traiter
    Vector vGlobalListUrl = new Vector();
    // Liste des Index pour chaque vGlobalListUrl
    Vector vGlobalListLoop = new Vector();
    // Liste des liens de la premiere page à traiter
    Vector vListUrl = null;
    if ( this.isUseIncludeList() )
    { // Parcoure les liens contenu dans la liste jTableLinkInclude
      vListUrl = new Vector();
      for ( int i=0 ; i<this.getParent().getUrlIncludeList().size() ; i++ )
      {
        ItemTableLink item = (ItemTableLink)this.getParent().getUrlIncludeList().elementAt(i);
        // Ajoute les liens ajouter dans la liste jTableLinkInclude
        vListUrl.add(item.getUrl());
      }
    }
    else {
      if ( this.getHtml().length() > 0 ) {
        // Recupere les liens contenu dans la page actuelle
        vListUrl = getListLink(this.getUrl(), this.getHtml());
      }
    }

    if ( vListUrl.size() > 0 )
    {

      // Affiche la liste des liens chez le parent
      getParent().showLink(vListUrl);

      // Ajout des liens de la première page
      vGlobalListUrl.addElement(vListUrl);
      // Ajout un index à Zero par default
      vGlobalListLoop.addElement(new Integer(0));

      // Boucle sur toutes les listes des liens
      while ( vGlobalListUrl.size() > 0 )
      {
        // Liste du niveau Actuel
        Vector item = (Vector)vGlobalListUrl.elementAt(iNiveauActuel);
        // Index Actuel de la liste des liens
        int iLoop = ((Integer)vGlobalListLoop.elementAt(iNiveauActuel)).intValue();
        if ( iLoop < item.size() )
        { // Il y a encore des liens à traiter

          // Lien a traiter
          String szUrl = (String)item.elementAt(iLoop++);
          // Teste si le lien doit être traiter ou non
          if ( !this.getParent().isExclude(this.getUrl()) )
          {
            // Met à jour l'index Actuel de la liste des liens
            vGlobalListLoop.setElementAt(new Integer(iLoop), iNiveauActuel);
            if ( (szUrl!=null) && (szUrl.length()>0) )
            {
              // Affiche le lien à traiter
              this.getParent().setTxtUrl(szUrl);
              // Mise à jour de l'url de context du parent
              this.getParent().setURLContext(szUrl);
              // Mise à jour de l'url de context
              this.setUrlContext(this.getParent().getURLContext());
              // Simule le click sur le bouton Go
              this.getParent().doGoUrl();
              // Attend une notification du parent
              try { synchronized(this){this.wait();} } catch ( java.lang.InterruptedException ex ) { ex.printStackTrace(); };
              // Mise à jour de l'affichage
              this.getParent().update(this.getParent().getGraphics());
              // Analyse la page
              this.parseUrl();
              // Si nous ne somme pas arrivé au niveau Maximum de traitement
              // et si nous voulons aussi les liens contenus dans la page
              if ( (iNiveauActuel<iNiveauMax) && this.isSubLink())
              {
                // Passe au niveau "Superieur"
                iNiveauActuel++;
                // Met à Zero l'index dans la liste
                iLoop=0;
                // Recupère la liste des liens contenu dans la page à traiter
                Vector vList = getListLink(this.getUrl(), this.getHtml());
                // Affiche la liste des liens chez le parent
                getParent().showLink(vList);
                // Ajoute la liste des liens a traiter
                vGlobalListUrl.addElement(vList);
                // Ajoute
                vGlobalListLoop.addElement(new Integer(iLoop));
              }
            }
          }
          else
          {
            // Passe à l'Url suivante
            vGlobalListLoop.setElementAt(new Integer(iLoop+1), iNiveauActuel);
          }
        }
        else
        { // Il n'y a plus de liens à traiter dans cette liste
          // Supprime la liste des Url
          vGlobalListUrl.remove(iNiveauActuel);
          // Supprime la liste Index
          vGlobalListLoop.remove(iNiveauActuel);
          // Reviend au niveau "Inferieur"
          iNiveauActuel--;
        }
      }
    }
  }
  /**
   * Retourne la liste des liens contenu dans szHTML
   * à partir de l'adresse szURL
   * @param szURL
   * @param szHTML
   * @return liste d'objet URL
   */
  protected Vector getListLink(String szURL, String szHTML)
  {
    Vector ret = new Vector();
    if ( szHTML.length() > 0 )
    {
      boolean bContinue = true;
      String szHTMLUp = szHTML.toUpperCase();
      String baliseLnk = "HREF=";
      String szProtocole = "http://";
      String szCommentDeb = "<!--";
      String szCommentFin = "-->";
      int iDeb=0;

      while( iDeb > -1 )
      {
        iDeb=szHTMLUp.indexOf(baliseLnk, iDeb);
        int iDebComment=szHTMLUp.indexOf(szCommentDeb, iDeb);
        int iFinComment=szHTMLUp.indexOf(szCommentFin, iDeb);
        if ( ( iDeb > -1 ) && ( (iFinComment==-1) || (iFinComment>=iDebComment) ) )
        {
          iDeb += baliseLnk.length();
          StringBuffer str = new StringBuffer();
          boolean bGuillemet = (boolean)(szHTMLUp.charAt(iDeb) == '"');
          boolean bLoop = true;
          if ( bGuillemet ) iDeb++;
          while( bLoop )
          {
            char c = szHTMLUp.charAt(iDeb++);
            switch( c )
            {
              case ' ':
                if ( !bGuillemet ) bLoop = false;
                break;
              case '>': case '"':
                bLoop = false;
                break;
            }
            if ( bLoop ) str.append(c);
          }
          iDeb+=str.length();

          if (str.length()>0)
          {
            try
            {
              URL urlTmp = new URL(this.getUrlContext(), str.toString());
              ret.add(urlTmp.toString());
            }
            catch( MalformedURLException ex )
            {
              System.out.println("ERROR: Malformed Url: " + str.toString());
            }
          }
        }
        else if ( iDeb > -1 )
          iDeb++;
      }
    }
    return ret;
  }

  /**
   * Analyse la page actuelle à la recherche des informations
   * à téléchanger contenu dans la liste jTableDownload
   */
  private void parseUrl()
  {
    //DEBUG
    //System.out.println( "FUNCTION parseUrl:" );
    Vector vListAction = this.getDownloadList();
    Vector vListText;
    Vector vListImage;
    String szTextPath = "";
    String szImagePath = "";

    if ( getDestination().equals(DESTINATION_XML) )
    {
      StringBuffer sbHTML = new StringBuffer(getParent().getHtml());
      sbHTML = new StringBuffer(sbHTML.toString().replaceAll("\r", "").replaceAll("\n", "\r\n"));

      XMLDocument xmlDoc = new XMLDocument();
      xmlDoc.setEncode(getParent().getTxtDataEncode());
    	int cnt = 0, iEnd = 0;
    	boolean bContinue = true;
    	do {
    		int iTmp = 0;
    		vListText = new Vector();
    		vListImage = new Vector();
	      for ( int i=0 ; i<vListAction.size() ; i++ )
	      {
	        ItemTableDwnld item = (ItemTableDwnld)vListAction.elementAt(i);
	        if ( item.getTextType().equalsIgnoreCase("text") )
	        {
	        	Vector data = clearUrl_Text(sbHTML, item);
	        	if (data.size()>0) {
		          Vector itemData = new Vector();
		          itemData.add(item.getTextName());
		          itemData.add(data);
		          vListText.add(itemData);
		          szTextPath = item.getTextPath();
		          // Recupère la position où à été trouvé le text
		          iTmp = Integer.parseInt((String)data.elementAt(1));
		          // Supprime la position dans la liste
		          data.remove(1);
		          //DEBUG
		          //System.out.println( "itemData TXT Name:" + itemData.elementAt(0) + " data:" + itemData.elementAt(1) );
	        	}
	        }
	        else if ( item.getTextType().equalsIgnoreCase("image") )
	        {
	        	Vector data = clearUrl_Image(sbHTML, item);
	        	if (data.size()>0) {
		          Vector itemData = new Vector();
		          itemData.add(item.getTextName());
		          itemData.add(data);
		          vListImage.add(itemData);
		          vListText.add(itemData);
		          szImagePath = item.getTextPath();
		          // Recupère la position où à été trouvé le text
		          iTmp = Integer.parseInt((String)data.elementAt(1));
		          // Supprime la position dans la liste
		          data.remove(1);
		          //DEBUG
		          //System.out.println( "itemData IMG Name:" + itemData.elementAt(0) + " data:" + itemData.elementAt(1) );
	        	}
	        }
	        if (iTmp>iEnd)
	        	iEnd = iTmp;
	      }
	      bContinue = (vListText.size()>0 || vListImage.size()>0);
	      if (bContinue) {
		      cnt++;
	    		Hashtable attribut = new Hashtable();
	    		attribut.put("id", Integer.toString(cnt));
	    		FxXML.beginXMLElement(xmlDoc, "DATA", attribut);
		      if (vListText.size() > 0) FxXML.addXMLData(xmlDoc, vListText);
		      if (vListImage.size() > 0) FxXML.addXMLData(xmlDoc, vListImage);
		      FxXML.closeXMLElement(xmlDoc, "DATA");
		      sbHTML.replace(0, iEnd, "");
	      }
    	} while (bContinue);

    	if (cnt>1) {
	      String szFileName = szTextPath.concat("\\").concat( ( (this.getUrlContext().getQuery()==null || this.getUrlContext().getQuery().equals("")) ? this.getUrlContext().getFile() : this.getUrlContext().getQuery()));
	      FxXML.writeXML(szFileName, xmlDoc);
    	}
    }
    else if ( getDestination().equals(DESTINATION_DATABASE) )
    {
  		vListText = new Vector();
  		vListImage = new Vector();
      for ( int i=0 ; i<vListAction.size() ; i++ )
      {
        ItemTableDwnld item = (ItemTableDwnld)vListAction.elementAt(i);
        if ( item.getTextType().equalsIgnoreCase("text") )
        {
          Vector itemData = new Vector();
          itemData.add(item.getTextName());
          itemData.add(parseUrl_Text(item));
          vListText.add(itemData);
          szTextPath = item.getTextPath();
          //DEBUG
          //System.out.println( "itemData TXT Name:" + itemData.elementAt(0) + " data:" + itemData.elementAt(1) );
        }
        else if ( item.getTextType().equalsIgnoreCase("image") )
        {
          Vector itemData = new Vector();
          itemData.add(item.getTextName());
          itemData.add(parseUrl_Image(item));
          vListImage.add(itemData);
          vListText.add(itemData);
          szImagePath = item.getTextPath();
          //DEBUG
          //System.out.println( "itemData IMG Name:" + itemData.elementAt(0) + " data:" + itemData.elementAt(1) );
        }
      }

      if ( vListText.size()>0 )
      {
        // Récupération du ClassLoader actuel à la place du Class.forName
        // pour une meilleur gestion de la mémoire
        ClassLoader classloader = this.getClass().getClassLoader();
        try {
          // Récupération de la classe
          Class classe = classloader.loadClass("videofuture.toolz.ImportURLFilmToDB");
          // Récupération de la methode avec comme parametre deux String
          Method method = classe.getMethod("execute", new Class[] {Vector.class});
          // Invocation de la methode
          method.invoke(classe.newInstance(), new Object[] {vListText});
        }
        catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }
  }

  private Vector parseUrl_Text(ItemTableDwnld item)
  {
    Vector ret = new Vector();
    StringBuffer szHTML = new StringBuffer(getParent().getHtml());
    szHTML = new StringBuffer(szHTML.toString().replaceAll("\r", "").replaceAll("\n", "\r\n"));

    if ( szHTML.length() > 0 )
    {
      boolean bContinue = true;
      String baliseDebTmp = item.getTextStart().replaceAll("\r", "").replaceAll("\n", "\r\n");
      String baliseFinTmp = item.getTextEnd().replaceAll("\r", "").replaceAll("\n", "\r\n");
      int iDeb = 0;
      int iFin = 0;
      while(bContinue)
      {
        iDeb = szHTML.toString().indexOf(baliseDebTmp, iDeb);
        iFin = szHTML.toString().indexOf(baliseFinTmp, iDeb+baliseDebTmp.length());
        // Cherche la dernière occurance de la balise de fin
        // Même si il y a d'autres balises du même type à l'interieur
        // exemple: <SCRIPT><SCRIPT>blablabla...</SCRIPT></SCRIPT>
        int iTmp = szHTML.toString().indexOf(baliseDebTmp, iDeb+baliseFinTmp.length());
        while( (iTmp>-1) && (iTmp<iFin) )
        {
          iFin = szHTML.toString().indexOf(baliseFinTmp, iFin+baliseFinTmp.length());
          iTmp = szHTML.toString().indexOf(baliseDebTmp, iTmp+baliseDebTmp.length());
        }
        bContinue = ( (iDeb>-1) && (iFin>-1) );
        if ( bContinue )
        {
          iDeb+=baliseDebTmp.length();
          String str = szHTML.substring(iDeb,iFin);
          str = cleanTag(str);
          ret.add( str );
        }
      }
    }
    return ret;
  }

  private Vector parseUrl_Image(ItemTableDwnld item)
  {
    Vector ret = new Vector();
    StringBuffer szHTML = new StringBuffer(getParent().getHtml());
    szHTML = new StringBuffer(szHTML.toString().replaceAll("\r", "").replaceAll("\n", "\r\n"));

    if ( szHTML.length() > 0 )
    {
      boolean bContinue = true;
      String baliseDebTmp = item.getTextStart().replaceAll("\r", "").replaceAll("\n", "\r\n");
      String baliseFinTmp = item.getTextEnd().replaceAll("\r", "").replaceAll("\n", "\r\n");
      int iDeb = 0;
      int iFin = 0;
      while(bContinue)
      {
        iDeb = szHTML.toString().indexOf(baliseDebTmp, iDeb);
        iFin = szHTML.toString().indexOf(baliseFinTmp, iDeb+baliseDebTmp.length());
        // Cherche la dernière occurance de la balise de fin
        // Même si il y a d'autres balises du même type à l'interieur
        // exemple: <SCRIPT><SCRIPT>blablabla...</SCRIPT></SCRIPT>
        int iTmp = szHTML.toString().indexOf(baliseDebTmp, iDeb+baliseFinTmp.length());
        while( (iTmp>-1) && (iTmp<iFin) )
        {
          iFin = szHTML.toString().indexOf(baliseFinTmp, iFin+baliseFinTmp.length());
          iTmp = szHTML.toString().indexOf(baliseDebTmp, iTmp+baliseDebTmp.length());
        }
        bContinue = ( (iDeb>-1) && (iFin>-1) );
        if ( bContinue )
        {
          iDeb+=baliseDebTmp.length();
          String str = szHTML.substring(iDeb,iFin).toUpperCase();
          String imgPath = FxHtml.getNextText(str, "SRC=", 0);
          if ( imgPath.length() > 0 )
          {
            ret.add( imgPath );
          }
        }
      }
    }
    return ret;
  }

  private Vector clearUrl_Text(StringBuffer szHTML, ItemTableDwnld item)
  {
  	Vector ret = new Vector();

    if ( szHTML.length() > 0 )
    {
      String baliseDebTmp = item.getTextStart().replaceAll("\r", "").replaceAll("\n", "\r\n");
      String baliseFinTmp = item.getTextEnd().replaceAll("\r", "").replaceAll("\n", "\r\n");
      int iDeb = 0;
      int iFin = 0;
      iDeb = szHTML.toString().indexOf(baliseDebTmp, iDeb);
      iFin = szHTML.toString().indexOf(baliseFinTmp, iDeb+baliseDebTmp.length());
      // Cherche la dernière occurance de la balise de fin
      // Même si il y a d'autres balises du même type à l'interieur
      // exemple: <SCRIPT><SCRIPT>blablabla...</SCRIPT></SCRIPT>
      int iTmp = szHTML.toString().indexOf(baliseDebTmp, iDeb+baliseFinTmp.length());
      while( (iTmp>-1) && (iTmp<iFin) )
      {
        iFin = szHTML.toString().indexOf(baliseFinTmp, iFin+baliseFinTmp.length());
        iTmp = szHTML.toString().indexOf(baliseDebTmp, iTmp+baliseDebTmp.length());
      }
      if ( (iDeb>-1) && (iFin>-1) )
      {
        iDeb+=baliseDebTmp.length();
        String str = szHTML.substring(iDeb,iFin);
        str = cleanTag(str);
        ret.add( str );
        ret.add(Integer.toString(iFin));
      }
      //szHTML.replace(iDeb, iFin, "");
    }
    return ret;
  }

  private Vector clearUrl_Image(StringBuffer szHTML, ItemTableDwnld item) {
    Vector ret = new Vector();

    if ( szHTML.length() > 0 )
    {
      boolean bContinue = true;
      String baliseDebTmp = item.getTextStart().replaceAll("\r", "").replaceAll("\n", "\r\n");
      String baliseFinTmp = item.getTextEnd().replaceAll("\r", "").replaceAll("\n", "\r\n");
      int iDeb = 0;
      int iFin = 0;
      while(bContinue)
      {
        iDeb = szHTML.toString().indexOf(baliseDebTmp, iDeb);
        iFin = szHTML.toString().indexOf(baliseFinTmp, iDeb+baliseDebTmp.length());
        // Cherche la dernière occurance de la balise de fin
        // Même si il y a d'autres balises du même type à l'interieur
        // exemple: <SCRIPT><SCRIPT>blablabla...</SCRIPT></SCRIPT>
        int iTmp = szHTML.toString().indexOf(baliseDebTmp, iDeb+baliseFinTmp.length());
        while( (iTmp>-1) && (iTmp<iFin) )
        {
          iFin = szHTML.toString().indexOf(baliseFinTmp, iFin+baliseFinTmp.length());
          iTmp = szHTML.toString().indexOf(baliseDebTmp, iTmp+baliseDebTmp.length());
        }
        bContinue = ( (iDeb>-1) && (iFin>-1) );
        if ( bContinue )
        {
          iDeb+=baliseDebTmp.length();
          String str = szHTML.substring(iDeb,iFin).toUpperCase();
          String imgPath = FxHtml.getNextText(str, "SRC=", 0);
          if ( imgPath.length() > 0 )
          {
            ret.add( imgPath );
            ret.add(Integer.toString(iFin));
          }
          //szHTML.replace(iDeb, iFin, "");
        }
      }
    }
    return ret;
  }

  /**
   * Supprime toutes les informations superflu de szHTML
   * @param szHTML : Chaine de caractère à traiter
   * @return
   */
  protected String cleanTag(String szHTML)
  {
    String str = "";
    str = removeTag(szHTML);
    str = FxString.replaceString(str, "     ", "");
    str = FxString.replaceString(str, "    ", "");
    str = FxString.replaceString(str, "   ", "");
    str = FxString.replaceString(str, "  ", "");
    str = FxString.replaceString(str, "\r", "");
    str = FxString.replaceString(str, "\n", "");
    str = FxString.replaceString(str, "\t", "");
    str = FxString.replaceString(str, "&gt;", "");
    str = FxString.replaceString(str, "&nbsp;", " ");
    str = FxString.replaceString(str, "&#160;", " ");
    return str.trim();
  }

  /**
   * Supprime les tag du texte szHTML
   * @param szHTML
   * @return
   */
  protected String removeTag(String szHTML)
  {
    String str = FxString.deleteString(szHTML, "<", ">");
    return str.trim();
  }
}