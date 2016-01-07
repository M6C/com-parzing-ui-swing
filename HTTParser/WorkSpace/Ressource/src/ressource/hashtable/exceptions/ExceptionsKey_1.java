package ressource.hashtable.exceptions;

/**
 * Definit les propriete d'une page (Niveu securite,Visible ou non,ordre Affichage,etc)
 * @author ressource
 * @version 1.0
 *
 * @see Exception
 */

public class ExceptionsKey extends ressource.hashtable.exceptions.Exceptions  {

	/** message */
	private String message = "";


	/**
	 * Constructeur
	 */
	public ExceptionsKey() {
		this.message = " Erreur dans le package ressource.hashtable.Menu \n La clef utilis�e est nulle";
	}


	/**
	 * Libell� du message erreur.
	 * @return la chaine de caract�res contenant le message erreur.
	 */
	public String toString() {
		return this.message;
	}

}