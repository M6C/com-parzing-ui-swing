package ressource.jsp.multilangue;

/**
 * Titre :        MultiLangue
 * Description :  Gestion du multi langue des libelles des pages de pr�sentation.
 * Copyright :    Copyright (c) 2001
 * Soci�t� :      Groupe ressource
 * @author FGA
 * @version 1.0
 */

import java.util.Hashtable;
import java.util.Enumeration;
import ressource.jsp.libelles.Jsp_Libelles;
import ressource.hashtable.menu.TreeMenu;


public class MultiLangueLibelle {

	/** largeur d'un espace entre bandeau et s�parateur */
	private final int ESPACE_BANDEAU_SEPARATEUR = 10;

	/** Liste des langues associ�s associ�s � un objet HashPages  */
	private HashMultiLangue hashMultiLangue = new HashMultiLangue();

	/** un objet libelle pour afficher le code HTML */
	Jsp_Libelles lib = new Jsp_Libelles();

	/**
	 * S�parateur entre les titres du bandeau.<p>
	 * Est utilis� si la propri�t� imageSeparateur est vide.<p>
	 */
	private String separateurPage = ">";

	/** Image servant de s�parateur entre les bandeau  */
	private String imageSeparateur = "";

	/** Indique si l'on mettre le titre de la page dans le bandeau */
	private boolean titreDansBandeau = false;

	/** Renseigne le style du bandeau */
	private String styleBandeau = "";


	/**
	 * Constructeur par d�faut
	 */
  public MultiLangueLibelle() {
  }


	/**
	 * Ajouter une liste d'items de bloc dans une page pour une langue donn�e.
	 * @param pLangue la langue du bloc
	 * @param pPage la page d'appartenance du bloc
	 * @param pHash Liste des items de blocs � ajouter
	 */
	public void ajouterBloc(String pLangue, String pPage, Hashtable pHash) {
		Enumeration enumKey = pHash.keys();
		Enumeration enumValue = pHash.elements();

		while (enumKey.hasMoreElements()) {
		  this.ajouterBloc(pLangue, pPage, enumKey.nextElement().toString(), enumValue.nextElement().toString());
		}
	}


	/**
	 * Ajouter un nouvel item de bloc dans une page pour une langue donn�e.
	 * @param pLangue la langue du bloc
	 * @param pPage la page d'appartenance du bloc
	 * @param pKey la clef du bloc
	 * @param pValue le libell� du bloc
	 */
	public void ajouterBloc(String pLangue, String pPage, String pKey, String pValue) {
		// La langue existe d�j�
		if (hashMultiLangue.containsKey(pLangue)) {
			HashPages hashPages = (HashPages)this.hashMultiLangue.get(pLangue);

			// La page existe d�j�
			if (hashPages.containsKey(pPage)) {
				HashBloc hashBloc = (HashBloc)hashPages.get(pPage);
				hashBloc.put(pKey, pValue);
			} else {
				HashBloc hashBloc = new HashBloc();
				hashBloc.put(pKey, pValue);

				// J'ajoute ma page avec la liste des blocs.
				hashPages.put(pPage, hashBloc);
			}

		} else {
		  // La langue n'est pas encore dans la liste
			// Pour ajouter mon bloc je dois ajouter un bloc, une page et une key
			// dans ma langue.
		  HashBloc hashBloc = new HashBloc();
			hashBloc.put(pKey, pValue);

		  HashPages hashPages = new HashPages();
			hashPages.put(pPage, hashBloc);

			this.hashMultiLangue.put(pLangue, hashPages);
		}
	}


