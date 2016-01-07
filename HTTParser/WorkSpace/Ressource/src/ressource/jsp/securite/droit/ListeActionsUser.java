package ressource.jsp.securite.droit;


/**
 * Classe permettant de stocker la liste des actions de notre utilisateur.
 * @author FGA
 * @version 1.0
 *
 * @see java.util.Vector
 */

public class ListeActionsUser extends java.util.Vector implements java.io.Serializable {


	/**
	 * Constructeur par d�faut
	 */
	public ListeActionsUser() {
		super();
	}


	/**
	 * Ajouter une action dans la liste.
	 * @param pAction action � ajouter
	 */
	public void ajouterAction(String pAction) {
		if (pAction != null) {
			this.addElement(pAction);
		}
	}


	/**
	 * Retourne true si la liste contient l'action demand�e.
	 * @param pAction action � chercher
	 * @return true si action trouv�e.
	 */
	public boolean contientAction(String pAction) {
		return this.contains(pAction);
	}


  /**
   * Implementation de la methode toString()
   * @return String contenu de l'objet
   */
  public String toString() {
    StringBuffer str = new StringBuffer();

    for (int i=0; i<this.size();i++) {
      str.append(" ** ");
      str.append(i);
      str.append(". ");
      str.append(this.elementAt(i).toString());
    }

    return str.toString();
  }

}