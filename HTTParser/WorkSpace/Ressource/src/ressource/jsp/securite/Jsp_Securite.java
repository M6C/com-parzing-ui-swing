package ressource.jsp.securite;

/**
 * Package qui contient les methode g�rant la s�curit�.
 * Ensemble des m�thodes servant � g�rer la s�curit�
 * @author ressource
 * @version 1.0
 **/


public class Jsp_Securite{


	/**
	 * Constructeur
	 */
	public Jsp_Securite() {
	}


	/**
	 * Confirme ou non si l'utilisateur a le droit de voir ce repertoire
	 * @param nonRepertoire  Nom du repertoire a verifier
	 * @param hashDroit      Hashtable contenant l'ensemble des repertoire et de leur droit
	 * @param userDroit      Code droit de l'utilisateur.
	 * @return true si l'utilisateur a les droits sinon false (par defaut true)
	 */
	public boolean confirmeDroit(String nomRepertoire, java.util.Hashtable hashDroit, String userDroit) {
		boolean droit = true;

		if (hashDroit.containsKey(nomRepertoire)) {
			String niveauSecurite = hashDroit.get(nomRepertoire).toString();
			if (userDroit.compareTo(niveauSecurite) < 0) {
				droit = false;
			}
		}

		return droit;
	}

}