	/**
	 * Retourne le libell� correspondant � une langue, � une page et � une
	 * clef (un idetifiant unique).
	 * @param pLangue la langue du bloc � ramener
	 * @param pPage la page d'appartenance du bloc
	 * @param pNumbloc la clef du bloc � retourner
	 * @return Le libell� du bloc trouv�.
	 * @return null si le bloc n'existe pas.
	 */
	public String getBloc(String pLangue, String pPage, String pNumbloc) {
		String valuebloc = null;

		// Etape 1 : on recherche si on a d�ja la langue que l'on souhaite
		HashPages hashPages = (HashPages)hashMultiLangue.get(pLangue);

		// Etape 2 : On recherche si on a d�j� la page
		if (hashPages != null) {
			HashBloc hashBloc = (HashBloc)hashPages.get(pPage);

			// Etape 3 : On recherche si on a d�j� un bloc
			if (hashBloc != null) {
				valuebloc = (String)hashBloc.get(pNumbloc);
			}
		}

		return valuebloc;
	}


	/**
	 * Retourne la hashtable bloc correspondant � une langue, � une page.
	 * @param pLangue la langue du bloc � ramener
	 * @param pPage la page d'appartenance du bloc
	 * @return La liste des blocs trouv�s.
	 * @return null si la page n'existe pas.
	 */
	public HashBloc getBloc(String pLangue, String pPage) {
		HashBloc hashBloc = null;

		// Etape 1 : on recherche si on a d�ja la langue que l'on souhaite
		HashPages hashPages = (HashPages)hashMultiLangue.get(pLangue);

		// Etape 2 : On recherche si on a d�j� la page
		if (hashPages != null) {
			hashBloc = (HashBloc)hashPages.get(pPage);
		}

		return hashBloc;
	}


	/**
	 * Retourne le titre correspondant � une langue, � une page
	 * @param pLangue la langue du titre � ramener
	 * @param pPage la page d'appartenance du bloc
	 * @return Le titre du bloc trouv�.
	 * @return null si le bloc n'existe pas.
	 */
	public String getTitre(String pLangue, String pPage) {
		return this.getBloc(pLangue, pPage, HashBloc.BLOC_TITRE);
	}


	/**
	 * Retourne le sous-titre correspondant � une langue, � une page
	 * @param pLangue la langue du titre � ramener
	 * @param pPage la page d'appartenance du bloc
	 * @return Le sous-titre du bloc trouv�.
	 * @return null si le bloc n'existe pas.
	 */
	public String getSousTitre(String pLangue, String pPage) {
		return this.getBloc(pLangue, pPage, HashBloc.BLOC_SOUS_TITRE);
	}


	/**
	 * Retourne le style � utiliser pour l'�criture du bandeau.
	 * @return le style utilis� pour �crire le bandeau.
	 */
	public String getStyleBandeau() {
		return this.styleBandeau;
	}


	/**
	 * Modifie le style devant �tre utilis� pour l'�criture du code HTML du bandeau
	 * @param pParam le style � utiliser.
	 */
	public void setStyleBandeau(String pParam) {
		this.styleBandeau = pParam;
	}


