package ressource.jsp.xml.menu;

/**
 * Description : Classe permettant de lire le fichier XML du menu et
 * de retourner les informations.
 *
 * Copyright : Copyright (c) 2001
 * Soci�t� : ressource
 * @author FGA
 * @version 1.0
 */

import java.io.File;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Vector;
import ressource.hashtable.menu.Menu;
import ressource.jsp.libelles.Jsp_Libelles;
import ressource.jsp.liens.Jsp_Liens;



public class DonneesMenuXML {

	/** r�pertoire des images du menu */
	public final static String REPERTOIRE_IMAGE_MENU = "/images/menu/";

	/**
	 * Liste des clefs lues dans le fichier XML.<p>
	 * On boucle sur "page".
	 */
	private final String CST_ITEM_MENU = "menu";
	private final String CST_ITEM_REPERTOIRE = "repertoire";
	private final String CST_ITEM_ORDRE_MENU = "ordre";
	private final String CST_ITEM_NIVEAU_MENU = "niveau";
	private final String CST_ITEM_CIBLE = "cible";
	private final String CST_ITEM_IMAGE_MENU = "image";
	private final String CST_ITEM_LIBELLE_MENU = "libelle";
	private final String CST_ITEM_LIBELLE_MENU_DEFAULT = "default";

	/** le document cr�� apr�s chargement du fichier XML */
	private Document document = null;

	/** Stockage des donn�es portant sur le menu */
	private Menu menu = null;

	/** un objet libelle pour afficher le code HTML */
	Jsp_Libelles lib = new Jsp_Libelles();

	/** le langage du menu � lire */
	private String strLanguage = "";

	/** r�pertoire des images */
	String repertoireImage = REPERTOIRE_IMAGE_MENU;


	/**
	 * Constructeur par d�faut.
	 */
	public DonneesMenuXML() {
	}


	/**
	 * Chargement du fichier XML
	 * @param pFileName nom du fichier XML � charger.
	 * @throws Exception Liste des erreurs possibles (fichier introuvable...)
	 */
	private void loadXMLDocument(String pFileName) throws Exception {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

		this.document = docBuilder.parse(new File(pFileName));
		this.document.getDocumentElement().normalize();
	}


	/**
	 * Initialise l'instance cr�� � partir fu fichier XML donn�.
	 * Cette initialisiation n'est faite qu'une et une seule fois.
	 * @param pFileMenu nom du fichier XML � traiter.
	 * @param planguage la langue � traiter.
	 * @throws Exception Liste des erreurs possibles (fichier introuvable...)
	 * @return Le nombre d'�l�ments lus.
	 */
	public void setInitialiser(String pFileMenu, String pLanguage) throws Exception {

		// On initialise la langue
		this.strLanguage = pLanguage;

  	// On charge une page
		this.loadXMLDocument(pFileMenu);
		this.chargerDonneesMenu();
	}


	/**
	 * Recherche un noeud avec un nom donn�.
	 * @param pNode Le noeud dans lequel il faut chercher.
	 * @param pName Le nom du noeud � chercher
	 * @return le noeud trouv�
	 */
	private Node findNote(Node pNode, String pName) {
	  if (pNode.getNodeName().equals(pName)) {
			return pNode;
	  }

		if (pNode.hasChildNodes()) {
			NodeList childs = pNode.getChildNodes();
			int length = childs.getLength();
			for (int i=0; i< length;i++) {
				Node found = this.findNote(childs.item(i), pName);
				if (found != null) {
					return found;
				}
			}
		}

		return null;
	}



