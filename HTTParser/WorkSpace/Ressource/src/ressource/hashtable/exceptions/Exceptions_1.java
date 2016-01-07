package ressource.hashtable.exceptions;

/**
 * Definit les propriete d'une page (Niveu securite,Visible ou non,ordre Affichage,etc)
 * @author ressource
 * @version 1.0
 */

public class Exceptions extends Exception {

	/** message */
	private String message = "";

	/**
	 * Constrcuteur
	 */
  public Exceptions() {
  }


	/**
	 * Libell� du message erreur.
	 * @return la chaine de caract�res contenant le message erreur.
	 */
	public String toString() {
		return this.message;
	}

}