	/**
	 * Retourne le code HTML permettant de construire le bandeau � partir des informations
	 * sur les parents de la page cible.
	 * @param pLangue langue.
	 * @param pPage La page courante.
	 * @return Le code html regroupant la liste du bandeau de la page.
	 */

public String traceEscargotXML(String pLangue, String uri, TreeMenu treemenu) {
    StringBuffer strResult = new StringBuffer();
    String courant = uri;

    String strBandeauCourant = null;
    String strTitreCourant = null;

    ressource.jsp.liens.Jsp_Liens link = new ressource.jsp.liens.Jsp_Liens();

    // Nombre d'�l�ment ajout� dans le bandeau
    int nbParcours = 0;

    while (courant != null)
    {
    // trace escargot
    StringBuffer str = new StringBuffer();

    strBandeauCourant = this.getBloc(pLangue, courant, HashBloc.BLOC_BANDEAU);
    strTitreCourant = this.getTitre(pLangue, courant);
    // Si on a un objet donnes alors nous �crivons les informations
    // dans un buffer (str).
      if (strBandeauCourant != null) {
              // La premi�re fois, je regarde si l'on souhaite avoir dans le bandeau
              // le bndeau de la page courante.
              if (nbParcours == 0) {
                // Si le bandeau de la page courante doit contenir son bandeau / Titre
                      // Pas de liens pour ceux la.
                      if (!this.getMettreTitreDansBandeau()) {
                              // Suivant le style � utiliser
                              if (this.getStyleBandeau().equals("")) {
                                      str.append( lib.jsp_Str(strBandeauCourant) );
                              } else {
                                      str.append( "<font class=\"" );
                                      str.append( this.getStyleBandeau() );
                                      str.append( "\">" );
                                      str.append( strBandeauCourant );
                                      str.append( "</font>" );
                              }
                      } else {
                              str.append( lib.jsp_Titre(strTitreCourant) );
                      }
              } else {

              // Suivant le style � utiliser
              if (this.getStyleBandeau().equals("")) {
                      // Style par d�faut
                      str.append( link.jsp_Link( strBandeauCourant, treemenu.getAction(courant)));
              } else {
                      str.append( link.jsp_Link( strBandeauCourant, treemenu.getAction(courant)));
              }

              // Je mets un s�parateur dans le bandeau.
              // Si ce n'est pas une image
              if (this.getSeparateurImage().equals("")) {
                      // Suivant le style � utiliser
                      if (this.getStyleBandeau().equals("")) {
                      str.append( lib.jsp_Dot(ESPACE_BANDEAU_SEPARATEUR,1) );
                              str.append( lib.jsp_Str(this.getSeparateur()) );
                              str.append( lib.jsp_Dot(ESPACE_BANDEAU_SEPARATEUR,1) );
                      } else {
                      str.append( lib.jsp_Dot(ESPACE_BANDEAU_SEPARATEUR,1) );
                              str.append( "<font class=\"" );
                              str.append( this.getStyleBandeau() );
                              str.append( "\">" );
                              str.append( this.getSeparateur() );
                              str.append( "</font>" );
                              str.append( lib.jsp_Dot(ESPACE_BANDEAU_SEPARATEUR,1) );
                      }
                // Si le s�parateur est une image
                } else {
                        str.append( lib.jsp_Dot(ESPACE_BANDEAU_SEPARATEUR,1) );
                        str.append( this.getSeparateurImage() );
                        str.append( lib.jsp_Dot(ESPACE_BANDEAU_SEPARATEUR,1) );
                }
          }//else parcours=0
      nbParcours++;
      strResult.insert(0, str.toString());

    //noeud suivant

    }//if
  courant = treemenu.getParent(courant);
  } //while
  return strResult.toString();
}