	/**
	 * Charge les donn�es � partir du fichier menu XML.<p>
	 * construit la liste des menus
	 * @throws Exception Liste des erreurs possibles
	 */
	private void chargerDonneesMenu() throws Exception {

      //DEBUG
      System.out.println("chargerDonneesMenu debut");

		// Document menu = don.loadXMLDocument();
		NodeList app = this.document.getDocumentElement().getElementsByTagName( CST_ITEM_MENU );
		int nbPage = app.getLength();

		this.menu = new Menu();

		for (int i=0;i<app.getLength();i++) {

      //DEBUG
      System.out.println("chargerDonneesMenu app.item(i):" + ((app.item(i)==null) ? "null" : app.item(i).toString()));

		  // Je prend les attributs : le r�pertoire
			NamedNodeMap nd = app.item(i).getAttributes();
			String strRepertoire = "";
			String strNiveau = "";
			String strOrdre = "";
		  for (int k=0;k<nd.getLength();k++) {
				if (nd.item(k).getNodeName().equals(this.CST_ITEM_REPERTOIRE)) {
					strRepertoire = nd.item(k).getNodeValue();
				}
				if (nd.item(k).getNodeName().equals(this.CST_ITEM_NIVEAU_MENU)) {
					strNiveau = nd.item(k).getNodeValue();
				}
				if (nd.item(k).getNodeName().equals(this.CST_ITEM_ORDRE_MENU)) {
					strOrdre = nd.item(k).getNodeValue();
				}
		  }

			Node nCible = this.findNote(app.item(i), this.CST_ITEM_CIBLE);

			Node nImg = this.findNote(app.item(i), this.CST_ITEM_IMAGE_MENU);

			Node nLibelle = this.findNote(app.item(i), this.CST_ITEM_LIBELLE_MENU);

      //DEBUG
      System.out.println("chargerDonneesMenu strRepertoire:"+((strRepertoire==null) ? "null" : strRepertoire));
      System.out.println("chargerDonneesMenu strNiveau:"+((strNiveau==null) ? "null" : strNiveau));
      System.out.println("chargerDonneesMenu strOrdre:"+((strOrdre==null) ? "null" : strOrdre));
      System.out.println("chargerDonneesMenu nCible:"+((nCible==null) ? "null" : nCible.toString()));
      System.out.println("chargerDonneesMenu nImg:"+((nImg==null) ? "null" : nImg.toString()));
      System.out.println("chargerDonneesMenu nLibelle:"+((nLibelle==null) ? "null" : nLibelle.toString()));

			// Je r�cup�re la liste des attributs du libell� et je prend la valeur
			// de l'attribut default. Elle est mise pour les libell�s nulles
			NamedNodeMap nnp = nLibelle.getAttributes();
		  String strLibelleDefault = "";
		  for (int c=0;c<nnp.getLength();c++) {
				if (nnp.item(c).getNodeName().equals(this.CST_ITEM_LIBELLE_MENU_DEFAULT)) {
					strLibelleDefault = nnp.item(c).getNodeValue();
				}
		  }

			Node nLibelleLangage = this.findNote(nLibelle, this.getLanguage());

			menu.addElement( strRepertoire );

			// FGA*14/08/2001 : Ajout d'un traitement du chemin de l'image en fct de la langue
			String imgLanguage = valueNode( nImg );
			if (!imgLanguage.equals("")) {
				imgLanguage = this.repertoireImage + this.strLanguage + "/" + imgLanguage;
			}
			menu.setCheminImage( strRepertoire, imgLanguage);

		  // Si le libell� de l'image associ� � la langue est vide alors je
			// met le libell� par d�faut.
			String strLibLangue = valueNode( nLibelleLangage );
			if (strLibLangue.equals("")) {
				strLibLangue = strLibelleDefault;
			}
			menu.setLibelle( strRepertoire, strLibLangue );

		  // Intuialisation des autres propri�t�s du menu
			// Idem pour toutes les langues.
			menu.setNiveau( strRepertoire, strNiveau );
			menu.setOrdreAffichage( strRepertoire, strOrdre );
			menu.setCible( strRepertoire, valueNode( nCible ) );
      //DEBUG
      System.out.println("chargerDonneesMenu menu:"+((menu==null) ? "null" : menu.toString()));

		}
      //DEBUG
      System.out.println("chargerDonneesMenu fin");
	}


	/**
	 * Retourne le menu
	 * @return les donn�es du menu
	 */
	public Menu getMenu() {
		return this.menu;
	}



	/**
	 * Teste l'�tat d'un noeud. Si le noeud est null alors retourne cha�ne vide.<p>
	 * Sa value sinon.<p>
	 * @param pNode le noeud.
	 * @return la valeur de la value
	 */
	private String valueNode(Node pNode) {

		if (pNode == null) {
			return "";
		}

		if (pNode.getFirstChild() == null) {
			return "";
		}

		return pNode.getFirstChild().getNodeValue();
	}



	/**
	 * Retourne la langue utilis�e lors de la derni�re initialisation
	 * @return la langue utilis�e au chargement du fichier XML du menu
	 */
	public String getLanguage() {
		return this.strLanguage;
	}


	/**
	 * Modifier le r�pertoire des images. Mettre un / � la fin du chemin.
	 * @param pRepertoire le nouveau r�pertoire des images.
	 */

	public void setRepertoireImage(String pRepertoire) {
		if (pRepertoire != null) {
			this.repertoireImage = pRepertoire;
		}
	}


	/**
	 * @return le r�pertoire par d�aut des images
	 */
	public String getRepertoireImage() {
		return this.repertoireImage;
	}

}