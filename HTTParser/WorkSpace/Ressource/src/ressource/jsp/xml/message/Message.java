package ressource.jsp.xml.message;

/**
  * Permet la gestion des messages d'erreurs de l'application
  * @version 1.0
  * @author ressource
  */

import java.util.Vector;
import java.io.File;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Message {

	/** langue des messages */
	private String language = "";

	/** Contient un ensemble de vecteur (id, libell�) */
	public Vector listeMessage = new Vector();

	/** Nombre de message enregistr� */
	private int nbMessage = 0;

	/** Message par d�faut si le num�ro de message n'existe pas */
	protected final static String MESSAGE_NON_TROUVE = "message non trouve.";

	/** le document cr�� apr�s chargement du fichier XML */
	private Document document = null;

	private final String CST_ITEM_MESSAGE = "message";
	private final String CST_ITEM_ID = "id";

	/**
	 * Constructeur par d�faut
	 */
	public Message() {
	}


	/**
	 * Initialiser la liste des messages � partir du fichier pass� en param�tre
	 * @param nomFichier  Le nom du fichier contenant la liste des messages
	 * @throws FileNotFoundException  Le fichier n'a pas �t� trouv�
	 * @throws IOException  Probl�me d'�criture/lecture avec le fichier
	 * @return Le nombre de message enregistr�
	 */
	public int initialiser(String nomFichier, String langue) throws FileNotFoundException, IOException, Exception {

		this.language = langue;
		this.loadXMLDocument(nomFichier);

		// Document menu = don.loadXMLDocument();
		NodeList app = this.document.getDocumentElement().getElementsByTagName( CST_ITEM_MESSAGE );

		for (int i=0;i<app.getLength();i++) {

		  // Je prend les attributs : le r�pertoire
			NamedNodeMap nd = app.item(i).getAttributes();
			String id = "";
		  for (int k=0;k<nd.getLength();k++) {
				if (nd.item(k).getNodeName().equals(this.CST_ITEM_ID)) {
					id = nd.item(k).getNodeValue();
				}
		  }

		  // On r�cup�re le libell� correspondant � langue utilis�e � l'initialisation.
			if (app.item(i).hasChildNodes()) {
				Node nodeLibelle = this.findNote(app.item(i), this.language);
				this.ajouterMessage(id, this.valueNode(nodeLibelle));
			}
		}

		return this.nbMessage;
	}


	/**
	 * Retourner un message d'un num�ro donn� et en
	 * passant des param�tres au message
	 * @param id  Le num�ro du message d�sir�
	 * @param vec  La liste des param�tres � mettre dans le message
	 * @return Le message trouv� et incluant la liste des param�tres
	 */
	public String getMessage(String id, java.util.Vector vec) {
		return construireMessage(id,vec);
	}


	/**
	 * Retourner un message d'un num�ro donn�
	 * @param id  le num�ro du message � retourner
	 * @return le message trouv�
	 */
	public String getMessage(String id) {
		return construireMessage(id, new java.util.Vector());
	}


	/**
	 * Cr�er le message � partir d'un num�ro et de la liste des param�tres
	 * @param id  Le num�ro du message d�sir�
	 * @param vec  La liste des param�tres � mettre dans le message
	 * @return le message trouv� et incluant la liste des param�tres
	 */
	private String construireMessage(String id, java.util.Vector vec) {
		int loop = 0;
		StringBuffer message = new StringBuffer();

		while ((loop < listeMessage.size())&&(!((Vector)listeMessage.elementAt(loop)).elementAt(0).toString().equals(id))) {
			loop++;
		}

		if (loop < listeMessage.size()) {
			message.append( ((Vector)listeMessage.elementAt(loop)).elementAt(1).toString() );
			for (int k=1;k<=vec.size();k++) {
				int i = message.toString().indexOf("%" + k );
				if (i>-1) {
					message.replace(i, i + String.valueOf(k).length()+1 , vec.elementAt(k-1).toString());
				}
			}
		} else {
			message.append( this.MESSAGE_NON_TROUVE );
		}

		return message.toString();
	}


	/**
	 * Ajouter un message � la liste des messages
	 * @Param	strId  Le num�ro du message
	 * @Param libelle  Le libell� du message
	 */
	private void ajouterMessage(String strId, String libelle) {
		Vector v = new Vector();
		v.addElement(strId);
		v.addElement(libelle);
		listeMessage.addElement(v);

		this.nbMessage++;
	}


	/**
	 * R�initialiser la liste des messages
	 */
	private void viderMessage() {
		for (int k=0;k<this.listeMessage.size();k++) {
			this.listeMessage.removeAllElements();
		}

		this.nbMessage = 0;
	}


	/**
	 * Teste l'�tat d'un noeud. Si le noeud est null alors retourne cha�ne vide.<p>
	 * Sa value sinon.<p>
	 * @return la valeur de la value
	 */
	private String valueNode(Node node) {

		if (node == null) {
			return MESSAGE_NON_TROUVE;
		}

		if (node.getFirstChild() == null) {
			return MESSAGE_NON_TROUVE;
		}

		return node.getFirstChild().getNodeValue();
	}


	/**
	 * Recherche un noeud avec un nom donn�.
	 * @param node Le noeud dans lequel il faut chercher.
	 * @param name Le nom du noeud � chercher
	 * @return le noeud trouv�
	 */
	private Node findNote(Node node, String name) {
	  if (node.getNodeName().equals(name)) {
			return node;
	  }

		if (node.hasChildNodes()) {
			NodeList childs = node.getChildNodes();
			int length = childs.getLength();
			for (int i=0; i< length;i++) {
				Node found = this.findNote(childs.item(i), name);
				if (found != null) {
					return found;
				}
			}
		}

		return null;
	}


	/**
	 * Chargement du fichier XML
	 * @param fileName nom du fichier XML � charger.
	 * @throws Exception Liste des erreurs possibles (fichier introuvable...)
	 */
	private void loadXMLDocument(String fileName) throws Exception {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

		this.document = docBuilder.parse(new File(fileName));
		this.document.getDocumentElement().normalize();
	}


	/**
	 * Retourne la langue de l'instance cr��e
	 * @return String la langue
	 */
	public String getlanguage() {
		return this.language;
	}

}