	public String getBandeau(String pLangue, String pPage) {
		StringBuffer strResult = new StringBuffer();

		String strBandeauCourant = null;
		String strTitreCourant = null;

		ressource.jsp.liens.Jsp_Liens link = new ressource.jsp.liens.Jsp_Liens();

		// Nombre d'�l�ment ajout� dans le bandeau
		int nbParcours = 0;

		do {
		  StringBuffer str = new StringBuffer();

			// Permet de prendre le r�pertoire parent.
			int borne2 = pPage.lastIndexOf("/");
			int borne1 = pPage.lastIndexOf("/", borne2-1);
			String rep = pPage.substring(borne1+1, borne2);
		  pPage = pPage.substring(0, borne2);

			// on prend le titre du bandeau correspondant au r�pertoire.
			strBandeauCourant = this.getBloc(pLangue, rep, HashBloc.BLOC_BANDEAU);
			strTitreCourant = this.getTitre(pLangue, rep);

			// Si on a un objet donnes alors nous �crivons les informations
			// dans un buffer (str).
			if (strBandeauCourant != null) {
				// La premi�re fois, je regarde si l'on souhaite avoir dans le bandeau
				// le bndeau de la page courante.
				if (nbParcours == 0) {
				  // Si le bandeau de la page courante doit contenir son bandeau / Titre
					// Pas de liens pour ceux la.
					if (!this.getMettreTitreDansBandeau()) {
						// Suivant le style � utiliser
						if (this.getStyleBandeau().equals("")) {
							str.append( lib.jsp_Str(strBandeauCourant) );
						} else {
							str.append( "<font class=\"" );
							str.append( this.getStyleBandeau() );
							str.append( "\">" );
							str.append( strBandeauCourant );
							str.append( "</font>" );
						}
					} else {
						str.append( lib.jsp_Titre(strTitreCourant) );
					}
				} else {

					// Suivant le style � utiliser
					if (this.getStyleBandeau().equals("")) {
						// Style par d�faut
						str.append( link.jsp_Link( strBandeauCourant, pPage + "/page.jsp") );
					} else {
						str.append( link.jsp_Link( strBandeauCourant, pPage + "/page.jsp", this.getStyleBandeau()) );
					}

					// Je mets un s�parateur dans le bandeau.
					// Si ce n'est pas une image
					if (this.getSeparateurImage().equals("")) {
						// Suivant le style � utiliser
						if (this.getStyleBandeau().equals("")) {
	  					str.append( lib.jsp_Dot(ESPACE_BANDEAU_SEPARATEUR,1) );
							str.append( lib.jsp_Str(this.getSeparateur()) );
							str.append( lib.jsp_Dot(ESPACE_BANDEAU_SEPARATEUR,1) );
						} else {
	  					str.append( lib.jsp_Dot(ESPACE_BANDEAU_SEPARATEUR,1) );
							str.append( "<font class=\"" );
							str.append( this.getStyleBandeau() );
							str.append( "\">" );
							str.append( this.getSeparateur() );
							str.append( "</font>" );
							str.append( lib.jsp_Dot(ESPACE_BANDEAU_SEPARATEUR,1) );
						}
				  // Si le s�parateur est une image
					} else {
						str.append( lib.jsp_Dot(ESPACE_BANDEAU_SEPARATEUR,1) );
						str.append( this.getSeparateurImage() );
						str.append( lib.jsp_Dot(ESPACE_BANDEAU_SEPARATEUR,1) );
					}
				}

				nbParcours++;

				strResult.insert(0, str.toString());
			}
		}
		while (strBandeauCourant != null);

		return strResult.toString();
	}


	/**
	 * Initialise le s�parateur. Ca doit �tre une image. Sinon, on utilise l'autre
	 * s�parateur.
	 * @param pCheminImage Le chemin complet de l'image
	 * @param pHeight la hauteur de l'image
	 * @param pWidth la largeur de l'image
	 */
	public void setSeparateurImage(String pCheminImage, int pHeight, int pWidth) {
		StringBuffer str = new StringBuffer();
		str.append( "<img src=\"" );
		str.append( pCheminImage );
		str.append( "\" height=\"" );
		str.append( pHeight );
		str.append( "\" width=\"" );
		str.append( pWidth );
		str.append( "\" border=\"0\">" );

		this.imageSeparateur = str.toString();
	}


	/**
	 * Retourne le code HTML permettant de consulter l'image
	 * @return le code HTML de l'image
	 */
	public String getSeparateurImage() {
		return this.imageSeparateur;
	}


	/**
	 * Initialise et indique s'il faut mettre le titre de la page dans la bandeau.
	 * @param param vrai si le titre de la page doit �tre dans bandeau.
	 */
	public void setMettreTitreDansBandeau(boolean pParam) {
		this.titreDansBandeau = pParam;
	}


	/**
	 * Retourne la propri�t� indiquant s'il faut mettre le titre de la page dans la bandeau.
	 * @return vrai si le titre de la page doit �tre dans bandeau.
	 */
	public boolean getMettreTitreDansBandeau() {
		return this.titreDansBandeau;
	}


	/**
	 * Retourne le nom du s�parateur
	 * @return le s�parateur
	 */
	public String getSeparateur() {
		return this.separateurPage;
	}

	/**
	 * Initialise le s�parateur
	 * @param pParam le s�parateur
	 */
	public void setSeparateur(String pParam) {
		this.separateurPage = pParam;
	}

}
